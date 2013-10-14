package es.uji.apps.cvn.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * The persistent class for the CVN_VIEW_PER_PART_PROY database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@IdClass(ParticipacionPersonaEnProyectoDTOId.class)
@Table(name = "CVN_VIEW_PER_PART_PROY")
public class ParticipacionPersonaEnProyectoDTO implements Serializable
{
    private String aportaciones;

    @Column(name = "CALIDAD_PARTICIPACION")
    private String calidadParticipacion;

    private Float dedicacion;

    @Id
    private Long persona;

    @Id
    private Long proyecto;

    private String responsable;

    @Column(name = "TIPO_PARTICIPACION")
    private String tipoParticipacion;

    public ParticipacionPersonaEnProyectoDTO()
    {
    }

    public String getAportaciones()
    {
        return this.aportaciones;
    }

    public void setAportaciones(String aportaciones)
    {
        this.aportaciones = aportaciones;
    }

    public String getCalidadParticipacion()
    {
        return this.calidadParticipacion;
    }

    public void setCalidadParticipacion(String calidadParticipacion)
    {
        this.calidadParticipacion = calidadParticipacion;
    }

    public Float getDedicacion()
    {
        return this.dedicacion;
    }

    public void setDedicacion(Float dedicacion)
    {
        this.dedicacion = dedicacion;
    }

    public Long getPersona()
    {
        return this.persona;
    }

    public void setPersona(Long persona)
    {
        this.persona = persona;
    }

    public Long getProyecto()
    {
        return this.proyecto;
    }

    public void setProyecto(Long proyecto)
    {
        this.proyecto = proyecto;
    }

    public String getResponsable()
    {
        return this.responsable;
    }

    public void setResponsable(String responsable)
    {
        this.responsable = responsable;
    }

    public String getTipoParticipacion()
    {
        return this.tipoParticipacion;
    }

    public void setTipoParticipacion(String tipoParticipacion)
    {
        this.tipoParticipacion = tipoParticipacion;
    }

}