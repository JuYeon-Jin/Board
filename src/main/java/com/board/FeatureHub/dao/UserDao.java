package com.board.FeatureHub.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
/*
    // 구글유저찾기
    @Select("")
    public int findGoogleUser(@Param("dto") User a);

    // 회원가입
    @Insert("INSERT INTO Users (userNo, pinNo, id, password, email) VALUES (uuid(), #{user.pinNo}, #{user.id}, #{user.password}, #{user.email})")
    public void joinUser(@Param("dto") User a);

    // 로그인 (id, password 입력 → pinNo)
    @Select("SELECT pinNo FROM Users WHERE id = #{user.id} AND password = #{user.password}")
    public String loginUser(@Param("dto") User a);

    // 토큰발급
*/
}
