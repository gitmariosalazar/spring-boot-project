package com.exa.hexagonal.products.infrastructure.controller;

import com.exa.hexagonal.errors.exception.BadRequestException;
import com.exa.hexagonal.errors.exception.ResourceNotFoundException;
import com.exa.hexagonal.errors.payload.MessageResponse;
import com.exa.hexagonal.products.application.services.ProductService;
import com.exa.hexagonal.products.domain.model.Product;
import com.exa.hexagonal.products.infrastructure.mappers.ProductMapper;
import com.exa.hexagonal.products.domain.dto.request.ProductRequest;
import com.exa.hexagonal.products.domain.dto.response.ProductResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product-Controller", description = "Product controller")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final MessageResponse messageResponse = new MessageResponse();

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) {
        try {
            Product productCreated = productService.createProduct(productRequest);
            ProductResponse productResponse = productMapper.fromProductToProductResponse(productCreated);
            messageResponse.setMessage("Product created successfully!");
            messageResponse.setObject(productResponse);
            return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            throw new BadRequestException("Error while creating product: " + e.getMessage());
        }
    }

    @PutMapping("/{code}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable(name = "code") String code,
            @RequestBody ProductRequest productRequest) {
        try {
            Product product = productService.updateProduct(code, productRequest).orElse(null);

            if (product == null) {
                throw new ResourceNotFoundException("product", "code", code);
            }
            ProductResponse productResponse = productMapper.fromProductToProductResponse(product);
            messageResponse.setMessage("Product updated successfully");
            messageResponse.setObject(productResponse);

            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new BadRequestException("Error while updating product: "+e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> findAllProducts() {
        List<Product> products = productService.findAllProducts();
        if(products==null || products.isEmpty()){
            throw new ResourceNotFoundException("products");
        }
        List<ProductResponse> productResponses = products.stream()
                .map(productMapper::fromProductToProductResponse)
                .toList();
        messageResponse.setMessage("Products found successfully!");
        messageResponse.setObject(productResponses);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/unpurchased-products")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> findAllUnpurchasedProducts() {
        List<Product> products = productService.findUnpurchasedProducts();
        if(products==null || products.isEmpty()){
            throw new ResourceNotFoundException("products");
        }
        List<ProductResponse> productResponses = products.stream()
                .map(productMapper::fromProductToProductResponse)
                .toList();
        messageResponse.setMessage("Products found successfully!");
        messageResponse.setObject(productResponses);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/warning_stock")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> findAllProductWarningStock() {
        List<Product> products = productService.findAllProductWarningStock(5);
        if(products==null || products.isEmpty()){
            throw new ResourceNotFoundException("products");
        }
        List<ProductResponse> productResponses = products.stream()
                .map(productMapper::fromProductToProductResponse)
                .toList();
        messageResponse.setMessage("Users found successfully!");
        messageResponse.setObject(productResponses);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/by-id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> findProductById(@PathVariable(name = "id") Long id) {
        Optional<Product> productFound = productService.findProductById(id);
        ProductResponse productResponse = productFound.map(productMapper::fromProductToProductResponse)
                .orElseThrow(()->new ResourceNotFoundException("product", "id", id));
        messageResponse.setObject(productResponse);
        messageResponse.setMessage("Product found successfully!");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> findProductByCode(@PathVariable(name = "code") String code) {
        Optional<Product> productFound = productService.findProductByCode(code);
        ProductResponse productResponse = productFound.map(productMapper::fromProductToProductResponse)
                .orElseThrow(()->new ResourceNotFoundException("product", "code", code));
        messageResponse.setObject(productResponse);
        messageResponse.setMessage("Product found successfully!");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") Long id) {
        if (productService.deleteProduct(id)) {
            messageResponse.setMessage("Delete product successfully!");
            messageResponse.setObject(null);
            return new ResponseEntity<>(messageResponse,HttpStatus.NO_CONTENT);
        } else {
            throw new ResourceNotFoundException("product","id", id);
        }
    }
}
