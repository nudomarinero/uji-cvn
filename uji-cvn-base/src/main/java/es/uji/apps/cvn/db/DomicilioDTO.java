package es.uji.apps.cvn.db;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the CVN_EXT_DOMICILIOS database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CVN_EXT_DOMICILIOS")
public class DomicilioDTO implements Serializable
{
    @Id
    private Long id;

    @Column(name = "CIUDAD_CONTACTO")
    private String ciudadContacto;

    @Column(name = "CODIGO_POSTAL")
    private String codigoPostal;

    @Column(name = "DIRECCION_CONTACTO")
    private String direccionContacto;

    @Column(name = "DIRECCION_CONTACTO_AUX")
    private String direccionContactoAux;

    @Column(name = "PAIS_CONTACTO")
    private String paisContacto;

    @Column(name = "PER_ID")
    private Long personaId;

    @Column(name = "PROVINCIA_CONTACTO")
    private String provinciaContacto;

    @Column(name = "REGION_CONTACTO")
    private String regionContacto;

    @Column(name = "ORDEN_PREFERENCIA")
    private String ordenPreferencia;

    public DomicilioDTO()
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

    public String getCiudadContacto()
    {
        return this.ciudadContacto;
    }

    public void setCiudadContacto(String ciudadContacto)
    {
        this.ciudadContacto = ciudadContacto;
    }

    public String getCodigoPostal()
    {
        return this.codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal)
    {
        this.codigoPostal = codigoPostal;
    }

    public String getDireccionContacto()
    {
        return this.direccionContacto;
    }

    public void setDireccionContacto(String direccionContacto)
    {
        this.direccionContacto = direccionContacto;
    }

    public String getDireccionContactoAux()
    {
        return this.direccionContactoAux;
    }

    public void setDireccionContactoAux(String direccionContactoAux)
    {
        this.direccionContactoAux = direccionContactoAux;
    }

    public String getPaisContacto()
    {
        return this.paisContacto;
    }

    public void setPaisContacto(String paisContacto)
    {
        this.paisContacto = paisContacto;
    }

    public Long getPersonaId()
    {
        return personaId;
    }

    public void setPersonaId(Long personaId)
    {
        this.personaId = personaId;
    }

    public String getProvinciaContacto()
    {
        return this.provinciaContacto;
    }

    public void setProvinciaContacto(String provinciaContacto)
    {
        this.provinciaContacto = provinciaContacto;
    }

    public String getRegionContacto()
    {
        return this.regionContacto;
    }

    public void setRegionContacto(String regionContacto)
    {
        this.regionContacto = regionContacto;
    }

    public String getOrdenPreferencia()
    {
        return ordenPreferencia;
    }

    public void setOrdenPreferencia(String ordenPreferencia)
    {
        this.ordenPreferencia = ordenPreferencia;
    }

}