package com.serhat.votingsystem.dto;

import java.util.List;

public record ResultResponse(
        int totalAttendance,
        int expectedAttendance,
        String attendanceRate,

        List<CandidateResultResponse> candidateResultResponse

) {
}
