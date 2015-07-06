package es.uji.apps.cvn.dao;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.impl.JPAQuery;

import es.uji.apps.cvn.db.DirrecionTesisDTO;
import es.uji.apps.cvn.db.QDirrecionTesisDTO;
import es.uji.apps.cvn.model.DireccionTesis;
import es.uji.commons.db.BaseDAODatabaseImpl;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

@Repository
public class TesisDAO extends BaseDAODatabaseImpl {


    private static final Logger log = Logger.getLogger(TesisDAO.class);

    public List<DireccionTesis> getTesisPersonaId(Long personaId) throws RegistroNoEncontradoException{



        Long mili = System.currentTimeMillis();

        JPAQuery query = new JPAQuery(entityManager);
        QDirrecionTesisDTO qTesis = QDirrecionTesisDTO.dirrecionTesisDTO;
        List<DirrecionTesisDTO> listaDirrecionTesisDTO = query
                .from(qTesis)
                .where(qTesis.persona.eq(personaId)).list(qTesis);

        List<DireccionTesis> listaTesis = new ArrayList<DireccionTesis>();

        for (DirrecionTesisDTO dirrecionTesisDTO : listaDirrecionTesisDTO)
        {
            listaTesis.add(creaTesisDesde(dirrecionTesisDTO));
        }

        mili = System.currentTimeMillis()-mili;
        log.info("TesisDAO.getTesisPersonaId " + mili);

        return listaTesis;

    }

    private DireccionTesis creaTesisDesde(DirrecionTesisDTO dirrecionTesisDTO) {

        DireccionTesis direccionTesis = new DireccionTesis();
        direccionTesis.setId(dirrecionTesisDTO.getId());
        direccionTesis.setTipoId(dirrecionTesisDTO.getTipoId());
        direccionTesis.setTipoTexto(dirrecionTesisDTO.getTipoTexto());
        direccionTesis.setPersona(dirrecionTesisDTO.getPersona());
        direccionTesis.setTitulo(dirrecionTesisDTO.getTitulo());
        direccionTesis.setCodirectorNombre(dirrecionTesisDTO.getCodirectorNombre());
        direccionTesis.setCodirectorApellido1(dirrecionTesisDTO.getCodirectorApellido1());
        direccionTesis.setCodirectorApellido2(dirrecionTesisDTO.getCodirectorApellido2());
        direccionTesis.setPais(dirrecionTesisDTO.getPais());
        direccionTesis.setRegion(dirrecionTesisDTO.getRegion());
        direccionTesis.setCiudad(dirrecionTesisDTO.getCiudad());
        direccionTesis.setEntidad(dirrecionTesisDTO.getEntidad());
        direccionTesis.setTipoEntidad(dirrecionTesisDTO.getTipoEntidad());
        direccionTesis.setAlumnoNombre(dirrecionTesisDTO.getAlumnoNombre());
        direccionTesis.setAlumnoApellido1(dirrecionTesisDTO.getAlumnoApellido1());
        direccionTesis.setAlumnoApellido2(dirrecionTesisDTO.getAlumnoApellido2());
        direccionTesis.setFechaLectura(dirrecionTesisDTO.getFechaLectura());
        direccionTesis.setCalificacion(dirrecionTesisDTO.getCalificacion());
        direccionTesis.setDoctorEuropeo(dirrecionTesisDTO.getDoctorEuropeo().equals("S"));
        direccionTesis.setFechaDoctorEuropeo(dirrecionTesisDTO.getFechaDoctorEuropeo());

        String mc = dirrecionTesisDTO.getMencionCalidad();
        if (mc!=null)
           direccionTesis.setMencionCalidad(mc.equals("S"));


        return direccionTesis;
    }
}
