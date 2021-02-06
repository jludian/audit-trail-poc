package com.josepoc.audit.configuration;

import java.io.IOException;
import java.net.ServerSocket;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.Charset;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.distribution.Version;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.SchemaConfig.aSchemaConfig;

@Profile("test")
@Configuration
@RequiredArgsConstructor
@Slf4j
public class EmbeddedMysqlConfiguration {

    private final EmbeddedMysqlProperties properties;

    @Bean
    @Primary
    public DataSource dataSource(EmbeddedMysql embeddedMysql) {
        MysqldConfig config = embeddedMysql.getConfig();
        return DataSourceBuilder.create()
                                .url(String.format("jdbc:mysql://localhost:%s/%s", config.getPort(), properties.getSchema()))
                                .username(config.getUsername())
                                .password(config.getPassword())
                                .build();
    }

    @Bean
    public EmbeddedMysql embeddedMysql(MysqldConfig embeddedMysqlConfig) {
        log.info("starting embedded mysql at port {}", embeddedMysqlConfig.getPort());
        return anEmbeddedMysql(embeddedMysqlConfig)
                   .addSchema(aSchemaConfig(properties.getSchema()).build())
                   .start();
    }

    @Bean
    public MysqldConfig embeddedMysqlConfig() {
        return MysqldConfig.aMysqldConfig(Version.v5_7_18)
                           .withPort(port(properties.getPort()))
                           .withCharset(Charset.UTF8MB4)
                           .withUser(properties.getUser(), properties.getPassword())
                           .build();
    }

    private int port(int port) {
        try {
            return new ServerSocket(port).getLocalPort();
        } catch (IOException e) {
            return randomPort();
        }
    }

    private int randomPort() {
        try {
            return new ServerSocket(0).getLocalPort();
        } catch (IOException e) {
            throw new RuntimeException("error while enabling embedded database port", e);
        }
    }
}
