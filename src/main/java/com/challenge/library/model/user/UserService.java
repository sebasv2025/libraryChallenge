package com.challenge.library.model.user;

import org.springframework.stereotype.Service;

@Service
public record UserService(UserRepository userRepository) {
    public void registerUser(UserRequest request) {
        User user = User.builder()
                .identificacionUsuario(request.identificacionUsuario())
                .activeLoans(request.activeLoans())
                .UserName(request.userName())
                .build();

            userRepository.save(user);
    }
}
