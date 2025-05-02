package com.example.mtb.exceptions;

import lombok.Getter;

@Getter
public class ShowTimeConflictException extends RuntimeException {

    private String message;

    public ShowTimeConflictException(String message) {
        this.message = message;
    }
}
