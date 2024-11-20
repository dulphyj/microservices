package com.dlph.respuesta_personalizada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RespuestaPersonalizadaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RespuestaPersonalizadaApplication.class, args);
	}

	@Bean
	public RestTemplate template(){
		return new RestTemplate();
	}

}
