package com.bookstore.assessment.service;

import com.bookstore.assessment.common.CustomApiResponse;
import com.bookstore.assessment.domain.ProductDetails;
import com.bookstore.assessment.dto.ProductDetailDto;
import com.bookstore.assessment.service.ProductDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class ProductDetailsServiceTest {

    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    ProductDetailsService productDetailsService;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testCreateProductMethodSuccessScenario(){
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setName("princess stories");
        productDetailDto.setDescription("fiction");
        productDetailDto.setRetailPrice(BigDecimal.valueOf(250));
        productDetailDto.setQuantityAvailable(20);

        ProductDetails productDetail = new ProductDetails();
        productDetail.setName("princess stories");
        productDetail.setDescription("fiction");
        productDetail.setRetailPrice(BigDecimal.valueOf(250));
        productDetail.setQuantityAvailable(20);

        when(modelMapper.map(any(), eq(ProductDetails.class))).thenReturn(productDetail);
        when(modelMapper.map(any(), eq(ProductDetailDto.class))).thenReturn(productDetailDto);
        CustomApiResponse<ProductDetailDto> productDetailDtoCustomApiResponse =
                productDetailsService.createProduct(productDetailDto);

        Assertions.assertNotNull(productDetailDtoCustomApiResponse);
        Assertions.assertEquals(productDetailDto, productDetailDtoCustomApiResponse.getData());
        Assertions.assertEquals("Product created successfully", productDetailDtoCustomApiResponse.getMessage());
    }

    @Test
    void testReadProductSuccessScenarios(){
        Integer productId = 1;
        ProductDetails productDetail = new ProductDetails();
        productDetail.setName("princess stories");
        productDetail.setDescription("fiction");
        productDetail.setRetailPrice(BigDecimal.valueOf(250));
        productDetail.setQuantityAvailable(20);
        Map<Integer, ProductDetails> productMap = new ConcurrentHashMap<>();
        productMap.put(1,productDetail);
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setProductId(productId);
        productDetailDto.setName("princess stories");
        productDetailDto.setDescription("fiction");
        productDetailDto.setRetailPrice(BigDecimal.valueOf(250));
        productDetailDto.setQuantityAvailable(20);

        ReflectionTestUtils.setField(productDetailsService, "productMap", productMap);
        when(modelMapper.map(any(ProductDetailDto.class), eq(ProductDetailDto.class))).thenReturn(productDetailDto);

        CustomApiResponse<ProductDetailDto> productDetailDtoCustomApiResponse =
                productDetailsService.readProduct(productId);

        Assertions.assertNotNull(productDetailDtoCustomApiResponse);
        Assertions.assertEquals("Product found", productDetailDtoCustomApiResponse.getMessage());

    }

    @Test
    void testReadProductFailScenarios(){
        Integer productId = 1;
        Map<Integer, ProductDetails> productMap = new ConcurrentHashMap<>();
        ReflectionTestUtils.setField(productDetailsService, "productMap", productMap);

        CustomApiResponse<ProductDetailDto> productDetailDtoCustomApiResponse =
                productDetailsService.readProduct(productId);

        Assertions.assertEquals("Product not found", productDetailDtoCustomApiResponse.getMessage());

    }

    @Test
    void testUpdateProductMethodSuccessScenario(){
        Integer productId = 1;
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setProductId(1);
        productDetailDto.setName("princess stories");
        productDetailDto.setDescription("fiction");
        productDetailDto.setRetailPrice(BigDecimal.valueOf(250));
        productDetailDto.setQuantityAvailable(20);

        ProductDetails productDetail = new ProductDetails();
        productDetail.setName("princess stories");
        productDetail.setDescription("fiction");
        productDetail.setRetailPrice(BigDecimal.valueOf(250));
        productDetail.setQuantityAvailable(20);

        Map<Integer, ProductDetails> productMap = new ConcurrentHashMap<>();
        productMap.put(1,productDetail);

        ReflectionTestUtils.setField(productDetailsService, "productMap", productMap);
        when(modelMapper.map(any(), eq(ProductDetails.class))).thenReturn(productDetail);
        when(modelMapper.map(any(), eq(ProductDetailDto.class))).thenReturn(productDetailDto);
        CustomApiResponse<ProductDetailDto> productDetailDtoCustomApiResponse =
                productDetailsService.updateProduct(productId,productDetailDto);

        Assertions.assertNotNull(productDetailDtoCustomApiResponse);
        Assertions.assertEquals(productDetailDto, productDetailDtoCustomApiResponse.getData());
        Assertions.assertEquals("Product updated successfully", productDetailDtoCustomApiResponse.getMessage());
    }

    @Test
    void testUpdateProductFailScenarios(){
        Integer productId = 1;
        Map<Integer, ProductDetails> productMap = new ConcurrentHashMap<>();
        ReflectionTestUtils.setField(productDetailsService, "productMap", productMap);

        CustomApiResponse<ProductDetailDto> productDetailDtoCustomApiResponse =
                productDetailsService.updateProduct(productId , new ProductDetailDto());

        Assertions.assertEquals("Product not updated", productDetailDtoCustomApiResponse.getMessage());

    }

    @Test
    void testDeleteProductMethodSuccessScenario(){
        Integer productId = 1;
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setProductId(1);
        productDetailDto.setName("princess stories");
        productDetailDto.setDescription("fiction");
        productDetailDto.setRetailPrice(BigDecimal.valueOf(250));
        productDetailDto.setQuantityAvailable(20);

        ProductDetails productDetail = new ProductDetails();
        productDetail.setName("princess stories");
        productDetail.setDescription("fiction");
        productDetail.setRetailPrice(BigDecimal.valueOf(250));
        productDetail.setQuantityAvailable(20);

        Map<Integer, ProductDetails> productMap = new ConcurrentHashMap<>();
        productMap.put(1,productDetail);

        ReflectionTestUtils.setField(productDetailsService, "productMap", productMap);
        when(modelMapper.map(any(), eq(ProductDetailDto.class))).thenReturn(productDetailDto);
        CustomApiResponse<ProductDetailDto> productDetailDtoCustomApiResponse =
                productDetailsService.deleteProduct(productId);

        Assertions.assertEquals(productDetailDto, productDetailDtoCustomApiResponse.getData());
        Assertions.assertEquals("Product deleted successfully", productDetailDtoCustomApiResponse.getMessage());
    }

    @Test
    void testDeleteProductFailScenarios(){
        Integer productId = 1;
        Map<Integer, ProductDetails> productMap = new ConcurrentHashMap<>();
        ReflectionTestUtils.setField(productDetailsService, "productMap", productMap);

        CustomApiResponse<ProductDetailDto> productDetailDtoCustomApiResponse =
                productDetailsService.deleteProduct(productId);

        Assertions.assertEquals("Product not found", productDetailDtoCustomApiResponse.getMessage());

    }

    @Test
    void testGetAllProductMethodSuccessScenario(){
        Integer productId = 1;
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setProductId(1);
        productDetailDto.setName("princess stories");
        productDetailDto.setDescription("fiction");
        productDetailDto.setRetailPrice(BigDecimal.valueOf(250));
        productDetailDto.setQuantityAvailable(20);

        List<ProductDetailDto> productDetailDtoList = new ArrayList<>();
        productDetailDtoList.add(productDetailDto);
        ProductDetails productDetail = new ProductDetails();
        productDetail.setName("princess stories");
        productDetail.setDescription("fiction");
        productDetail.setRetailPrice(BigDecimal.valueOf(250));
        productDetail.setQuantityAvailable(20);

        Map<Integer, ProductDetails> productMap = new ConcurrentHashMap<>();
        productMap.put(1,productDetail);

        ReflectionTestUtils.setField(productDetailsService, "productMap", productMap);
        when(modelMapper.map(any(), eq(ProductDetailDto.class))).thenReturn(productDetailDto);
        CustomApiResponse<List<ProductDetailDto>> productDetailDtoCustomApiResponse =
                productDetailsService.getAllProducts();

        Assertions.assertEquals(productDetailDtoList, productDetailDtoCustomApiResponse.getData());
        Assertions.assertEquals("All products retrieved successfully", productDetailDtoCustomApiResponse.getMessage());
    }

    @Test
    void testApplyDiscountOrTaxMethodSuccessScenarioWithDiscount(){
        Integer productId = 1;
        BigDecimal discount = BigDecimal.valueOf(10);
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setProductId(1);
        productDetailDto.setName("princess stories");
        productDetailDto.setDescription("fiction");
        productDetailDto.setRetailPrice(BigDecimal.valueOf(250));
        productDetailDto.setQuantityAvailable(20);

        ProductDetails productDetail = new ProductDetails();
        productDetail.setProductId(1);
        productDetail.setName("princess stories");
        productDetail.setDescription("fiction");
        productDetail.setRetailPrice(BigDecimal.valueOf(250));
        productDetail.setQuantityAvailable(20);

        Map<Integer, ProductDetails> productMap = new ConcurrentHashMap<>();
        productMap.put(1,productDetail);

        ReflectionTestUtils.setField(productDetailsService, "productMap", productMap);
        when(modelMapper.map(any(), eq(ProductDetails.class))).thenReturn(productDetail);
        when(modelMapper.map(any(), eq(ProductDetailDto.class))).thenReturn(productDetailDto);
        CustomApiResponse<ProductDetailDto> productDetailDtoCustomApiResponse =
                productDetailsService.applyDiscountOrTax(productId,discount,null);

        Assertions.assertNotNull(productDetailDtoCustomApiResponse);
        Assertions.assertEquals(productDetailDto, productDetailDtoCustomApiResponse.getData());
        Assertions.assertEquals("Discount added successfully", productDetailDtoCustomApiResponse.getMessage());
    }

    @Test
    void testApplyDiscountOrTaxMethodSuccessScenarioWithTax(){
        Integer productId = 1;
        BigDecimal tax = BigDecimal.valueOf(10);
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setProductId(1);
        productDetailDto.setName("princess stories");
        productDetailDto.setDescription("fiction");
        productDetailDto.setRetailPrice(BigDecimal.valueOf(250));
        productDetailDto.setQuantityAvailable(20);
        productDetailDto.setFinalPrice(BigDecimal.valueOf(275.0));

        ProductDetails productDetail = new ProductDetails();
        productDetail.setProductId(1);
        productDetail.setName("princess stories");
        productDetail.setDescription("fiction");
        productDetail.setRetailPrice(BigDecimal.valueOf(250));
        productDetail.setQuantityAvailable(20);
        productDetail.setFinalPrice(BigDecimal.valueOf(275.0));

        Map<Integer, ProductDetails> productMap = new ConcurrentHashMap<>();
        productMap.put(1,productDetail);

        ReflectionTestUtils.setField(productDetailsService, "productMap", productMap);
        when(modelMapper.map(any(), eq(ProductDetails.class))).thenReturn(productDetail);
        when(modelMapper.map(any(), eq(ProductDetailDto.class))).thenReturn(productDetailDto);
        CustomApiResponse<ProductDetailDto> productDetailDtoCustomApiResponse =
                productDetailsService.applyDiscountOrTax(productId,null,tax);

        Assertions.assertNotNull(productDetailDtoCustomApiResponse);
        Assertions.assertEquals(productDetailDto, productDetailDtoCustomApiResponse.getData());
        Assertions.assertEquals("Tax added successfully", productDetailDtoCustomApiResponse.getMessage());
    }

    @Test
    void testApplyDiscountOrTaxMethodWhenRecordIsNotFound(){
        Integer productId = 1;
        Map<Integer, ProductDetails> productMap = new ConcurrentHashMap<>();

        ReflectionTestUtils.setField(productDetailsService, "productMap", productMap);

        CustomApiResponse<ProductDetailDto> productDetailDtoCustomApiResponse =
                productDetailsService.applyDiscountOrTax(productId,null,null);

        Assertions.assertNotNull(productDetailDtoCustomApiResponse);
        Assertions.assertEquals("Product not found",
                productDetailDtoCustomApiResponse.getMessage());
    }

    @Test
    void testApplyDiscountOrTaxMethodWhenOutBothParameter(){
        Integer productId = 1;
        Map<Integer, ProductDetails> productMap = new ConcurrentHashMap<>();

        ProductDetails productDetail = new ProductDetails();
        productDetail.setProductId(1);
        productDetail.setName("princess stories");
        productDetail.setDescription("fiction");
        productDetail.setRetailPrice(BigDecimal.valueOf(250));
        productDetail.setQuantityAvailable(20);
        ReflectionTestUtils.setField(productDetailsService, "productMap", productMap);
        productMap.put(1,productDetail);
        CustomApiResponse<ProductDetailDto> productDetailDtoCustomApiResponse =
                productDetailsService.applyDiscountOrTax(productId,null,null);

        Assertions.assertNotNull(productDetailDtoCustomApiResponse);
        Assertions.assertEquals("Either discount or tax must be provided",
                productDetailDtoCustomApiResponse.getMessage());
    }
}