package com.bialas.software.metalmakowskadataservice.security.oauth2;

import com.bialas.software.metalmakowskadataservice.security.SecurityRolesService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CustomAuthenticationConverter implements Converter<Jwt, JwtAuthenticationToken> {

    private final SecurityRolesService securityRolesService;
    private static final String GROUPS_CLAIM = "groups";

    @Override
    public JwtAuthenticationToken convert(@NotNull Jwt jwt) {
        Set<GrantedAuthority> grantedAuthorities = getAuthorities(jwt);
        return new JwtAuthenticationToken(jwt, grantedAuthorities);
    }

    private Set<GrantedAuthority> getAuthorities(@NotNull Jwt jwt) {
        @SuppressWarnings("unchecked cast")
        List<String> groups = (List<String>) jwt.getClaims().get(GROUPS_CLAIM);
        Set<String> roles = securityRolesService.getRoles(groups);
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toUnmodifiableSet());
    }
}
