package es.uji.apps.cvn.builders;

import es.uji.apps.cvn.dao.ProyectoDAO;
import es.uji.apps.cvn.model.Entidad;

public class EntidadBuilder
{
    private Entidad entidad;
    private ProyectoDAO proyectoDAO;
    
    public EntidadBuilder(ProyectoDAO proyectoDAO)
    {
        this.proyectoDAO = proyectoDAO;
        entidad = new Entidad();
    }
    
    public EntidadBuilder()
    {
        this(null);
    }
    
    public EntidadBuilder withId(Long id)
    {
        entidad.setId(id);
        return this;
    }
    
    public EntidadBuilder withAcronimo(String acronimo)
    {
        entidad.setAcronimo(acronimo);
        return this;
    }
    
    public EntidadBuilder withNombre(String nombre)
    {
        entidad.setNombre(nombre);
        return this;
    }
    
    public Entidad build()
    {
        if (proyectoDAO != null)
        {
            entidad = proyectoDAO.insertaEntidad(entidad);
        }
        
        return entidad;
    }
}
