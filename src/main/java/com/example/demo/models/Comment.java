package com.example.demo.models;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Comment {

    @Id @GeneratedValue
    Long id;

    String wordComment;
    Date date;

    @ManyToOne
    Book book;
}
