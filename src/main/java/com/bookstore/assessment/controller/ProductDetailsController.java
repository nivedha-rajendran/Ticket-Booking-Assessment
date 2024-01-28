package com.bookstore.assessment.controller;

import com.bookstore.assessment.common.ApiDocumentationTags;
import com.bookstore.assessment.common.ApiResponseMessages;
import com.bookstore.assessment.common.CustomApiResponse;
import com.bookstore.assessment.dto.ProductDetailDto;
import com.bookstore.assessment.service.ProductDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Controller class for managing product details through RESTful API endpoints.
 */
@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductDetailsController {

    @Autowired
    private ProductDetailsService productDetailsService;

    /**
     * Creates a new product based on the provided details.
     *
     * @param productDetailDto The details of the product to be created.
     * @return ResponseEntity containing the response for the create operation.
     */
    @PostMapping("/")
    @Operation(summary = ApiDocumentationTags.CREATE_PRODUCT_SUMMARY,
            description = ApiDocumentationTags.CREATE_PRODUCT_DESCRIPTION,
            tags = ApiDocumentationTags.PRODUCT_DETAILS)
    @ApiResponse(responseCode = "200", description = ApiResponseMessages.PRODUCT_CREATED_SUCCESSFULLY)
    @ApiResponse(responseCode = "500", description = ApiResponseMessages.PRODUCT_CREATION_FAILED)
    public ResponseEntity<CustomApiResponse<ProductDetailDto>> createProduct(
            @RequestBody ProductDetailDto productDetailDto) {
        CustomApiResponse<ProductDetailDto> response =
                productDetailsService.createProduct(productDetailDto);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves the details of a product based on its unique identifier.
     *
     * @param productId The unique identifier of the product to be retrieved.
     * @return ResponseEntity containing the response for the read operation.
     */
    @GetMapping("/{productId}")
    @Operation(summary = ApiDocumentationTags.READ_PRODUCT_SUMMARY,
            description = ApiDocumentationTags.READ_PRODUCT_DESCRIPTION,
            tags = ApiDocumentationTags.PRODUCT_DETAILS)
    @ApiResponse(responseCode = "200", description = ApiResponseMessages.PRODUCT_FOUND)
    @ApiResponse(responseCode = "500", description = ApiResponseMessages.PRODUCT_NOT_FOUND)
    public ResponseEntity<CustomApiResponse<ProductDetailDto>> readProduct(
            @PathVariable Integer productId) {
        CustomApiResponse<ProductDetailDto> response =
                productDetailsService.readProduct(productId);
        return ResponseEntity.ok(response);
    }

    /**
     * Updates the details of an existing product based on its unique identifier.
     *
     * @param productId        The unique identifier of the product to be updated.
     * @param productDetailDto The updated details of the product.
     * @return ResponseEntity containing the response for the update operation.
     */
    @PutMapping("/{productId}")
    @Operation(summary = ApiDocumentationTags.UPDATE_PRODUCT_SUMMARY,
            description = ApiDocumentationTags.UPDATE_PRODUCT_DESCRIPTION,
            tags = ApiDocumentationTags.PRODUCT_DETAILS)
    @ApiResponse(responseCode = "200", description = ApiResponseMessages.PRODUCT_UPDATED)
    @ApiResponse(responseCode = "500", description = ApiResponseMessages.PRODUCT_NOT_UPDATED)
    public ResponseEntity<CustomApiResponse<ProductDetailDto>> updateProduct(
            @PathVariable Integer productId,
            @RequestBody ProductDetailDto productDetailDto) {
        CustomApiResponse<ProductDetailDto> response =
                productDetailsService.updateProduct(productId, productDetailDto);
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes a product based on its unique identifier.
     *
     * @param productId The unique identifier of the product to be deleted.
     * @return ResponseEntity containing the response for the delete operation.
     */
    @DeleteMapping("/{productId}")
    @Operation(summary = ApiDocumentationTags.DELETE_PRODUCT_SUMMARY,
            description = ApiDocumentationTags.DELETE_PRODUCT_DESCRIPTION,
            tags = ApiDocumentationTags.PRODUCT_DETAILS)
    @ApiResponse(responseCode = "200", description = ApiResponseMessages.PRODUCT_DELETED)
    @ApiResponse(responseCode = "500", description = ApiResponseMessages.PRODUCT_NOT_DELETED)
    public ResponseEntity<CustomApiResponse<ProductDetailDto>> deleteProduct(
            @PathVariable Integer productId) {
        CustomApiResponse<ProductDetailDto> response =
                productDetailsService.deleteProduct(productId);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves a list of all products.
     *
     * @return ResponseEntity containing the response for retrieving all products.
     */
    @GetMapping("/list")
    @Operation(summary = ApiDocumentationTags.GET_ALL_PRODUCTS_SUMMARY,
            description = ApiDocumentationTags.GET_ALL_PRODUCTS_DESCRIPTION,
            tags = ApiDocumentationTags.PRODUCT_DETAILS)
    @ApiResponse(responseCode = "200", description = ApiResponseMessages.ALL_PRODUCTS_RETRIEVED)
    @ApiResponse(responseCode = "500", description = ApiResponseMessages.PRODUCT_NOT_FOUND)
    public ResponseEntity<CustomApiResponse<List<ProductDetailDto>>> getAllProducts() {
        CustomApiResponse<List<ProductDetailDto>> response =
                productDetailsService.getAllProducts();
        return ResponseEntity.ok(response);
    }

    /**
     * Applies a discount or tax to a product based on its unique identifier.
     *
     * @param productId The unique identifier of the product.
     * @param discount   The discount to be applied (optional).
     * @param tax        The tax to be applied (optional).
     * @return ResponseEntity containing the response for applying discount or tax.
     */
    @PutMapping("/{productId}/apply-discount-or-tax")
    @Operation(summary = ApiDocumentationTags.APPLY_DISCOUNT_OR_TAX_SUMMARY,
            description = ApiDocumentationTags.APPLY_DISCOUNT_OR_TAX_DESCRIPTION,
            tags = ApiDocumentationTags.PRODUCT_DETAILS)
    @ApiResponse(responseCode = "200", description = ApiResponseMessages.DISCOUNT_OR_TAX_APPLIED_SUCCESSFULLY)
    @ApiResponse(responseCode = "500", description = ApiResponseMessages.FAILED_TO_APPLY_DISCOUNT_OR_TAX)
    public ResponseEntity<CustomApiResponse<ProductDetailDto>> applyDiscountOrTax(
            @PathVariable Integer productId,
            @RequestParam(required = false) BigDecimal discount,
            @RequestParam(required = false) BigDecimal tax) {
        CustomApiResponse<ProductDetailDto> response =
                productDetailsService.applyDiscountOrTax(productId, discount, tax);
        return ResponseEntity.ok(response);
    }
}
