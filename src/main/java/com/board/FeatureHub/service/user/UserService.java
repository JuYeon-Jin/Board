package com.board.FeatureHub.service.user;

import com.board.FeatureHub.dao.UserDao;
import com.board.FeatureHub.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public String getUserId(String id, String pw, String provider) {

        String userId = userDao.getUserId(id, pw, provider);

        // 회원이면 PK return
        if (userId != null) {
            return userId;
        } else {
        // 회원이 아니라면 DB 에 INSERT
            userDao.joinUser(id, pw, provider, "신규가입자");
            userId = userDao.getUserId(id, pw, provider);
            return userId;
        }

    }



}
