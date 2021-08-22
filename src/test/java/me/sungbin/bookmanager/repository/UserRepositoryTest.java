package me.sungbin.bookmanager.repository;

import me.sungbin.bookmanager.domain.Address;
import me.sungbin.bookmanager.domain.Gender;
import me.sungbin.bookmanager.domain.User;
import me.sungbin.bookmanager.domain.UserHistory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Autowired
    private EntityManager entityManager;

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
        System.out.println("findByNameWithPaging: " + userRepository.findByName("robert", PageRequest.of(1, 1, Sort.by(Sort.Order.desc("id")))).getTotalElements());
    }

    @Test
    void insertAndUpdateTest() {
        User user = new User();

        user.setName("robert");
        user.setEmail("robert@gmail.com");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("sungbin");

        userRepository.save(user2);
    }

    @Test
    void enumTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);

        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRawRecord().get("gender"));
    }

    @Test
    void listenerTest() {
        User user = new User();
        user.setEmail("robert2@google.com");
        user.setName("robert");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("robbbert");

        userRepository.save(user2);

        userRepository.deleteById(4L);
    }

    @Test
    void prePersistTest() {
        User user = new User();
        user.setEmail("robert2@gmail.com");
        user.setName("robert");
//        user.setCreatedAt(LocalDateTime.now());
//        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        System.out.println(userRepository.findByEmail("robert2@gmail.com"));
    }

    @Test
    void preUpdateTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println("as-is: " + user);

        user.setName("robert22");
        userRepository.save(user);

        System.out.println("to-be: " + userRepository.findAll().get(0));
    }

    @Test
    void userHistoryTest() {
        User user = new User();
        user.setEmail("robert@nate.com");
        user.setName("sungbin");

        userRepository.save(user);

        user.setName("yangsungbin");
        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
    }

    @Test
    void userRelationTest() {
        User user = new User();
        user.setName("david");
        user.setEmail("davide@naver.com");
        user.setGender(Gender.MALE);
        userRepository.save(user);

        user.setName("daniel");
        userRepository.save(user);

        user.setEmail("daniel@naver.com");
        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);

//        List<UserHistory> result = userHistoryRepository.findByUserId(userRepository.findByEmail("daniel@naver.com").getId());

        List<UserHistory> result = userRepository.findByEmail("daniel@naver.com").getUserHistories();

        result.forEach(System.out::println);

        System.out.println("UserHistory.getUser() " + userHistoryRepository.findAll().get(0).getUser());
    }

    @Test
    void embedTest() {
        userRepository.findAll().forEach(System.out::println);

        User user = new User();
        user.setName("steve");
        user.setHomeAddress(new Address("경기도", "시흥시", "서울대학로284 호반써밋", "28333"));
        user.setCompanyAddress(new Address("서울시", "강남구", "역삼역", "1234"));

        userRepository.save(user);

        User user1 = new User();
        user1.setName("jacob");
        user1.setHomeAddress(null);
        user1.setCompanyAddress(null);

        userRepository.save(user1);

        User user2 = new User();
        user2.setName("mark");
        user2.setHomeAddress(new Address());
        user2.setCompanyAddress(new Address());

        userRepository.save(user2);

        entityManager.clear();

        userRepository.findAll().forEach(System.out::println);
        userHistoryRepository.findAll().forEach(System.out::println);

        userRepository.findAllRowRecord().forEach(a -> System.out.println(a.values()));
    }
}