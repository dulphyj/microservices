package com.dlph.microservicio_clente_contacto_webclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Persona {
    private String nombre;
    private String email;
    private int edad;
}
