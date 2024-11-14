package com.serhat.votingsystem.repository;

import com.serhat.votingsystem.entity.Candidate;
import com.serhat.votingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository  extends JpaRepository<Candidate,Integer> {
}
