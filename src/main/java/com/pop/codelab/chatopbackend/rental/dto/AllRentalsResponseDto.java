package com.pop.codelab.chatopbackend.rental.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AllRentalsResponseDto {

    private List<RentalDto> rentals;

}
