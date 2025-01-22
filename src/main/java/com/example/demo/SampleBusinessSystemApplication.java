package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example", "com.home"})
public class SampleBusinessSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleBusinessSystemApplication.class, args);
	}

}
