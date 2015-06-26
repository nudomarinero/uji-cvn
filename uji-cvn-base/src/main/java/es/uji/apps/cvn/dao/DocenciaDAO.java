package es.uji.apps.cvn.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.impl.JPAQuery;

import es.uji.apps.cvn.db.DocenciaImpartidaDTO;
import es.uji.apps.cvn.db.QDocenciaImpartidaDTO;
import es.uji.apps.cvn.model.DocenciaImpartida;
import es.uji.commons.db.BaseDAODatabaseImpl;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

@Repository
public class DocenciaDAO extends BaseDAODatabaseImpl
{
    private static final Logger log = Logger.getLogger(DocenciaDAO.class);

    public List<DocenciaImpartida> getDocendiaImpartidaByID(Long personaId)
            throws RegistroNoEncontradoException
    {

        Long mili = System.currentTimeMillis();

        JPAQuery query = new JPAQuery(entityManager);
        QDocenciaImpartidaDTO qDocenciaImpartidaDTO = QDocenciaImpartidaDTO.docenciaImpartidaDTO;

        List<DocenciaImpartidaDTO> docenciaImpartidaDTOList = query
                .from(qDocenciaImpartidaDTO)
                .where(qDocenciaImpartidaDTO.perId.eq(personaId))
                .list(qDocenciaImpartidaDTO);

        List<DocenciaImpartida> docenciaImpartidaList = new ArrayList<DocenciaImpartida>();

        for (DocenciaImpartidaDTO docenciaImpartidaDTO : docenciaImpartidaDTOList)
        {
            docenciaImpartidaList.add(creaDocenciaImpartidaDesde(docenciaImpartidaDTO));
        }

        mili = System.currentTimeMillis()-mili;
        log.info("DocenciaDAO.getDocendiaImpartidaByID " + mili);

        return docenciaImpartidaList;
    }

    private DocenciaImpartida creaDocenciaImpartidaDesde(DocenciaImpartidaDTO docenciaImpartidaDTO)
    {
        DocenciaImpartida docenciaImpartida = new DocenciaImpartida();
        docenciaImpartida.setTipo(docenciaImpartidaDTO.getTipo());
        docenciaImpartida.setTitulacion(docenciaImpartidaDTO.getTitulacion());
        docenciaImpartida.setTitulacionTexto(docenciaImpartidaDTO.getTitulacionTexto());
        docenciaImpartida.setPais(docenciaImpartidaDTO.getPais());
        docenciaImpartida.setRegion(docenciaImpartidaDTO.getRegion());
        docenciaImpartida.setCiudad(docenciaImpartidaDTO.getCiudad());
        docenciaImpartida.setEntidad(docenciaImpartidaDTO.getEntidad());
        docenciaImpartida.setTipoEntidad(docenciaImpartidaDTO.getTipoEntidad());
        docenciaImpartida.setDepartamento(docenciaImpartidaDTO.getDepartamento());
        docenciaImpartida.setNombreAsignatura(docenciaImpartidaDTO.getNombreAsignatura());
        docenciaImpartida.setTipoHorasCreditos(docenciaImpartidaDTO.getTipoHorasCreditos());
        docenciaImpartida.setCreditos(docenciaImpartidaDTO.getCreditos());
        docenciaImpartida.setIdioma(docenciaImpartidaDTO.getIdioma());
        docenciaImpartida.setNumeroVeces(docenciaImpartidaDTO.getNumeroVeces());
        docenciaImpartida.setUltimaVez(docenciaImpartidaDTO.getUltimaVez());

        return docenciaImpartida;
    }
}
