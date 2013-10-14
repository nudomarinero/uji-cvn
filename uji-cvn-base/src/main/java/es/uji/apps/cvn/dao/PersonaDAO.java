package es.uji.apps.cvn.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.jpa.impl.JPAQuery;

import es.uji.apps.cvn.db.DomicilioDTO;
import es.uji.apps.cvn.db.PersonaDTO;
import es.uji.apps.cvn.db.QDomicilioDTO;
import es.uji.apps.cvn.model.Domicilio;
import es.uji.apps.cvn.model.Persona;
import es.uji.commons.db.BaseDAODatabaseImpl;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

@Repository
public class PersonaDAO extends BaseDAODatabaseImpl
{
    public Persona getPersonaById(Long personaId) throws RegistroNoEncontradoException
    {
        PersonaDTO personaDTO;

        try
        {
            personaDTO = get(PersonaDTO.class, personaId).get(0);
        }
        catch (Exception e)
        {
            throw new RegistroNoEncontradoException();
        }

        return creaPersonaDesde(personaDTO);
    }

    public Persona getPersonaByIdConDomicilio(Long personaId) throws RegistroNoEncontradoException
    {
        PersonaDTO personaDTO;

        try
        {
            personaDTO = get(PersonaDTO.class, personaId).get(0);
        }
        catch (Exception e)
        {
            throw new RegistroNoEncontradoException();
        }
        
        Persona persona = creaPersonaDesde(personaDTO);

        JPAQuery query = new JPAQuery(entityManager);
        QDomicilioDTO qDomicilio = QDomicilioDTO.domicilioDTO;

        List<DomicilioDTO> domiciliosDTO = query.from(qDomicilio)
                .where(qDomicilio.personaId.eq(personaId)).list(qDomicilio);
        
        List<Domicilio> domicilios = new ArrayList<Domicilio>();
        for (DomicilioDTO domicilioDTO : domiciliosDTO)
        {
            domicilios.add(creaDomicilioDesde(domicilioDTO));
        }        

        persona.setDomiciliosContacto(domicilios);
        
        return persona;
    }

    private Persona creaPersonaDesde(PersonaDTO personaDTO)
    {
        Persona persona = new Persona();
        persona.setId(personaDTO.getId());
        persona.setNombre(personaDTO.getNombre());
        persona.setApellido1(personaDTO.getApellido1());
        persona.setApellido2(personaDTO.getApellido2());
        persona.setCiudadNacimiento(personaDTO.getCiudadNacimiento());
        persona.setEmail(personaDTO.getEmail());
        persona.setFax(personaDTO.getFax());
        persona.setFechaNacimiento(personaDTO.getFechaNacimiento());
        persona.setFoto(personaDTO.getFoto());
        persona.setGenero(personaDTO.getGenero());
        persona.setIdentificacion(personaDTO.getIdentificacion());
        persona.setIndiceH(personaDTO.getIndiceH());
        persona.setIndiceHFecha(personaDTO.getIndiceHFecha());
        persona.setMimeTypeFoto(personaDTO.getMimeType());
        persona.setNacionalidad(personaDTO.getNacionalidad());
        persona.setPaisNacimiento(personaDTO.getPaisNacimiento());
        persona.setRegionNacimiento(personaDTO.getRegionNacimiento());
        persona.setTelefonoFijo(personaDTO.getTelefonoFijo());
        persona.setTelefonoMovil(personaDTO.getTelefonoMovil());
        persona.setTipoIdentificacion(personaDTO.getTipoIdentificacion());
        persona.setWebpage(personaDTO.getWebpage());

        return persona;
    }

    private Domicilio creaDomicilioDesde(DomicilioDTO domicilioDTO)
    {
        Domicilio domicilio = new Domicilio();
        domicilio.setCiudadContacto(domicilioDTO.getCiudadContacto());
        domicilio.setCodigoPostal(domicilioDTO.getCodigoPostal());
        domicilio.setDireccionContacto(domicilioDTO.getDireccionContacto());
        domicilio.setDireccionContactoAux(domicilioDTO.getDireccionContactoAux());
        domicilio.setId(domicilioDTO.getId());
        domicilio.setOrdenPreferencia(domicilioDTO.getOrdenPreferencia());
        domicilio.setPaisContacto(domicilioDTO.getPaisContacto());
        domicilio.setPersonaId(domicilioDTO.getPersonaId());
        domicilio.setProvinciaContacto(domicilioDTO.getProvinciaContacto());
        domicilio.setRegionContacto(domicilioDTO.getRegionContacto());

        return domicilio;
    }

    @Transactional
    public Persona insertaPersona(Persona persona)
    {
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(persona.getId());
        personaDTO.setNombre(persona.getNombre());
        personaDTO.setApellido1(persona.getApellido1());
        personaDTO.setApellido2(persona.getApellido2());
        personaDTO.setCiudadNacimiento(persona.getCiudadNacimiento());
        personaDTO.setEmail(persona.getEmail());
        personaDTO.setFax(persona.getFax());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try
        {
            personaDTO.setFechaNacimiento(persona.getFechaNacimiento().toGregorianCalendar()
                    .getTime());
        }
        catch (Exception e)
        {
        }

        try
        {
            personaDTO.setFoto(Base64.decodeBase64(persona.getFoto().getBytes()));
        }
        catch (Exception e)
        {
        }

        personaDTO.setGenero(persona.getGenero());
        personaDTO.setIdentificacion(persona.getIdentificacion());
        personaDTO.setIndiceH(persona.getIndiceH());

        try
        {
            personaDTO.setIndiceHFecha(formatter.parse(persona.getIndiceHFecha()));
        }
        catch (Exception e)
        {
        }

        personaDTO.setMimeType(persona.getMimeTypeFoto());
        personaDTO.setNacionalidad(persona.getNacionalidad());
        personaDTO.setPaisNacimiento(persona.getPaisNacimiento());
        personaDTO.setRegionNacimiento(persona.getRegionNacimiento());
        personaDTO.setTelefonoFijo(persona.getTelefonoFijo());
        personaDTO.setTelefonoMovil(persona.getTelefonoMovil());
        personaDTO.setTipoIdentificacion(persona.getTipoIdentificacion());
        personaDTO.setWebpage(persona.getWebpage());

        return creaPersonaDesde(insert(personaDTO));
    }

    public void eliminaPersona(Long personaId)
    {
        try
        {
            PersonaDTO personaDTO = get(PersonaDTO.class, personaId).get(0);
            for (DomicilioDTO domicilioDTO : personaDTO.getDomicilios())
            {
                delete(DomicilioDTO.class, domicilioDTO.getId());
            }
            delete(PersonaDTO.class, personaId);
        }
        catch (Exception e)
        {
        }
    }
}