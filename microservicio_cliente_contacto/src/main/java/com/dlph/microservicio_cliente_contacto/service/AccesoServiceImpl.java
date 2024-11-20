package com.dlph.microservicio_cliente_contacto.service;

import com.dlph.microservicio_cliente_contacto.model.Persona;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AccesoServiceImpl implements IAccesoService {
    @Autowired
    RestTemplate template;
    String url = "http://localhost:8080";

    @Async
    public CompletableFuture<List<Persona>> llamadaServicio(Persona persona){
        return CompletableFuture.completedFuture(llamada(persona).getBody());
    }

    public ResponseEntity<List<Persona>> llamada(Persona persona){
        try {
            template.postForLocation(url + "/contactos", persona);
            Persona[] personas = template.getForObject(url + "/contactos", Persona[].class);
            log.info("Persona creada: ", persona.getEmail());
            return new ResponseEntity<List<Persona>>(Arrays.asList(personas), HttpStatus.OK);
        } catch (HttpStatusCodeException exception){
            HttpHeaders headers = new HttpHeaders();
            headers.add("error", exception.getResponseBodyAsString());
            log.error("Persona existente!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return new ResponseEntity<>(null, headers, HttpStatus.CONFLICT);
        }
    }

}
