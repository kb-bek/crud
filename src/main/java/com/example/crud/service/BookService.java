package com.example.crud.service;


import com.example.crud.dto.BookDTO;
import com.example.crud.exceptions.BookNotFound;
import com.example.crud.model.Book;
import com.example.crud.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFound("Book with id " + id + " not found"));
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPrice(book.getPrice());

        return bookDTO;
    }


    public void addBook(BookDTO newBook) {
        Book book = new Book();
        book.setTitle(newBook.getTitle());
        book.setDescription(newBook.getDescription());
        book.setAuthor(newBook.getAuthor());
        book.setPrice(newBook.getPrice());

        bookRepository.save(book);
    }

}
