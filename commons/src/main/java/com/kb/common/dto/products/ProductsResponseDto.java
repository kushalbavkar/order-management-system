package com.kb.common.dto.products;

public record ProductsResponseDto(
    Integer id,
    String name,
    Long price,
    Boolean inStock
){}
