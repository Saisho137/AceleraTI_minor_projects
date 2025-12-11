package com.arca.spring_data.domain.exception;

import java.io.Serial;

public class BusinessRuleException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public BusinessRuleException(String message) {
        super(message);
    }
}
