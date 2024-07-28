package com.book.library.issuerms.utility;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class Config {
	@Value("${bookMs.base.url}")
    private String baseUrl;
	@Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
        		.rootUri(baseUrl)
                .build();
    }

	@Bean
	public WebClient webClient() {
	  return WebClient.builder().baseUrl(baseUrl).build();
	}
	
}
