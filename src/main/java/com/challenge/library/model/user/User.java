package com.challenge.library.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Getter
@Entity(name = "libraryUser")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )
    Integer id;
    String identificacionUsuario;
    String UserName;
    int activeLoans;
    int tipoUsuario;

    public User(String UserName, String identificacionUsuario, int tipoUsuario){
       if (isUserAllowed(identificacionUsuario)){
            this.UserName = UserName;
            this.identificacionUsuario = identificacionUsuario;
            this.tipoUsuario = tipoUsuario;
            activeLoans = 0;
        }else{
           throw new UserException("User is not allowed because the ID has more than 10 characters");
       }
    }

    private boolean isUserAllowed(String identificacionUsuario){
        return identificacionUsuario.length() <= 10;
    }
}
