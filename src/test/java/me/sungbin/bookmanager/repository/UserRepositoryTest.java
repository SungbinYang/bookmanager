package me.sungbin.bookmanager.repository;

import me.sungbin.bookmanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void crud() {
        userRepository.save(new User("david", "david@google.com"));

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("robert-updated@google.com");

        userRepository.save(user);
    }

    @Test
    void select() {
        System.out.println(userRepository.findByName("dennis"));

        System.out.println("findByEmail: " + userRepository.findByEmail("robert@google.com"));
        System.out.println("getByEmail: " + userRepository.getByEmail("robert@google.com"));
        System.out.println("readByEmail: " + userRepository.readByEmail("robert@google.com"));
        System.out.println("queryByEmail: " + userRepository.queryByEmail("robert@google.com"));
        System.out.println("searchByEmail: " + userRepository.searchByEmail("robert@google.com"));
        System.out.println("streamByEmail: " + userRepository.streamByEmail("robert@google.com"));
        System.out.println("findUserByEmail: " + userRepository.findUserByEmail("robert@google.com"));
        System.out.println("findSomethingByEmail: " + userRepository.findSomethingByEmail("robert@google.com"));

        System.out.println("findTop2ByName: " + userRepository.findTop2ByName("robert"));
        System.out.println("findFirst2ByName: " + userRepository.findFirst2ByName("robert"));
        System.out.println("findLast1ByName: " + userRepository.findLast1ByName("robert"));

    }
}