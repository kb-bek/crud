package com.example.crud.controller;


import com.example.crud.dto.BookDTO;
import com.example.crud.model.Book;
import com.example.crud.repository.BookRepository;
import com.example.crud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {

    private  final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    public String index() {
        return "index";
    }


    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("newBook", new BookDTO());
        return "add-book";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("newBook") BookDTO newBook) {
        bookService.addBook(newBook);
        return "add-book";
    }

    @GetMapping("/all")
    public String listOfBooks(Model model) {
        model.addAttribute("allBooks", bookService.getAllBooks());
        return "all-books";
    }

    @GetMapping("/{id}")
    public String book(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));

        return "book-details";
    }

    @GetMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "update-book";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("book") Book changedBook) {
        bookService.updateBook(id, changedBook);
        return "redirect:/book/" + id;
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/book/all";
    }

}
