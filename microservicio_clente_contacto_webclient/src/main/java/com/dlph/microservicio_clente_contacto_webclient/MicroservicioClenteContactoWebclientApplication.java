package com.dlph.microservicio_clente_contacto_webclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MicroservicioClenteContactoWebclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioClenteContactoWebclientApplication.class, args);
	}

	@Bean
	public WebClient getWebClient(){
		return WebClient.create();
	}
	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}
}
