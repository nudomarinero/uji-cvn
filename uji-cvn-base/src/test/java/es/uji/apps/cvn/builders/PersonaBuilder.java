package es.uji.apps.cvn.builders;

import es.uji.apps.cvn.dao.PersonaDAO;
import es.uji.apps.cvn.model.Persona;

public class PersonaBuilder
{
    private Persona persona;
    private PersonaDAO personaDAO;
    
    public PersonaBuilder(PersonaDAO personaDAO)
    {
        this.personaDAO = personaDAO;
        persona = new Persona();
    }
    
    public PersonaBuilder()
    {
        this(null);
    }
    
    public PersonaBuilder withId(Long id)
    {
        persona.setId(id);
        return this;
    }
    
    public PersonaBuilder withNombre(String nombre)
    {
        persona.setNombre(nombre);
        return this;
    }
    
    public PersonaBuilder withApellido1(String apellido1)
    {
        persona.setApellido1(apellido1);
        return this;
    }
    
    public PersonaBuilder withApellido2(String apellido2)
    {
        persona.setApellido2(apellido2);
        return this;
    }
    
    public PersonaBuilder withIdentificacion(String identificacion)
    {
        persona.setIdentificacion(identificacion);
        return this;
    }
    
    public PersonaBuilder withTipoIdentificacion(String tipoIdentificacion)
    {
        persona.setTipoIdentificacion(tipoIdentificacion);
        return this;
    }
    
    public Persona build()
    {
        if (personaDAO != null)
        {
            persona = personaDAO.insertaPersona(persona);
        }
        
        return persona;
    }
}
