package es.uji.apps.cvn.dao;

import java.util.ArrayList;
import java.util.List;

import es.uji.apps.cvn.db.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.impl.JPAQuery;

import es.uji.apps.cvn.model.Entidad;
import es.uji.apps.cvn.model.ParticipacionProyecto;
import es.uji.apps.cvn.model.Persona;
import es.uji.apps.cvn.model.ProyectoInvestigacion;
import es.uji.commons.db.BaseDAODatabaseImpl;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

@Repository
public class ProyectoDAO extends BaseDAODatabaseImpl
{

    private static final Logger log = Logger.getLogger(ProyectoDAO.class);

    public List<ParticipacionProyecto> getParticipacionesEnProyectosByPersonaId(Long personaId)
            throws RegistroNoEncontradoException
    {
        Long mili = System.currentTimeMillis();

        List<ParticipacionProyecto> participacionProyectos = getParticipacionesEnProyectos(personaId);
        participacionProyectos.addAll(getParticipacionesEnProyectosExternos(personaId));

        mili = System.currentTimeMillis()-mili;
        log.info("ProyectoDAO.getParticipacionesEnProyectosByPersonaId " + mili);

        return participacionProyectos;
    }

    private List<ParticipacionProyecto> getParticipacionesEnProyectos(Long personaId)
            throws RegistroNoEncontradoException
    {
        Long mili = System.currentTimeMillis();


        JPAQuery query = new JPAQuery(entityManager);
        QParticipacionPersonaEnProyectoDTO qParticipacionProyecto = QParticipacionPersonaEnProyectoDTO.participacionPersonaEnProyectoDTO;

        List<ParticipacionPersonaEnProyectoDTO> participacionProyectosDTO = query
                .from(qParticipacionProyecto).where(qParticipacionProyecto.persona.eq(personaId))
                .list(qParticipacionProyecto);

        List<ParticipacionProyecto> participacionProyectos = new ArrayList<ParticipacionProyecto>();

        for (ParticipacionPersonaEnProyectoDTO participacionProyectoDTO : participacionProyectosDTO)
        {
            ParticipacionProyecto participacionProyecto = creaParticipacionProyectoDesde(participacionProyectoDTO);
            participacionProyecto.getProyectoInvestigacion().setResponsables(
                    getResponsablesByProyectoId(participacionProyecto.getProyectoInvestigacion()
                            .getId()));
            participacionProyectos.add(participacionProyecto);
        }

        mili = System.currentTimeMillis()-mili;
        log.info("ProyectoDAO.getParticipacionesEnProyectos " + mili);

        return participacionProyectos;
    }

    private List<ParticipacionProyecto> getParticipacionesEnProyectosExternos(Long personaId)
            throws RegistroNoEncontradoException
    {
        Long mili = System.currentTimeMillis();

        JPAQuery query = new JPAQuery(entityManager);
        QParticipacionPersonaEnProyectoExternoDTO qParticipacionProyecto = QParticipacionPersonaEnProyectoExternoDTO.participacionPersonaEnProyectoExternoDTO;

        List<ParticipacionPersonaEnProyectoExternoDTO> participacionesProyectosDTO = query
                .from(qParticipacionProyecto).where(qParticipacionProyecto.persona.eq(personaId))
                .list(qParticipacionProyecto);

        List<ParticipacionProyecto> participacionProyectos = new ArrayList<ParticipacionProyecto>();

        for (ParticipacionPersonaEnProyectoExternoDTO participacionProyectoDTO : participacionesProyectosDTO)
        {
            ParticipacionProyecto participacionProyecto = creaParticipacionProyectoDesde(participacionProyectoDTO);
            participacionProyecto.getProyectoInvestigacion().setResponsables(
                    getResponsablesByProyectoExternoId(participacionProyecto
                            .getProyectoInvestigacion().getId()));
            participacionProyectos.add(participacionProyecto);
        }

        mili = System.currentTimeMillis()-mili;
        log.info("ProyectoDAO.getParticipacionesEnProyectosExternos " + mili);

        return participacionProyectos;
    }

