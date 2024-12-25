package com.exa.hexagonal.selling.infrastructure.controller;

import com.exa.hexagonal.errors.exception.BadRequestException;
import com.exa.hexagonal.errors.exception.ResourceNotFoundException;
import com.exa.hexagonal.errors.payload.MessageResponse;
import com.exa.hexagonal.selling.application.services.SellingService;
import com.exa.hexagonal.selling.domain.dto.request.SellingRequest;
import com.exa.hexagonal.selling.domain.dto.response.GreaterOrLessSellingResponse;
import com.exa.hexagonal.selling.domain.dto.response.ProductBySellingResponse;
import com.exa.hexagonal.selling.domain.dto.response.TotalSellingResponse;
import com.exa.hexagonal.selling.domain.model.Selling;
import com.exa.hexagonal.selling.domain.model.SellingItems;
import com.exa.hexagonal.selling.infrastructure.mappers.SellingMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/selling")
public class SellingController {
    private final SellingService sellingService;
    private final SellingMapper sellingMapper;
    private final MessageResponse messageResponse = new MessageResponse();

    public SellingController(SellingService sellingService, SellingMapper sellingMapper) {
        this.sellingMapper = sellingMapper;
        this.sellingService = sellingService;
    }

    @Operation(
            summary = "Create a new selling record",
            description = "Create a new selling transaction with selling items and customer details.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Selling request with products, quantities, and client information.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SellingRequest.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Create Selling Example",
                                            summary = "Example of creating a new selling record",
                                            description = "Provide the selling code, items, and client details for the new sale.",
                                            value = "{\n" +
                                                    "  \"sellingItemsRequests\": [\n" +
                                                    "    {\n" +
                                                    "      \"product\": {\n" +
                                                    "        \"id\": 1,\n" +
                                                    "        \"code\": \"PT001TECH\",\n" +
                                                    "        \"name\": \"Mouse keyboard\",\n" +
                                                    "        \"description\": \"This mouse is black\",\n" +
                                                    "        \"mark\": \"SONY\",\n" +
                                                    "        \"supplierPrice\": 12.36,\n" +
                                                    "        \"iva\": 12,\n" +
                                                    "        \"percentageIncrement\": 35,\n" +
                                                    "        \"publicPrice\": 18.69,\n" +
                                                    "        \"quantity\": 10\n" +
                                                    "      },\n" +
                                                    "      \"quantity\": 5\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "      \"product\": {\n" +
                                                    "        \"id\": 2,\n" +
                                                    "        \"code\": \"PT002TECH\",\n" +
                                                    "        \"name\": \"Laptop Dell Inspiron 5567 16 GB RAM\",\n" +
                                                    "        \"description\": \"This computer is on stock\",\n" +
                                                    "        \"mark\": \"DELL\",\n" +
                                                    "        \"supplierPrice\": 1102.57,\n" +
                                                    "        \"iva\": 12,\n" +
                                                    "        \"percentageIncrement\": 35,\n" +
                                                    "        \"publicPrice\": 1667.09,\n" +
                                                    "        \"quantity\": 15\n" +
                                                    "      },\n" +
                                                    "      \"quantity\": 2\n" +
                                                    "    }\n" +
                                                    "  ],\n" +
                                                    "  \"client\": {\n" +
                                                    "    \"id\": 15,\n" +
                                                    "    \"identification\": \"1003938471\",\n" +
                                                    "    \"firstname\": \"Mario Andres\",\n" +
                                                    "    \"lastname\": \"Salazar Anrango\",\n" +
                                                    "    \"email\": \"mariosalazar.ms.10@gmail.com\",\n" +
                                                    "    \"address\": \"El Tejar - Ibarra\",\n" +
                                                    "    \"phone\": \"099456325\"\n" +
                                                    "  }\n" +
                                                    "}"
                                    )
                            }
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Selling record created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Selling.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request - Invalid input data",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Error Response",
                                                    summary = "Invalid request data",
                                                    description = "Occurs when the provided data does not meet the required schema.",
                                                    value = "{\n\"error\": \"Bad request\",\n\"message\": \"Invalid input data\"\n}"
                                            )
                                    }
                            )
                    )
            }
    )
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> createSelling(@RequestBody SellingRequest sellingRequest) {
        try {
            Selling sellingCreated = sellingService.createSelling(sellingMapper.fromSellingRequestToSelling(sellingRequest));
            messageResponse.setObject(sellingCreated);
            messageResponse.setMessage("Selling created successfully!");
            return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
        } catch (DataAccessException exception) {
            throw new BadRequestException("Error while creating selling: " + exception.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> updateSelling(@PathVariable(name = "id") Long id, @RequestBody SellingRequest sellingRequest) {
        try {
            Selling selling = sellingService.updateSelling(id, sellingMapper.fromSellingRequestToSelling(sellingRequest)).orElse(null);
            if (selling == null) {
                throw new ResourceNotFoundException("selling", "id", id);
            }
            messageResponse.setObject(selling);
            messageResponse.setMessage("Selling updated successfully!");
            return new ResponseEntity<>(messageResponse, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new BadRequestException("Failed to updated selling: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> findSellingById(@PathVariable(name = "id") Long id) {
        Optional<Selling> sellingFound = sellingService.findSellingById(id);
        return sellingFound.map(
                selling -> {
                    messageResponse.setObject(selling);
                    messageResponse.setMessage("Selling found successfully!");
                    return new ResponseEntity<>(messageResponse, HttpStatus.OK);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("selling", "id", id));
    }

    @GetMapping("/products/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> findProductsBySellingId(@PathVariable(name = "id") Long id) {
        Optional<Selling> sellingFound = sellingService.findSellingById(id);
        return sellingFound.map(selling -> {
            List<ProductBySellingResponse> products = selling.getSellingItems().stream()
                    .map(item -> {
                        ProductBySellingResponse product = new ProductBySellingResponse();
                        product.setCode(item.getProduct().getCode());
                        product.setName(item.getProduct().getName());
                        product.setQuantity(item.getProduct().getQuantity());
                        product.setPublicPrice(item.getProduct().getPublicPrice());
                        product.setDescription(item.getProduct().getDescription());
                        product.setIva(item.getProduct().getIva());
                        product.setMark(item.getProduct().getMark());
                        product.setSellingId(selling.getId());
                        product.setSupplierPrice(item.getProduct().getSupplierPrice());
                        product.setPercentageIncrement(item.getProduct().getPercentageIncrement());
                        return product;
                    })
                    .collect(Collectors.toList());
            messageResponse.setMessage("Selling found successfully!");
            messageResponse.setObject(products);
            return new ResponseEntity<>(messageResponse, HttpStatus.OK);
        }).orElseThrow(()->new ResourceNotFoundException("selling", "id", id));
    }


    @GetMapping("/greater-selling")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> findGreaterSelling() {
        List<Selling> sellingList = sellingService.findAllSelling();
        Optional<Selling> highestSelling = sellingList.stream()
                .max(Comparator.comparing(Selling::getTotal));
        if (highestSelling.isPresent()) {
            GreaterOrLessSellingResponse greaterSellingResponse = new GreaterOrLessSellingResponse();
            Selling selling = highestSelling.get();
            greaterSellingResponse.setSellingId(selling.getId());
            greaterSellingResponse.setSellingCode(selling.getSellingCode());
            greaterSellingResponse.setSellingDate(selling.getSellingDate());
            greaterSellingResponse.setFirstname(selling.getClient().getFirstname());
            greaterSellingResponse.setLastname(selling.getClient().getLastname());
            greaterSellingResponse.setTotal(selling.getTotal());
            greaterSellingResponse.setQuantityProducts(selling.getSellingItems().stream().mapToInt(SellingItems::getQuantity).sum());
            messageResponse.setMessage("Greater selling found successfully!");
            messageResponse.setObject(greaterSellingResponse);
            return new ResponseEntity<>(messageResponse, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("greater selling");
        }
    }

    @GetMapping("/smaller-selling")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> findSmallerSelling() {
        List<Selling> sellingList = sellingService.findAllSelling();
        Optional<Selling> highestSelling = sellingList.stream()
                .min(Comparator.comparing(Selling::getTotal));
        if (highestSelling.isPresent()) {
            GreaterOrLessSellingResponse greaterSellingResponse = new GreaterOrLessSellingResponse();
            Selling selling = highestSelling.get();
            greaterSellingResponse.setSellingId(selling.getId());
            greaterSellingResponse.setSellingCode(selling.getSellingCode());
            greaterSellingResponse.setSellingDate(selling.getSellingDate());
            greaterSellingResponse.setFirstname(selling.getClient().getFirstname());
            greaterSellingResponse.setLastname(selling.getClient().getLastname());
            greaterSellingResponse.setTotal(selling.getTotal());
            greaterSellingResponse.setQuantityProducts(selling.getSellingItems().stream().mapToInt(SellingItems::getQuantity).sum());
            messageResponse.setMessage("Smaller selling found successfully!");
            messageResponse.setObject(greaterSellingResponse);
            return new ResponseEntity<>(messageResponse, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("smaller selling");
        }
    }

    @GetMapping("/amount/{selling_date}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> findTotalAmountSellingByDate(
            @PathVariable(name = "selling_date") LocalDate sellingDate) {
        List<Selling> sellingList = sellingService.FindAllSellingByDate(sellingDate);
        if(sellingList==null || sellingList.isEmpty()){
            throw new ResourceNotFoundException("selling", "sellingDate", sellingDate);
        }
        BigDecimal totalAmount = sellingList.stream()
                .map(Selling::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        int quantitySelling = sellingList.size();
        TotalSellingResponse totalSellingResponse = new TotalSellingResponse();
        totalSellingResponse.setTotalAmount(totalAmount);
        totalSellingResponse.setQuantitySelling(quantitySelling);
        totalSellingResponse.setDate(sellingDate);
        messageResponse.setMessage("Total selling found successfully!");
        messageResponse.setObject(totalSellingResponse);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> findAllSelling() {
        List<Selling> sellingList = sellingService.findAllSelling();
        if(sellingList==null || sellingList.isEmpty()){
            throw new ResourceNotFoundException("selling");
        }
        messageResponse.setMessage("Selling found successfully!");
        messageResponse.setObject(sellingList);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> deleteSelling(@PathVariable(name = "id") Long id) {
        if (sellingService.deleteSelling(id)) {
            messageResponse.setMessage("Selling deleted successfully!");
            messageResponse.setObject(null);
            return new ResponseEntity<>(messageResponse,HttpStatus.NO_CONTENT);
        } else {
            throw new ResourceNotFoundException("selling", "id", id);
        }
    }
}
