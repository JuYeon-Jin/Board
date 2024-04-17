package com.board.FeatureHub.service;

import com.board.FeatureHub.oauth.GoogleOAuth;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {

    public void googleOauthLogin(String code) {
        System.out.println("code = " + code);


    }

}
