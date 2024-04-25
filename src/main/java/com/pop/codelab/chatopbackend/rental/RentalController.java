package com.pop.codelab.chatopbackend.rental;

import com.pop.codelab.chatopbackend.controllers.CrudController;
import com.pop.codelab.chatopbackend.message.MessageCreationResponse;
import com.pop.codelab.chatopbackend.message.MessageDto;
import com.pop.codelab.chatopbackend.service.ImageService;
import com.pop.codelab.chatopbackend.user.UserDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/rentals")
public class RentalController extends CrudController<RentalDto> {

    private static final Logger logger = LoggerFactory.getLogger(RentalController.class);

    @Value("${application.local-storage.upload-directory}")
    private String uploadDirectory;

    @Autowired
    private ImageService imageService;

    /**
     * The RentalController class handles the HTTP requests related to rental entities.
     *
     * @param rentalService the service used for performing CRUD operations on rental entities
     */
    public RentalController(RentalService rentalService) {
        super(rentalService);
    }

    /**
     * Saves the rental object to the server.
     * Picture uploading feature
     *
     * @param rentalDto the object to be saved
     * @return a ResponseEntity containing the saved rentalDto and the HTTP status code CREATED (201)
     * @throws RuntimeException if the rentalDto fails to save
     */
    @Override
    @PostMapping(path = "/", consumes = {"multipart/form-data"})
    public ResponseEntity<RentalDto> save(@ModelAttribute RentalDto rentalDto) {
        logger.info("Saving rental...");
        var imageFile = rentalDto.getPicture();
        if (imageFile!=null){
            try {
                imageService.saveImageToStorage(uploadDirectory, imageFile) ;
                logger.debug("Rental {} - Picture : {}", rentalDto.getName(), rentalDto.getPicture().getOriginalFilename());
            } catch (IOException e) {
                logger.error("An error has occurred when saving file : {}", imageFile.getOriginalFilename());
                throw new RuntimeException(e);
            }
        }
        RentalDto savedRentalDto = (RentalDto) super.save(rentalDto).getBody();
        if (savedRentalDto==null){
            logger.error("The Rental DTO has not been saved !");
            throw new RuntimeException("The Rental DTO has not been saved !");
        }
        logger.debug("Saved : {}", savedRentalDto);
        return  new ResponseEntity<>(savedRentalDto, HttpStatus.CREATED);
    }
}
