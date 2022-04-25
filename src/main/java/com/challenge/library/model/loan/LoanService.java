package com.challenge.library.model.loan;

import com.challenge.library.model.user.User;
import com.challenge.library.model.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public record LoanService(LoanRepository loanRepository, UserRepository userRepository) {

    public Response registerLoan(LoanRequest request) {
        User user = userRepository.findByidentificacionUsuario(request.identificacionUsuario());
        Loan loan;
        loan = Loan.builder()
                .isbn(request.isbn())
                .identificacionUsuario(request.identificacionUsuario())
                .tipoUsuario(request.tipoUsuario())
                .build();

        try {
            switch (request.tipoUsuario()) {
                case 1:
                    loan.setFechaMaximaDevolucion(java.time.LocalDate.now().plusDays(10));
                    loanRepository.save(loan);
                    return new Response(loan, HttpStatus.OK);
                case 2:
                    loan.setFechaMaximaDevolucion(java.time.LocalDate.now().plusDays(8));
                    loanRepository.save(loan);
                    return new Response(loan, HttpStatus.FOUND);
                case 3:
                    if (user.getActiveLoans() > 0) {
                        return new Response("El usuario con identificación: " + request.identificacionUsuario() +
                                " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo", HttpStatus.BAD_REQUEST);
                    } else {
                        loan.setFechaMaximaDevolucion(java.time.LocalDate.now().plusDays(7));
                        loanRepository.save(loan);
                        return new Response(loan, HttpStatus.OK);
                    }
                default:
                    return new Response("Tipo de usuario no permitido en la biblioteca", HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (NullPointerException exception) {
            return new Response(loan, HttpStatus.FORBIDDEN);
        }
    }

}
