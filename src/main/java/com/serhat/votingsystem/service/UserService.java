package com.serhat.votingsystem.service;

import com.serhat.votingsystem.dto.UserResponse;
import com.serhat.votingsystem.entity.User;
import com.serhat.votingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

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
                .orElseThrow(() -> new RuntimeException("User with name " + name + " not found"));
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getHasVoted(),
                user.getAbleToVote()
        );
    }


}
