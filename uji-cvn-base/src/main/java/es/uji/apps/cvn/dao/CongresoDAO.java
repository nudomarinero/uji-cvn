package es.uji.apps.cvn.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.impl.JPAQuery;

import es.uji.apps.cvn.db.CongresoDTO;
import es.uji.apps.cvn.db.ParticipacionPersonaEnCongresoDTO;
import es.uji.apps.cvn.db.ParticipacionPersonaExternaEnCongresoDTO;
import es.uji.apps.cvn.db.PersonaDTO;
import es.uji.apps.cvn.db.QParticipacionPersonaEnCongresoDTO;
import es.uji.apps.cvn.db.QParticipacionPersonaExternaEnCongresoDTO;
import es.uji.apps.cvn.model.AutorPublicacion;
import es.uji.apps.cvn.model.Congreso;
import es.uji.apps.cvn.model.Entidad;
import es.uji.apps.cvn.model.ParticipacionCongreso;
import es.uji.apps.cvn.model.Publicacion;
import es.uji.commons.db.BaseDAODatabaseImpl;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

@Repository
public class CongresoDAO extends BaseDAODatabaseImpl
{
    final private static Long CARACTER_DOCENTE = 20L;
    private static final Logger log = Logger.getLogger(CongresoDAO.class);

    public List<ParticipacionCongreso> getParticipacionesEnCongresosByPersonaId(Long personaId)
            throws RegistroNoEncontradoException
    {

        Long mili = System.currentTimeMillis();

        JPAQuery query = new JPAQuery(entityManager);
        QParticipacionPersonaEnCongresoDTO qParticipacionCongreso = QParticipacionPersonaEnCongresoDTO.participacionPersonaEnCongresoDTO;

        List<ParticipacionPersonaEnCongresoDTO> participacionCongresosDTO = query
                .from(qParticipacionCongreso)
                .where(qParticipacionCongreso.persona.eq(personaId).and(
                        qParticipacionCongreso.caracterPublicacion.ne(CARACTER_DOCENTE)))
                .list(qParticipacionCongreso);

        List<ParticipacionCongreso> participacionEnCongresos = new ArrayList<ParticipacionCongreso>();

        for (ParticipacionPersonaEnCongresoDTO participacionCongresoDTO : participacionCongresosDTO)
        {
            participacionEnCongresos.add(creaParticipacionCongresoDesde(participacionCongresoDTO));
        }

        mili = System.currentTimeMillis()-mili;
        log.info("CongresoDAO.getParticipacionesEnCongresosByPersonaId " + mili);

        return participacionEnCongresos;
    }
    
    
    public List<ParticipacionCongreso> getParticipacionesEnCongresosDocentesByPersonaId(Long personaId)
    throws RegistroNoEncontradoException
    {

        Long mili = System.currentTimeMillis();

        JPAQuery query = new JPAQuery(entityManager);
    	QParticipacionPersonaEnCongresoDTO qParticipacionCongreso = QParticipacionPersonaEnCongresoDTO.participacionPersonaEnCongresoDTO;

    	List<ParticipacionPersonaEnCongresoDTO> participacionCongresosDTO = query
        	.from(qParticipacionCongreso)
        	.where(qParticipacionCongreso.persona.eq(personaId).and(
        			qParticipacionCongreso.caracterPublicacion.eq(CARACTER_DOCENTE)))
        			.list(qParticipacionCongreso);

    	List<ParticipacionCongreso> participacionEnCongresos = new ArrayList<ParticipacionCongreso>();

    	for (ParticipacionPersonaEnCongresoDTO participacionCongresoDTO : participacionCongresosDTO)
    	{
    		participacionEnCongresos.add(creaParticipacionCongresoDesde(participacionCongresoDTO));
    	}

        mili = System.currentTimeMillis()-mili;
        log.info("CongresoDAO.getParticipacionesEnCongresosDocentesByPersonaId " + mili);

    	return participacionEnCongresos;
    }


    
    

