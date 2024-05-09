package com.board.FeatureHub.service;

import com.board.FeatureHub.dao.UserDao;
import com.board.FeatureHub.dto.OAuthUserDto;
import com.board.FeatureHub.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public String oauthLogin(String id, String pw, String provider) {

        String userId = userDao.getUserId(id, pw, provider);
        System.out.println("userId = " + userId);

        if (userId == null) {

            System.out.println("id = " + id);
            System.out.println("pw = " + pw);
            System.out.println("provider = " + provider);


            // DB 에 고객정보 INSERT #{id}, #{pw}, #{provider}, #{nickName}
            userDao.joinUser(id, pw, provider, "신규가입자");
            // PK 조회
            userId = userDao.getUserId(id, pw, provider);
            // 토큰 발급
            return userId;

        } else {
            // 토큰 발급
            return userId;
        }

        // 토큰 DB 에 INSERT

        // 토큰 RETURN

    }

    public void homeLogin(UserDto userDto) {


    }

}
