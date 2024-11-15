package com.serhat.votingsystem.Controller;

import com.serhat.votingsystem.dto.CandidateResponse;
import com.serhat.votingsystem.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/candidates")
public class CandidateController {
    private final CandidateService service;

    @GetMapping
    public ResponseEntity<List<CandidateResponse>> findAllCandidates(){
        return ResponseEntity.ok(service.findAllCandidates());
    }

}
