package com.bialas.software.metalmakowskadataservice.version;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class VersionController {
    private static final String API_VERSION = "0.0.1";

    @GetMapping("/version")
    public String getVersion() {
        log.info("The version of the backend is " + API_VERSION);
        return "API VERSION = " + API_VERSION;
    }
}