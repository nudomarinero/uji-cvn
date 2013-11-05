package es.uji.apps.cvn.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.impl.JPAQuery;

import es.uji.apps.cvn.db.ImpactoPublicacionDTO;
import es.uji.apps.cvn.db.ParticipacionPersonaEnPublicacionDTO;
import es.uji.apps.cvn.db.ParticipacionPersonaExternaEnPublicacionDTO;
import es.uji.apps.cvn.db.PersonaDTO;
import es.uji.apps.cvn.db.PublicacionDTO;
import es.uji.apps.cvn.db.QImpactoPublicacionDTO;
import es.uji.apps.cvn.db.QParticipacionPersonaEnPublicacionDTO;
import es.uji.apps.cvn.db.QParticipacionPersonaExternaEnPublicacionDTO;
import es.uji.apps.cvn.model.AutorPublicacion;
import es.uji.apps.cvn.model.Impacto;
import es.uji.apps.cvn.model.ParticipacionPublicacionCientificoTecnica;
import es.uji.apps.cvn.model.ParticipacionPublicacionDocente;
import es.uji.apps.cvn.model.Publicacion;
import es.uji.apps.cvn.model.PublicacionCientificoTecnica;
import es.uji.apps.cvn.model.PublicacionDocente;
import es.uji.apps.cvn.translators.TipoProduccion;
import es.uji.apps.cvn.translators.TipoSoporteDocente;
import es.uji.commons.db.BaseDAODatabaseImpl;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

@Repository
public class PublicacionDAO extends BaseDAODatabaseImpl
{
    final private static Long CARACTER_DOCENTE = 20L;
    private static final Logger log = Logger.getLogger(PublicacionDAO.class);

    public List<ParticipacionPublicacionCientificoTecnica> getParticipacionesEnPublicacionesCientificoTecnicasByPersonaId(
            Long personaId) throws RegistroNoEncontradoException
    {

        Long mili = System.currentTimeMillis();


        JPAQuery query = new JPAQuery(entityManager);
        QParticipacionPersonaEnPublicacionDTO qParticipacionPublicacion = QParticipacionPersonaEnPublicacionDTO.participacionPersonaEnPublicacionDTO;

        List<ParticipacionPersonaEnPublicacionDTO> participacionPublicacionesDTO = query
                .from(qParticipacionPublicacion)
                .where(qParticipacionPublicacion.persona.eq(personaId).and(
                        qParticipacionPublicacion.caracterPublicacion.ne(CARACTER_DOCENTE)))
                .list(qParticipacionPublicacion);

        List<ParticipacionPublicacionCientificoTecnica> participacionPublicaciones = new ArrayList<ParticipacionPublicacionCientificoTecnica>();

        for (ParticipacionPersonaEnPublicacionDTO participacionPublicacionDTO : participacionPublicacionesDTO)
        {
            participacionPublicaciones
                    .add(creaParticipacionPublicacionCientificoTecnicaDesde(participacionPublicacionDTO));
        }

        mili = System.currentTimeMillis()-mili;
        log.info("PublicacionDAO.getParticipacionesEnPublicacionesCientificoTecnicasByPersonaId " + mili);


        return participacionPublicaciones;
    }
    
    
    
