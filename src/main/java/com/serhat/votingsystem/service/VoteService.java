package com.serhat.votingsystem.service;

import com.serhat.votingsystem.entity.*;
import com.serhat.votingsystem.repository.CandidateRepository;
import com.serhat.votingsystem.repository.UserRepository;
import com.serhat.votingsystem.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoteService {
    private final VoteRepository voteRepository;
    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;

    @Transactional
    public VoteResponse castVote(String userName, Integer candidateId) {
        String lowercaseUsername = userName.toLowerCase();

        User user = userRepository.findByNameIgnoreCase(lowercaseUsername)
                .orElseThrow(() -> new RuntimeException("User with name " + userName + " not found"));

        log.info("User voting: {} {}", user.getName(), user.getSurname());

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate with ID " + candidateId + " not found"));

        if (user.getHasVoted().equals(HasVoted.YES)) {
            throw new RuntimeException("This User has already voted!");
        }
        if (user.getAbleToVote().equals(AbleToVote.NO)) {
            throw new RuntimeException("This User is not able to vote!");
        }

        Vote vote = Vote.builder()
                .user(user)
                .candidate(candidate)
                .voteDate(LocalDateTime.now())
                .build();

        voteRepository.save(vote);
        candidate.setVotesReceived(candidate.getVotesReceived() + 1);
        candidateRepository.save(candidate);

        user.setHasVoted(HasVoted.YES);
        userRepository.save(user);


        return new VoteResponse(
                user.getId(),
                user.getName(),
                LocalDateTime.now(),
                candidateId,
                candidate.getName(),
                candidate.getParty()
        );

    }



}
