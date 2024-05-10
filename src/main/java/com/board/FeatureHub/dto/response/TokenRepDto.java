package com.board.FeatureHub.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TokenRepDto {

    private String accessToken;
    private String refreshToken;

}