    public List<ParticipacionPublicacionDocente> getParticipacionesEnPublicacionesDocentesByPersonaId(
            Long personaId) throws RegistroNoEncontradoException
    {

        Long mili = System.currentTimeMillis();


        JPAQuery query = new JPAQuery(entityManager);
        QParticipacionPersonaEnPublicacionDTO qParticipacionPublicacion = QParticipacionPersonaEnPublicacionDTO.participacionPersonaEnPublicacionDTO;

        List<ParticipacionPersonaEnPublicacionDTO> participacionPublicacionesDTO = query
                .from(qParticipacionPublicacion)
                .where(qParticipacionPublicacion.persona.eq(personaId).and(
                        qParticipacionPublicacion.caracterPublicacion.eq(CARACTER_DOCENTE)))
                .list(qParticipacionPublicacion);

        List<ParticipacionPublicacionDocente> participacionPublicaciones = new ArrayList<ParticipacionPublicacionDocente>();

        for (ParticipacionPersonaEnPublicacionDTO participacionPublicacionDTO : participacionPublicacionesDTO)
        {
            participacionPublicaciones
                    .add(creaParticipacionPublicacionDocenteDesde(participacionPublicacionDTO));
        }
        mili = System.currentTimeMillis()-mili;
        log.info("PublicacionDAO.getParticipacionesEnPublicacionesDocentesByPersonaId " + mili);

        return participacionPublicaciones;
    }
    
    
    
    

    private PublicacionCientificoTecnica getPublicacionCientificoTecnicaById(Long publicacionId)
            throws RegistroNoEncontradoException
    {
        Long mili = System.currentTimeMillis();


        PublicacionDTO publicacion = null;

        try
        {
            publicacion = get(PublicacionDTO.class, publicacionId).get(0);
        }
        catch (Exception e)
        {
            throw new RegistroNoEncontradoException();
        }
        
        if (publicacion.getCaracter().equals(CARACTER_DOCENTE))
        {
            throw new RegistroNoEncontradoException();	
        }
       
        if (publicacion.getTipo().equals(TipoProduccion.ARTICULO.getTipoId()))
        {
            publicacion.setListaImpactoPublicacion(getListaImpactoByPublicacionId(publicacionId));
        }
        else
        {
            publicacion.setListaImpactoPublicacion(new ArrayList<ImpactoPublicacionDTO>());
        }
        mili = System.currentTimeMillis()-mili;
        log.info("PublicacionDAO.getPublicacionCientificoTecnicaById " + mili);

        return creaPublicacionCientificoTecnicaDesde(publicacion);
    }
    
    
    
    private PublicacionDocente getPublicacionDocenteById(Long publicacionId)
    throws RegistroNoEncontradoException
    {
        Long mili = System.currentTimeMillis();


        PublicacionDTO publicacion = null;

    	try
    	{
    		publicacion = get(PublicacionDTO.class, publicacionId).get(0);
    	}
    	catch (Exception e)
    	{
    		throw new RegistroNoEncontradoException();
    	}

    	if (!publicacion.getCaracter().equals(CARACTER_DOCENTE))
    	{
    		throw new RegistroNoEncontradoException();	
        }
        mili = System.currentTimeMillis()-mili;
        log.info("PublicacionDAO.getPublicacionDocenteById " + mili);

        return creaPublicacionDocenteDesde(publicacion);
    }


	private List<ImpactoPublicacionDTO> getListaImpactoByPublicacionId(Long publicacionId)
    {
        Long mili = System.currentTimeMillis();

        JPAQuery query = new JPAQuery(entityManager);
        QImpactoPublicacionDTO qImpactoPublicacion = QImpactoPublicacionDTO.impactoPublicacionDTO;

        List<ImpactoPublicacionDTO> listaImpacto = query.from(qImpactoPublicacion)
                .where(qImpactoPublicacion.produccion.eq(publicacionId)).list(qImpactoPublicacion);

        return listaImpacto;
    }

