package com.example.demo.repositories;

import com.example.demo.DemoApplication;
import com.example.demo.models.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class BooksTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void addBook(){
        Book book = new Book();
        book.setAuthor("a");
        book.setIsbn("1234");
        book.setName("b");
        bookRepository.save(book);
        assertThat(bookRepository.count()).isEqualTo(1);
        Book testing_book = bookRepository.findById(1L).orElse(null);
        assertThat(testing_book.getIsbn()).isEqualTo("1234");
    }
    public void addComment(){
        Book book = new Book();
        book.setAuthor("a");
        book.setIsbn("1234");
        book.setName("b");
        bookRepository.save(book);
        Comment comment = new Comment();
        comment.setWordComment("aaa");
        comment.setDate(new Date());
        comment.setBook(book);
        commentRepository.save(comment);
        assertThat(commentRepository.count()).isEqualTo(1);
        Comment testing_comment = commentRepository.findById(1L).orElse(null);
        assertThat(testing_comment.getBook().getId()).isEqualTo(1L);
    }
}
