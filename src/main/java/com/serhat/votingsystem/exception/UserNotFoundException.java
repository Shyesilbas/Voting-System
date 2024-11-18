package com.serhat.votingsystem.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message){
        super(message);
    }
}
