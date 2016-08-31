package es.uji.apps.cvn.db;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the CVN_VIEW_PER_PART_PROD_CONG database table.
 * 
 */
@Entity
@IdClass(ParticipacionPersonaEnCongresoDTOId.class)
@Table(name = "CVN_VIEW_PER_PART_CONG_PCI")
public class ParticipacionPersonaEnCongresoDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long orden;

    @Id
    private Long persona;

    @Id
    private Long produccion;

    @Column(name = "TIPO_PARTICIPACION")
    private String tipoParticipacion;

    @Column(name = "CARACTER")
    private Long caracterPublicacion;

    @Column(name = "CORRESPONDING_AUTHOR")
    private Boolean correspondingAuthor;

    public ParticipacionPersonaEnCongresoDTO()
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

    public Long getPersona()
    {
        return this.persona;
    }

    public void setPersona(Long persona)
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

    public Long getCaracterPublicacion()
    {
        return caracterPublicacion;
    }

    public void setCaracterPublicacion(Long caracterPublicacion)
    {
        this.caracterPublicacion = caracterPublicacion;
    }

    public Boolean getCorrespondingAuthor()
    {
        return correspondingAuthor;
    }

    public void setCorrespondingAuthor(Boolean correspondingAuthor)
    {
        this.correspondingAuthor = correspondingAuthor;
    }
}