    private List<AutorPublicacion> getAutoresPublicacion(Long publicacionId)
            throws RegistroNoEncontradoException
    {
        Long mili = System.currentTimeMillis();

        JPAQuery query = new JPAQuery(entityManager);
        QParticipacionPersonaEnPublicacionDTO qParticipacion = QParticipacionPersonaEnPublicacionDTO.participacionPersonaEnPublicacionDTO;

        List<ParticipacionPersonaEnPublicacionDTO> participacionesDTO = query.from(qParticipacion)
                .where(qParticipacion.produccion.eq(publicacionId)).list(qParticipacion);

        List<AutorPublicacion> autores = new ArrayList<AutorPublicacion>();
        for (ParticipacionPersonaEnPublicacionDTO participacionDTO : participacionesDTO)
        {
            try
            {
                PersonaDTO personaDTO = get(PersonaDTO.class, participacionDTO.getPersona()).get(0);
                autores.add(creaAutorPublicacionDesde(personaDTO, participacionDTO));
            }
            catch (Exception e)
            {
                throw new RegistroNoEncontradoException();
            }
        }
        mili = System.currentTimeMillis()-mili;
        log.info("PublicacionDAO.QParticipacionPersonaEnPublicacionDTO " + mili);

        return autores;
    }

    private List<AutorPublicacion> getAutoresExternosPublicacion(Long publicacionId)
    {
        Long mili = System.currentTimeMillis();


        JPAQuery query = new JPAQuery(entityManager);
        QParticipacionPersonaExternaEnPublicacionDTO qParticipacion = QParticipacionPersonaExternaEnPublicacionDTO.participacionPersonaExternaEnPublicacionDTO;

        List<ParticipacionPersonaExternaEnPublicacionDTO> participacionesDTO = query
                .from(qParticipacion).where(qParticipacion.produccion.eq(publicacionId))
                .list(qParticipacion);

        List<AutorPublicacion> autores = new ArrayList<AutorPublicacion>();
        for (ParticipacionPersonaExternaEnPublicacionDTO participacionDTO : participacionesDTO)
        {
            autores.add(creaAutorPublicacionDesde(participacionDTO));
        }
        mili = System.currentTimeMillis()-mili;
        log.info("PublicacionDAO.getAutoresExternosPublicacion " + mili);

        return autores;
    }

    private ParticipacionPublicacionCientificoTecnica creaParticipacionPublicacionCientificoTecnicaDesde(
            ParticipacionPersonaEnPublicacionDTO participacionPersonaEnPublicacionDTO)
            throws RegistroNoEncontradoException
    {
        ParticipacionPublicacionCientificoTecnica participacionPublicacion = new ParticipacionPublicacionCientificoTecnica();
        participacionPublicacion
                .setPublicacionCientificoTecnica(getPublicacionCientificoTecnicaById(participacionPersonaEnPublicacionDTO
                        .getProduccion()));
        participacionPublicacion.setCalidad(participacionPersonaEnPublicacionDTO
                .getCalidadParticipacion());

        if (participacionPersonaEnPublicacionDTO.getPosSobreTotal() != null)
        {
            participacionPublicacion.setPosicionAutor(
                    participacionPersonaEnPublicacionDTO.getPosSobreTotal(),
                    participacionPublicacion.getCalidad());
        }
        else
        {
            participacionPublicacion.setPosicionAutor(
                    participacionPersonaEnPublicacionDTO.getOrden(),
                    participacionPublicacion.getPublicacionCientificoTecnica().getPublicacion()
                            .getAutores().size(), participacionPublicacion.getCalidad());
        }

        participacionPublicacion.setResultadosDestacados(participacionPersonaEnPublicacionDTO
                .getResultadosDestacados());
        participacionPublicacion.setIsPublicacionRelevante(participacionPersonaEnPublicacionDTO
                .getIsPublicacionRelevante());

        return participacionPublicacion;
    }

