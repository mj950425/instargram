package com.cos.photogramstart.domain.user;

import com.cos.photogramstart.domain.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY) //나는 연관관계의 주인이 아니다. 그러므로 테이블에 컬럼을 만들지마.
    //User를 select 할 때 해당 유저 Id로 등록된 이미지들을 다 가져와.
    //Lazy = User를 select할 때 해당 User id로 등록된 image들을 가져오지마
    //Eager = User를 select할 때 해당 User id로 등록된 image들을 전부 join해서 가져와

    private List<Image> images;

    @PrePersist //DB에 insert되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }


}
