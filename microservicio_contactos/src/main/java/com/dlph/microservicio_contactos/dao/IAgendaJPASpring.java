package com.dlph.microservicio_contactos.dao;

import com.dlph.microservicio_contactos.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IAgendaJPASpring extends JpaRepository<Contacto, Integer> {
    Contacto findByEmail(String email);
    @Transactional
    @Modifying
    @Query("Delete from Contacto c Where c.email=?1")
    void eliminarPorEmail(String email);
}
