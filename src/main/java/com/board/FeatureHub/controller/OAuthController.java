package com.board.FeatureHub.controller;

import com.board.FeatureHub.dto.OAuthUserDto;
import com.board.FeatureHub.dto.UserDto;
import com.board.FeatureHub.dto.response.TokenRepDto;
import com.board.FeatureHub.oauth.GoogleOAuth;
import com.board.FeatureHub.service.UserCompService;
import com.board.FeatureHub.service.user.OAuthService;
import com.board.FeatureHub.service.user.UserService;
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

    @Autowired
    private UserCompService component;

    // 구글 로그인창 접근
    @GetMapping("/googleLogin")
    public void googleOauthLogin (HttpServletResponse response) throws IOException {
        response.sendRedirect(googleOAuth.returnUrl());
    }

    // 구글 로그인 토큰 발급
    @GetMapping("/oauth/google")
    public TokenRepDto result (@RequestParam(name="code") String code) throws JsonProcessingException {

        // 1. 구글 회원 정보 받아오기
        OAuthUserDto userDto = oAuthService.googleOauthLogin(code);

        System.out.println("userDto.getName() = " + userDto.getName());
        System.out.println("userDto.getGiven_name() = " + userDto.getGiven_name());

        // 2. 로그인/회원가입
        // dto.getEmail() = juuju.00b@gmail.com → id 로 사용
        // dto.getId() = 114793538545492658262  → pw 로 사용
        return component.loginUser(userDto.getEmail(), userDto.getId(), "google");

    }
}
