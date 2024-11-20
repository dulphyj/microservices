package com.dlph.respuesta_personalizada.service;

import com.dlph.respuesta_personalizada.model.Country;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountriesServiceImpl implements ICountriesService{
    String url = "https://restcountries.com/v2/all";
    @Autowired
    RestTemplate template;


    @Override
    public List<Country> getCountries() {
        String res = template.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Country> countries = new ArrayList<>();
        ArrayNode array;
        try {
            array = (ArrayNode)mapper.readTree(res);
            for(Object ob:array){
                ObjectNode json = (ObjectNode) ob;
                countries.add(new Country(json.get("name").asText(),
                        json.get("population").asInt(),
                        json.get("flag").asText()));
            }
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public List<Country> findCountries(String name) {
        return getCountries()
                .stream()
                .filter(c -> c.getName().contains(name))
                .collect(Collectors.toList());
    }
}
