package com.hms.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hms.app.mqtt.Subscriber;

@SpringBootApplication
@EnableAutoConfiguration
public class HumidityMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HumidityMicroserviceApplication.class, args);
		final Subscriber subscriber = new Subscriber();
	    subscriber.start();
	}
}
