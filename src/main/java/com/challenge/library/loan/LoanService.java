package com.challenge.library.loan;

import com.challenge.library.exception.ApiRequestException;
import com.challenge.library.user.User;
import com.challenge.library.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;


@Service
public record LoanService(LoanRepository loanRepository, UserRepository userRepository) {

    public Response registerLoan(LoanRequest request) {
        LocalDate sysdate = java.time.LocalDate.now();
        User user = userRepository.findByidentificacionUsuario(request.identificacionUsuario());

        Optional<User> userOptional = Optional.ofNullable(user);

        Loan loan = Loan.builder()
                .isbn(request.isbn())
                .identificacionUsuario(request.identificacionUsuario())
                .tipoUsuario(request.tipoUsuario())
                .build();

        if (userOptional.isPresent()) {
            int amountLoans = user.getActiveLoans();
            try {
                switch (request.tipoUsuario()) {
                    case 1:
                        loan.setFechaMaximaDevolucion(addDaysSkippingWeekends(sysdate, 10));
                        user.setActiveLoans(amountLoans+1);
                        loanRepository.save(loan);
                        userRepository.save(user);
                        return new Response(loan);
                    case 2:
                        loan.setFechaMaximaDevolucion(addDaysSkippingWeekends(sysdate, 8));
                        user.setActiveLoans(amountLoans+1);
                        loanRepository.save(loan);
                        userRepository.save(user);
                        return new Response(loan);
                    case 3:
                        if (user.getActiveLoans() > 0) {
                            throw  new ApiRequestException("El usuario con identificación: " + request.identificacionUsuario() +
                                    " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
                        } else {
                            loan.setFechaMaximaDevolucion(addDaysSkippingWeekends(sysdate, 7));
                            user.setActiveLoans(amountLoans+1);
                            loanRepository.save(loan);
                            userRepository.save(user);
                            return new Response(loan);
                        }
                    default:
                        throw new RuntimeException("User type is not allowed in library.");
                }
            } catch (ApiRequestException e) {
                throw new ApiRequestException("El usuario con identificación: " + request.identificacionUsuario() +
                        " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
            } catch (RuntimeException e){
                throw new ApiRequestException("User type is not allowed in library.");
            } catch (Exception e){
                throw new ApiRequestException("Error in the request, please verify the data.");
            }
        }
        throw new ApiRequestException("User no exists.");
    }
    public LocalDate addDaysSkippingWeekends(LocalDate date, int daysToAdd) {
        LocalDate result = date;
        int addedDays = 0;
        while (addedDays < daysToAdd) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result;
    }

}
