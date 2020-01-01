package com.mimacom.back;

import com.mimacom.back.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(WebConfig.class);
		springApplication.setAdditionalProfiles("h2");
		springApplication.run();
	}
}
