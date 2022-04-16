package com.challenge.library.model;

import lombok.Getter;

@Getter

public class User {

    String name;
    String identificacionUsuario;
    int activeLoans;
    int tipoUsuario;

    public User(String name, String identificacionUsuario, int tipoUsuario){
       if (isUserAllowed(identificacionUsuario)){
            this.name = name;
            this.identificacionUsuario = identificacionUsuario;
            this.tipoUsuario = tipoUsuario;
            activeLoans = 0;
        }else{
           System.out.println("User is not allowed because the ID has more than 10 characters");
       }
    }

    private boolean isUserAllowed(String identificacionUsuario){
        return identificacionUsuario.length() <= 10;
    }
}
