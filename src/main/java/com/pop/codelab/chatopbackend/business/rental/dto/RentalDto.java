package com.pop.codelab.chatopbackend.business.rental.dto;

import com.pop.codelab.chatopbackend.business.user.dto.UserDto;
import com.pop.codelab.chatopbackend.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

/**
 * The RentalDto class represents a Data Transfer Object (DTO) for Rental entities.
 * It contains properties that are used for transferring rental data between different layers of the application,
 * such as the controller and the service layer.
 * The RentalDto class extends the BaseDto class, which provides common properties such as id, createAt, and updatedAt.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class RentalDto extends BaseDto {

    /**
     * The private variable `name` is a String type and represents the name of a rental.
     * It is used to store the name of the rental represented by a RentalDto object.
     * This variable is encapsulated and can only be accessed within the class that declares it.
     *
     * @see RentalDto
     */
    private String name;

    /**
     * The surface variable is a private BigDecimal type and represents the surface area of a rental property.
     * It is used to store the surface area of the rental property represented by a RentalDto object.
     * This variable is encapsulated and can only be accessed within the class that declares it.
     */
    private BigDecimal surface;

    /**
     * The price variable represents the price of a rental property.
     * It is a private BigDecimal type and is used to store the price of the rental property represented by a RentalDto object.
     * This variable is encapsulated and can only be accessed within the class that declares it.
     */
    private BigDecimal price;

    /**
     * The picture variable is of type MultipartFile and represents an uploaded image file.
     * It is used to store the image file uploaded by the user.
     * This variable is encapsulated and can only be accessed within the class that declares it.
     */
    private MultipartFile picture;

    /**
     * The private variable `description` is a String type and represents the description of a rental.
     * It is used to store the description of the rental represented by a RentalDto object.
     * This variable is encapsulated and can only be accessed within the class that declares it.
     *
     * @see RentalDto
     */
    private String description;

    /**
     * The user variable represents a UserDto object.
     * It is used to store the user data, including the user's name, email, role, and other properties.
     * This variable is of type UserDto and is part of the RentalDto class, which is a Data Transfer Object (DTO) for Rental entities.
     *
     * @see UserDto
     * @see RentalDto
     */
    private UserDto user;
}
