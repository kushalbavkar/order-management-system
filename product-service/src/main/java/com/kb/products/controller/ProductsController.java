package com.kb.products.controller;

import com.kb.common.dto.products.ProductsDto;
import com.kb.common.dto.products.ProductsResponseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProductsController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductsResponseDto>> getProducts();

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductsResponseDto> getProductById(@PathVariable("id") Integer id);

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_NDJSON_VALUE)
    ResponseEntity<ProductsResponseDto> saveProduct(@RequestBody ProductsDto product);

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_NDJSON_VALUE)
    ResponseEntity<ProductsResponseDto> updateProduct(@RequestBody ProductsDto product, @PathVariable("id") Integer id);

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_NDJSON_VALUE)
    ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id);
}
