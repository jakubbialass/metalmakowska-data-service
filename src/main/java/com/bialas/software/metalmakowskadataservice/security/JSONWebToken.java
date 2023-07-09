package com.bialas.software.metalmakowskadataservice.security;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.jwt.JwtException;

import java.time.Instant;
import java.util.Map;

public final class JSONWebToken {
    private final String rawTokenValue;
    private final Map<String, Object> header, payload;

    /**
     * @param h decoded header
     * @param p decoded payload
     * @param t raw token value
     */
    public JSONWebToken(Map<String, Object> h, Map<String, Object> p, String t) {
        this.header = h;
        this.payload = p;
        this.rawTokenValue = t;
    }

    public Jwt toJwt() {
        try {
            return Jwt.withTokenValue(this.rawTokenValue)
                    .headers(h -> h.putAll(this.header))
                    .claims(c -> {
                        c.putAll(this.payload);
                        c.put(JwtClaimNames.IAT, toInstant(c.get(JwtClaimNames.IAT)));
                        c.put(JwtClaimNames.EXP, toInstant(c.get(JwtClaimNames.EXP)));
                    })
                    .build();
        } catch (Exception e) {
            throw new JwtException("Could not parse token");
        }
    }

    private static Instant toInstant(Object o) {
        return Instant.ofEpochSecond(Long.valueOf((Integer) o));
    }
}
