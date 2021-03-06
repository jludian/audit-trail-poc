package com.josepoc.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class AuditTrailApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditTrailApplication.class, args);
	}
}
