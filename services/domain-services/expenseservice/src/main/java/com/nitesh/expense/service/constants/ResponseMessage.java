package com.nitesh.expense.service.constants;

public final class ResponseMessage {

    private ResponseMessage() {
        // Prevent instantiation
    }

    // Success messages
    public static final String SUCCESS = "Operation completed successfully.";
    public static final String CREATED = "Resource created successfully.";
    public static final String UPDATED = "Resource updated successfully.";
    public static final String DELETED = "Resource deleted successfully.";
    public static final String RETRIEVED = "Resource retrived successfully.";

    // Error messages
    public static final String ERROR_GENERIC = "An unexpected error occurred. Please try again later.";
    public static final String ERROR_NOT_FOUND = "Requested resource not found.";
    public static final String ERROR_BAD_REQUEST = "Invalid request parameters.";
    public static final String ERROR_UNAUTHORIZED = "You are not authorized to perform this action.";
    public static final String ERROR_FORBIDDEN = "Access is forbidden for the current user.";

    // Validation messages
    public static final String VALIDATION_FAILED = "Validation failed for the request.";
    public static final String FIELD_REQUIRED = "This field is required.";
    public static final String FIELD_INVALID = "Invalid value provided.";

    // Common labels / keys
    public static final String STATUS = "status";
    public static final String MESSAGE = "message";
    public static final String DATA = "data";
}
