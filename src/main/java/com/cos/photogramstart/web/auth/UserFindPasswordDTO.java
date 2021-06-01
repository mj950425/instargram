package com.cos.photogramstart.web.auth;

import lombok.Data;

@Data
public class UserFindPasswordDTO {
    private String username; // 아이디
    private String email;
    private String name;
}
