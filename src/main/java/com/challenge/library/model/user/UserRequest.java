package com.challenge.library.model.user;

public record UserRequest (String identificacionUsuario,
                           String userName,
                           int activeLoans,
                           int tipoUsuario){
}
