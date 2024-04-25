package com.pop.codelab.chatopbackend.rental;

import com.pop.codelab.chatopbackend.controllers.CrudController;
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
    private RentalService rentalService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ModelMapper modelMapper;

    public RentalController(RentalService rentalService) {

        super(rentalService);
    }

    @PostMapping(path = "/", consumes = {"multipart/form-data"})
    //public ResponseEntity<Rental> saveRental(@RequestBody RentalDto rentalDto, @RequestPart MultipartFile image){
    //public ResponseEntity<Rental> saveRental(@RequestParam RentalDto rentalDto){
    public ResponseEntity<Rental> saveRental(@ModelAttribute RentalDto rentalDto) {
        logger.info("Saving rental...");
        var imageFile = rentalDto.getPicture();
        if (imageFile!=null){
            try {
                imageService.saveImageToStorage(uploadDirectory, imageFile) ;
                logger.debug("Rental {} - Picture : {}", rentalDto.getName(), rentalDto.getPicture().getOriginalFilename());
            } catch (IOException e) {
                //TODO Implements custom error
                logger.error("An error has occurred when saving file : {}", imageFile.getOriginalFilename());
                throw new RuntimeException(e);
            }
        }



        var savedRentalDto = this.rentalService.save(rentalDto);
        logger.debug("Saved : {}", savedRentalDto);
        return new ResponseEntity<Rental>(modelMapper.map(savedRentalDto, Rental.class), HttpStatus.CREATED);
    }
}
