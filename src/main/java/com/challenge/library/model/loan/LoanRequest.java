package com.challenge.library.model.loan;

import java.time.LocalDate;

public record LoanRequest(String isbn, String identificacionUsuario, int tipoUsuario, LocalDate fechaMaximaDevolucion) {
}
