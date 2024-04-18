package com.board.FeatureHub.service;

import com.board.FeatureHub.dto.OAuthTokenDto;
import com.board.FeatureHub.dto.OAuthUserDto;
import com.board.FeatureHub.oauth.GoogleOAuth;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {

    @Autowired
    private GoogleOAuth googleOAuth;

    // 반환 타입은 GoogleUserInfoDto 타입이다. 나는 OAuthUserDto 로 만들었다.
    public OAuthUserDto googleOauthLogin(String code) throws JsonProcessingException {

        System.out.println("------------------------------------------------------------------");
        System.out.println();
        System.out.println("code = " + code);
        System.out.println();
        System.out.println("------------------------------------------------------------------");


        // 1. Token 을 발급받기 위한 요청 객체
        //    요청을 보낼 URL, 요청 본문 HttpEntity 객체, 요청에 대해 응답 본문을 어떤 식으로 받을지 지정
        //    여기서 요청도 보내고 응답도 받는다.
        ResponseEntity<String> accessTokenResponse = googleOAuth.requestAccessToken(code);

        // 검증
        System.out.println("------------------------------------------------------------------");
        System.out.println();
        System.out.println(accessTokenResponse);
        System.out.println();
        System.out.println("------------------------------------------------------------------");

        // 2. accessTokenResponse 를 자바 객체 형식 으로 변환 (dto)
        //   ※ EXCEPTION 추가
        OAuthTokenDto tokenDto = googleOAuth.getAccessToken(accessTokenResponse);

        // 검증
        System.out.println();
        System.out.println("2. tokenDto.getAccess_token() = " + tokenDto.getAccess_token());
        System.out.println("2. tokenDto.getId_token() = " + tokenDto.getId_token());
        System.out.println("2. tokenDto.getToken_type() = " + tokenDto.getToken_type());
        System.out.println("2. tokenDto.getScope() = " + tokenDto.getScope());
        System.out.println("2. tokenDto.getExpires_in() = " + tokenDto.getExpires_in());
        System.out.println();

        // 3. 이 token 을 가지고 사용자의 정보를 요청할 것
        //    그러기 위해선 헤더에 access_token 이 있어야 한다.
        ResponseEntity<String> userInfoResponse = googleOAuth.requestUserInfo(tokenDto);

        // 검증
        System.out.println("------------------------------------------------------------------");
        System.out.println();
        System.out.println(userInfoResponse);
        System.out.println();
        System.out.println("------------------------------------------------------------------");

        // 4. userInfoResponse 를 자바 객체 형식으로 변환 (dto)
        OAuthUserDto userDto = googleOAuth.getUserInfo(userInfoResponse);

        // 검증
        System.out.println("------------------------------------------------------------------");
        System.out.println();
        System.out.println("4. userDto.getId() = " + userDto.getId());
        System.out.println("4. userDto.getEmail() = " + userDto.getEmail());
        System.out.println("4. userDto.getName() = " + userDto.getName());
        System.out.println("4. userDto.getGiven_name() = " + userDto.getGiven_name());
        System.out.println("4. userDto.getLocale() = " + userDto.getLocale());
        System.out.println("4. userDto.getPicture() = " + userDto.getPicture());
        System.out.println("4. userDto.getVerified_email() = " + userDto.getVerified_email());
        System.out.println();
        System.out.println("------------------------------------------------------------------");

        return userDto;

    }

}
