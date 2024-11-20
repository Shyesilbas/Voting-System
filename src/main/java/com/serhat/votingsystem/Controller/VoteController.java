package com.serhat.votingsystem.Controller;

import com.serhat.votingsystem.dto.ResultResponse;
import com.serhat.votingsystem.dto.VoteRequest;
import com.serhat.votingsystem.entity.VoteResponse;
import com.serhat.votingsystem.service.VoteService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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

    @GetMapping("/results")
    public ResponseEntity<ResultResponse> displayElectionResult(){
        return ResponseEntity.ok(voteService.resultInformation());
    }

    @GetMapping("/allVotes")
    @RolesAllowed("ADMIN")
    public ResponseEntity<List<VoteResponse>> allVotes(){
        return ResponseEntity.ok(voteService.allVotes());
    }


}
