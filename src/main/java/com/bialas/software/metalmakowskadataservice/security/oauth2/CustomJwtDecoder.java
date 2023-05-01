package com.bialas.software.metalmakowskadataservice.security.oauth2;
import com.bialas.software.metalmakowskadataservice.security.JSONWebToken;
import com.bialas.software.metalmakowskadataservice.security.SecurityService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;

public class CustomJwtDecoder implements JwtDecoder {

    private final SecurityService securityService;

    public CustomJwtDecoder(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public Jwt decode(String tokenValue) throws JwtException {
        JSONWebToken token = securityService.parse(tokenValue);
        if (token == null) {
            throw new JwtException("Could not parse JwtToken");
        }
        if (!securityService.isValid(token)) {
            throw new JwtException("Provided JwtToken is not valid");
        }

        return token.toJwt();
    }
}
