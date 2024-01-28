package com.bookstore.assessment.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Domain entity representing detailed information about a product.
 * This class is typically used for business logic and persistence.
 */
@Data
public class ProductDetails {

   /**
    * Unique identifier for the product.
    */
   public Integer productId;

   /**
    * The name of the product.
    */
   public String name;

   /**
    * A detailed description of the product.
    */
   public String description;

   /**
    * The retail price of the product.
    */
   public BigDecimal retailPrice;

   /**
    * The quantity of the product available in stock.
    */
   public Integer quantityAvailable;

   /**
    * The final price of the product after applying discounts or taxes.
    */
   public BigDecimal finalPrice;
}
