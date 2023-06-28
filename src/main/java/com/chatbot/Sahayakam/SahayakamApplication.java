package com.chatbot.Sahayakam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class SahayakamApplication {
	private static final Logger logger = LogManager.getLogger(SahayakamApplication.class);

	public static void main(String[] args) {

		logger.info("Application Started");
		SpringApplication.run(SahayakamApplication.class, args);
	}

}
