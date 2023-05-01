package com.bialas.software.metalmakowskadataservice.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class JSONWebTokenParser {

    @Nullable
    JSONWebToken parse(String tokenValue) {
        try {
            String[] splitToken = tokenValue.split("\\.");
            if (splitToken.length < 2) {
                log.error("Invalid access token {}", tokenValue);
            } else {
                log.trace("Access token was valid {}", tokenValue);
            }

            String headerBase64 = splitToken[0];
            Map<String, Object> header = decode(headerBase64);

            String payloadBase64 = splitToken[1];
            Map<String, Object> payload = decode(payloadBase64);

            return new JSONWebToken(header, payload, tokenValue);
        } catch (Exception e) {
            log.error("Can not parse JWT", e);
            return null;
        }
    }

    private Map<String, Object> decode(String base64) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String token = new String(decoder.decode(base64));
        return new ObjectMapper().readValue(token, new TypeReference<>() {});
    }
}
