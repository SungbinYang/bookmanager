package me.sungbin.bookmanager.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import me.sungbin.bookmanager.domain.listener.UserEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@EntityListeners(value = UserEntityListener.class)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @NonNull
    private String email;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private List<UserHistory> userHistories = new ArrayList<>();
}
