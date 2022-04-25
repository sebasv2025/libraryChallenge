package com.challenge.library.model.loan;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Getter
public class Response {

    private String mensaje;
    private int id;
    final HttpStatus Status;
    private LocalDate fechaMaximaDevolucion;

    public Response(String mensaje, HttpStatus status){
        this.mensaje = mensaje;
        this.Status = status;
    }
    public Response(Loan loan, HttpStatus status){
        this.id = loan.id;
        this.fechaMaximaDevolucion = loan.fechaMaximaDevolucion;
        this.Status = status;
    }

}
