package com.nitesh.expense.service.exceptions;


import com.nitesh.expense.service.dto.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {
    private final List<ErrorDetail> errors;

    public ValidationException(String message,
                               List<ErrorDetail> errors) {
        super(message);
        this.errors = errors;
    }

    public List<ErrorDetail> getErrors() {
        return errors;
    }
}
