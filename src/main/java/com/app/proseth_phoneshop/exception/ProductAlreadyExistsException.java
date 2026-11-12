package com.app.proseth_phoneshop.exception;

import org.springframework.http.HttpStatus;

public class ProductAlreadyExistsException extends  ApiExceptions{
    public ProductAlreadyExistsException(Long modelId, Long colorId, String storage) {
        super(HttpStatus.CONFLICT, String.format("Product already exists with modelId=%d, colorId=%d, storage=%s", modelId, colorId, storage
        ));
    }

}
