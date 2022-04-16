package com.challenge.library.model;

import lombok.Getter;

@Getter

public class User {

    String name;
    String identificacionUsuario;
    int activeLoans;
    int tipoUsuario;

    public User(String name, String identificacionUsuario, int tipoUsuario){
        this.name = name;
        this.identificacionUsuario = identificacionUsuario;
        this.tipoUsuario = tipoUsuario;
        activeLoans = 0;
    }
}
