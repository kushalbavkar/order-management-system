package com.kb.common.dto.users;

import com.kb.common.dto.MandatoryFieldsValidator;
import com.kb.common.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;

public record UsersDto(
        String userName,
        String email
) implements MandatoryFieldsValidator {

    @Override
    public void validate() {
        final List<String> errors = new ArrayList<>();

        if (userName == null || userName.isBlank()) errors.add("userName must not be null or blank");
        if (email == null || email.isBlank()) errors.add("email must not be null or blank");

        if (!errors.isEmpty()) {
            throw new ValidationException(String.join(", ", errors));
        }
    }
}
