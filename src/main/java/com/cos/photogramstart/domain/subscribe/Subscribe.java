package com.cos.photogramstart.domain.subscribe;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
        uniqueConstraints ={
                @UniqueConstraint(
                        name="subscribe_uk",
                        columnNames = {"fromUserID","toUserId"}//실제 데이터베이스의 컬럼명
                )

        }
)
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "fromUserId")
    @ManyToOne
    private User fromUser;

    @JoinColumn(name = "toUserId")
    @ManyToOne
    private User toUser;


    private LocalDateTime createDate;

    @PrePersist //DB에 insert되기 직전에 실행
    public void createDate()
    {
        this.createDate = LocalDateTime.now();
    }


}
