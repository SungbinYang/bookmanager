package me.sungbin.bookmanager.service;

import lombok.RequiredArgsConstructor;
import me.sungbin.bookmanager.domain.User;
import me.sungbin.bookmanager.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class UserService {

    private final EntityManager entityManager;

    private final UserRepository userRepository;

    @Transactional
    public void put() {
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@gmail.com");

        entityManager.persist(user);
//        entityManager.detach(user);

        user.setName("newUserAfterPersist");
        entityManager.merge(user);
//        entityManager.flush();
//        entityManager.clear();

        User user1 = userRepository.findById(1L).get();
        entityManager.remove(user1);
    }
}
