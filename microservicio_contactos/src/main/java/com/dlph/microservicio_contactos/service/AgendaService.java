package com.dlph.microservicio_contactos.service;

import com.dlph.microservicio_contactos.model.Contacto;

import java.util.List;

public interface AgendaService {
    void agregarContacto(Contacto contacto) throws Exception;
    List<Contacto> recuperarCOntactos();
    void actualizarContacto(Contacto contacto);
    boolean eliminarCOntacto(int idContacto);
    Contacto buscarContacto(int idContacto);
}
