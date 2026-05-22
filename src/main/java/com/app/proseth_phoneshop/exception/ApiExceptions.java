package com.app.proseth_phoneshop.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@RequiredArgsConstructor
public class ApiExceptions  extends RuntimeException{
    private final HttpStatus status;
    private final String message;
}
