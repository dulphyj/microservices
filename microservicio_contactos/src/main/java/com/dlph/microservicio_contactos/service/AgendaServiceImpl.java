package com.dlph.microservicio_contactos.service;

import com.dlph.microservicio_contactos.dao.AgendaDao;
import com.dlph.microservicio_contactos.model.Contacto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AgendaServiceImpl implements AgendaService{
    @Autowired
    AgendaDao agendaDao;


    @Override
    public void agregarContacto(Contacto contacto) throws Exception {
        if(agendaDao.recuperarContacto(contacto.getEmail())==null){
            agendaDao.agregarContacto(contacto);
            log.info("COntacto : ", contacto.getEmail());
        } else {
            log.error("contacto ya existente !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            throw new Exception("El contacto ya existe");
        }

    }

    @Override
    public List<Contacto> recuperarCOntactos() {
        return agendaDao.devolverContactos();
    }

    @Override
    public void actualizarContacto(Contacto contacto) {
        if(agendaDao.recuperarContacto(contacto.getIdContacto())!=null){
            agendaDao.actualizarContacto(contacto);
        }
    }

    @Override
    public boolean eliminarCOntacto(int idContacto) {
        if(agendaDao.recuperarContacto(idContacto)!=null){
            agendaDao.eliminarContacto(idContacto);
            return true;
        }
        return false;
    }

    @Override
    public Contacto buscarContacto(int idContacto) {
        return agendaDao.recuperarContacto(idContacto);
    }
}
