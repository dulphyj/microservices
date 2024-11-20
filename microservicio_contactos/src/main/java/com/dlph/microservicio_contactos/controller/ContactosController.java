package com.dlph.microservicio_contactos.controller;

import com.dlph.microservicio_contactos.model.Contacto;
import com.dlph.microservicio_contactos.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.*;

@RestController
public class ContactosController {
    @Autowired
    AgendaService service;

    @GetMapping(value = "/contactos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Contacto> recuperarContactos(){
        return service.recuperarCOntactos();
    }

    @GetMapping(value = "/contactos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Contacto recuperarContacto(@PathVariable("id") int id){
        return service.buscarContacto(id);
    }

    @PostMapping(value = "/contactos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public void guardarContacto(@RequestBody Contacto contacto) throws Exception {
        service.agregarContacto(contacto);
    }

    @PutMapping(value = "/contactos", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void actualizarContacto(@RequestBody Contacto contacto){
        service.actualizarContacto(contacto);
    }

    @DeleteMapping(value = "/contactos/{id}")
    public void eliminarPorId(@PathVariable("id") int idContacto){
        service.eliminarCOntacto(idContacto);
    }
}
