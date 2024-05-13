package com.pop.codelab.chatopbackend.business.rental;

import com.pop.codelab.chatopbackend.business.rental.dto.requests.RentalCreationDto;
import com.pop.codelab.chatopbackend.business.rental.dto.requests.RentalUpdateDto;
import com.pop.codelab.chatopbackend.business.rental.dto.responses.AllRentalsDto;
import com.pop.codelab.chatopbackend.business.rental.dto.responses.MessageDto;
import com.pop.codelab.chatopbackend.business.rental.dto.responses.OneRentalDto;
import com.pop.codelab.chatopbackend.business.rental.entity.Rental;
import com.pop.codelab.chatopbackend.business.user.entity.User;
import com.pop.codelab.chatopbackend.business.user.service.UserServiceImpl;
import com.pop.codelab.chatopbackend.exception.ResourceNotFoundException;
import com.pop.codelab.chatopbackend.service.ImageService;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The RentalService class provides CRUD operations for managing RentalCreationDto objects.
 * It implements the CrudService interface, which defines common CRUD operations.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class RentalService {


    /**
     * The uploadDirectory variable represents the directory where uploaded files are stored.
     * <p>
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
     * It is typically injected into the RentalService class, allowing the class to access and manipulate Rental entities
     * stored in the database through the methods provided by the RentalRepository interface.</p>
     *
     * @see RentalRepository
     * @see JpaRepository
     * @see RentalService
     */
    private final RentalRepository rentalRepository;

    //TODO create interface

    /**
     * The imageService variable represents an instance of the ImageService class.
     * <p>
     * The ImageService class provides functionality for saving, retrieving, and deleting image files.
     * It is used to handle image-related operations in the application.</p>
     * The ImageService class has the following methods:</p>
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
     * @see RentalService
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
     * @throws ResourceNotFoundException if no rentals are found
     */
    public AllRentalsDto getAllRentals() {
        log.info("Collecting all rentals...");
        List<Rental> rentals = rentalRepository.findAll();
        if (rentals.isEmpty()) {
            throw new ResourceNotFoundException("No rental found");
        }
        List<OneRentalDto> rentalsList = new ArrayList<>();
        for (Rental rental : rentals) {
            OneRentalDto oneRentalResponseDto = modelMapper.map(rental, OneRentalDto.class);
            if (rental.getPicture() != null && !rental.getPicture().isEmpty()) {
                String fileName = String.valueOf(Path.of(uploadDirectory).resolve(rental.getPicture()));
                if (Files.exists(Path.of(fileName))) {
                    log.debug("Retrieving the image : {} ", fileName);
                    String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/images/")
                            .path(rental.getPicture())
                            .toUriString();
                    oneRentalResponseDto.setPicture(imageUrl);
                    log.debug("Image will be served at  = {}", imageUrl);
                } else {
                    log.warn("The image {} has not been found !", fileName);
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
        if (optionalRental.get().getPicture() != null) {
            log.debug("The rental : {} has a picture, need to extract it...", optionalRental.get().getName());
            String fileName = String.valueOf(Path.of(uploadDirectory).resolve(optionalRental.get().getPicture()));

            log.debug("Image file name : {} ", fileName);
        }
        log.debug("Rental Dto retrieved : {} ", rentalDto);
        return rentalDto;
    }

    /**
     * Sets the multipart file for the given RentalCreationDto object using the specified picture file path.
     *
     * @param rentalDto        the RentalCreationDto object to set the multipart file for
     * @param pictureFilePath  the file path of the picture to set as the multipart file
     * @return the RentalCreationDto object with the multipart file set
     */
//    private RentalCreationDto setMultipart(OneRentalDto rentalDto, String pictureFilePath){
//        try {
//            String fileName = String.valueOf(Path.of(uploadDirectory).resolve(pictureFilePath));
//            if (Files.exists(Path.of(fileName))) {
//                log.info("Retrieving the image : {} ", fileName);
//                MockMultipartFile file = new MockMultipartFile(pictureFilePath, new FileInputStream(fileName));
//                rentalDto.setPicture(file);
//            } else {
//                log.warn("The image {} has not been found !", fileName);
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException("Cannot retrieve the rental Picture :" + pictureFilePath);
//        }
//        return rentalDto;
//    }


    /**
     * Saves the given RentalCreationDto object to the repository.
     *
     * @param rentalDtoToSave the DTO object to be saved
     * @return the saved RentalCreationDto object
     */
    public MessageDto create(final RentalCreationDto rentalDtoToSave, Authentication authentication) {

        RentalCreationDto rentalDto = modelMapper.map(rentalDtoToSave, RentalCreationDto.class);

        Rental rental = modelMapper.map(rentalDto, Rental.class);

        rental.setOwner_id(this.getauthenticatedUserId(authentication));
        var imageFile = rentalDtoToSave.getPicture();
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
     * Deletes a rental object by its ID.
     *
     * @param id the ID of the rental object to be deleted
     */
    public void delete(final Long id) {
        rentalRepository.deleteById(id);
    }

    /**
     * Updates a RentalCreationDto object with the specified ID.
     *
     * @param id        the ID of the object to be updated
     * @param rentalDto the updated object
     * @return a RentalCreationDto object representing the updated object
     */
    public MessageDto update(
            final Long id,
            final @ModelAttribute RentalUpdateDto rentalDto, Authentication authentication) {
        Rental savedRental = rentalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No rental found with Id : " + id));
        // Must be sure that the connected user owns the rental
        doesLoggedUserOwnThisRental(savedRental.getOwner_id(), authentication);
        if (savedRental.getOwner_id() != getauthenticatedUserId(authentication)) {
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

    private long getauthenticatedUserId(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        log.debug("Authenticated user Id is {} - name : {}", user.getId(), user.getName());
        return user.getId();
    }

    private void doesLoggedUserOwnThisRental(long ownerId, Authentication authentication) throws HttpClientErrorException {
        long userId = this.getauthenticatedUserId(authentication);
        if (userId != ownerId) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }
}
