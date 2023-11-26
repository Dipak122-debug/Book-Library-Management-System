package com.book.library.issuerms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IssuerMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssuerMsApplication.class, args);
	}

}
