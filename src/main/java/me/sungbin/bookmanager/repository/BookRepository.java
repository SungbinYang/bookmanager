package me.sungbin.bookmanager.repository;

import me.sungbin.bookmanager.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
