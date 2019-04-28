package com.example.demo.controllers;

import com.example.demo.models.Book;
import com.example.demo.models.Comment;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.CommentRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/books")
    public String list(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "books/list";
    }

    @GetMapping(value="/books/form")
    public String form(Model model){
        model.addAttribute("book", new Book());
        return "books/create";
    }

    @PostMapping(value="/books")
    public String create(Book book){
        bookRepository.save(book);
        return "redirect:/books";
    }
    @GetMapping(value="/books/{id}/form")
    public String edit(@PathVariable("id") long id, Model model){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        model.addAttribute("comment", new Comment());
        List<Comment> comments = commentRepository.findAllByBook(book);

        model.addAttribute("comments",comments);

        return "books/edit";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Book book,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return "update-book";
        }
        bookRepository.save(book);
        model.addAttribute("books", bookRepository.findAll());
        return "books/list";
    }
    @PostMapping("/add_comment/{id}")
    public String addComment(@PathVariable("id") long id, @Valid Comment comment,
                             BindingResult result, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        comment.setDate(new Date());
        comment.setBook(book);
        commentRepository.save(comment);
        model.addAttribute("books", bookRepository.findAll());
        return "books/list";
    }
    @DeleteMapping(value="/books/{id}")
    public String delete(Book book){
        bookRepository.delete(book);
        return "redirect:/books";
    }
}
