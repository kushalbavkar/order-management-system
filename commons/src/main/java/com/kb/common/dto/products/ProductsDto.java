package com.kb.common.dto.products;

public record ProductsDto(
        String name,
        Long price,
        Boolean inStock
) {}
