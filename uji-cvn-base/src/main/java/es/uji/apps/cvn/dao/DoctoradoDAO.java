package es.uji.apps.cvn.dao;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.impl.JPAQuery;

import es.uji.apps.cvn.db.DoctoradoDTO;
import es.uji.apps.cvn.db.QDoctoradoDTO;
import es.uji.apps.cvn.model.Doctorado;
import es.uji.commons.db.BaseDAODatabaseImpl;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

@Repository
public class DoctoradoDAO extends BaseDAODatabaseImpl {


    private static final Logger log = Logger.getLogger(DoctoradoDAO.class);

    public List<Doctorado> getDoctoradoPersonaId(Long personaId) throws RegistroNoEncontradoException{



        Long mili = System.currentTimeMillis();

        JPAQuery query = new JPAQuery(entityManager);
        QDoctoradoDTO qDoctorado = QDoctoradoDTO.doctoradoDTO;
        List<DoctoradoDTO> listaDoctoradoDTO = query
                .from(qDoctorado)
                .where(qDoctorado.persona.eq(personaId).and(qDoctorado.tesisPropia.eq(1))).list(qDoctorado);

        List<Doctorado> doctorados = new ArrayList<Doctorado>();

        for (DoctoradoDTO doctoradoDTO : listaDoctoradoDTO)
        {
            doctorados.add(creaDoctoradoDesde(doctoradoDTO));
        }

        mili = System.currentTimeMillis()-mili;
        log.info("DoctoradoDAO.getDoctoradoPersonaId " + mili);

        return doctorados;

    }

    private Doctorado creaDoctoradoDesde(DoctoradoDTO doctoradoDTO) {

        Doctorado doctorado = new Doctorado();

        doctorado.setId(doctoradoDTO.getId());
        doctorado.setPrograma(doctoradoDTO.getPrograma());
        doctorado.setSuficiencia(doctoradoDTO.getSuficiencia());
        doctorado.setSuficienciaFecha(doctoradoDTO.getSuficienciaFecha());
        doctorado.setPais(doctoradoDTO.getPais());
        doctorado.setRegion(doctoradoDTO.getRegion());
        doctorado.setCiudad(doctoradoDTO.getCiudad());
        doctorado.setEntidad(doctoradoDTO.getEntidad());
        doctorado.setTipoEntidad(doctoradoDTO.getTipoEntidad());
        doctorado.setTitulacionFecha(doctoradoDTO.getTitulacionFecha());
        doctorado.setFechaDoctorEuropeo(doctoradoDTO.getFechaDoctorEuropeo());
        doctorado.setTitulo(doctoradoDTO.getTitulo());
        doctorado.setDirectorNombre(doctoradoDTO.getDirectorNombre());
        doctorado.setDirectorApellido1(doctoradoDTO.getDirectorApellido1());
        doctorado.setDirectorApellido2(doctoradoDTO.getDirectorApellido2());
        doctorado.setCodirectorNombre(doctoradoDTO.getCodirectorNombre());
        doctorado.setCodirectorApellido1(doctoradoDTO.getCodirectorApellido1());
        doctorado.setCodirectorApellido2(doctoradoDTO.getCodirectorApellido2());
        doctorado.setCalificacion(doctoradoDTO.getCalificacion());

        String mc = doctoradoDTO.getMencionCalidad();
        if (mc!=null)
        {
            doctorado.setIsMencion(mc.equals("S"));
            doctorado.setMencionFecha(doctoradoDTO.getFechaMencionCalidad());
        }

        String pre = doctoradoDTO.getPremio();
        if (pre!=null){
            doctorado.setIsPremiado(pre.equals("S"));
            doctorado.setPremioFecha(doctoradoDTO.getPremioFecha());
        }

        String de = doctoradoDTO.getDoctorEuropeo();
        if (de!=null)
        {
            doctorado.setIsDoctorEuropeo(de.equals("S"));
            doctorado.setFechaDoctorEuropeo(doctoradoDTO.getFechaDoctorEuropeo());
        }


        return doctorado;
    }
}
