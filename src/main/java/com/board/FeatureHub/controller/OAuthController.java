package com.board.FeatureHub.controller;

import com.board.FeatureHub.oauth.GoogleOAuth;
import com.board.FeatureHub.service.OAuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;


@Controller
public class OAuthController {

    @Autowired
    private GoogleOAuth googleOAuth;

    // 구글 로그인창 접근
    @GetMapping("/googleLogin")
    public void googleOauthLogin (HttpServletResponse response) throws IOException {
        response.sendRedirect(googleOAuth.returnUrl());
    }

    // redirect URL 로 접근
    @GetMapping("/oauth/google")
    public String result (@RequestParam(name="code") String code) {
        // authrization code 받아오기
        OAuthService oAuthService = new OAuthService();
        oAuthService.googleOauthLogin(code);

        System.out.println("컨트롤러 빠져나옴");

        return "oauthTest.html";
    }

}
