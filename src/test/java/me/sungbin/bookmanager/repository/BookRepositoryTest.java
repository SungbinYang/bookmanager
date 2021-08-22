package me.sungbin.bookmanager.repository;

import me.sungbin.bookmanager.domain.Book;
import me.sungbin.bookmanager.domain.Publisher;
import me.sungbin.bookmanager.domain.Review;
import me.sungbin.bookmanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

        Book book2 = bookRepository.findById(1L).get();
//        bookRepository.delete(book2);

//        publisherRepository.delete(book2.getPublisher());

        Book book3 = bookRepository.findById(1L).get();
        book3.setPublisher(null);

        bookRepository.save(book3);

        System.out.println("books: " + bookRepository.findAll());
        System.out.println("publishers: " + publisherRepository.findAll());
        System.out.println("book3-publisher: " + bookRepository.findById(1L).get().getPublisher());

    }

    @Test
    void bookRemoveCascadeTest() {

        bookRepository.deleteById(1L);

        System.out.println("books: " + bookRepository.findAll());
        System.out.println("publishers: " + publisherRepository.findAll());

        bookRepository.findAll().forEach(book -> System.out.println(book.getPublisher()));

    }

    @Test
    void softDelete() {
        bookRepository.findAll().forEach(System.out::println);
        System.out.println(bookRepository.findById(3L));

        bookRepository.findByCategoryIsNull().forEach(System.out::println);

        bookRepository.findAllByDeletedFalse().forEach(System.out::println);
        bookRepository.findByCategoryIsNullAndDeletedFalse().forEach(System.out::println);
    }

    @Test
    void queryTest() {
        bookRepository.findAll().forEach(System.out::println);
        System.out.println("findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual : " +
                bookRepository.findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual("JPA CASCADE", LocalDateTime.now().minusDays(1L), LocalDateTime.now().minusDays(1L)));

        System.out.println("findByNameRecently: " +
                bookRepository.findByNameRecently("JPA CASCADE", LocalDateTime.now().minusDays(1L), LocalDateTime.now().minusDays(1L)));

        System.out.println(bookRepository.findBookNameAndCategory());

        bookRepository.findBookNameAndCategory().forEach(b -> {
            System.out.println(b.getName() + " : " + b.getCategory());
        });

        bookRepository.findBookNameAndCategory(PageRequest.of(1, 1)).forEach(bookNameAndCategory -> {
            System.out.println(bookNameAndCategory.getName() + " : " + bookNameAndCategory.getCategory());
        });

        bookRepository.findBookNameAndCategory(PageRequest.of(0, 1)).forEach(bookNameAndCategory -> {
            System.out.println(bookNameAndCategory.getName() + " : " + bookNameAndCategory.getCategory());
        });
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