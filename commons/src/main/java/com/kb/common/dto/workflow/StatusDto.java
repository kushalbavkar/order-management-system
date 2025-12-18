package com.kb.common.dto.workflow;

import com.kb.common.constants.orders.OrderStatus;
import com.kb.common.dto.MandatoryFieldsValidator;
import com.kb.common.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;

public record StatusDto(OrderStatus status) implements MandatoryFieldsValidator {

    @Override
    public void validate() {
        final List<String> errors = new ArrayList<>();

        if (status == null) errors.add("status must not be null");

        if (!errors.isEmpty()) {
            throw new ValidationException(String.join(", ", errors));
        }
    }
}
