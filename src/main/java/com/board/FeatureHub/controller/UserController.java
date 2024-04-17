package com.board.FeatureHub.controller;

import com.board.FeatureHub.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/test")
    public void Test() {
        System.out.println("컨트롤러");
        UserService userService = new UserService();
        userService.Test();
    }

}
