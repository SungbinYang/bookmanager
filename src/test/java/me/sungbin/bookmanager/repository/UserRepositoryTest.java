package me.sungbin.bookmanager.repository;

import me.sungbin.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;

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
//        System.out.println(userRepository.findByName("dennis"));
//
//        System.out.println("findByEmail: " + userRepository.findByEmail("robert@google.com"));
//        System.out.println("getByEmail: " + userRepository.getByEmail("robert@google.com"));
//        System.out.println("readByEmail: " + userRepository.readByEmail("robert@google.com"));
//        System.out.println("queryByEmail: " + userRepository.queryByEmail("robert@google.com"));
//        System.out.println("searchByEmail: " + userRepository.searchByEmail("robert@google.com"));
//        System.out.println("streamByEmail: " + userRepository.streamByEmail("robert@google.com"));
//        System.out.println("findUserByEmail: " + userRepository.findUserByEmail("robert@google.com"));
//        System.out.println("findSomethingByEmail: " + userRepository.findSomethingByEmail("robert@google.com"));
//
//        System.out.println("findTop2ByName: " + userRepository.findTop2ByName("robert"));
//        System.out.println("findFirst2ByName: " + userRepository.findFirst2ByName("robert"));
//        System.out.println("findLast1ByName: " + userRepository.findLast1ByName("robert"));

        System.out.println("findByEmailAndName" + userRepository.findByEmailAndName("robert@google.com", "robert"));
        System.out.println("findByEmailOrName" + userRepository.findByEmailOrName("robert@google.com", "dennis"));

        System.out.println("findByCreatedAtAfter: " + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdAfter: " + userRepository.findByIdAfter(4L));
        System.out.println("findByCreatedAtGreaterThan: " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtGreaterThanEqual: " + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtBetween: " + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));
        System.out.println("findByIdBetween: " + userRepository.findByIdBetween(1L, 3L));
        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual: " + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L, 3L));
        System.out.println("findByIdIsNotNull: " + userRepository.findByIdIsNotNull());
//        System.out.println("findByAddressesIsNotEmpty: " + userRepository.findByAddressesIsNotEmpty());
        System.out.println("findByNameIn: " + userRepository.findByNameIn(Lists.newArrayList("robert", "dennis")));

        System.out.println("findByNameStartingWith: " + userRepository.findByNameStartingWith("ro"));
        System.out.println("findByNameEndingWith: " + userRepository.findByNameEndingWith("ert"));
        System.out.println("findByNameContains: " + userRepository.findByNameContains("be"));

        System.out.println("findByNameLike: " + userRepository.findByNameLike("%" + "ber" + "%"));
    }

    @Test
    void pagingAndSortingTest() {
        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("robert"));
        System.out.println("findTopByNameOrderByIdDesc : " + userRepository.findTopByNameOrderByIdDesc("robert"));
        System.out.println("findFirstByNameOrderByIdDescEmailAsc: " + userRepository.findFirstByNameOrderByIdDescEmailAsc("robert"));
        System.out.println("findFirstByNameWithSortParams: " + userRepository.findFirstByName("robert", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));
    }
}