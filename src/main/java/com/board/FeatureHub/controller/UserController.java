package com.board.FeatureHub.controller;

import com.board.FeatureHub.service.TokenService;
import com.board.FeatureHub.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/getToken")
    public void joinFromGoogle() {
        System.out.println("컨트롤러");
        TokenService tokenService = new TokenService();
        System.out.println("createJWT = " + tokenService.createJWT("i00d"));
    }

    @GetMapping("/getUserInfo")
    public void getUserInfo() {

    }


}
