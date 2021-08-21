package me.sungbin.bookmanager.service;

import lombok.RequiredArgsConstructor;
import me.sungbin.bookmanager.domain.Book;
import me.sungbin.bookmanager.repository.AuthorRepository;
import me.sungbin.bookmanager.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final EntityManager entityManager;

    private final AuthorService authorService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void putBookAndAuthor(){
        Book book = new Book();
        book.setName("JPA Start");

        bookRepository.save(book);

        try {
            authorService.putAuthor();
        } catch (RuntimeException e) {
        }

//        Author author = new Author();
//        author.setName("robert");
//
//        authorRepository.save(author);
//
        throw new RuntimeException("오류가 발생하였습니다. transaction은 어떻게 될까요?");
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void get(Long id) {
        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        entityManager.clear();

        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        bookRepository.update();

        entityManager.clear();

//        Book book = bookRepository.findById(id).get();
//        book.setName("바뀔 값");
//        bookRepository.save(book);
    }
}
