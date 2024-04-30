package com.pop.codelab.chatopbackend.business.rental;

import com.pop.codelab.chatopbackend.business.rental.dto.RentalDto;
import com.pop.codelab.chatopbackend.exception.ResourceNotFoundException;
import com.pop.codelab.chatopbackend.service.CrudService;
import com.pop.codelab.chatopbackend.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The RentalService class provides CRUD operations for managing RentalDto objects.
 * It implements the CrudService interface, which defines common CRUD operations.
 * <p></p>
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class RentalService implements CrudService<RentalDto> {

    /**
     * Logger for the RentalController class.
     * It is used to log messages and events related to the RentalController class.
     * The logger is initialized using the LoggerFactory.getLogger method and the class name RentalController.class.
     * The logger should be private, static, and final to ensure thread-safety and avoid any unintended modifications.
     */
    private static final Logger logger = LoggerFactory.getLogger(RentalService.class);

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
     *
     * <p>The RentalRepository interface extends the JpaRepository interface, which provides basic CRUD operations for managing Rental entities in the database.
     * This interface inherits all the methods from the JpaRepository interface, such as save(), findById(), findAll(), delete(), etc.</p>
     *
     * <p>The rentalRepository variable is declared as private and final, meaning it cannot be reassigned to a different object once initialized.
     * It is typically injected into the RentalService class, allowing the class to access and manipulate Rental entities
     * stored in the database through the methods provided by the RentalRepository interface.</p>
     *
     * @see RentalRepository
     * @see JpaRepository
     * @see RentalService
     */
    private final RentalRepository rentalRepository;

    /**
     * The imageService variable represents an instance of the ImageService class.
     *
     * <p>The ImageService class provides functionality for saving, retrieving, and deleting image files.
     * It is used to handle image-related operations in the application.</p>
     * <p></p>
     * <p>The ImageService class has the following methods:</p>
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
     * The ModelMapper class is used for mapping objects from one type to another.
     * It is a powerful and flexible Java bean mapping library that simplifies the mapping process between two objects.
     * Here, used to map Dto and Entity.
     *
     * <p>The ModelMapper class is autowired to enable dependency injection, making it easy to use within a Spring application.</p>
     *
     * @see RentalService
     * @see RentalDto
     * @see CrudService
     * @see RentalRepository
     * @see ImageService
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Retrieves all rentals from the repository.
     * <p></p>
     *
     * @return a list of RentalDto objects representing all rentals
     * @throws ResourceNotFoundException if no rentals are found
     */
    @Override
    public List<RentalDto> findAll() {
        logger.info("Gathering all rentals...");
        List<Rental> rentals = rentalRepository.findAll();
        if (rentals.isEmpty()) {
            throw new ResourceNotFoundException("No rental found");
        }
        logger.debug("Rental(s) count : {}", rentals.size());
        return rentals.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * Retrieves a RentalDto object by its ID.
     * <p></p>
     *
     * @param id the ID of the rental to be retrieved
     * @return an Optional object containing a RentalDto if found, or an empty Optional if not found
     * @throws ResourceNotFoundException if the rental with the given ID is not found
     */
    @Override
    public Optional<RentalDto> findById(final Long id) {
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if (optionalRental.isEmpty()) {
            logger.warn("Rental id : {} not found ! ", id);
            throw new ResourceNotFoundException(String.format("Rental id : %s not found ! ", id));
        }
        RentalDto rentalDto = modelMapper.map(optionalRental, RentalDto.class);
        if (optionalRental.get().getPicture() != null) {
            logger.debug("The rental : {} has a picture, need to extract it...", optionalRental.get().getName());
            try {
                String fileName = String.valueOf(Path.of(uploadDirectory).resolve(optionalRental.get().getPicture()));
                MockMultipartFile file = new MockMultipartFile(optionalRental.get().getPicture(), new FileInputStream(fileName));
                rentalDto.setPicture(file);

            } catch (IOException e) {
                throw new RuntimeException("Cannot retrieve the rental Picture :" + optionalRental.get().getPicture());
            }
        }
        logger.debug("Rental Dto retrieved : {} ", rentalDto);
        return Optional.of(rentalDto);
    }

    /**
     * Saves the given RentalDto object to the repository.
     * <p></p>
     *
     * @param rentalDto the DTO object to be saved
     * @return the saved RentalDto object
     */
    @Override
    public RentalDto save(final RentalDto rentalDto) {
        Rental rental = modelMapper.map(rentalDto, Rental.class);

        var imageFile = rentalDto.getPicture();
        if (imageFile != null) {
            try {
                String uploadedFileName = imageService.saveImageToStorage(uploadDirectory, imageFile);
                logger.debug("Rental {} - Picture : {} has been uploaded : {}",
                        rentalDto.getName(),
                        rentalDto.getPicture().getOriginalFilename(),
                        uploadedFileName);
                rental.setPicture(uploadedFileName);
            } catch (IOException e) {
                logger.error("An error has occurred when saving file : {}", imageFile.getOriginalFilename());
                throw new RuntimeException(e);
            }
        }

        Rental savedRental = rentalRepository.save(rental);
        logger.debug("Rental : {} has been saved.", rentalDto);
        return modelMapper.map(savedRental, RentalDto.class);
    }

    /**
     * Deletes a rental object by its ID.
     * <p></p>
     *
     * @param id the ID of the rental object to be deleted
     */
    @Override
    public void delete(final Long id) {
        // TODO Ckeck how to handle exceptions on Web
        rentalRepository.deleteById(id);
    }

    /**
     * Updates a RentalDto object with the specified ID.
     * <p></p>
     *
     * @param id        the ID of the object to be updated
     * @param rentalDto the updated object
     * @return a RentalDto object representing the updated object
     */
    @Override
    public RentalDto update(
            final Long id,
            final RentalDto rentalDto) {
        var updatedRental = rentalRepository.save(modelMapper.map(rentalDto, Rental.class));
        return modelMapper.map(updatedRental, RentalDto.class);
    }

    /**
     * Converts a Rental object to a RentalDto object.
     * <p></p>
     *
     * @param rental the Rental object to be converted
     * @return the RentalDto object representing the converted Rental object
     */
    private RentalDto convertToDto(final Rental rental) {
        return modelMapper.map(rental, RentalDto.class);
    }
}
