package com.serhat.votingsystem.repository;

import com.serhat.votingsystem.entity.User;
import com.serhat.votingsystem.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository  extends JpaRepository<Vote,Integer> {
}
