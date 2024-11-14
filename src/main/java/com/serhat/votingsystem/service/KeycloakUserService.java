package com.serhat.votingsystem.service;

import com.serhat.votingsystem.entity.User;
import com.serhat.votingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeycloakUserService {
    private final Keycloak keycloak;
    private final UserRepository userRepository;

    @Transactional
    public void addUsersToKeycloak() {
        List<User> users = userRepository.findAll();

        users.forEach(user -> {

            UserRepresentation keycloakUser = new UserRepresentation();
            keycloakUser.setUsername(user.getName());
            keycloakUser.setEnabled(true);

            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setTemporary(false);
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(String.valueOf(user.getPersonalId()));

            keycloakUser.setCredentials(Collections.singletonList(credential));

            keycloak.realm("votingSystem")
                    .users()
                    .create(keycloakUser);
        });
    }

}
