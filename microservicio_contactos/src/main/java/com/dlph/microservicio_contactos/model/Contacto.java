package com.dlph.microservicio_contactos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "contactos")

@NamedQuery(name = "Contacto.findAll", query = "SELECT c FROM Contacto c")
@Data
public class Contacto {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContacto;
    private int edad;
    private String email;
    private String nombre;

}
