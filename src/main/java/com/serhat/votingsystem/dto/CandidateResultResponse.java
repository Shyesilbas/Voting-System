package com.serhat.votingsystem.dto;

public record CandidateResultResponse(
        String name,
        String party,
        Long votesReceived,
        String percent
) {
}
