package com.board.FeatureHub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private String userId;
    private String id;
    private String pw;
    private String provider;
    private String nickName;
    private String accessToken;
    private String refreshToken;
    private String joinDate;
    private String modifiedDate;
    private String withdrawDate;

}