    private List<Persona> getResponsablesByProyectoId(Long proyectoId)
    {
        Long mili = System.currentTimeMillis();

        JPAQuery query = new JPAQuery(entityManager);
        QParticipacionPersonaEnProyectoDTO qParticipacionProyecto = QParticipacionPersonaEnProyectoDTO.participacionPersonaEnProyectoDTO;
        QPersonaMiniDTO qPersonaMini = QPersonaMiniDTO.personaMiniDTO;

        List<PersonaMiniDTO> responsablesDTO = query
                .from(qParticipacionProyecto, qPersonaMini)
                .where(qParticipacionProyecto.proyecto.eq(proyectoId).and(
                        qParticipacionProyecto.persona.eq(qPersonaMini.id).and(
                                qParticipacionProyecto.responsable.eq("S")))).list(qPersonaMini);

        List<Persona> responsables = new ArrayList<Persona>();
        for (PersonaMiniDTO responsableDTO : responsablesDTO)
        {
            responsables.add(creaResponsableDesde(responsableDTO));
        }
        mili = System.currentTimeMillis()-mili;
        log.info("ProyectoDAO.getResponsablesByProyectoId ("+ proyectoId + "):" + mili + "m.");


        return responsables;
    }

    private List<Persona> getResponsablesByProyectoExternoId(Long proyectoId)
    {
        Long mili = System.currentTimeMillis();

        JPAQuery query = new JPAQuery(entityManager);
        QParticipacionPersonaEnProyectoExternoDTO qParticipacionProyecto = QParticipacionPersonaEnProyectoExternoDTO.participacionPersonaEnProyectoExternoDTO;
        QPersonaMiniDTO qPersonaMini = QPersonaMiniDTO.personaMiniDTO;

        List<PersonaMiniDTO> responsablesDTO = query
                .from(qParticipacionProyecto, qPersonaMini)
                .where(qParticipacionProyecto.proyecto.eq(proyectoId).and(
                        qParticipacionProyecto.persona.eq(qPersonaMini.id).and(
                                qParticipacionProyecto.responsable.eq("S")))).list(qPersonaMini);

        List<Persona> responsables = new ArrayList<Persona>();
        for (PersonaMiniDTO responsableDTO : responsablesDTO)
        {
            responsables.add(creaResponsableDesde(responsableDTO));
        }
        mili = System.currentTimeMillis()-mili;
        log.info("ProyectoDAO.getResponsablesByProyectoExternoId " + mili);

        return responsables;
    }

    private ParticipacionProyecto creaParticipacionProyectoDesde(
            ParticipacionPersonaEnProyectoDTO participacionPersonaEnProyectoDTO)
            throws RegistroNoEncontradoException
    {

        ParticipacionProyecto participacionProyecto = new ParticipacionProyecto();
        try {
        participacionProyecto.setAportaciones(participacionPersonaEnProyectoDTO.getAportaciones());
        } catch (Exception e){
              log.error("Error",e);
       }

        participacionProyecto.setCalidadParticipacion(participacionPersonaEnProyectoDTO
                .getCalidadParticipacion());
        participacionProyecto
                .setProyectoInvestigacion(getProyectoInvestigacionById(participacionPersonaEnProyectoDTO
                        .getProyecto()));
        participacionProyecto.setDedicacion(participacionPersonaEnProyectoDTO.getDedicacion());
        participacionProyecto.setResponsable(participacionPersonaEnProyectoDTO.getResponsable());
        participacionProyecto.setTipoParticipacion(participacionPersonaEnProyectoDTO
                .getTipoParticipacion());

        return participacionProyecto;
    }

