package me.sungbin.bookmanager.repository;

import me.sungbin.bookmanager.domain.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
}
