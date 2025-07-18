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

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFound("Book with id " + id + " not found"));
    }




    public void addBook(BookDTO newBook) {
        Book book = new Book();
        book.setTitle(newBook.getTitle());
        book.setDescription(newBook.getDescription());
        book.setAuthor(newBook.getAuthor());
        book.setPrice(newBook.getPrice());

        bookRepository.save(book);
    }

    public void updateBook(Long id, Book changedBook) {
        Book book = getBookById(id);
        book.setTitle(changedBook.getTitle());
        book.setDescription(changedBook.getDescription());
        book.setAuthor(changedBook.getAuthor());
        book.setPrice(changedBook.getPrice());
        bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