    private Congreso getCongresoById(Long congresoId) throws RegistroNoEncontradoException
    {
        Long mili = System.currentTimeMillis();

        CongresoDTO congresoDTO = null;

        try
        {
            congresoDTO = get(CongresoDTO.class, congresoId).get(0);
        }
        catch (Exception e)
        {
            throw new RegistroNoEncontradoException();
        }


        mili = System.currentTimeMillis()-mili;
        log.info("CongresoDAO.getCongresoById " + mili);

        return creaCongresoDesde(congresoDTO);
    }

    private List<AutorPublicacion> getAutoresCongreso(Long congresoId)
            throws RegistroNoEncontradoException
    {
        Long mili = System.currentTimeMillis();


        JPAQuery query = new JPAQuery(entityManager);
        QParticipacionPersonaEnCongresoDTO qParticipacion = QParticipacionPersonaEnCongresoDTO.participacionPersonaEnCongresoDTO;

        List<ParticipacionPersonaEnCongresoDTO> participacionesDTO = query.from(qParticipacion)
                .where(qParticipacion.produccion.eq(congresoId)).list(qParticipacion);

        List<AutorPublicacion> autores = new ArrayList<AutorPublicacion>();
        for (ParticipacionPersonaEnCongresoDTO participacionDTO : participacionesDTO)
        {
            try
            {
                PersonaDTO personaDTO = get(PersonaDTO.class, participacionDTO.getPersona()).get(0);
                autores.add(creaAutorPublicacionCongresoDesde(personaDTO, participacionDTO));
            }
            catch (Exception e)
            {
                throw new RegistroNoEncontradoException();
            }
        }

        mili = System.currentTimeMillis()-mili;
        log.info("CongresoDAO.getAutoresCongreso " + mili);

        return autores;
    }

    private List<AutorPublicacion> getAutoresExternosCongreso(Long congresoId)
    {
        Long mili = System.currentTimeMillis();

        JPAQuery query = new JPAQuery(entityManager);
        QParticipacionPersonaExternaEnCongresoDTO qParticipacion = QParticipacionPersonaExternaEnCongresoDTO.participacionPersonaExternaEnCongresoDTO;

        List<ParticipacionPersonaExternaEnCongresoDTO> participacionesDTO = query
                .from(qParticipacion).where(qParticipacion.produccion.eq(congresoId))
                .list(qParticipacion);

        List<AutorPublicacion> autores = new ArrayList<AutorPublicacion>();
        for (ParticipacionPersonaExternaEnCongresoDTO participacionDTO : participacionesDTO)
        {
            autores.add(creaAutorPublicacionCongresoDesde(participacionDTO));
        }

        mili = System.currentTimeMillis()-mili;
        log.info("CongresoDAO.getAutoresExternosCongreso " + mili);

        return autores;
    }

    private ParticipacionCongreso creaParticipacionCongresoDesde(
            ParticipacionPersonaEnCongresoDTO participacionPersonaEnCongresoDTO)
            throws RegistroNoEncontradoException
    {
        ParticipacionCongreso participacionCongreso = new ParticipacionCongreso();
        participacionCongreso.setId(participacionPersonaEnCongresoDTO.getProduccion());
        participacionCongreso.setTipoParticipacion(participacionPersonaEnCongresoDTO
                .getTipoParticipacion());
        participacionCongreso.setCongreso(getCongresoById(participacionPersonaEnCongresoDTO
                .getProduccion()));

        return participacionCongreso;
    }

