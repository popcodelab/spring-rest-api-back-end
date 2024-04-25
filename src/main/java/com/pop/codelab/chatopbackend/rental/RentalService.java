package com.pop.codelab.chatopbackend.rental;

import com.pop.codelab.chatopbackend.exception.ResourceNotFoundException;
import com.pop.codelab.chatopbackend.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalService implements CrudService<RentalDto> {
    private static final Logger logger = LoggerFactory.getLogger(RentalService.class);

    private final RentalRepository rentalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RentalDto> findAll() {
        logger.info("Gathering all rentals...");
        List<Rental> rentals = rentalRepository.findAll();
        if (rentals.isEmpty()) {
            throw new ResourceNotFoundException("No message found");
        }
        logger.debug("Rental(s) count : {}", rentals.size());
        return rentals.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<RentalDto> findById(Long id) {
        Optional<Rental> rentalOptional = rentalRepository.findById(id);
        if (!rentalOptional.isPresent()){
            throw new ResourceNotFoundException("No rental found with Id : "+ id);
        }
        logger.debug("Dto retrieved : {} ", rentalOptional);
        return rentalOptional.map(this::convertToDto);
    }


    @Override
    public RentalDto save(RentalDto rentalDto) {
        Rental rental = modelMapper.map(rentalDto, Rental.class);
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
    public RentalDto update(Long id, RentalDto messageDto) {
        //TODO Check if dates are automatically updated. IMPORTANT
        Rental savedRental = rentalRepository.findById(id).orElseThrow();
        Rental rentalToUpdate = modelMapper.map(messageDto, Rental.class);
        savedRental.setUser(rentalToUpdate.getUser());
        var updatedRental = rentalRepository.save(savedRental);
        return modelMapper.map(updatedRental, RentalDto.class);
    }

    private RentalDto convertToDto(Rental rental) {
        return modelMapper.map(rental, RentalDto.class);
    }
}
