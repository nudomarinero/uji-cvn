package es.uji.apps.cvn.dao;


import java.util.ArrayList;
import java.util.List;

import es.uji.apps.cvn.db.DireccionTesisDTO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.impl.JPAQuery;

import es.uji.apps.cvn.db.QDireccionTesisDTO;
import es.uji.apps.cvn.model.DireccionTesis;
import es.uji.commons.db.BaseDAODatabaseImpl;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

@Repository
public class TesisDAO extends BaseDAODatabaseImpl {


    private static final Logger log = Logger.getLogger(TesisDAO.class);

    public List<DireccionTesis> getTesisPersonaId(Long personaId) throws RegistroNoEncontradoException{



        Long mili = System.currentTimeMillis();

        JPAQuery query = new JPAQuery(entityManager);
        QDireccionTesisDTO qTesis = QDireccionTesisDTO.direccionTesisDTO;
        List<DireccionTesisDTO> listaDireccionTesisDTO = query
                .from(qTesis)
                .where(qTesis.persona.eq(personaId).and(qTesis.tesisPropia.eq(0))).list(qTesis);

        List<DireccionTesis> listaTesis = new ArrayList<DireccionTesis>();

        for (DireccionTesisDTO direccionTesisDTO : listaDireccionTesisDTO)
        {
            listaTesis.add(creaTesisDesde(direccionTesisDTO));
        }

        mili = System.currentTimeMillis()-mili;
        log.info("TesisDAO.getDoctoradoPersonaId " + mili);

        return listaTesis;

    }

    private DireccionTesis creaTesisDesde(DireccionTesisDTO direccionTesisDTO) {

        DireccionTesis direccionTesis = new DireccionTesis();
        direccionTesis.setId(direccionTesisDTO.getId());
        direccionTesis.setTipoId(direccionTesisDTO.getTipoId());
        direccionTesis.setTipoTexto(direccionTesisDTO.getTipoTexto());
        direccionTesis.setPersona(direccionTesisDTO.getPersona());
        direccionTesis.setTitulo(direccionTesisDTO.getTitulo());
        direccionTesis.setCodirectorNombre(direccionTesisDTO.getCodirectorNombre());
        direccionTesis.setCodirectorApellido1(direccionTesisDTO.getCodirectorApellido1());
        direccionTesis.setCodirectorApellido2(direccionTesisDTO.getCodirectorApellido2());
        direccionTesis.setPais(direccionTesisDTO.getPais());
        direccionTesis.setRegion(direccionTesisDTO.getRegion());
        direccionTesis.setCiudad(direccionTesisDTO.getCiudad());
        direccionTesis.setEntidad(direccionTesisDTO.getEntidad());
        direccionTesis.setTipoEntidad(direccionTesisDTO.getTipoEntidad());
        direccionTesis.setAlumnoNombre(direccionTesisDTO.getAlumnoNombre());
        direccionTesis.setAlumnoApellido1(direccionTesisDTO.getAlumnoApellido1());
        direccionTesis.setAlumnoApellido2(direccionTesisDTO.getAlumnoApellido2());
        direccionTesis.setFechaLectura(direccionTesisDTO.getFechaLectura());
        direccionTesis.setCalificacion(direccionTesisDTO.getCalificacion());
        direccionTesis.setDoctorEuropeo(direccionTesisDTO.getDoctorEuropeo().equals("S"));
        direccionTesis.setFechaDoctorEuropeo(direccionTesisDTO.getFechaDoctorEuropeo());

        String mc = direccionTesisDTO.getMencionCalidad();
        if (mc!=null)
           direccionTesis.setMencionCalidad(mc.equals("S"));


        return direccionTesis;
    }
}
