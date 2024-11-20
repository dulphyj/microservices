package com.dlph.microservicio_cliente_contacto.controller;

import com.dlph.microservicio_cliente_contacto.model.Persona;
import com.dlph.microservicio_cliente_contacto.service.IAccesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@RestController
public class PersonasController {
    @Autowired
    IAccesoService accesoService;

    @GetMapping(value = "/personas/{nombre}/{email}/{edad}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> altaPersona(@PathVariable("nombre") String nombre, @PathVariable("email") String email, @PathVariable("edad") int edad){
        Persona persona = new Persona(nombre, email, edad);
        CompletableFuture<List<Persona>>  res = accesoService.llamadaServicio(persona);
        try {
            return res.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    /*@GetMapping(value = "/personas/{edad1}/{edad2}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> buscarEdades(@PathVariable("edad1") int edad1, @PathVariable("edad2") int edad2){
        Persona[] personas = template.getForObject(url + "/contactos", Persona[].class);
        return Arrays.stream(personas)
                .filter(p -> p.getEdad()>=edad1&&p.getEdad()<=edad2)
                .collect(Collectors.toList());
    }*/
}