    private Congreso creaCongresoDesde(CongresoDTO congresoDTO)
            throws RegistroNoEncontradoException
    {
        Congreso congreso = new Congreso();
        congreso.setAmbito(congresoDTO.getAmbito());
        congreso.setCiudad(congresoDTO.getCiudadCongreso());
        congreso.setFecha(congresoDTO.getFechaCongreso());
        congreso.setAno(congresoDTO.getAnoCongreso());
        congreso.setFechaFinalizacion(congresoDTO.getFechaFin());
        congreso.setHasComiteExterno(congresoDTO.getIsPubEvaluada());
        congreso.setIsPublicadoActas(congresoDTO.getIsActa());
        congreso.setNombre(congresoDTO.getNombreCongreso());
        congreso.setPais(congresoDTO.getPaisCongreso());
        congreso.setRegion(congresoDTO.getRegionCongreso());
        congreso.setTipoEvento(congresoDTO.getTipoEvento());
        congreso.setTitulo(congresoDTO.getTitulo());

        Entidad entidadOrganizadora = new Entidad();
        entidadOrganizadora.setNombre(congresoDTO.getEntidadOrganizadora());
        congreso.setEntidadOrganizadora(entidadOrganizadora);

        Publicacion publicacion = new Publicacion();
        publicacion.setDepositoLegal(congresoDTO.getDepLegalPub());
        publicacion.setFecha(congresoDTO.getFechaPublicacion());

        List<String> isbns = new ArrayList<String>();
        isbns.add(Congreso.getIsbnOrIssnFrom(congresoDTO.getIsbnPub()));
        publicacion.setIsbns(isbns);

        publicacion.setNombre(congresoDTO.getNombreCongreso());
        publicacion.setPaginasInicioFin(congresoDTO.getPaginasPub());
        publicacion.setPais(congresoDTO.getPaisPub());
        publicacion.setRegion(congresoDTO.getRegionPub());
        publicacion.setTipo(Congreso.getTipoPublicacionFrom(congresoDTO.getTipoPublicacion()));
        publicacion.setEditorial(Congreso.getEditorialPublicacionIfCapituloLibro(
                congresoDTO.getEditorialPub(), publicacion.getTipo()));
        publicacion.setTitulo(congresoDTO.getTituloPublicacion());
        publicacion.setUrl(congresoDTO.getWebPub());
        publicacion.setVolumen(congresoDTO.getVolumenPub());

        List<AutorPublicacion> autores = getAutoresCongreso(congresoDTO.getId());
        autores.addAll(getAutoresExternosCongreso(congresoDTO.getId()));
        publicacion.setAutores(autores);

        congreso.setPublicacion(publicacion);
        
        
        //Específicos de congreso de caracter docente
        congreso.setObjetivos(congresoDTO.getObjetivos());
        congreso.setDestinatarios(congresoDTO.getDestinatarios());
        congreso.setIdioma(congresoDTO.getIdioma());
        congreso.setFechaPresentacion(congresoDTO.getFechaPresentacion());
        congreso.setHoras(congresoDTO.getHoras());
        

        return congreso;
    }

    private AutorPublicacion creaAutorPublicacionCongresoDesde(PersonaDTO personaDTO,
            ParticipacionPersonaEnCongresoDTO participacionPersonaEnCongresoDTO)
    {
        AutorPublicacion autorPublicacion = new AutorPublicacion();
        autorPublicacion.setNombre(personaDTO.getNombre());
        autorPublicacion.setApellido1(personaDTO.getApellido1());
        autorPublicacion.setApellido2(personaDTO.getApellido2());
        autorPublicacion.setOrden(participacionPersonaEnCongresoDTO.getOrden());

        return autorPublicacion;
    }

    private AutorPublicacion creaAutorPublicacionCongresoDesde(
            ParticipacionPersonaExternaEnCongresoDTO participacionPersonaExternaEnCongresoDTO)
    {
        AutorPublicacion autorPublicacion = new AutorPublicacion();
        
        String fullname = participacionPersonaExternaEnCongresoDTO.getPersona();
        
        String [] splitname = fullname.replaceAll("\\s+", " ").trim().split(" ",3);
        
        autorPublicacion.setNombre(splitname[0]);
        if(splitname.length > 1)
        {
        	autorPublicacion.setApellido1(splitname[1]);
        }
        if(splitname.length > 2)
        {
        	autorPublicacion.setApellido2(splitname[2]);
        }
        autorPublicacion.setOrden(participacionPersonaExternaEnCongresoDTO.getOrden());

        return autorPublicacion;
    }
    
    
    
   

}
