package me.sungbin.bookmanager.service;

import lombok.RequiredArgsConstructor;
import me.sungbin.bookmanager.domain.Comment;
import me.sungbin.bookmanager.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void init() {
        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment();
            comment.setComment("최고에요");

            commentRepository.save(comment);
        }
    }

    @Transactional(readOnly = true)
    public void updateSomething() {
        List<Comment> comments = commentRepository.findAll();

        for (Comment comment : comments) {
            comment.setComment("별루에요");

//            commentRepository.save(comment);
        }
    }

    @Transactional
    public void insertSomething() {
//        Comment comment = new Comment();
        Comment comment = commentRepository.findById(1L).get();
        comment.setComment("이건뭐죠?");

//        commentRepository.save(comment);
    }
}
