package me.sungbin.bookmanager.domain.listener;

import me.sungbin.bookmanager.domain.User;
import me.sungbin.bookmanager.domain.UserHistory;
import me.sungbin.bookmanager.repository.UserHistoryRepository;
import me.sungbin.bookmanager.support.BeanUtils;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class UserEntityListener {

    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object o) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User) o;
        UserHistory userHistory = new UserHistory();

        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        userHistory.setUser(user);

        userHistoryRepository.save(userHistory);

    }
}
