package com.board.FeatureHub.service;

import com.board.FeatureHub.dao.UserDao;
import com.board.FeatureHub.dto.OAuthTokenDto;
import com.board.FeatureHub.dto.OAuthUserDto;
import com.board.FeatureHub.dto.UserDto;
import com.board.FeatureHub.oauth.GoogleOAuth;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {

    @Autowired
    private GoogleOAuth googleOAuth;

    public OAuthUserDto googleOauthLogin(String code) throws JsonProcessingException {

        // 1. Access Token 발급
        OAuthTokenDto tokenDto = googleOAuth.requestAccessToken(code);

        // 2. Access Token 으로 사용자 정보 요청
        return googleOAuth.requestUserInfo(tokenDto);

    }
}
