package me.sungbin.bookmanager.domain.listener;

import me.sungbin.bookmanager.domain.User;
import me.sungbin.bookmanager.domain.UserHistory;
import me.sungbin.bookmanager.repository.UserHistoryRepository;
import me.sungbin.bookmanager.support.BeanUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class UserEntityListener {

    @PrePersist
    @PreUpdate
    public void prePersistAndPreUpdate(Object o) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User) o;
        UserHistory userHistory = new UserHistory();

        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());

        userHistoryRepository.save(userHistory);

    }
}
