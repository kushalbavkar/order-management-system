package com.kb.products.service;

import com.kb.common.dto.products.ProductsDto;
import com.kb.common.dto.products.ProductsResponseDto;
import com.kb.products.exceptions.ProductsException;
import com.kb.products.repository.ProductsRepository;
import com.kb.products.repository.mapper.EntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductsService {
    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<ProductsResponseDto> getAllProducts() {
        log.info("Getting all products");
        return productsRepository
                .findAll()
                .stream()
                .map(EntityMapper::entityToDto)
                .toList();
    }

    public ProductsResponseDto getProductById(final int id) {
        log.info("Getting product by id: {}", id);
        return productsRepository
                .findById(id)
                .map(EntityMapper::entityToDto)
                .orElseThrow(() -> new ProductsException("Missing product with id: " + id));
    }

    public ProductsResponseDto saveProduct(final ProductsDto product) {
        log.info("Saving product: {}", product);
        return Optional
                .of(EntityMapper.dtoToEntity(product))
                .map(productsRepository::save)
                .map(EntityMapper::entityToDto)
                .orElseThrow(() -> new ProductsException("Unable to save product: " + product));
    }

    public ProductsResponseDto updateProductById(final ProductsDto product, final int id) {
        log.info("Updating product with id: {}", id);
        return productsRepository
                .findById(id)
                .map(entity -> EntityMapper.updateEntity(entity, product))
                .map(productsRepository::save)
                .map(EntityMapper::entityToDto)
                .orElseThrow(() -> new ProductsException("Failed to update product with id: " + id));
    }

    public void deleteProductById(final int id) {
        log.info("Deleting product with id: {}", id);
        productsRepository
                .deleteById(id);
    }
}
