package com.board.FeatureHub.controller;

import com.board.FeatureHub.dao.UserDao;
import com.board.FeatureHub.dto.OAuthUserDto;
import com.board.FeatureHub.dto.UserDto;
import com.board.FeatureHub.dto.response.TokenRepDto;
import com.board.FeatureHub.dto.response.UserRepDto;
import com.board.FeatureHub.service.user.TokenService;
import com.board.FeatureHub.service.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class UserController {

    @PostMapping("/getUserInfo")
    public void getUserInfoByToken (@RequestParam("token") String token) {
        UserRepDto userDto;
        TokenRepDto tokenDto;

        String a = "1";

        ResponseEntity.ok(a);
    }

    @PostMapping("")
    public void getAccessToken (@RequestParam("token") String token) {

        String a = "1";

        ResponseEntity.ok(a);
    }


}