    private ParticipacionProyecto creaParticipacionProyectoDesde(
            ParticipacionPersonaEnProyectoExternoDTO participacionPersonaEnProyectoExternoDTO)
            throws RegistroNoEncontradoException
    {
        ParticipacionProyecto participacionProyecto = new ParticipacionProyecto();

        participacionProyecto.setAportaciones(participacionPersonaEnProyectoExternoDTO
                .getAportaciones());
        participacionProyecto.setCalidadParticipacion(participacionPersonaEnProyectoExternoDTO
                .getCalidadParticipacion());
        participacionProyecto
                .setProyectoInvestigacion(getProyectoExternoById(participacionPersonaEnProyectoExternoDTO
                        .getProyecto()));
        participacionProyecto.setDedicacion(participacionPersonaEnProyectoExternoDTO
                .getDedicacion());
        participacionProyecto.setResponsable(participacionPersonaEnProyectoExternoDTO
                .getResponsable());
        participacionProyecto.setTipoParticipacion(participacionPersonaEnProyectoExternoDTO
                .getTipoParticipacion());

        return participacionProyecto;
    }

    private ProyectoInvestigacion getProyectoInvestigacionById(Long proyectoId)
            throws RegistroNoEncontradoException
    {
        ProyectoInvestigacionDTO proyecto = null;
        Long mili = System.currentTimeMillis();
        try
        {
            proyecto = get(ProyectoInvestigacionDTO.class, proyectoId).get(0);
        }
        catch (Exception e)
        {
            throw new RegistroNoEncontradoException();
        }
        mili = System.currentTimeMillis()-mili;
        log.info("ProyectoDAO.getProyectoInvestigacionById ("+ proyectoId + "):" + mili + "m.");

        return creaProyectoInvestigacionDesde(proyecto);
    }

    private ProyectoInvestigacion getProyectoExternoById(Long proyectoId)
            throws RegistroNoEncontradoException
    {
        ProyectoExternoDTO proyecto = null;

        try
        {
            proyecto = get(ProyectoExternoDTO.class, proyectoId).get(0);
        }
        catch (Exception e)
        {
            throw new RegistroNoEncontradoException();
        }

        return creaProyectoInvestigacionDesde(proyecto);
    }

    private ProyectoInvestigacion creaProyectoInvestigacionDesde(
            ProyectoInvestigacionDTO proyectoInvestigacionDTO)
    {
        ProyectoInvestigacion proyectoInvestigacion = new ProyectoInvestigacion();

        proyectoInvestigacion.setAmbitoStr(proyectoInvestigacionDTO.getAmbito());
        proyectoInvestigacion.setCodigoExterno(proyectoInvestigacionDTO.getCodigoExterno());
        proyectoInvestigacion.setCodigoProyectoFinanciadora(proyectoInvestigacionDTO
                .getCodigoProyectoFinanciadora());

        try
        {
            proyectoInvestigacion.setDotacionSubproyecto(Float.parseFloat(proyectoInvestigacionDTO
                    .getDotacionSubproyecto()));
        }
        catch (Exception e)
        {
        }

        proyectoInvestigacion.setDotacionTotal(proyectoInvestigacionDTO.getDotacionTotal());
        //proyectoInvestigacion.setDuracion(proyectoInvestigacionDTO.getDuracion());
        proyectoInvestigacion.setDuracion(proyectoInvestigacionDTO.getFechaInicio(), proyectoInvestigacionDTO.getFechaFin());
        proyectoInvestigacion.setEntidadEjecutora(proyectoInvestigacionDTO.getEntidadEjecutora());
        proyectoInvestigacion.setFechaFin(proyectoInvestigacionDTO.getFechaFin());
        proyectoInvestigacion.setFechaInicio(proyectoInvestigacionDTO.getFechaInicio());
        proyectoInvestigacion.setId(proyectoInvestigacionDTO.getId());
        proyectoInvestigacion.setMicrotipo(proyectoInvestigacionDTO.getMicrotipo());
        proyectoInvestigacion.setModalidad(proyectoInvestigacionDTO.getModalidad());
        proyectoInvestigacion.setNInvestigadores(proyectoInvestigacionDTO.getNInvestigadores(),
                proyectoInvestigacionDTO.getNInvestigadoresExt());
        proyectoInvestigacion.setNombreProgramaFinanciacion(proyectoInvestigacionDTO
                .getNombreProgramaFinanciacion());

        try
        {
            proyectoInvestigacion.setNPersonasAnyo(Long.parseLong(proyectoInvestigacionDTO
                    .getNPersonasAnyo()));
        }
        catch (Exception e)
        {
        }

        proyectoInvestigacion.setPorcentajeCredito(proyectoInvestigacionDTO.getPorcentajeCredito());
        proyectoInvestigacion.setPorcentajeMixto(proyectoInvestigacionDTO.getPorcentajeMixto());
        proyectoInvestigacion.setPorcentajeSubvencion(proyectoInvestigacionDTO
                .getPorcentajeSubvencion());
        proyectoInvestigacion.setResultadosKeywords(proyectoInvestigacionDTO
                .getResultadosKeywords());
        proyectoInvestigacion.setResultadosRelevantes(proyectoInvestigacionDTO
                .getResultadosRelevantes());
        proyectoInvestigacion.setSubtipo(proyectoInvestigacionDTO.getSubtipo());
        proyectoInvestigacion.setTipo(proyectoInvestigacionDTO.getTipo());
        proyectoInvestigacion.setTitulo(proyectoInvestigacionDTO.getTitulo());
        proyectoInvestigacion.setTituloKeywords(proyectoInvestigacionDTO.getTituloKeywords());

        List<Entidad> entidadesFinanciadoras = new ArrayList<Entidad>();
        for (EntidadDTO entidadFinanciadora : proyectoInvestigacionDTO.getEntidadesFinanciadoras())
        {
            entidadesFinanciadoras.add(creaEntidadDesde(entidadFinanciadora,
                    proyectoInvestigacionDTO.getSuborganismo()));
        }
        proyectoInvestigacion.setEntidadesFinanciadoras(entidadesFinanciadoras);

        List<Entidad> entidadesParticipantes = new ArrayList<Entidad>();
        for (EntidadDTO entidadParticipante : proyectoInvestigacionDTO.getEntidadesParticipantes())
        {
            entidadesParticipantes.add(creaEntidadDesde(entidadParticipante,
                    proyectoInvestigacionDTO.getSuborganismo()));
        }
        proyectoInvestigacion.setEntidadesParticipantes(entidadesParticipantes);

        return proyectoInvestigacion;
    }

