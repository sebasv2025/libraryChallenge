package com.challenge.library.user;

public record UserRequest (String identificacionUsuario,
                           String userName,
                           int activeLoans,
                           int tipoUsuario){
}
