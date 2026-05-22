package com.app.proseth_phoneshop.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
public class ErrorRespones {
    private HttpStatus status;
    private  String message;
}
