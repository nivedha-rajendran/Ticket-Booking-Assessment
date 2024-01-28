package com.bookstore.assessment.service;

import com.bookstore.assessment.common.ApiResponseMessages;
import com.bookstore.assessment.common.CustomApiResponse;
import com.bookstore.assessment.domain.ProductDetails;
import com.bookstore.assessment.dto.ProductDetailDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Service class responsible for managing product details.
 */

@Service
public class ProductDetailsService {

    /**
     * In-memory storage for product details using a ConcurrentHashMap.
     */
    public final Map<Integer, ProductDetails> productMap = new ConcurrentHashMap<>();

    /**
     * Spring's ModelMapper for mapping between DTOs and domain entities.
     */
    @Autowired
    ModelMapper modelMapper;

    /**
     * Creates a new product with the provided details.
     *
     * @param productDetailDto The details of the product to be created.
     * @return The response containing the created product details.
     */
    public CustomApiResponse<ProductDetailDto> createProduct(ProductDetailDto productDetailDto) {
        try {
            Integer productId = generateProductId();
            ProductDetails productDetails = modelMapper.map(productDetailDto, ProductDetails.class);
            productDetails.setProductId(productId);
            productMap.put(productId, productDetails);
            ProductDetails ProductDetailsObj = productMap.get(productId);
            ProductDetailDto productDetailDtoObj = modelMapper.map(ProductDetailsObj, ProductDetailDto.class);
            return new CustomApiResponse<>(HttpStatus.OK.value(),
                    ApiResponseMessages.PRODUCT_CREATED_SUCCESSFULLY, productDetailDtoObj);
        } catch(Exception exception){
            exception.printStackTrace();
            return null;
        }

    }

    /**
     * Retrieves the details of a product based on its unique identifier.
     *
     * @param productId The unique identifier of the product.
     * @return The response containing the product details if found; otherwise, an error response.
     */
    public CustomApiResponse<ProductDetailDto> readProduct(Integer productId) {
        ProductDetails ProductDetails = productMap.get(productId);
        if (ProductDetails != null) {
            ProductDetailDto productDetailDto = modelMapper.map(ProductDetails, ProductDetailDto.class);
            return new CustomApiResponse<>(HttpStatus.OK.value(),
                    ApiResponseMessages.PRODUCT_FOUND, productDetailDto);
        } else {
            return new CustomApiResponse<>(HttpStatus.NOT_FOUND.value(),
                    ApiResponseMessages.PRODUCT_NOT_FOUND, null);
        }
    }

    /**
     * Updates the details of an existing product based on its unique identifier.
     *
     * @param productId        The unique identifier of the product to be updated.
     * @param productDetailDto The updated details of the product.
     * @return The response containing the updated product details if successful; otherwise, an error response.
     */
    public CustomApiResponse<ProductDetailDto> updateProduct(Integer productId, ProductDetailDto productDetailDto) {
        ProductDetails existingProduct = productMap.get(productId);
        if (existingProduct != null) {
            existingProduct.setDescription(productDetailDto.getDescription());
            existingProduct.setName(productDetailDto.getName());
            existingProduct.setQuantityAvailable(productDetailDto.getQuantityAvailable());
            existingProduct.setRetailPrice(productDetailDto.getRetailPrice());
            if (productDetailDto.getFinalPrice() != null) {
                existingProduct.setFinalPrice(productDetailDto.getFinalPrice());
            }
            modelMapper.map(productDetailDto, existingProduct);
            return new CustomApiResponse<>(HttpStatus.OK.value(),
                    ApiResponseMessages.PRODUCT_UPDATED, modelMapper.map(existingProduct, ProductDetailDto.class));
        } else {
            return new CustomApiResponse<>(HttpStatus.NOT_FOUND.value(),
                    ApiResponseMessages.PRODUCT_NOT_UPDATED, null);
        }
    }

