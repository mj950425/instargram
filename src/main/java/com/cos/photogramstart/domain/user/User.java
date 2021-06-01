package com.cos.photogramstart.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String website;
    private String bio;


    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;
    private LocalDateTime createDate;
    private String profileImageUrl;
    private String role;


    @PrePersist //DB에 insert되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }


}
