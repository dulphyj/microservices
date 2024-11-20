package com.dlph.respuesta_personalizada.controller;

import com.dlph.respuesta_personalizada.model.Country;
import com.dlph.respuesta_personalizada.service.ICountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountriesController {
    @Autowired
    ICountriesService service;
    @GetMapping(value = "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Country> countries(){
        return service.getCountries();
    }

    @GetMapping(value = "/countries/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Country> countries(@PathVariable("name") String name){
        return service.findCountries(name);
    }
}
