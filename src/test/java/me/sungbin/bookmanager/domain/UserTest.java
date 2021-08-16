package me.sungbin.bookmanager.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void test() {
        User user = new User();
        user.setEmail("robert@gmail.com");
        user.setName("robert");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

//        User user1 = new User(null, "sungbin", Gender.MALE, "sungbin@gmail.com");
        User user2 = new User("robert", "robert@aaa.com");

        User user3 = User.builder().name("robert").email("robert@gmail.com").build();

        System.out.println(">>> " + user);
//        System.out.println(">>> " + user1);
        System.out.println(">>> " + user2);
        System.out.println(">>> " + user3);
    }
}