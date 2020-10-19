package com.bitmovin.trial.encodingapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User: sini_ann
 * Date: 17/10/20 12:45 pm
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomNotFoundException extends RuntimeException{

    public CustomNotFoundException(String exception) {
        super(exception);
    }
}
