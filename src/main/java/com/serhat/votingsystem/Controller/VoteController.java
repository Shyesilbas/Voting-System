package com.serhat.votingsystem.Controller;

import com.serhat.votingsystem.dto.VoteRequest;
import com.serhat.votingsystem.entity.VoteResponse;
import com.serhat.votingsystem.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/votes")
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/cast")
    public ResponseEntity<VoteResponse> castVote(Principal principal, @RequestBody VoteRequest voteRequest){
        String userName = principal.getName();
        return ResponseEntity.ok(voteService.castVote(userName, voteRequest.candidateId()));
    }
}