    private ProyectoInvestigacion creaProyectoInvestigacionDesde(
            ProyectoExternoDTO proyectoExternoDTO)
    {
        ProyectoInvestigacion proyectoInvestigacion = new ProyectoInvestigacion();

        proyectoInvestigacion.setAmbitoStr(proyectoExternoDTO.getAmbito());
        proyectoInvestigacion.setCodigoExterno(proyectoExternoDTO.getCodigoExterno());
        proyectoInvestigacion.setCodigoProyectoFinanciadora(proyectoExternoDTO
                .getCodigoProyectoFinanciadora());

        try
        {
            proyectoInvestigacion.setDotacionSubproyecto(Float.parseFloat(proyectoExternoDTO
                    .getDotacionSubproyecto()));
        }
        catch (Exception e)
        {
        }

        proyectoInvestigacion.setDotacionTotal(proyectoExternoDTO.getDotacionTotal());
        proyectoInvestigacion.setDuracion(proyectoExternoDTO.getDuracion());
        proyectoInvestigacion.setEntidadEjecutora(proyectoExternoDTO.getEntidadEjecutora());
        proyectoInvestigacion.setFechaFin(proyectoExternoDTO.getFechaFin());
        proyectoInvestigacion.setFechaInicio(proyectoExternoDTO.getFechaInicio());
        proyectoInvestigacion.setId(proyectoExternoDTO.getId());
        proyectoInvestigacion.setMicrotipo(proyectoExternoDTO.getMicrotipo());
        proyectoInvestigacion.setModalidad(proyectoExternoDTO.getModalidad());
        proyectoInvestigacion.setNInvestigadores(proyectoExternoDTO.getNInvestigadores(),
                proyectoExternoDTO.getNInvestigadoresExt());
        proyectoInvestigacion.setNombreProgramaFinanciacion(proyectoExternoDTO
                .getNombreProgramaFinanciacion());
        proyectoInvestigacion.setNPersonasAnyo(proyectoExternoDTO.getNPersonasAnyo());
        proyectoInvestigacion.setPorcentajeCredito(proyectoExternoDTO.getPorcentajeCredito());
        proyectoInvestigacion.setPorcentajeMixto(proyectoExternoDTO.getPorcentajeMixto());
        proyectoInvestigacion.setPorcentajeSubvencion(proyectoExternoDTO.getPorcentajeSubvencion());
        proyectoInvestigacion.setResultadosKeywords(proyectoExternoDTO.getResultadosKeywords());
        proyectoInvestigacion.setResultadosRelevantes(proyectoExternoDTO.getResultadosRelevantes());
        proyectoInvestigacion.setSubtipo(proyectoExternoDTO.getSubtipo());
        proyectoInvestigacion.setTipo(proyectoExternoDTO.getTipo());
        proyectoInvestigacion.setTitulo(proyectoExternoDTO.getTitulo());
        proyectoInvestigacion.setTituloKeywords(proyectoExternoDTO.getTituloKeywords());

        Entidad entidadFinanciadora = new Entidad();
        entidadFinanciadora.setNombre(proyectoExternoDTO.getNombreEntidadFinanciadora());
        List<Entidad> entidadesFinanciadoras = new ArrayList<Entidad>();
        entidadesFinanciadoras.add(entidadFinanciadora);
        proyectoInvestigacion.setEntidadesFinanciadoras(entidadesFinanciadoras);

        proyectoInvestigacion.setEntidadesParticipantes(new ArrayList<Entidad>());

        return proyectoInvestigacion;
    }

