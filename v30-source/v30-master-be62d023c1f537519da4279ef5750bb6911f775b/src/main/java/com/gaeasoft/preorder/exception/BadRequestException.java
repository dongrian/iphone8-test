/*
 * Copyright (c) 2016 gaeasoft.co.kr to Present.
 * All rights reserved.
 */
package com.gaeasoft.preorder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    
    /**
     * Unique ID for Serialized object
     */
    private static final long serialVersionUID = -1;

    public BadRequestException(String message) {
        super(message);
    }
}
