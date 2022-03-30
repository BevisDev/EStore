package com.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BevisShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BevisShopApplication.class, args);
	}

}
