package es.uji.apps.cvn.db;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the CVN_PLANTILLAS database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CVN_PLANTILLAS")
public class PlantillaDTO implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String idioma;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_ULTIMA_ACTUALIZACION")
    private Date fechaUltimaActualizacion;

    private String nombre;

    @Column(name = "PER_ID")
    private Long persona;

    @Lob()
    private byte[] plantilla;

    public PlantillaDTO()
    {
    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getIdioma()
    {
        return idioma;
    }

    public void setIdioma(String idioma)
    {
        this.idioma = idioma;
    }

    public Date getFechaUltimaActualizacion()
    {
        return this.fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion)
    {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public Long getPersona()
    {
        return this.persona;
    }

    public void setPersona(Long persona)
    {
        this.persona = persona;
    }

    public byte[] getPlantilla()
    {
        return this.plantilla;
    }

    public void setPlantilla(byte[] plantilla)
    {
        this.plantilla = plantilla;
    }

}