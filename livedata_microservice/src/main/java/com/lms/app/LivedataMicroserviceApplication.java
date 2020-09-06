package com.lms.app;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lms.app.mqtt.Subscriber;

@SpringBootApplication
public class LivedataMicroserviceApplication {
	public static File file = new File("../LiveDataJson.json");

	public static void main(String[] args) {
		SpringApplication.run(LivedataMicroserviceApplication.class, args);
		
		final Subscriber subscriber = new Subscriber();
	    subscriber.start();
	}

}
