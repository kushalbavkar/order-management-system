package com.kb.common.dto.products;

import com.kb.common.dto.MandatoryFieldsValidator;
import com.kb.common.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;

public record ProductsDto(
        String name,
        Long price,
        Boolean inStock
) implements MandatoryFieldsValidator {

    @Override
    public void validate() {
        final List<String> errors = new ArrayList<>();

        if (name == null || name.isBlank()) errors.add("userId must not be null or blank");
        if (price == null) errors.add("price must not be null");
        if (inStock == null) errors.add("inStock must not be null");

        if (!errors.isEmpty()) {
            throw new ValidationException(String.join(", ", errors));
        }
    }
}
