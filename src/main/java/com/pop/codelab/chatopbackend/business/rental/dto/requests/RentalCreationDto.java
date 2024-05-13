package com.pop.codelab.chatopbackend.business.rental.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

/**
 * RentalDto represents the data transfer object for creating a rental.
 *
 * @author Pignon Pierre-Olivier
 * @version 2
 */
@Data

public class RentalDto {

    private String name;

    private BigDecimal surface;

    private BigDecimal price;

    private MultipartFile picture;

    private String description;
}
