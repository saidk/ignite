package com.example.demo.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue
    Long id;

    String name;
    String author;
    String isbn;

    public String getName() {
        return name;
    }
    public Long getId(){
        return id;
    }
}
