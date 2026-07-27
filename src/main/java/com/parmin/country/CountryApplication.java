package com.parmin.country;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@EnableCaching
@SpringBootApplication
public class CountryApplication {
	public static void main(String[] args) {
		SpringApplication.run(CountryApplication.class, args);
	}

}
