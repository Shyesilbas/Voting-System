package com.serhat.votingsystem.service;

import com.serhat.votingsystem.dto.CandidateResultResponse;
import com.serhat.votingsystem.dto.ResultResponse;
import com.serhat.votingsystem.entity.*;
import com.serhat.votingsystem.exception.CandidateNotFoundException;
import com.serhat.votingsystem.exception.NotAbleToVoteException;
import com.serhat.votingsystem.exception.UserAlreadyVotedException;
import com.serhat.votingsystem.exception.UserNotFoundException;
import com.serhat.votingsystem.repository.CandidateRepository;
import com.serhat.votingsystem.repository.UserRepository;
import com.serhat.votingsystem.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
                .orElseThrow(() -> new UserNotFoundException("User with name " + userName + " not found"));

        log.info("User voting: {} {}", user.getName(), user.getSurname());

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate with ID " + candidateId + " not found"));

        if (user.getHasVoted().equals(HasVoted.YES)) {
            throw new UserAlreadyVotedException(userName + ", you Already Voted!");
        }
        if (user.getAbleToVote().equals(AbleToVote.NO)) {
            throw new NotAbleToVoteException(userName +", Unfortunately you are not able to vote");
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

    public ResultResponse resultInformation(){
        List<User> eligibleUser = userRepository.findAll()
                .stream()
                .filter(user -> user.getAbleToVote().equals(AbleToVote.YES))
                .toList();

        int expectedAttendance = eligibleUser.size();

        List<User> votedUsers = userRepository.findAll()
                .stream()
                .filter(user -> user.getHasVoted().equals(HasVoted.YES))
                .toList();

        int totalAttendance = votedUsers.size();

        double attendanceRate = ((double) totalAttendance / expectedAttendance) * 100;
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedAttendanceRate ="%" + df.format(attendanceRate);




        List<CandidateResultResponse> candidateResults = candidateRepository.findAll()
                .parallelStream()
                .map(candidate -> {
                    double percentage = ((double) candidate.getVotesReceived() / totalAttendance) * 100;

                    return new CandidateResultResponse(
                            candidate.getName(),
                            candidate.getParty(),
                            candidate.getVotesReceived(),
                            "%" + new DecimalFormat("#.00").format(percentage)
                    );
                })
                .toList();



        return new ResultResponse(
                totalAttendance,
                expectedAttendance,
                formattedAttendanceRate,
                candidateResults
        );
    }


}
