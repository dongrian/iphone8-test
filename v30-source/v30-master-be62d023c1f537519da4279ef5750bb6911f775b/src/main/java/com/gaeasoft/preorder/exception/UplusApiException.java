/*
 * Copyright (c) 2016 gaeasoft.co.kr to Present.
 * All rights reserved.
 */
package com.gaeasoft.preorder.exception;

import com.gaeasoft.preorder.remote.vo.Header;

public class UplusApiException extends RuntimeException {
    
    /**
     * Unique ID for Serialized object
     */
    private static final long serialVersionUID = 3060242153277122860L;

    public UplusApiException(Header response) {
        super(response.getResponseMessage() + "(code=" + response.getResponseCode() + ")");
    }

    public UplusApiException(String message, Throwable cause) {
    	super(message, cause);
    }
    
    public UplusApiException(Throwable cause) {
        super(cause);
    }
    
    public UplusApiException(String message) {
        super(message);
    }
}
