package me.sungbin.bookmanager.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "user", indexes = {@Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @NonNull
    private String email;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Transient
    private String testData;
    
//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> addresses;
}
