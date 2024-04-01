package com.nhnacademy.backendserver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "nhn.student")
public class StudentProperties {
    private String name;
}
