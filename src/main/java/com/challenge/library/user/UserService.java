package com.challenge.library.user;

import org.springframework.stereotype.Service;

@Service
public record UserService(UserRepository userRepository) {
    public void registerUser(UserRequest request) {
        User user = new User(request.userName(), request.identificacionUsuario(), request.tipoUsuario());
            userRepository.save(user);
    }
}
