package com.serhat.votingsystem.repository;

import com.serhat.votingsystem.entity.Candidate;
import com.serhat.votingsystem.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository  extends JpaRepository<Vote,Integer> {
    Optional<Vote>findByUserName(String user);

}
