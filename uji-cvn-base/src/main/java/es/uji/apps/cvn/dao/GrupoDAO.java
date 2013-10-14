package es.uji.apps.cvn.dao;

import com.mysema.query.jpa.impl.JPAQuery;
import es.uji.apps.cvn.db.GrupoInvestigacionDTO;
import es.uji.apps.cvn.db.ParticipacionPersonaEnGrupoDTO;
import es.uji.apps.cvn.db.QGrupoInvestigacionDTO;
import es.uji.apps.cvn.db.QParticipacionPersonaEnGrupoDTO;
import es.uji.apps.cvn.model.GrupoInvestigacion;
import es.uji.apps.cvn.model.ParticipacionGrupo;
import es.uji.apps.cvn.model.Persona;
import es.uji.commons.db.BaseDAODatabaseImpl;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GrupoDAO extends BaseDAODatabaseImpl
{
    public List<ParticipacionGrupo> getParticipacionesEnGruposByPersonaId(Long personaId)
            throws RegistroNoEncontradoException
    {
        JPAQuery query = new JPAQuery(entityManager);
        QParticipacionPersonaEnGrupoDTO qParticipacionGrupos = QParticipacionPersonaEnGrupoDTO.participacionPersonaEnGrupoDTO;

        List<ParticipacionPersonaEnGrupoDTO> participacionGruposDTO = query
                .from(qParticipacionGrupos).where(qParticipacionGrupos.persona.eq(personaId))
                .list(qParticipacionGrupos);

        List<ParticipacionGrupo> participacionGrupos = new ArrayList<ParticipacionGrupo>();

        for (ParticipacionPersonaEnGrupoDTO participacionGrupoDTO : participacionGruposDTO)
        {
            participacionGrupos.add(creaParticipacionGrupoDesde(participacionGrupoDTO));
        }

        return participacionGrupos;
    }

    private ParticipacionGrupo creaParticipacionGrupoDesde(
            ParticipacionPersonaEnGrupoDTO participacionPersonaEnGrupoDTO)
            throws RegistroNoEncontradoException
    {
        ParticipacionGrupo participacionGrupo = new ParticipacionGrupo();
        participacionGrupo.setFechaFin(participacionPersonaEnGrupoDTO.getFechaFin());
        participacionGrupo.setFechaInicio(participacionPersonaEnGrupoDTO.getFechaInicio());
        participacionGrupo
                .setGrupoInvestigacion(getGrupoInvestigacionById(participacionPersonaEnGrupoDTO
                        .getGrupo()));
        participacionGrupo.setResultadosDesc(participacionPersonaEnGrupoDTO.getResultadosDesc());
        participacionGrupo.setResultadosKeywords(participacionPersonaEnGrupoDTO
                .getResultadosKeywords());
        participacionGrupo.setResultadosRelevantes(participacionPersonaEnGrupoDTO
                .getResultadosRelevantes());
        participacionGrupo
                .setTipoColaboracion(participacionPersonaEnGrupoDTO.getTipoColaboracion());
        participacionGrupo.setDuracion(participacionPersonaEnGrupoDTO.getDuracion());

        return participacionGrupo;
    }

    private GrupoInvestigacion getGrupoInvestigacionById(String grupoId)
            throws RegistroNoEncontradoException
    {
        JPAQuery query = new JPAQuery(entityManager);
        QGrupoInvestigacionDTO qGrupo = QGrupoInvestigacionDTO.grupoInvestigacionDTO;

        GrupoInvestigacionDTO grupo = query.from(qGrupo).where(qGrupo.id.eq(grupoId))
                .singleResult(qGrupo);

        if (grupo == null)
        {
            throw new RegistroNoEncontradoException();
        }

        return creaGrupoInvestigacionDesde(grupo);
    }

    private GrupoInvestigacion creaGrupoInvestigacionDesde(
            GrupoInvestigacionDTO grupoInvestigacionDTO)
    {
        GrupoInvestigacion grupoInvestigacion = new GrupoInvestigacion();

        grupoInvestigacion.setActivo(grupoInvestigacionDTO.getActivo().equals("S"));
        grupoInvestigacion.setCiudad(grupoInvestigacionDTO.getCiudad());
        grupoInvestigacion.setCodigoNormalizado(grupoInvestigacionDTO.getCodigoNormalizado());
        grupoInvestigacion.setFechaActualizacion(grupoInvestigacionDTO.getFechaActualizacion());
        grupoInvestigacion.setFechaAlta(grupoInvestigacionDTO.getFechaAlta());
        grupoInvestigacion.setId(grupoInvestigacionDTO.getId());
        grupoInvestigacion.setNComponentes(grupoInvestigacionDTO.getNComponentes());

        try
        {
            grupoInvestigacion.setNTesis(Long.parseLong(grupoInvestigacionDTO.getNTesis()));
        }
        catch (Exception e)
        {
        }

        try
        {
            grupoInvestigacion.setNPostDoc(Long.parseLong(grupoInvestigacionDTO.getNPostDoc()));
        }
        catch (Exception e)
        {
        }

        grupoInvestigacion.setNombre(grupoInvestigacionDTO.getNombre());
        grupoInvestigacion.setNombreEntidad(grupoInvestigacionDTO.getNombreEntidad());
        grupoInvestigacion.setObjetivo(grupoInvestigacionDTO.getObjetivo());
        grupoInvestigacion.setPais(grupoInvestigacionDTO.getPais());
        grupoInvestigacion.setRegion(grupoInvestigacionDTO.getRegion());

        
        if (grupoInvestigacionDTO.getResponsable() != null)
        {
        	Persona responsable = new Persona();
        	responsable.setId(grupoInvestigacionDTO.getResponsable().getId());
        	responsable.setNombre(grupoInvestigacionDTO.getResponsable().getNombre());
        	responsable.setApellido1(grupoInvestigacionDTO.getResponsable().getApellido1());
        	responsable.setApellido2(grupoInvestigacionDTO.getResponsable().getApellido2());
        	grupoInvestigacion.setResponsable(responsable);
        }
        
        return grupoInvestigacion;
    }
}
