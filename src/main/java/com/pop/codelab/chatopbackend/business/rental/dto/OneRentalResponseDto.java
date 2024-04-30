package com.pop.codelab.chatopbackend.business.rental.dto;

import com.pop.codelab.chatopbackend.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * The OneRentalResponseDto class represents the response DTO (Data Transfer Object)
 * for a single rental entity. It extends the BaseDto class.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OneRentalResponseDto extends BaseDto {

    /**
     * The name variable can be used to store the name of the rental entity.
     * <p></p>>
     *
     * @see OneRentalResponseDto
     */
    private String name;

    /**
     * The surface variable represents the surface of a rental entity.
     * It is of type BigDecimal and can be used to store the surface value in decimal format with high precision.
     * The surface value indicates the total area or size of the rental entity.
     * <p></p>
     *
     * @see OneRentalResponseDto
     */
    private BigDecimal surface;
    /**
     * The price variable represents the price of a rental entity.
     * It is of type BigDecimal and can be used to store the price value in decimal format with high precision.
     * The price value indicates the cost or amount to be paid for the rental entity.
     * <p></p>
     *
     * @see OneRentalResponseDto
     */
    private BigDecimal price;
    /**
     * The picture variable represents the picture of a rental entity.
     * It is of type String and can be used to store the picture URL or file path.
     * The picture value represents the visual representation of the rental.
     * <p></p>
     *
     * @see OneRentalResponseDto
     */
    private String picture;
    /**
     * The description value provides additional information or details about the rental entity.
     * <p></p>
     *
     * @see OneRentalResponseDto
     */
    private String description;
    /**
     * The owner_id variable represents the identifier of the owner of a rental entity.
     * It is of type Long and can be used to uniquely identify the owner.
     * The owner_id value is typically used to establish a relationship between a rental entity and its owner.
     * <p></p>
     *
     * @see OneRentalResponseDto
     */
    private Long owner_id;

}
