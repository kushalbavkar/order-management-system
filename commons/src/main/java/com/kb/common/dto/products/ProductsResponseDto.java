package com.kb.common.dto.products;

import java.math.BigDecimal;

public record ProductsResponseDto(
    Integer id,
    String name,
    BigDecimal price,
    Boolean inStock
){}
