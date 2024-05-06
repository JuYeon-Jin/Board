package com.board.FeatureHub.dao;

import com.board.FeatureHub.dto.UserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserDao {

    // userId, id, pw, provider, nickName, accessToken, refreshToken, joinDate, modifiedDate, withdrawDate

    // 토큰 검사
    @Select("SELECT COUNT(*) FROM USER WHERE ACCESS_TOKEN = #{accessToken}")
    public int checkValidToken(@Param("accessToken") String a);

    // 회원 가입
    @Insert("INSERT INTO USER (USER_ID, ID, PW, PROVIDER, NICKNAME) VALUES (UUID(), #{id}, #{pw}, #{provider}, #{nickName})")
    public void joinUser(@Param("id") String a,
                         @Param("pw") String b,
                         @Param("provider") String c,
                         @Param("nickName") String d);

    // 로그인 시 PK 가져오기
    @Select("SELECT USER_ID FROM USER WHERE ID = #{id} AND PW = #{pw}")
    public String getUserIdFromLogin(@Param("id") String a,
                                     @Param("pw") String b);

    // 토큰 업데이트(발급)
    @Update("UPDATE USER SET ACCESS_TOKEN = #{accessToken} WHERE USER_ID = #{userId})")
    public void updateToken(@Param("accessToken") String a,
                            @Param("userId") String b);

    // 토큰으로 유저정보 가져오기
    @Select("SELECT * FROM USER WHERE ACCESS_TOKEN = #{accessToken}")
    public UserDto getUserIdFromToken(@Param("accessToken") String a);

}
