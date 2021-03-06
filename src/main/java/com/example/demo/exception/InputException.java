package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InputException extends RuntimeException {
    private String message;
    public InputException(String message){
        super(message);
    }

    public InputException(String message,Throwable cause){
        super(message,cause);
    }
}
