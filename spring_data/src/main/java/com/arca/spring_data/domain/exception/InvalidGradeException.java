package com.arca.spring_data.domain.exception;

import java.io.Serial;

public class InvalidGradeException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidGradeException(String message) {
        super(message);
    }

    public InvalidGradeException(Double grade) {
        super(String.format("Invalid grade: %.2f. Grade must be between 0.0 and 5.0", grade));
    }
}
