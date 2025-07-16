package com.example.crud.service;


import com.example.crud.dto.BookDTO;
import com.example.crud.model.Book;
import com.example.crud.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
