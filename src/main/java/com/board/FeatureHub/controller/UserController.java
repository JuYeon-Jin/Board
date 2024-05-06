package com.board.FeatureHub.controller;

import com.board.FeatureHub.dao.UserDao;
import com.board.FeatureHub.dto.OAuthUserDto;
import com.board.FeatureHub.dto.UserDto;
import com.board.FeatureHub.service.TokenService;
import com.board.FeatureHub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 구글 로그인
    public void oauthLogin(OAuthUserDto oauthUserDto) {

        userService.oauthLogin(oauthUserDto);

    }

    // 일반 로그인 (@RequestBody ?)
    public void homeLogin(UserDto userDto) {

        userService.homeLogin(userDto);

    }



    // 토큰 발급
    @GetMapping("/getToken")
    public void getToken() {

    }

    // 토큰 검증

    // 유저 정보 가져오기
    @GetMapping("/getUserInfo")
    public void getUserInfo() {

    }


}
