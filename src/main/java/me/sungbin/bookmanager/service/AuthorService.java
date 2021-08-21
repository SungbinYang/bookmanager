package me.sungbin.bookmanager.service;

import lombok.RequiredArgsConstructor;
import me.sungbin.bookmanager.domain.Author;
import me.sungbin.bookmanager.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional(propagation = Propagation.NESTED)
    public void putAuthor() {
        Author author = new Author();
        author.setName("robert");

        authorRepository.save(author);

//        throw new RuntimeException("오류가 발생하였습니다. transaction은 어떻게 될까요?");
    }
}
