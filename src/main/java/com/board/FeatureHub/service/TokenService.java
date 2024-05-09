package com.board.FeatureHub.service;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class TokenService {

    private SecretKey key = Jwts.SIG.HS256.key().build();

    // 토큰 발급
    public String createJWT(String id) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar c = Calendar.getInstance();

        // 날짜 더하기 : add(int field, int amount)
        c.add(Calendar.MINUTE, 30); // + 30분

        System.out.println("c = " + dateFormat.format(c.getTime()));

        c.add(Calendar.HOUR, 1);

        System.out.println("c = " + dateFormat.format(c.getTime()));

        String token = null;

        token = Jwts.builder()
                .header()
                    .keyId("aJwtKey")
                    .and()
                .claim("id", id)
                .subject("temp")
                //.expiration(expiration)
                //.notBefore(notBefore) //a java.util.Date
                .issuedAt(new Date()) // for example, now
                .signWith(key)
                .compact();

        // exp: 토큰 만료 시간
        // nbf: 토큰 활성 날짜(이 날자 이전의 토큰은 활성화 되지 않음을 보장)
        // iat: 토큰 발급 시간

        // ----------------------------------------------------------------------

        System.out.println("date = " + date);
        System.out.println("dateFormat = " + dateFormat.format(date));








        // ----------------------------------------------------------------------

        return token;


    }

    // 클라이언트가 ID,PW로 서버에게 인증을 요청, 서버는 이를 확인하여 Access Token 과 Refresh Token을 발급


    // 클라이언트는 Refresh Token을 본인이 잘 저장하고 Access Token으로 서버에게 자유롭게 요청
    // 서비스를 이용중 Access Token이 만료되어 사용할수 없다는 오류를 서버로 부터 전달 받음
    // 클라이언트는 Access Token의 만료 사실을 인지하고 본인이 갖고있던 Refresh Token으로 서버에게 전달하여 새로운 Access Token의 발급을 요청
    // 서버는 Refresh Token을 받아 서버의 Refresh Token Storage에 토큰의 존재 여부를 확인후 있다면 새로운 Access Token을 생성하여 전달
    // 이후 2번으로 돌아가서 동일한 작업 진행

}
