package com.board.FeatureHub.service;

import com.board.FeatureHub.dto.OAuthTokenDto;
import com.board.FeatureHub.dto.OAuthUserDto;
import com.board.FeatureHub.dto.response.TokenRepDto;
import com.board.FeatureHub.oauth.GoogleOAuth;
import com.board.FeatureHub.service.user.OAuthService;
import com.board.FeatureHub.service.user.TokenService;
import com.board.FeatureHub.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// DAO 사용 X
@Service
public class UserCompService {

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    // 토큰 검증
    public boolean vaildToken(String token) {
        if (token == null) {
            return false;
        } else {
            return true;
        }


    }

    // 로그인 → 토큰(Access / Refresh) 발급
    public TokenRepDto loginUser(String id, String pw, String provider) {
        String userId = userService.getUserId(id, pw, provider);
        // return new TokenRepDto(tokenService.createAccessToken(userId), tokenService.createRefreshToken());
        String Access = tokenService.createAccessToken(userId);
        System.out.println("Access = " + Access);
        tokenService.parsingToken(Access);
        String Refresh = tokenService.createRefreshToken();
        System.out.println("Refresh = " + Refresh);
        tokenService.parsingToken(Refresh);



        return new TokenRepDto(Access, Refresh);
    }

    // 아이디 확인

    // 비밀번호 확인

    // 닉네임 확인



}
