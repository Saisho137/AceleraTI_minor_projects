package com.arca.spring_data.domain.constants;

public final class ErrorMessages {

    private ErrorMessages() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final String RESOURCE_NOT_FOUND = "%s not found with id: %d";
    public static final String USER_NOT_STUDENT = "User with id %d is not a student";
    public static final String USER_NOT_TEACHER = "User with id %d is not a teacher";
    public static final String GRADE_CANNOT_BE_NULL = "Grade cannot be null";
    public static final String INVALID_GRADE_RANGE = "Invalid grade: %.2f. Grade must be between 0.0 and 5.0";
}
