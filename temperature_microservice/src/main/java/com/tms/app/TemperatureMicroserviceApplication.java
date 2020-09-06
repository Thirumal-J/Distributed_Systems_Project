package com.tms.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tms.app.mqtt.Subscriber;

@SpringBootApplication
@EnableAutoConfiguration
public class TemperatureMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemperatureMicroserviceApplication.class, args);
		final Subscriber subscriber = new Subscriber();
	    subscriber.start();
	}
}
