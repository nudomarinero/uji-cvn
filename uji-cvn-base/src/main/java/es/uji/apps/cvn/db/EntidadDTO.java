package es.uji.apps.cvn.db;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the CVN_EXT_ENTIDADES database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CVN_EXT_ENTIDADES")
public class EntidadDTO implements Serializable
{
    @Id
    private Long id;

    private String acronimo;

    private String ciudad;

    private String nombre;

    private String pais;

    private String region;

    @Column(name = "REGION_STR")
    private String regionStr;

    private String subtipo;

    private String tipo;

    @Column(name = "TIPO_DESCR")
    private String tipoDescr;

    public EntidadDTO()
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

    public String getAcronimo()
    {
        return this.acronimo;
    }

    public void setAcronimo(String acronimo)
    {
        this.acronimo = acronimo;
    }

    public String getCiudad()
    {
        return this.ciudad;
    }

    public void setCiudad(String ciudad)
    {
        this.ciudad = ciudad;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getPais()
    {
        return this.pais;
    }

    public void setPais(String pais)
    {
        this.pais = pais;
    }

    public String getRegion()
    {
        return this.region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getRegionStr()
    {
        return this.regionStr;
    }

    public void setRegionStr(String regionStr)
    {
        this.regionStr = regionStr;
    }

    public String getSubtipo()
    {
        return this.subtipo;
    }

    public void setSubtipo(String subtipo)
    {
        this.subtipo = subtipo;
    }

    public String getTipo()
    {
        return this.tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public String getTipoDescr()
    {
        return this.tipoDescr;
    }

    public void setTipoDescr(String tipoDescr)
    {
        this.tipoDescr = tipoDescr;
    }

}