package com.ecommarce.app.exception;

import com.ecommarce.app.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<String> handlerException(ProductPurchaseException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handlerException(ProductNotFoundException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
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
