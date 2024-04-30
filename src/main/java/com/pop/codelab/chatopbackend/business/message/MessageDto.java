package com.pop.codelab.chatopbackend.business.message;

import com.pop.codelab.chatopbackend.dto.BaseDto;
import com.pop.codelab.chatopbackend.business.rental.dto.RentalDto;
import com.pop.codelab.chatopbackend.business.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The MessageDto class represents a data transfer object (DTO) that is used for transferring message-related data.
 * It extends the BaseDto class, which provides common properties such as id, createAt, and updatedAt.
 * <p>
 * The MessageDto class is annotated with Lombok annotations, such as @Data, @SuperBuilder, @NoArgsConstructor,
 * and @AllArgsConstructor, to automatically generate getter, setter, builder, and constructor methods.
 * </p>
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto extends BaseDto {

    /**
     * The message variable represents the content of the message.
     * It is a String type and stores the actual message that needs to be transferred.
     */
    private String message;

    /**
     * The user variable represents a user data transfer object (DTO).
     * It extends the BaseDto class, which provides common properties such as id, createAt, and updatedAt.
     *
     * @see UserDto
     * @see BaseDto
     */
    private UserDto user;

    /**
     * The rentalDto variable represents a data transfer object (DTO) that is used for transferring rental-related data.
     * It extends the BaseDto class, which provides common properties such as id, createAt, and updatedAt.
     *
     * @see BaseDto
     */
    private RentalDto rentalDto;
}
