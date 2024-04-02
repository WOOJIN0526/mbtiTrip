package com.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MbtiTripApplication {

	public static void main(String[] args) {
		SpringApplication.run(MbtiTripApplication.class, args);
	}

}
