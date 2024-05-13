package com.pop.codelab.chatopbackend.business.rental.service;

import com.pop.codelab.chatopbackend.business.rental.RentalRepository;
import com.pop.codelab.chatopbackend.business.rental.dto.requests.RentalCreationDto;
import com.pop.codelab.chatopbackend.business.rental.dto.requests.RentalUpdateDto;
import com.pop.codelab.chatopbackend.business.rental.dto.responses.AllRentalsDto;
import com.pop.codelab.chatopbackend.business.rental.dto.responses.OneRentalDto;
import com.pop.codelab.chatopbackend.business.rental.entity.Rental;
import com.pop.codelab.chatopbackend.business.user.entity.User;
import com.pop.codelab.chatopbackend.business.user.service.UserServiceImpl;
import com.pop.codelab.chatopbackend.common.responses.MessageDto;
import com.pop.codelab.chatopbackend.exception.ResourceNotFoundException;
import com.pop.codelab.chatopbackend.services.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The RentalServiceImpl class provides CRUD operations for managing RentalCreationDto objects.
 * It implements the CrudService interface, which defines common CRUD operations.
 *
 * @author Pignon Pierre-Olivier
 * @version 2.0
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class RentalServiceImpl implements RentalService {


    /**
     * The uploadDirectory variable represents the directory where uploaded files are stored.
     * It is a String type and is set using the value from the application property 'application.local-storage.upload-directory' using the @Value annotation.
     */
    @Value("${application.local-storage.upload-directory}")
    private String uploadDirectory;

    /**
     * The rentalRepository variable is an instance of the RentalRepository interface.
     * It is used to interact with the database and perform CRUD (Create, Read, Update, Delete) operations on Rental entities.
     * <p>
     * The RentalRepository interface extends the JpaRepository interface, which provides basic CRUD operations for managing Rental entities in the database.
     * This interface inherits all the methods from the JpaRepository interface, such as save(), findById(), findAll(), delete(), etc.</p>
     * <p>
     * The rentalRepository variable is declared as private and final, meaning it cannot be reassigned to a different object once initialized.
     * It is typically injected into the RentalServiceImpl class, allowing the class to access and manipulate Rental entities
     * stored in the database through the methods provided by the RentalRepository interface.</p>
     *
     * @see RentalRepository
     * @see JpaRepository
     * @see RentalServiceImpl
     */
    private final RentalRepository rentalRepository;

    /**
     * The imageService variable represents an instance of the ImageService class.
     * The ImageService class provides functionality for saving, retrieving, and deleting image files.
     * It is used to handle image-related operations in the application.
     * The ImageService class has the following methods:
     * <ul>
     *     <li>saveImageToStorage(String uploadDirectory, MultipartFile imageFile): Saves the given image file to the specified upload directory.</li>
     *     <li>getImage(String imageDirectory, String imageName): Retrieves the image with the specified imageDirectory and imageName.</li>
     *     <li>deleteImage(String imageDirectory, String imageName): Deletes the image with the specified imageDirectory and imageName.</li>
     * </ul>
     *
     * @see ImageService
     */
    private final ImageService imageService;

    /**
     * The userService variable represents an instance of the UserServiceImpl class.
     */
    private final UserServiceImpl userService;

    /**
     * The ModelMapper class is used for mapping objects from one type to another.
     * It is a powerful and flexible Java bean mapping library that simplifies the mapping process between two objects.
     * Here, used to map Dto and Entity.
     * <p>
     * The ModelMapper class is autowired to enable dependency injection, making it easy to use within a Spring application.</p>
     *
     * @see RentalServiceImpl
     * @see RentalCreationDto
     * @see CrudService
     * @see RentalRepository
     * @see ImageService
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Retrieves all rentals from the repository.
     *
     * @return a list of RentalCreationDto objects representing all rentals
     */
    public AllRentalsDto getAllRentals() {
        log.info("Collecting all rentals...");
        List<Rental> rentals = rentalRepository.findAll();
        List<OneRentalDto> rentalsList = new ArrayList<>();
        for (Rental rental : rentals) {
            OneRentalDto oneRentalResponseDto = modelMapper.map(rental, OneRentalDto.class);
            if (rental.getPicture() != null && !rental.getPicture().isEmpty()) {
                String imageToServeUrl = this.imageService.getImageToServeUrl(rental.getPicture());
                if (!imageToServeUrl.isEmpty()) {
                    oneRentalResponseDto.setPicture(imageToServeUrl);
                }
            }
            rentalsList.add(oneRentalResponseDto);
        }
        log.debug("Rental(s) count : {}", rentals.size());
        AllRentalsDto response = AllRentalsDto
                .builder()
                .rentals(rentalsList)
                .build();
        log.debug("Response {}", response.getRentals());
        return response;
    }


    /**
     * Retrieves a RentalCreationDto object by its ID.
     *
     * @param id the ID of the rental to be retrieved
     * @return an Optional object containing a RentalCreationDto if found, or an empty Optional if not found
     * @throws ResourceNotFoundException if the rental with the given ID is not found
     */
    public OneRentalDto getRentalById(final Long id) {
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if (optionalRental.isEmpty()) {
            log.warn("Rental id : {} not found ! ", id);
            throw new ResourceNotFoundException(String.format("Rental id : %s not found ! ", id));
        }
        OneRentalDto rentalDto = modelMapper.map(optionalRental, OneRentalDto.class);
        if (optionalRental.get().getPicture() != null && !optionalRental.get().getPicture().isEmpty()) {
            String imageToServeUrl = this.imageService.getImageToServeUrl(optionalRental.get().getPicture());
            if (!imageToServeUrl.isEmpty()) {
                rentalDto.setPicture(imageToServeUrl);
            }
        }
        log.debug("Rental Dto retrieved : {} ", rentalDto);
        return rentalDto;
    }


    /**
     * Creates a rental using the provided rental data.
     *
     * @param rentalDtoToSave   The RentalCreationDto object containing the rental data.
     * @param authentication   The authentication object representing the user's credentials.
     * @return A MessageDto object indicating that the rental has been created.
     */
    public MessageDto createRental(final RentalCreationDto rentalDtoToSave, final Authentication authentication) {

        RentalCreationDto rentalDto = modelMapper.map(rentalDtoToSave, RentalCreationDto.class);

        Rental rental = modelMapper.map(rentalDto, Rental.class);

        rental.setOwnerId(this.getauthenticatedUserId(authentication));
        MultipartFile imageFile = rentalDtoToSave.getPicture();
        if (imageFile != null) {
            try {
                String uploadedFileName = imageService.saveImageToStorage(uploadDirectory, imageFile);
                log.debug("Rental {} - Picture : {} has been uploaded : {}",
                        rentalDtoToSave.getName(),
                        rentalDtoToSave.getPicture().getOriginalFilename(),
                        uploadedFileName);
                rental.setPicture(uploadedFileName);
            } catch (IOException e) {
                log.error("An error has occurred when saving file : {}", imageFile.getOriginalFilename());
                throw new RuntimeException(e);
            }
        }
        this.rentalRepository.save(rental);
        log.debug("Rental : {} has been created.", rentalDtoToSave);
        return MessageDto.builder()
                .message("Rental created")
                .build();
    }


    /**
     * Updates an existing rental entity with the provided rental data.
     *
     * @param id             the identifier of the rental entity to update
     * @param rentalDto      the RentalUpdateDto object containing the rental data
     * @param authentication the authentication object representing the user's credentials
     *
     * @return a MessageDto object indicating that the rental has been updated
     *
     * @throws ResourceNotFoundException   if no rental with the given ID is found
     * @throws HttpClientErrorException    if the authenticated user is not authorized to update the rental
     */
    public MessageDto updateRental(
            final Long id,
            final @ModelAttribute RentalUpdateDto rentalDto, final Authentication authentication) {
        Rental savedRental = rentalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No rental found with Id : " + id));
        // Must be sure that the connected user owns the rental
        doesLoggedUserOwnThisRental(savedRental.getOwnerId(), authentication);
        if (savedRental.getOwnerId() != getauthenticatedUserId(authentication)) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
        savedRental.setName(rentalDto.getName());
        savedRental.setSurface(rentalDto.getSurface());
        savedRental.setPrice(rentalDto.getPrice());
        savedRental.setDescription(rentalDto.getDescription());
        Rental updatedRental = rentalRepository.save(savedRental);
        log.debug("Rental {} has been updated ! ", updatedRental.getName());
        return MessageDto
                .builder()
                .message("Rental updated")
                .build();
    }

    /**
     * Retrieves the authenticated user ID from the given Authentication object.
     *
     * @param authentication the Authentication object representing the authenticated user
     * @return the ID of the authenticated user
     */
    private long getauthenticatedUserId(final Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        log.debug("Authenticated user Id is {} - name : {}", user.getId(), user.getName());
        return user.getId();
    }

    /**
     * Checks if the logged in user owns the rental.
     *
     * @param ownerId        the ID of the owner of the rental
     * @param authentication the authentication object representing the logged in user
     * @throws HttpClientErrorException if the logged in user does not own the rental
     */
    private void doesLoggedUserOwnThisRental(final long ownerId, final Authentication authentication) throws HttpClientErrorException {
        long userId = this.getauthenticatedUserId(authentication);
        if (userId != ownerId) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }
}
