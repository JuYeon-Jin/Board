package com.board.FeatureHub.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserRepDto {

    private String userId;
    private String id;
    private String nickName;
    private String accessToken;
    private String refreshToken;

}
