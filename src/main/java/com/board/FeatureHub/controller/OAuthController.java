package com.board.FeatureHub.controller;

import com.board.FeatureHub.oauth.GoogleOAuth;
import com.board.FeatureHub.service.OAuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    @Autowired
    private OAuthService oAuthService;

    // 구글 로그인창 접근
    @GetMapping("/googleLogin")
    public void googleOauthLogin (HttpServletResponse response) throws IOException {
        response.sendRedirect(googleOAuth.returnUrl());
    }

    // redirect 될 맵핑 주소로 code 를 전달받은 후 Service 로 code 값을 넘겨준다.
    // 여기서 Controller 는 구글에서 넘겨준 code 값을 Service 에 넘겨주는 아주 단순한 역할을 한다.
    // 리다이렉트 될 경우 URL에 설정해둔 대로 바로 code값이 넘어오니 @RequestParam 을 이용해 값을 바로 받아오기만 하면 된다.
    @GetMapping("/oauth/google")
    public String result (@RequestParam(name="code") String code) throws JsonProcessingException {

        oAuthService.googleOauthLogin(code);

        System.out.println("컨트롤러 빠져나옴");

        // 참조 블로그에서의 return 타입은 SingleResult<TokenUserDTO> 타입
        return "oauthTest.html";
    }

}
