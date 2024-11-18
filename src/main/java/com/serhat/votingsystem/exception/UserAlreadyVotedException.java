package com.serhat.votingsystem.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserAlreadyVotedException extends RuntimeException {
    public UserAlreadyVotedException(String s) {
        super(s);
    }
}
