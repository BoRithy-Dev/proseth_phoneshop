package com.app.proseth_phoneshop.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundExceptions extends  ApiExceptions{
    public ResourceNotFoundExceptions(String resourceName, Long id) {
        super(HttpStatus.NOT_FOUND, String.format("%s with id = %d not fund",resourceName,id));
    }
}
