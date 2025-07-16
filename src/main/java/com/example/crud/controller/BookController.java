package com.example.crud.controller;


import com.example.crud.dto.BookDTO;
import com.example.crud.repository.BookRepository;
import com.example.crud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    private  final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    public String index() {
        return "index";
    }


    @GetMapping("/add-book")
    public String addBook(Model model) {
        model.addAttribute("newBook", new BookDTO());
        return "add-book";
    }

    @PostMapping("/add-book")
    public String addBook(@ModelAttribute("newBook") BookDTO newBook) {
        bookService.addBook(newBook);
        return "add-book";
    }

}
