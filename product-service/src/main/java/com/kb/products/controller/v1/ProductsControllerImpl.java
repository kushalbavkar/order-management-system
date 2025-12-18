package com.kb.products.controller.v1;

import com.kb.common.dto.products.ProductsDto;
import com.kb.common.dto.products.ProductsResponseDto;
import com.kb.products.controller.ProductsController;

import com.kb.products.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductsControllerImpl implements ProductsController {
    private final ProductsService productsService;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductsResponseDto>> getProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productsService.getAllProducts());
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductsResponseDto> getProductById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(productsService.getProductById(id));
    }

    @Override
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductsResponseDto> saveProduct(@RequestBody ProductsDto product) {
        product.validate();
        return ResponseEntity.status(HttpStatus.CREATED).body(productsService.saveProduct(product));
    }

    @Override
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductsResponseDto> updateProduct(@RequestBody ProductsDto product, @PathVariable("id") Integer id) {
        product.validate();
        return ResponseEntity.status(HttpStatus.OK).body(productsService.updateProductById(product, id));
    }

    @Override
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {
        productsService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
