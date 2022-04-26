package com.challenge.library.loan;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Getter
public class Response {
    final Integer id;
    final LocalDate fechaMaximaDevolucion;

    public Response(Loan loan){
        this.id = loan.id;
        this.fechaMaximaDevolucion = loan.fechaMaximaDevolucion;
    }

}
