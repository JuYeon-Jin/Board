package com.board.FeatureHub.oauth;

import com.board.FeatureHub.dto.OAuthTokenDto;
import com.board.FeatureHub.dto.OAuthUserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@PropertySource("classpath:application-oauth.properties")
@Component
@RequiredArgsConstructor
public class GoogleOAuth {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    // ↑ 얘네 때문에 필요  @RequiredArgsConstructor

    private final String googleLoginUrl = "https://accounts.google.com";
    private final String GOOGLE_TOKEN_REQUEST_URL = "https://oauth2.googleapis.com/token";
    private final String GOOGLE_USERINFO_REQUEST_URL = "https://www.googleapis.com/oauth2/v1/userinfo";

    @Value("${app.google.clientId}")
    private String googleClientId;
    @Value("${app.google.redirectURL}")
    private String googleRedirectUrl;
    @Value("${app.google.clientSecret}")
    private String googleClientSecret;

    // private final String googleResponseType = "token";
    private final String googleResponseType = "code";

    public String returnUrl() {

        return googleLoginUrl + "/o/oauth2/v2/auth" +
                "?client_id=" + googleClientId +
                "&response_type=" + googleResponseType +
                "&redirect_uri=" + googleRedirectUrl +
                "&scope=https://www.googleapis.com/auth/userinfo.email";

        /*
        에러 원인 1. @Component 미지정
        에러 원인 3. @Value("${oauth.app.google.clientSecret}") 아님
        에러 원인 2. @PropertySource("classpath:application-oauth.properties") 경로 미설정

        */

        /*
        계속 null 값이 나왔던 이유

        외부에서 해당 클래스를 new로 생성했을 경우
         - @Value 애너테이션은 Spring Context에 의존하기 때문에 해당 클래스가 Spring Bean으로 등록되어 있지 않으면 @Value 값은 null을 반환한다.
         - 따라서 new로 클래스 인스턴스를 생성하면 Spring Bean으로 등록되어 있지 않았기 때문에 @Value 값이 null을 반환했던 것이다.
         - 등록된 Bean을 사용하기 위해서는 @Autowired 애너테이션을 사용해야 한다. @Autowired 애너테이션은 해당 타입의 Bean을 찾아서 주입해준다.

        System.out.println("googleClientId = " + googleClientId);
        System.out.println("googleRedirectUrl = " + googleRedirectUrl);
        System.out.println("googleClientSecret = " + googleClientSecret);
        */
    }

    // requestAccessToken()
    // 일회용 코드를 다시 구글로 보내 액세스 토큰을 포함한 JSON String 이 담긴 Response 를 받아온다.
    // RestTemplate 사용: 스프링 프레임워크에서 제공하는 HTTP 통신을 단순화하고 간소화하기 위한 클래스. 주로 RESTful 웹 서비스와의 통신에 사용.
    //                   이 클래스를 사용하면 HTTP 요청을 보내고 응답을 받는 것이 매우 간단해진다.
    //                   postForEntity 메소드는 요청을 보내고 응답을 받아올 때 ResponseEntity 객체로 응답을 받는다.
    public ResponseEntity<String> requestAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();


        // 1차 실패: 실패 사유: Get 이 아니고, Post 라 불가능
        /*
        return "code=" + code +
                "&client_id=" + googleClientId +
                "&client_secret=" + googleClientSecret +
                "&redirect_uri=" + googleRedirectUrl +
                "&grant_type=authorization_code";
        */

        /*   2차 실패 : 실패 사유: RestTemplate 의 반환타입 ResponseEntity 객체 미고려
        // POST 요청을 보낼 때는 요청 본문을 HttpEntity 객체로 랩핑해서 보내야 한다.
        HttpHeaders headers = new HttpHeaders();

        // content-type : x-www-form-urlencoded
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 파라미터
        String params = "code=" + code +
                        "&client_id=" + googleClientId +
                        "&client_secret=" + googleClientSecret +
                        "&redirect_uri=" + googleRedirectUrl +
                        "&grant_type=authorization_code";

        // 완성된 객체
        HttpEntity<String> request = new HttpEntity<>(params, headers);

        restTemplate.postForEntity(GOOGLE_TOKEN_REQUEST_URL, request);
        */
        // String 으로 받는다면?
        /*
        String params = "code=" + code +
                "&client_id=" + googleClientId +
                "&client_secret=" + googleClientSecret +
                "&redirect_uri=" + googleRedirectUrl +
                "&grant_type=authorization_code";
        */

        // HashMap 으로 받는다면?
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", googleClientId);
        params.put("client_secret", googleClientSecret);
        params.put("redirect_uri", googleRedirectUrl);
        params.put("grant_type", "authorization_code");

        // 파라미터는 세개인듯, 두개만 넣으면 에러난다.
        // 1. 요청을 보낼 URL, 2. 요청 본문 HttpEntity 객체, 이는 헤더, 본문등의 정보를 포함. 3. 요청에 대해 응답 본문을 어떤형식으로 받을지 지정
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(GOOGLE_TOKEN_REQUEST_URL, params, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity;
        }

        return null;
    }

    // getAccessToken()
    public OAuthTokenDto getAccessToken(ResponseEntity<String> responseEntity) throws JsonProcessingException {
        return objectMapper.readValue(responseEntity.getBody(), OAuthTokenDto.class);
    }

    // requestUserInfo()
    // access_token을 발급받고 객체로 변환해 Dto를 통해 정보를 담아내는 것까지 성공했기 때문에 이 token을 가지고 사용자의 정보를 요청
    // 그러기 위해선 헤더에 GoogleOauthTokenDto에서 get으로 access_token을 가져와서 삽입
    public ResponseEntity<String> requestUserInfo(OAuthTokenDto dto) {
        HttpHeaders headers = new HttpHeaders();

        // HttpEntity를 하나 생성하여 만들어둔 헤더를 삽입하고
        // URL로 GET 요청과 생성한 HttpEntity를 넣고 return 해준다면 성공적으로 사용자의 정보를 가져올 것
        // 하지만 위와 마찬가지로 이는 자바 객체로 변환시켜주어야 하기 때문에
        // Dto를 통해 사용자의 정보를 담는 과정을 반복한다.
        headers.add("Authorization", "Bearer" + dto.getAccess_token());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(GOOGLE_USERINFO_REQUEST_URL, HttpMethod.GET, request, String.class);
        return response;

    }

    public OAuthUserDto getUserInfo(ResponseEntity<String> response) throws JsonProcessingException {
        return objectMapper.readValue(response.getBody(), OAuthUserDto.class);
    }

}
