package com.mycompany.propertymanagement.exception;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bEx) {
        return new ResponseEntity<>(bEx.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException mavEx){
        List<FieldError> fieldErrors = mavEx.getBindingResult().getFieldErrors();

        List<ErrorModel> errorModels = fieldErrors.stream()
                .map(fieldError -> new ErrorModel(fieldError.getCode(), fieldError.getDefaultMessage()))
                .toList();

        return new ResponseEntity<>(errorModels, HttpStatus.BAD_REQUEST);
    }
}
