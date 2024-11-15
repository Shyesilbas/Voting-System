package com.serhat.votingsystem.dto;

import com.serhat.votingsystem.entity.AbleToVote;
import com.serhat.votingsystem.entity.HasVoted;

public record UserResponse(
        Integer id,
        String name,
        String surname,
        HasVoted hasVoted,
        AbleToVote ableToVote
) {
}
