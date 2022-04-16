package com.challenge.library.model;

import lombok.Getter;

@Getter
public class Book {
    String isbn;

    public Book(String isbn){
        this.isbn = isbn;
    }

}
