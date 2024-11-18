package com.serhat.votingsystem.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VoteNotFoundException extends RuntimeException {
    public VoteNotFoundException(String s) {
        super(s);
    }
}
