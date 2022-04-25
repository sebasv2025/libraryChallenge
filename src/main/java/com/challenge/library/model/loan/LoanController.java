package com.challenge.library.model.loan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/prestamo")
public record LoanController(LoanService loanService) {

    @PostMapping
    public ResponseEntity<Response> registerLoan(@RequestBody LoanRequest loanRequest){
        log.info("New loan registration {}", loanRequest);
        Response response = loanService.registerLoan(loanRequest);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable int id){
        Loan loan = loanService.loanRepository().getById(id);
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }

}
