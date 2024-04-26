package com.pop.codelab.chatopbackend.rental;

import com.pop.codelab.chatopbackend.exception.ResourceNotFoundException;
import com.pop.codelab.chatopbackend.rental.dto.RentalDto;
import com.pop.codelab.chatopbackend.service.CrudService;
import com.pop.codelab.chatopbackend.service.ImageService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
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

@Service
@RequiredArgsConstructor
public class RentalService implements CrudService<RentalDto> {
    private static final Logger logger = LoggerFactory.getLogger(RentalService.class);

    @Value("${application.local-storage.upload-directory}")
    private String uploadDirectory;

    private final RentalRepository rentalRepository;

    private final ImageService imageService;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${application.local-storage.upload-directory}")
    private String imagesDirectoryPath;

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


    @Override
    public Optional<RentalDto> findById(Long id) {
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if (optionalRental.isEmpty()) {
            logger.warn("Rental id : {} not found ! ", id);
            throw new ResourceNotFoundException(String.format("Rental id : %s not found ! ", id));
        }
        RentalDto rentalDto = modelMapper.map(optionalRental, RentalDto.class);
        if (optionalRental.get().getPicture()!=null) {
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




    @Override
    public RentalDto save(RentalDto rentalDto) {
        Rental rental = modelMapper.map(rentalDto, Rental.class);

        var imageFile = rentalDto.getPicture();
        if (imageFile != null) {
            try {
                String uploadedFileName = imageService.saveImageToStorage(uploadDirectory, imageFile);
                logger.debug("Rental {} - Picture : {} has been uploaded : {}",
                        rentalDto.getName(),
                        rentalDto.getPicture().getOriginalFilename(),
                        uploadedFileName);
                rental.setPicture( uploadedFileName);
            } catch (IOException e) {
                logger.error("An error has occurred when saving file : {}", imageFile.getOriginalFilename());
                throw new RuntimeException(e);
            }
        }

        Rental savedRental = rentalRepository.save(rental);
        logger.debug("Rental : {} has been saved.", rentalDto);
        return modelMapper.map(savedRental, RentalDto.class);
    }

    @Override
    public void delete(Long id) {
        // TODO Ckeck how to handle exceptions on Web
        rentalRepository.deleteById(id);
    }

    @Override
    public RentalDto update(Long id, RentalDto rentalDto) {
        var updatedRental = rentalRepository.save(modelMapper.map(rentalDto, Rental.class ));
        return modelMapper.map(updatedRental, RentalDto.class);
    }

    private RentalDto convertToDto(Rental rental) {
        return modelMapper.map(rental, RentalDto.class);
    }


}
