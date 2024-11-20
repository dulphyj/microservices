package com.dlph.respuesta_personalizada.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Country {
    private String name;
    //private String capital; //"error with this value"
    private int population;
    private String flag;

}
