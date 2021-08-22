package me.sungbin.bookmanager.repository;

import me.sungbin.bookmanager.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
