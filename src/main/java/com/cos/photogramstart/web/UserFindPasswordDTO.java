package com.cos.photogramstart.web;

import lombok.Data;

@Data
public class UserFindPasswordDTO {
    private String username; // 아이디
    private String email;
}
