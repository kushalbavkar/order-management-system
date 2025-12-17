package com.kb.products.repository.mapper;


import com.kb.common.dto.products.ProductsDto;
import com.kb.common.dto.products.ProductsResponseDto;
import com.kb.products.entity.Products;


public class EntityMapper {
    private EntityMapper() {
    }

    public static ProductsResponseDto entityToDto(final Products products) {
        return new ProductsResponseDto(
                products.getId(),
                products.getName(),
                products.getPrice(),
                products.getInStock()
        );
    }

    public static Products dtoToEntity(final ProductsDto products) {
        final Products entity = new Products();
        entity.setName(products.name());
        entity.setPrice(products.price());
        entity.setInStock(products.inStock());
        return entity;
    }

    public static Products updateEntity(final Products entity, final ProductsDto dto) {
        entity.setName(dto.name());
        entity.setPrice(dto.price());
        entity.setInStock(dto.inStock());
        return entity;
    }
}