    private Entidad creaEntidadDesde(EntidadDTO entidadDTO, String suborganismo)
    {
        Entidad entidad = new Entidad();

        entidad.setAcronimo(entidadDTO.getAcronimo());
        entidad.setCiudad(entidadDTO.getCiudad());
        entidad.setId(entidadDTO.getId());

        String nombre = entidadDTO.getNombre();
        if (entidadDTO.getId().equals(Entidad.GV_ID))
        {
            nombre = nombre + " " + suborganismo;
        }
        entidad.setNombre(nombre);

        entidad.setPais(entidadDTO.getPais());
        entidad.setRegion(entidadDTO.getRegion());
        entidad.setSubtipo(entidadDTO.getSubtipo());
        entidad.setTipo(entidadDTO.getTipo());
        entidad.setTipoDescr(entidadDTO.getTipoDescr());

        return entidad;
    }

    private Persona creaResponsableDesde(PersonaMiniDTO personaMiniDTO)
    {
        Persona responsable = new Persona();
        responsable.setNombre(personaMiniDTO.getNombre());
        responsable.setApellido1(personaMiniDTO.getApellido1());
        responsable.setApellido2(personaMiniDTO.getApellido2());

        return responsable;
    }

    public Entidad getEntidadById(Long entidadId) throws RegistroNoEncontradoException
    {
        EntidadDTO entidadDTO;

        try
        {
            entidadDTO = get(EntidadDTO.class, entidadId).get(0);
        }
        catch (Exception e)
        {
            throw new RegistroNoEncontradoException();
        }

        return creaEntidadDesde(entidadDTO, null);
    }

    public Entidad insertaEntidad(Entidad entidad)
    {
        EntidadDTO entidadDTO = new EntidadDTO();

        entidadDTO.setAcronimo(entidad.getAcronimo());
        entidadDTO.setCiudad(entidad.getCiudad());
        entidadDTO.setId(entidad.getId());
        entidadDTO.setNombre(entidad.getNombre());
        entidadDTO.setPais(entidad.getPais());
        entidadDTO.setRegion(entidad.getRegion());
        entidadDTO.setSubtipo(entidad.getSubtipo());
        entidadDTO.setTipo(entidad.getTipo());
        entidadDTO.setTipoDescr(entidad.getTipoDescr());

        return creaEntidadDesde(insert(entidadDTO), null);
    }

    public void eliminaEntidad(Long entidadId)
    {
        delete(EntidadDTO.class, entidadId);
    }
}