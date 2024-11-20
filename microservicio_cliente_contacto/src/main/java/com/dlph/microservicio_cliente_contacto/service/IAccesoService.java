package com.dlph.microservicio_cliente_contacto.service;

import com.dlph.microservicio_cliente_contacto.model.Persona;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IAccesoService {
    CompletableFuture<List<Persona>> llamadaServicio(Persona persona);
}
