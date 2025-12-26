package com.ecommarce.order.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handlerException(EntityNotFoundException customerNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerNotFoundException.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handlerException(BusinessException customerNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerNotFoundException.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerException(MethodArgumentNotValidException customerNotFoundException){
        var errorMap = new HashMap<String,String>();
        customerNotFoundException.getBindingResult().getAllErrors().forEach(error -> {
                    var field = ((FieldError) error).getField();
                    var message = error.getDefaultMessage();
                    errorMap.put(field,message);
                }
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorMap));
    }
}
