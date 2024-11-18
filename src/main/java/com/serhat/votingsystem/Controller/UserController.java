package com.serhat.votingsystem.Controller;

import com.serhat.votingsystem.dto.UserResponse;
import com.serhat.votingsystem.entity.VoteResponse;
import com.serhat.votingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/allUsers")
    public ResponseEntity<List<UserResponse>> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/byName")
    public ResponseEntity<UserResponse> findUserByName(Principal principal){
        String name = principal.getName();
        return ResponseEntity.ok(userService.findUser(name));
    }

    @GetMapping("/voteInfo")
    public ResponseEntity<VoteResponse> userVoteInfo(Principal principal){
        String name = principal.getName();
        return ResponseEntity.ok(userService.voteInformation(name));
    }

}
