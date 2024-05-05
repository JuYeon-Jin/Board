package com.board.FeatureHub.controller;

import com.board.FeatureHub.dto.OAuthUserDto;
import com.board.FeatureHub.oauth.GoogleOAuth;
import com.board.FeatureHub.service.OAuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class OAuthController {

    @Autowired
    private GoogleOAuth googleOAuth;

    @Autowired
    private OAuthService oAuthService;

    // 구글 로그인창 접근
    @GetMapping("/googleLogin")
    public void googleOauthLogin (HttpServletResponse response) throws IOException {
        response.sendRedirect(googleOAuth.returnUrl());
    }

    // 구글 userInfo 받아 오기
    @GetMapping("/oauth/google")
    public OAuthUserDto result (@RequestParam(name="code") String code) throws JsonProcessingException {
        return oAuthService.googleOauthLogin(code);
    }

}
