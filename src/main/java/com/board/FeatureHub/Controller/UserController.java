package com.board.FeatureHub.Controller;

import com.board.FeatureHub.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/oauth")
    public void oauthTest() {
        UserService userService = new UserService();
        userService.oauthTest();
        System.out.println("컨트롤러");
    }

}