    private PublicacionCientificoTecnica creaPublicacionCientificoTecnicaDesde(PublicacionDTO publicacionDTO)
            throws RegistroNoEncontradoException
    {
        PublicacionCientificoTecnica publicacionCientificoTecnica = new PublicacionCientificoTecnica();
        publicacionCientificoTecnica.setId(publicacionDTO.getId());

        Publicacion publicacion = new Publicacion();
        publicacion.setTipo(publicacionDTO.getTipo());
        publicacion.setSoporte(publicacionDTO.getSoporte());
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setNombre(publicacionDTO.getNombrePublicacion());
        publicacion.setVolumen(publicacionDTO.getVolumenPublicacion());
        publicacion.setPaginasInicioFin(publicacionDTO.getPaginasPublicacion());
        publicacion.setEditorial(publicacionDTO.getEditorialPublicacion());
        publicacion.setPais(publicacionDTO.getPaisPublicacion());
        publicacion.setRegion(publicacionDTO.getRegionPublicacion());
        publicacion.setCiudad(publicacionDTO.getCiudadPublicacion());
        publicacion.setFecha(publicacionDTO.getFechaPublicacion());
        publicacion.setUrl(publicacionDTO.getWebPublicacion());
        publicacion.setIsbns(Arrays.asList(publicacionDTO.getIsbnPublicacion()));

        publicacion.setDepositoLegal(publicacionDTO.getDepositoLegalPublicacion());
        publicacion.setColeccion(publicacionDTO.getColeccion());

        List<Impacto> listaImpacto = new ArrayList<Impacto>();
        for (ImpactoPublicacionDTO impactoDTO : publicacionDTO.getListaImpactoPublicacion())
        {
            listaImpacto.add(creaImpactoDesde(impactoDTO));
        }
        publicacionCientificoTecnica.setListaImpacto(listaImpacto);

        publicacionCientificoTecnica
                .setNResenyasEnRevistas(publicacionDTO.getNResenyasEnRevistas());
        publicacionCientificoTecnica.setTraducciones(publicacionDTO.getTraducciones());

        List<AutorPublicacion> autores = getAutoresPublicacion(publicacionDTO.getId());
        autores.addAll(getAutoresExternosPublicacion(publicacionDTO.getId()));
        publicacion.setAutores(autores);

        publicacionCientificoTecnica.setPublicacion(publicacion);

        return publicacionCientificoTecnica;
    }

    private AutorPublicacion creaAutorPublicacionDesde(PersonaDTO personaDTO,
            ParticipacionPersonaEnPublicacionDTO participacionPersonaEnPublicacionDTO)
    {
        AutorPublicacion autorPublicacion = new AutorPublicacion();
        autorPublicacion.setNombre(personaDTO.getNombre());
        autorPublicacion.setApellido1(personaDTO.getApellido1());
        autorPublicacion.setApellido2(personaDTO.getApellido2());
        autorPublicacion.setOrden(participacionPersonaEnPublicacionDTO.getOrden());

        return autorPublicacion;
    }

    private AutorPublicacion creaAutorPublicacionDesde(
            ParticipacionPersonaExternaEnPublicacionDTO participacionPersonaExternaEnPublicacionDTO)
    {
    	   AutorPublicacion autorPublicacion = new AutorPublicacion();
           
           String fullname = participacionPersonaExternaEnPublicacionDTO.getPersona();
           
           Pattern formattedName = Pattern.compile("^\\s*([^ ]+)(\\s+([^ ]+))?\\s*,\\s*(.+)$");
           Matcher formattedNameMatcher = formattedName.matcher(fullname);

           if (formattedNameMatcher.matches())
           {       	   
        	   autorPublicacion.setNombre(formattedNameMatcher.group(4));
        	   autorPublicacion.setApellido1(formattedNameMatcher.group(1));
        	   if (formattedNameMatcher.group(3) != null)
        	   {
        		   autorPublicacion.setApellido2(formattedNameMatcher.group(3));
        	   }
        	           	   
           }else
           {
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
           }	   

           autorPublicacion.setOrden(participacionPersonaExternaEnPublicacionDTO.getOrden());

           return autorPublicacion;
    }

