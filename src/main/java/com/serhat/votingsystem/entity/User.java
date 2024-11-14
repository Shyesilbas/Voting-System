package com.serhat.votingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "voter")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    @Column(name = "birth_date",nullable = false)
    private LocalDate birthDate;

    @Column(name = "personal_id",nullable = false,unique = true)
    private Long personalId;

    @Enumerated(EnumType.STRING)
    @Column(name = "able_to_vote",nullable = false)
    private AbleToVote ableToVote;

    @Enumerated(EnumType.STRING)
    @Column(name = "has_voted",nullable = false)
    private HasVoted hasVoted;


    @PrePersist
    @PreUpdate
    public void calculateAbleToVote(){
        if(birthDate != null){
            long age = calculateAge(birthDate);
            this.ableToVote = (age >= 18 * 365) ? AbleToVote.YES : AbleToVote.NO;
        }
    }

    private long calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return ChronoUnit.DAYS.between(birthDate, currentDate);
    }




}
