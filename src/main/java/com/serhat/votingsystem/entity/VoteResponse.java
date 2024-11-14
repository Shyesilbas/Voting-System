package com.serhat.votingsystem.entity;

import java.time.LocalDateTime;

public record VoteResponse(
        Integer userId,
        String userName,
        LocalDateTime time,
        Integer candidateId,
        String candidateName,
        String party
) {
}
