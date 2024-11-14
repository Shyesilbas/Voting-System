package com.serhat.votingsystem.component;

import com.serhat.votingsystem.service.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeycloakUserLoader implements CommandLineRunner {
    private final KeycloakUserService keycloakUserService;

    @Override
    public void run(String... args) throws Exception {
        keycloakUserService.addUsersToKeycloak();
    }

}
