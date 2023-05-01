package com.bialas.software.metalmakowskadataservice.security;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final JSONWebTokenParser jsonWebTokenParser = new JSONWebTokenParser();

    @Nullable
    public JSONWebToken parse(String token) {
        return jsonWebTokenParser.parse(token);
    }

    // TODO: add validation
    public boolean isValid(JSONWebToken token) {
        return true;
    }
}