    private Impacto creaImpactoDesde(ImpactoPublicacionDTO impactoPublicacionDTO)
    {
        Impacto impacto = new Impacto();
        impacto.setIndice(impactoPublicacionDTO.getIndiceImpacto());
        impacto.setFuente(impactoPublicacionDTO.getFuenteImpacto());
        impacto.setCategoria(impactoPublicacionDTO.getCategoria1(),
                impactoPublicacionDTO.getCategoria2());
        impacto.setPosicion(impactoPublicacionDTO.getPosicion());
        impacto.setNRevistas(impactoPublicacionDTO.getTotal());
        impacto.setIsDentroPorc25Top(impactoPublicacionDTO.getIs25pTop());

        return impacto;
    }
    

    private PublicacionDocente creaPublicacionDocenteDesde(
			PublicacionDTO publicacionDTO) throws RegistroNoEncontradoException {
    	  PublicacionDocente publicacionDocente = new PublicacionDocente();
    	  publicacionDocente.setId(publicacionDTO.getId());

          Publicacion publicacion = new Publicacion();
          publicacion.setTitulo(publicacionDTO.getTitulo());
          publicacion.setTipo(publicacionDTO.getTipo());
          publicacion.setSoporte(TipoSoporteDocente.getTipo(publicacionDTO.getSoporte()));          
          publicacion.setNombre(publicacionDTO.getNombrePublicacion());
          publicacion.setVolumen(publicacionDTO.getVolumenPublicacion());
          publicacion.setPaginasInicioFin(publicacionDTO.getPaginasPublicacion());
          publicacion.setEditorial(publicacionDTO.getEditorialPublicacion());
          publicacion.setPais(publicacionDTO.getPaisPublicacion());
          publicacion.setRegion(publicacionDTO.getRegionPublicacion());
          //publicacion.setCiudad(publicacionDTO.getCiudadPublicacion()); //No existe para pub docentes
          publicacion.setFecha(publicacionDTO.getFechaPublicacion());
          publicacion.setUrl(publicacionDTO.getWebPublicacion());
          publicacion.setIsbns(Arrays.asList(publicacionDTO.getIsbnPublicacion()));
          publicacion.setDepositoLegal(publicacionDTO.getDepositoLegalPublicacion());


          List<AutorPublicacion> autores = getAutoresPublicacion(publicacionDTO.getId());
          autores.addAll(getAutoresExternosPublicacion(publicacionDTO.getId()));
          publicacion.setAutores(autores);

          publicacionDocente.setPublicacion(publicacion);
          
          publicacionDocente.setDenominacion(publicacionDTO.getDenominacion());
          publicacionDocente.setDestinatarios(publicacionDTO.getDestinatarios());
          publicacionDocente.setFechaCreacion(publicacionDTO.getFechaCreacion());
          publicacionDocente.setJustificacion(publicacionDTO.getJustificacion());
          
          return publicacionDocente;
	}
    
    
    private ParticipacionPublicacionDocente creaParticipacionPublicacionDocenteDesde(
            ParticipacionPersonaEnPublicacionDTO participacionPersonaEnPublicacionDTO)
            throws RegistroNoEncontradoException
    {
    	ParticipacionPublicacionDocente participacionPublicacion = new ParticipacionPublicacionDocente();
        participacionPublicacion
                .setPublicacionDocente(getPublicacionDocenteById(participacionPersonaEnPublicacionDTO
                        .getProduccion()));
        participacionPublicacion.setCalidad(participacionPersonaEnPublicacionDTO
                .getCalidadParticipacion());

        if (participacionPersonaEnPublicacionDTO.getPosSobreTotal() != null)
        {
            participacionPublicacion.setPosicionAutor(
                    participacionPersonaEnPublicacionDTO.getPosSobreTotal(),
                    participacionPublicacion.getCalidad());
        }
        else
        {
            participacionPublicacion.setPosicionAutor(
                    participacionPersonaEnPublicacionDTO.getOrden(),
                    participacionPublicacion.getPublicacionDocente().getPublicacion()
                            .getAutores().size(), participacionPublicacion.getCalidad());
        }

        return participacionPublicacion;
    }

}
