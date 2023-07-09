package com.bialas.software.metalmakowskadataservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties("properties")
public class MMProperties {
    private String audience;
    private Groups groups;

    @Data
    public static class Groups {
        private String admin;
        private String manager;
        private String regular;
    }
}
