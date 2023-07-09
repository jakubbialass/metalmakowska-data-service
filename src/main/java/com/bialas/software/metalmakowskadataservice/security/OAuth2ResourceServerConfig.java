package com.bialas.software.metalmakowskadataservice.security;

import com.bialas.software.metalmakowskadataservice.security.oauth2.CustomAuthenticationConverter;
import com.bialas.software.metalmakowskadataservice.security.oauth2.CustomJwtDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class OAuth2ResourceServerConfig {

    private final SecurityService securityService;
    private final SecurityRolesService securityRolesService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/api/version"))
                        .permitAll().anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .decoder(new CustomJwtDecoder(this.securityService))
                                .jwtAuthenticationConverter(new CustomAuthenticationConverter(this.securityRolesService))));
        return http.build();
    }
}
