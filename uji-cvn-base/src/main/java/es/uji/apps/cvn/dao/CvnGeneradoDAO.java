package es.uji.apps.cvn.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.impl.JPAQuery;

import es.uji.apps.cvn.db.CvnGeneradoDTO;
import es.uji.apps.cvn.db.QCvnGeneradoDTO;
import es.uji.apps.cvn.model.CvnGenerado;
import es.uji.commons.db.BaseDAODatabaseImpl;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

@Repository
public class CvnGeneradoDAO extends BaseDAODatabaseImpl
{
    private CvnGeneradoDTO creaCvnGeneradoDTOFrom(CvnGenerado cvnGenerado)
    {
        CvnGeneradoDTO cvnGeneradoDTO = new CvnGeneradoDTO();

        cvnGeneradoDTO.setId(cvnGenerado.getId());
        cvnGeneradoDTO.setPersonaId(cvnGenerado.getPersonaId());
        cvnGeneradoDTO.setCvn(cvnGenerado.getCvn());
        cvnGeneradoDTO.setFechaUltimaActualizacion(cvnGenerado.getFechaUltimaActualizacion());
        cvnGeneradoDTO.setEstado(cvnGenerado.getEstado());
        cvnGeneradoDTO.setMensaje(cvnGenerado.getMensaje());
        cvnGeneradoDTO.setSolicitante(cvnGenerado.getSolicitante());
        cvnGeneradoDTO.setTemplate(cvnGenerado.getTemplate());
        cvnGeneradoDTO.setIdioma(cvnGenerado.getIdioma());
        cvnGeneradoDTO.setPlantillaId(cvnGenerado.getPlantillaId());

        return cvnGeneradoDTO;
    }

    public void resetCvnGenerado(CvnGenerado cvnGenerado)
    {
        JPAQuery query = new JPAQuery(entityManager);
        QCvnGeneradoDTO qCvnGenerado = QCvnGeneradoDTO.cvnGeneradoDTO;

        List<CvnGeneradoDTO> listaCvnsGeneradosDTO = query
                .from(qCvnGenerado)
                .where(qCvnGenerado.personaId.eq(cvnGenerado.getPersonaId()).and(
                        qCvnGenerado.solicitante.eq(cvnGenerado.getSolicitante())))
                .list(qCvnGenerado);

        for (CvnGeneradoDTO cvnGeneradoDTO : listaCvnsGeneradosDTO)
        {
            delete(CvnGeneradoDTO.class, cvnGeneradoDTO.getId());
        }

        CvnGeneradoDTO generado = insert(creaCvnGeneradoDTOFrom(cvnGenerado));
        cvnGenerado.setId(generado.getId());
    }

    public CvnGenerado getCvnGeneradoByPersonaId(Long personaId, Long solicitante)
            throws RegistroNoEncontradoException
    {
        JPAQuery query = new JPAQuery(entityManager);
        QCvnGeneradoDTO qCvnGenerado = QCvnGeneradoDTO.cvnGeneradoDTO;

        CvnGeneradoDTO cvnGeneradoDTO = query
                .from(qCvnGenerado)
                .where(qCvnGenerado.personaId.eq(personaId).and(
                        qCvnGenerado.solicitante.eq(solicitante))).singleResult(qCvnGenerado);

        if (cvnGeneradoDTO == null)
        {
            throw new RegistroNoEncontradoException();
        }

        return creaCvnGeneradoFrom(cvnGeneradoDTO);
    }

    private CvnGenerado creaCvnGeneradoFrom(CvnGeneradoDTO cvnGeneradoDTO)
    {
        CvnGenerado cvnGenerado = new CvnGenerado();

        cvnGenerado.setId(cvnGeneradoDTO.getId());
        cvnGenerado.setPersonaId(cvnGeneradoDTO.getPersonaId());
        cvnGenerado.setCvn(cvnGeneradoDTO.getCvn());
        cvnGenerado.setFechaUltimaActualizacion(cvnGeneradoDTO.getFechaUltimaActualizacion());
        cvnGenerado.setEstado(cvnGeneradoDTO.getEstado());
        cvnGenerado.setMensaje(cvnGeneradoDTO.getMensaje());
        cvnGenerado.setSolicitante(cvnGeneradoDTO.getSolicitante());
        cvnGenerado.setTemplate(cvnGeneradoDTO.getTemplate());
        cvnGenerado.setIdioma(cvnGeneradoDTO.getIdioma());
        cvnGenerado.setPlantillaId(cvnGeneradoDTO.getPlantillaId());

        return cvnGenerado;
    }

    public void actualizaCvn(CvnGenerado cvnGenerado)
    {
        try
        {
            update(creaCvnGeneradoDTOFrom(cvnGenerado));
        }
        catch (Exception e)
        {
        }
    }

    public List<CvnGenerado> getListaCvnsGeneradosBySolicitante(Long solicitante)
    {
        JPAQuery query = new JPAQuery(entityManager);
        QCvnGeneradoDTO qCvnGenerado = QCvnGeneradoDTO.cvnGeneradoDTO;

        List<CvnGeneradoDTO> listaCvnsGeneradosDTO = query.from(qCvnGenerado)
                .where(qCvnGenerado.solicitante.eq(solicitante)).list(qCvnGenerado);

        List<CvnGenerado> listaCvnsGenerados = new ArrayList<CvnGenerado>();

        for (CvnGeneradoDTO cvnGeneradoDTO : listaCvnsGeneradosDTO)
        {
            listaCvnsGenerados.add(creaCvnGeneradoFrom(cvnGeneradoDTO));
        }

        return listaCvnsGenerados;
    }

    public void deleteListaCvnsGeneradosBySolicitante(Long solicitante)
    {
        JPAQuery query = new JPAQuery(entityManager);
        QCvnGeneradoDTO qCvnGenerado = QCvnGeneradoDTO.cvnGeneradoDTO;

        List<CvnGeneradoDTO> listaCvnsGeneradosDTO = query.from(qCvnGenerado)
                .where(qCvnGenerado.solicitante.eq(solicitante)).list(qCvnGenerado);

        for (CvnGeneradoDTO cvnGeneradoDTO : listaCvnsGeneradosDTO)
        {
            delete(CvnGeneradoDTO.class, cvnGeneradoDTO.getId());
        }
    }

    public List<CvnGeneradoDTO> getCvnsPendientesGeneracion()
    {
        JPAQuery query = new JPAQuery(entityManager);
        QCvnGeneradoDTO qCvnGenerado = QCvnGeneradoDTO.cvnGeneradoDTO;

        return query.from(qCvnGenerado).where(qCvnGenerado.estado.eq("200")).list(qCvnGenerado);
    }
}