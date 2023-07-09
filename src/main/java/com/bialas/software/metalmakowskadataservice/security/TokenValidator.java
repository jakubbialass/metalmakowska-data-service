package com.bialas.software.metalmakowskadataservice.security;

import com.bialas.software.metalmakowskadataservice.config.MMProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class TokenValidator {
    private final MMProperties properties;

    boolean isValid(JSONWebToken jsonWebToken) {
        try {
            Jwt jwtToken = jsonWebToken.toJwt();
            Map<String, Object> claims = jwtToken.getClaims();
            return claims.get(JwtClaimNames.AUD).equals(properties.getAudience());
        } catch (JwtException e) {
            log.error("Could not parse token. " + e.getMessage());
            return false;
        }
    }
}
