package me.sungbin.bookmanager.repository;

import me.sungbin.bookmanager.domain.Book;
import me.sungbin.bookmanager.domain.Publisher;
import me.sungbin.bookmanager.domain.Review;
import me.sungbin.bookmanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void bookTest() {
        Book book = new Book();
        book.setName("Jpa 스터디");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }

    @Test
    @Transactional
    void bookRelationTest() {
        givenBookAndReview();

        User user = userRepository.findByEmail("robert@google.com");

        System.out.println("Review: " + user.getReviews());
        System.out.println("Book: " + user.getReviews().get(0).getBook());
        System.out.println("Publisher: " + user.getReviews().get(0).getBook().getPublisher());
    }

    @Test
    void bookCascadeTest() {
        Book book = new Book();
        book.setName("JPA CASCADE");

        Publisher publisher = new Publisher();
        publisher.setName("sungbin");

        book.setPublisher(publisher);
        bookRepository.save(book);

        System.out.println("books: " + bookRepository.findAll());
        System.out.println("publisher: " + publisherRepository.findAll());

        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("bini");

        bookRepository.save(book1);

        System.out.println("publisher: " + publisherRepository.findAll());

    }

    private void givenBookAndReview() {
        givenReview(givenUser(), givenBook(givenPublisher()));
    }

    private User givenUser() {
        return userRepository.findByEmail("robert@google.com");
    }

    private Book givenBook(Publisher publisher) {
        Book book = new Book();
        book.setName("JPA 스터디");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private void givenReview(User user, Book book) {
        Review review = new Review();
        review.setTitle("내인생 책");
        review.setContent("너무너무 유익한 책이였습니다.");
        review.setScore(5.0f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }

    private Publisher givenPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("나출판사");

        return publisherRepository.save(publisher);
    }
}