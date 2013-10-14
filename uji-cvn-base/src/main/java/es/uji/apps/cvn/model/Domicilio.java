package es.uji.apps.cvn.model;

import java.util.Collections;
import java.util.List;

import es.uji.apps.cvn.model.comparators.DomicilioComparator;
import es.uji.apps.cvn.translators.TipoProvincia;

public class Domicilio
{
    private Long id;

    private String ciudadContacto;

    private String codigoPostal;

    private String direccionContacto;

    private String direccionContactoAux;

    private String paisContacto;

    private Long personaId;

    private String provinciaContacto;

    private String regionContacto;

    private String ordenPreferencia;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCiudadContacto()
    {
        return ciudadContacto;
    }

    public void setCiudadContacto(String ciudadContacto)
    {
        this.ciudadContacto = ciudadContacto;
    }

    public String getCodigoPostal()
    {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal)
    {
        this.codigoPostal = codigoPostal;
    }

    public String getDireccionContacto()
    {
        return direccionContacto;
    }

    public void setDireccionContacto(String direccionContacto)
    {
        this.direccionContacto = direccionContacto;
    }

    public String getDireccionContactoAux()
    {
        return direccionContactoAux;
    }

    public void setDireccionContactoAux(String direccionContactoAux)
    {
        this.direccionContactoAux = direccionContactoAux;
    }

    public String getPaisContacto()
    {
        return paisContacto;
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
        return provinciaContacto;
    }

    public void setProvinciaContacto(String provinciaContacto)
    {
        this.provinciaContacto = TipoProvincia.getCodigoTipo(provinciaContacto);
    }

    public String getRegionContacto()
    {
        return regionContacto;
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

    public static void ordenaListaDomiciliosPorOrdenPreferencia(List<Domicilio> domicilios)
    {
        Collections.sort(domicilios, new DomicilioComparator());
    }
}
