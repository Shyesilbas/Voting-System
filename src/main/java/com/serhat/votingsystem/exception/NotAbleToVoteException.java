package com.serhat.votingsystem.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotAbleToVoteException extends RuntimeException {
    public NotAbleToVoteException(String s) {
        super(s);
    }
}
