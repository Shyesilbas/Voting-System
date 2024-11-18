package com.serhat.votingsystem.service;

import com.serhat.votingsystem.dto.UserResponse;
import com.serhat.votingsystem.entity.Candidate;
import com.serhat.votingsystem.entity.User;
import com.serhat.votingsystem.entity.Vote;
import com.serhat.votingsystem.entity.VoteResponse;
import com.serhat.votingsystem.exception.UserNotFoundException;
import com.serhat.votingsystem.exception.VoteNotFoundException;
import com.serhat.votingsystem.repository.CandidateRepository;
import com.serhat.votingsystem.repository.UserRepository;
import com.serhat.votingsystem.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final VoteRepository voteRepository;
    private final CandidateRepository candidateRepository;


    public List<UserResponse> findAllUsers(){
        return repository.findAll()
                .stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getSurname(),
                        user.getHasVoted(),
                        user.getAbleToVote()
                ))
                .toList();
    }

    public UserResponse findUser(String name){
        String lowercaseUsername = name.toLowerCase();

        User user = repository.findByNameIgnoreCase(lowercaseUsername)
                .orElseThrow(() -> new UserNotFoundException("User with name " + name + " not found"));
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getHasVoted(),
                user.getAbleToVote()
        );
    }


    public VoteResponse voteInformation(String name){
        String lowercaseUsername = name.toLowerCase();

        User user = repository.findByNameIgnoreCase(lowercaseUsername)
                .orElseThrow(()-> new UserNotFoundException("User Not Found"));

        Vote vote = voteRepository.findByUserName(user.getName())
                .orElseThrow(()-> new VoteNotFoundException("Vote not found for : "+name));

        return new VoteResponse(
                user.getId(),
                user.getName(),
                vote.getVoteDate(),
                vote.getCandidate().getId(),
                vote.getCandidate().getName(),
                vote.getCandidate().getParty()
        );
    }


}
