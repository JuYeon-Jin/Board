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

    private final String googleLoginUrl = "https://accounts.google.com/o/oauth2/v2/auth";
    private final String GOOGLE_TOKEN_REQUEST_URL = "https://oauth2.googleapis.com/token";
    private final String GOOGLE_USERINFO_REQUEST_URL = "https://www.googleapis.com/oauth2/v1/userinfo";

    @Value("${app.google.clientId}")
    private String googleClientId;
    @Value("${app.google.redirectURL}")
    private String googleRedirectUrl;
    @Value("${app.google.clientSecret}")
    private String googleClientSecret;

    private final String responseType = "code";
    private final String scope = "https://www.googleapis.com/auth/userinfo.email";

    public String returnUrl() {

        return googleLoginUrl +
                "?client_id=" + googleClientId +
                "&response_type=" + responseType +
                "&redirect_uri=" + googleRedirectUrl +
                "&scope=" + scope;

    }

    public OAuthTokenDto requestAccessToken(String code) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", googleClientId);
        params.put("client_secret", googleClientSecret);
        params.put("redirect_uri", googleRedirectUrl);
        params.put("grant_type", "authorization_code");

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(GOOGLE_TOKEN_REQUEST_URL, params, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return objectMapper.readValue(responseEntity.getBody(), OAuthTokenDto.class);
        }

        return null;
    }

    public OAuthUserDto requestUserInfo(OAuthTokenDto dto) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Bearer" + dto.getAccess_token());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(GOOGLE_USERINFO_REQUEST_URL, HttpMethod.GET, request, String.class);

        System.out.println("response = " + response);

        return objectMapper.readValue(response.getBody(), OAuthUserDto.class);

    }

}
