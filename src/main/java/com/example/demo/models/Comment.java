package com.example.demo.models;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    Book book;
}