    /**
     * Deletes a product based on its unique identifier.
     *
     * @param productId The unique identifier of the product to be deleted.
     * @return The response containing the deleted product details if successful; otherwise, an error response.
     */
    public CustomApiResponse<ProductDetailDto> deleteProduct(Integer productId) {
        ProductDetails removedProduct = productMap.remove(productId);
        if (removedProduct != null) {
            return new CustomApiResponse<>(HttpStatus.OK.value(),
                    ApiResponseMessages.PRODUCT_DELETED, modelMapper.map(removedProduct, ProductDetailDto.class));
        } else {
            return new CustomApiResponse<>(HttpStatus.NOT_FOUND.value(),
                    ApiResponseMessages.PRODUCT_NOT_FOUND, null);
        }
    }

    /**
     * Retrieves a list of all products.
     *
     * @return The response containing the list of all products.
     */
    public CustomApiResponse<List<ProductDetailDto>> getAllProducts() {
        try {
        List<ProductDetailDto> productDetailDtoList = productMap.values().stream()
                .map(productDetails -> modelMapper.map(productDetails, ProductDetailDto.class))
                .collect(Collectors.toList());
        return new CustomApiResponse<>(HttpStatus.OK.value(),
                ApiResponseMessages.ALL_PRODUCTS_RETRIEVED, productDetailDtoList);
        } catch(Exception exception){
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Generates a new unique product identifier.
     *
     * @return The generated product identifier.
     */
    private Integer generateProductId() {
        return productMap.size() + 1;
    }

    /**
     * Applies a discount or tax to a product based on its unique identifier.
     *
     * @param productId The unique identifier of the product.
     * @param discount   The discount to be applied (optional).
     * @param tax        The tax to be applied (optional).
     * @return The response containing the updated product details if successful; otherwise, an error response.
     */
    public CustomApiResponse<ProductDetailDto> applyDiscountOrTax(Integer productId, BigDecimal discount, BigDecimal tax) {
        String successMessage;
        ProductDetails productDetail = productMap.get(productId);

        if (productDetail != null) {
            BigDecimal currentPrice = productDetail.getRetailPrice();

            if (discount != null) {
                successMessage = applyDiscount(productDetail, currentPrice, discount);
            } else if (tax != null) {
                successMessage = applyTax(productDetail, currentPrice, tax);
            } else {
                // Neither discount nor tax provided
                return new CustomApiResponse<>(HttpStatus.BAD_REQUEST.value(),
                        ApiResponseMessages.EITHER_DISCOUNT_OR_TAX_MUST_BE_PROVIDED, null);
            }

            CustomApiResponse<ProductDetailDto> productWithFinalPrice =
                    updateProduct(productId, modelMapper.map(productDetail, ProductDetailDto.class));
            return new CustomApiResponse<>(HttpStatus.OK.value(),
                    successMessage, productWithFinalPrice.getData());
        } else {
            return new CustomApiResponse<>(HttpStatus.NOT_FOUND.value(),
                    ApiResponseMessages.PRODUCT_NOT_FOUND, null);
        }
    }

    // Helper methods
    private String applyDiscount(ProductDetails productDetail, BigDecimal currentPrice, BigDecimal discount) {
        BigDecimal discountedPrice = currentPrice.subtract(currentPrice.multiply(discount.divide(BigDecimal.valueOf(100))));
        productDetail.setFinalPrice(discountedPrice);
        return ApiResponseMessages.DISCOUNT_ADDED_SUCCESSFULLY;
    }

    private String applyTax(ProductDetails productDetail, BigDecimal currentPrice, BigDecimal tax) {
        BigDecimal taxedPrice = currentPrice.add(currentPrice.multiply(tax.divide(BigDecimal.valueOf(100))));
        productDetail.setFinalPrice(taxedPrice);
        return ApiResponseMessages.TAX_ADDED_SUCCESSFULLY;
    }


}