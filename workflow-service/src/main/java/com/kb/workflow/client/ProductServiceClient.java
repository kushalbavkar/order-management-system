package com.kb.workflow.client;

import com.kb.common.dto.products.ProductsDto;
import com.kb.common.dto.products.ProductsResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "productName-service",
        url = "${services.product-service.url}"
)
public interface ProductServiceClient {
    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductsResponseDto>> getProducts();

    @GetMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductsResponseDto> getProductById(@PathVariable("id") Integer id);

    @PostMapping(value = "/products/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductsResponseDto> saveProduct(@RequestBody ProductsDto product);

    @PutMapping(value = "/products/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductsResponseDto> updateProduct(@RequestBody ProductsDto product, @PathVariable("id") Integer id);

    @DeleteMapping(value = "/products/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id);
}
