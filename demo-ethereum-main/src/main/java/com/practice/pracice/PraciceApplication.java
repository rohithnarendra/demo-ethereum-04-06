package com.practice.pracice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;

@SpringBootApplication(exclude = SpringDataWebAutoConfiguration.class)
public class PraciceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PraciceApplication.class, args);
	}

}
