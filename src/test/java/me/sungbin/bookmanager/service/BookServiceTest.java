package me.sungbin.bookmanager.service;

import me.sungbin.bookmanager.domain.Book;
import me.sungbin.bookmanager.repository.AuthorRepository;
import me.sungbin.bookmanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void transactionTest() {

        try {
            bookService.putBookAndAuthor();
        } catch (RuntimeException e) {
            System.out.println(">>> " + e.getMessage());
        }

        System.out.println("books: " + bookRepository.findAll());
        System.out.println("authors: " + authorRepository.findAll());
    }

    @Test
    void isolationTest() {
        Book book = new Book();
        book.setName("JPA Lecture");

        bookRepository.save(book);

        bookService.get(1L);
        System.out.println(">>> " + bookRepository.findAll());
    }
}