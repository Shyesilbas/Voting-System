package com.serhat.votingsystem.service;

import com.serhat.votingsystem.dto.CandidateResponse;
import com.serhat.votingsystem.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;

    public List<CandidateResponse> findAllCandidates(){
      return  candidateRepository.findAll()
                .stream()
                .map(candidate -> new CandidateResponse(
                        candidate.getId(),
                        candidate.getName(),
                        candidate.getSurname(),
                        candidate.getBirthDate(),
                        candidate.getGender(),
                        candidate.getParty()
                        ))
                .toList();


    }
}
