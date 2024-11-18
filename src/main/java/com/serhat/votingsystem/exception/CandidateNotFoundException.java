package com.serhat.votingsystem.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CandidateNotFoundException extends RuntimeException {
    public CandidateNotFoundException(String s) {
        super(s);
    }
}
