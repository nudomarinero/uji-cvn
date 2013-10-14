package es.uji.apps.cvn.model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import es.uji.apps.cvn.client.exceptions.PlantillaNoAutorizadaException;
import es.uji.apps.cvn.model.comparators.PlantillaComparator;

public class PlantillaCvn
{
    private Long id;

    private Long persona;

    private Date fechaUltimaActualizacion;

    private String nombre;

    private String idioma;

    private byte[] plantilla;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getPersona()
    {
        return persona;
    }

    public void setPersona(Long persona)
    {
        this.persona = persona;
    }

    public Date getFechaUltimaActualizacion()
    {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion)
    {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getIdioma()
    {
        return idioma;
    }

    public void setIdioma(String idioma)
    {
        this.idioma = idioma;
    }

    public byte[] getPlantilla()
    {
        return plantilla;
    }

    public void setPlantilla(byte[] plantilla)
    {
        this.plantilla = plantilla;
    }

    public void compruebaPropietarioPlantilla(Long personaId) throws PlantillaNoAutorizadaException
    {
        if (!this.persona.equals(personaId))
        {
            throw new PlantillaNoAutorizadaException();
        }
    }

    public static void ordenaListaPlantillasPorFechaActualizacion(List<PlantillaCvn> plantillas)
    {
        Collections.sort(plantillas, new PlantillaComparator());
    }
    
    public void updatePlantilla(String nombre, String idioma, byte[] plantilla)
    {
        this.nombre = nombre;
        this.idioma = idioma;
        this.plantilla = plantilla;
        this.fechaUltimaActualizacion = new Date();
    }
}
