package com.josepoc.audit.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Profile("test")
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "mysql.embedded")
public class EmbeddedMysqlProperties {

    private int port;
    private String user;
    private String password;
    private String schema;
}
