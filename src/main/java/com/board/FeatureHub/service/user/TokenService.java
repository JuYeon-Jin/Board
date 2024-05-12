package com.board.FeatureHub.service.user;

import com.board.FeatureHub.dao.UserDao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;

@Component
public class TokenService {

    @Autowired
    private UserDao userDao;

    private SecretKey key = Jwts.SIG.HS256.key().build();

    // Access Token 생성
    public String createAccessToken(String userId) {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, 1); // + 1시간
        Date expirationAccess = c.getTime();

        String token = Jwts.builder()
                .header()
                .keyId("aFeatureHub")
                .type("JWT")
                .and()
                .subject("A")
                .expiration(expirationAccess)
                .claim("userId", userId)
                .issuedAt(new Date())
                .signWith(key)
                .compact();

        return token;
    }

    // Refresh Token 생성
    public String createRefreshToken() {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 7);
        Date expirationRefresh = c.getTime();

        String token = Jwts.builder()
                .header()
                .keyId("rFeatureHub")
                .type("JWT")
                .and()
                .subject("R")
                .expiration(expirationRefresh)
                .issuedAt(new Date())
                .signWith(key)
                .compact();

        return token;
    }

    // 토큰 읽어들이기
    public void parsingToken(String token) {
        Jws<Claims> jwt;

        try {
            jwt = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);

            // 페이로드를 가져온다.
            // Object payload = jwt.getPayload();
            Object payload = jwt.getPayload();
            // 페이로드를 출력한다. 출력 => "payload = {userId=1}"
            System.out.println("payload = " + payload);


            // Jws에서 Claims 가져오기
            Claims claims = jwt.getPayload();

            // "exp" 필드 값 가져오기
            long exp = claims.getExpiration().getTime();
            String userId = claims.get("userId", String.class);
            System.out.println("exp = " + exp);
            System.out.println("userId = " + userId);

            // JwtException 예외를 캐치한다. (읽기에 실패한다면!, 유효하지 않다면!)
        /*} catch (JwtException e) {
            throw new IllegalStateException("Invalid Token. " + e.getMessage());
        }*/
        } catch (JwtException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
        // 클라이언트가 ID,PW로 서버에게 인증을 요청, 서버는 이를 확인하여 Access Token 과 Refresh Token을 발급
    // 클라이언트는 Refresh Token을 본인이 잘 저장하고 Access Token으로 서버에게 자유롭게 요청
    // 서비스를 이용중 Access Token이 만료되어 사용할수 없다는 오류를 서버로 부터 전달 받음
    // 클라이언트는 Access Token의 만료 사실을 인지하고 본인이 갖고있던 Refresh Token으로 서버에게 전달하여 새로운 Access Token의 발급을 요청
    // 서버는 Refresh Token을 받아 서버의 Refresh Token Storage에 토큰의 존재 여부를 확인후 있다면 새로운 Access Token을 생성하여 전달
    // 이후 2번으로 돌아가서 동일한 작업 진행

}
