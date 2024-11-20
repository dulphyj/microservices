package com.dlph.microservicio_cliente_contacto.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Persona {
    private String nombre;
    private String email;
    private int edad;
}
