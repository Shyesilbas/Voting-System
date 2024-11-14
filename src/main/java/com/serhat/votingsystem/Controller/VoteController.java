package com.serhat.votingsystem.Controller;

import com.serhat.votingsystem.entity.VoteResponse;
import com.serhat.votingsystem.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/votes")
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/cast")
    public ResponseEntity<VoteResponse> castVote(@RequestParam Integer userId , @RequestParam Integer candidateId){
        return ResponseEntity.ok(voteService.castVote(userId,candidateId));
    }


}
