package com.example.plan.user.entity;

import com.example.plan.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String passWord;

    public User(String name, String email, String passWord) {
        this.name = name;
        this.email = email;
        this.passWord = passWord;
    }

    public void update(String name, String email, String passWord) {
        this.name = name;
        this.email = email;
        this.passWord = passWord;
    }
}
