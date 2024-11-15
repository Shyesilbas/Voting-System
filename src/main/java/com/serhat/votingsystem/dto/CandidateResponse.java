package com.serhat.votingsystem.dto;

import com.serhat.votingsystem.entity.Gender;

import java.time.LocalDate;

public record CandidateResponse(
        Integer id,
        String name,
        String surname,
        LocalDate birthday,
        Gender gender,
        String party
) {
}
