package com.challenge.library.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer> {
    User findByidentificacionUsuario(String name);
}
