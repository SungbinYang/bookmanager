package me.sungbin.bookmanager.domain;

import lombok.*;
import me.sungbin.bookmanager.domain.listener.Auditable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserHistory extends BaseEntity implements Auditable {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String name;

    private String email;

//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    private LocalDateTime updatedAt;
}
