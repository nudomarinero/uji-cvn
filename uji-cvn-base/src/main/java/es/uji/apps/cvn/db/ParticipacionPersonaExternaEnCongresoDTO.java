package es.uji.apps.cvn.db;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the CVN_VIEW_PER_PART_PROD_CONG_EX database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@IdClass(ParticipacionPersonaExternaEnCongresoDTOId.class)
@Table(name = "CVN_VIEW_PER_PART_PROD_CONG_EX")
public class ParticipacionPersonaExternaEnCongresoDTO implements Serializable
{
    private Long orden;

    @Id
    private String persona;

    @Id
    private Long produccion;

    @Column(name = "TIPO_PARTICIPACION")
    private String tipoParticipacion;

    public ParticipacionPersonaExternaEnCongresoDTO()
    {
    }

    public Long getOrden()
    {
        return this.orden;
    }

    public void setOrden(Long orden)
    {
        this.orden = orden;
    }

    public String getPersona()
    {
        return this.persona;
    }

    public void setPersona(String persona)
    {
        this.persona = persona;
    }

    public Long getProduccion()
    {
        return this.produccion;
    }

    public void setProduccion(Long produccion)
    {
        this.produccion = produccion;
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