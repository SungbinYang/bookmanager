package me.sungbin.bookmanager.repository;

import me.sungbin.bookmanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void crud() {
        User user = new User();
        user.setEmail("naver");

        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("email", contains());
        Example<User> example = Example.of(user, matcher);

        userRepository.findAll(example).forEach(System.out::println);
    }
}