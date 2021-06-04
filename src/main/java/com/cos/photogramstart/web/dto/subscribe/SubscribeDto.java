package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeDto {
    private int id;
    private String username;
    private String profileImageUrl;
    private Integer subscribeState;
    private Integer equalUserState;

}
