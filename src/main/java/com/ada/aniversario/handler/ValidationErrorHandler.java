package com.ada.aniversario.handler;

import com.ada.aniversario.validation.dto.ValidationErrorsOutputDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorsOutputDto handlerValidationError(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        ValidationErrorsOutputDto validationErrors = new ValidationErrorsOutputDto();
        fieldErrors.forEach(error -> validationErrors.addFieldError(error.getField(), error.getDefaultMessage()));
        return validationErrors;
    }
}
