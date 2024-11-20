package com.dlph.microservicio_clente_contacto_webclient.controller;

import com.dlph.microservicio_clente_contacto_webclient.model.Persona;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@RestController
public class PersonaController {

    @Autowired
    WebClient.Builder webClientBuilder;

    String url = "http://localhost:8080";
    String user = "admin";
    String password = "admin";
    String token;

    @PostConstruct
    public void autenticar() {
        try {
            token = webClientBuilder.build()
                    .post()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("http")
                            .host("localhost")
                            .port(8080)
                            .path("/login")
                            .queryParam("user", user)
                            .queryParam("password", password)
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }   catch (WebClientResponseException e) {
            System.err.println("Error en la autenticación: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }

    @GetMapping(value = "/personas/{nombre}/{email}/{edad}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> altaPersona(@PathVariable("nombre") String nombre, @PathVariable("email") String email, @PathVariable("edad") int edad) {
        Persona persona = new Persona(nombre, email, edad);

        webClientBuilder.build()
                .post()
                .uri(url + "/contactos")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .body(BodyInserters.fromValue(persona))
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        Persona[] personas = webClientBuilder.build()
                .get()
                .uri(url + "/contactos")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(Persona[].class)
                .block();

        return Arrays.asList(personas);
    }

    private String getBase64(String usuario, String password) {
        String credentials = usuario + ":" + password;
        return Base64.getEncoder().encodeToString(credentials.getBytes());
    }
}
