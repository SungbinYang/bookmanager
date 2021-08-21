package me.sungbin.bookmanager.service;

import lombok.RequiredArgsConstructor;
import me.sungbin.bookmanager.domain.Author;
import me.sungbin.bookmanager.domain.Book;
import me.sungbin.bookmanager.repository.AuthorRepository;
import me.sungbin.bookmanager.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    @Transactional()
    public void putBookAndAuthor(){
        Book book = new Book();
        book.setName("JPA Start");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("robert");

        authorRepository.save(author);

        throw new RuntimeException("오류가 나서 DB commit이 발생하지 않았습니다.");
    }
}
