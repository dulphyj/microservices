package com.dlph.respuesta_personalizada.service;

import com.dlph.respuesta_personalizada.model.Country;

import java.util.List;

public interface ICountriesService {
    List<Country> getCountries();
    List<Country> findCountries(String name);
}
