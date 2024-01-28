package com.bookstore.assessment.dto;

import lombok.Data;

import java.math.BigDecimal;
/**
 * Data Transfer Object (DTO) representing product details.
 * This class is used for transferring product-related information between different layers of the application.
 */
@Data
public class ProductDetailDto {

    /**
     * Unique identifier for the product.
     */
    private Integer productId;

    /**
     * The name of the product.
     */
    private String name;

    /**
     * A brief description of the product.
     */
    private String description;

    /**
     * The retail price of the product.
     */
    private BigDecimal retailPrice;

    /**
     * The quantity of the product available in stock.
     */
    private Integer quantityAvailable;

    /**
     * The final price of the product after applying discounts or taxes.
     */
    private BigDecimal finalPrice;
}
