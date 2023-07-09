package com.bialas.software.metalmakowskadataservice.version;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api")
public class VersionController {
    private static final String API_VERSION = "0.2.0";

    @GetMapping("/version")
    public String getVersion() {
        log.info("The version of the backend is " + API_VERSION);
        return "API VERSION = " + API_VERSION;
    }

    @GetMapping("/secured")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getSecured() {
        return "You are high privileged person as you see this.";
    }
}
