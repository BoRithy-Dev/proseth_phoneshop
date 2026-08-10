package com.app.proseth_phoneshop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobleExceptionsHandler {
    @ExceptionHandler(ApiExceptions.class)
    public ResponseEntity<?> handlerExceptionApi(ApiExceptions e){
        ErrorRespones errorRespones = new ErrorRespones(e.getStatus(), e.getMessage());
        return ResponseEntity
                .status(e.getStatus())
                .body(errorRespones);
    }
}
