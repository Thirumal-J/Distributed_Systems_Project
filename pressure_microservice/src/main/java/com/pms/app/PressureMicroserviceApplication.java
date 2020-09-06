package com.pms.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pms.app.mqtt.Subscriber;

@SpringBootApplication
@EnableAutoConfiguration
public class PressureMicroserviceApplication {

	private static final Logger logger = LoggerFactory.getLogger(PressureMicroserviceApplication.class);

	public static void main(String[] args) {
		logger.info("Init the application...");
		SpringApplication.run(PressureMicroserviceApplication.class, args);
		final Subscriber subscriber = new Subscriber();
        subscriber.start();
		}
}
