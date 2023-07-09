package com.bialas.software.metalmakowskadataservice.security;

import com.bialas.software.metalmakowskadataservice.config.MMProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class SecurityRolesService {

    private final MMProperties properties;
    private static final Set<String> REGULAR_ROLES = Set.of("REGULAR");
    private static final Set<String> MANAGER_ROLES = getExtendedSet(REGULAR_ROLES, "MANAGER");
    private static final Set<String> ADMIN_ROLES = getExtendedSet(MANAGER_ROLES, "ADMIN");


    public Set<String> getRoles(List<String> groups) {
        if (groups.contains(properties.getGroups().getAdmin())) {
            return ADMIN_ROLES;
        } else if (groups.contains(properties.getGroups().getManager())) {
            return MANAGER_ROLES;
        } else if (groups.contains(properties.getGroups().getRegular())) {
            return REGULAR_ROLES;
        } else {
            return Set.of();
        }
    }
    private static Set<String> getExtendedSet(Set<String> set, String s) {
        HashSet<String> hashSet = new HashSet<>(set);
        hashSet.add(s);
        return Set.copyOf(hashSet);
    }
}
