package com.board.FeatureHub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private String UserId;
    private String Id;
    private String Pw;
    private String Provider;
    private String NickName;
    private String AccessToken;
    private String RefreshToken;
    private String JoinDate;
    private String ModifiedDate;
    private String WithdrawDate;

}
