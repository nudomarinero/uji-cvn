package es.uji.apps.cvn.model;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DocenciaImpartida
{
    private String tipo;
    private String titulacion;
    private String pais;
    private String region;
    private String ciudad;
    private String entidad;
    private String tipoEntidad;
    private String departamento;
    private String nombreAsignatura;
    private String tipoHorasCreditos;
    private Double creditos;
    private String idioma ;
    private Double numeroVeces;
    private XMLGregorianCalendar ultimaVez;
    private String titulacionTexto;


    public DocenciaImpartida()
    {
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public String getTitulacion()
    {
        return titulacion;
    }

    public void setTitulacion(String titulacion)
    {
        this.titulacion = titulacion;
    }

    public String getPais()
    {
        return pais;
    }

    public void setPais(String pais)
    {
        this.pais = pais;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getCiudad()
    {
        return ciudad;
    }

    public void setCiudad(String ciudad)
    {
        this.ciudad = ciudad;
    }

    public String getEntidad()
    {
        return entidad;
    }

    public void setEntidad(String entidad)
    {
        this.entidad = entidad;
    }

    public String getTipoEntidad()
    {
        return tipoEntidad;
    }

    public void setTipoEntidad(String tipoEntidad)
    {
        this.tipoEntidad = tipoEntidad;
    }

    public String getDepartamento()
    {
        return departamento;
    }

    public void setDepartamento(String departamento)
    {
        this.departamento = departamento;
    }

    public String getNombreAsignatura()
    {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura)
    {
        this.nombreAsignatura = nombreAsignatura;
    }

    public String getTipoHorasCreditos()
    {
        return tipoHorasCreditos;
    }

    public void setTipoHorasCreditos(String tipoHorasCreditos)
    {
        this.tipoHorasCreditos = tipoHorasCreditos;
    }

    public String getIdioma()
    {
        return idioma;
    }

    public void setIdioma(String idioma)
    {
        this.idioma = idioma;
    }

    public Double getCreditos()
    {
        return creditos;
    }

    public void setCreditos(Double creditos)
    {
        this.creditos = creditos;
    }

    public Double getNumeroVeces()
    {
        return numeroVeces;
    }

    public void setNumeroVeces(Double numeroVeces)
    {
        this.numeroVeces = numeroVeces;
    }

    public XMLGregorianCalendar getUltimaVez()
    {
        return ultimaVez;
    }

    public void setUltimaVez(Date ultimaVez)
    {
        if (ultimaVez != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(ultimaVez);
            try
            {
                this.ultimaVez = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            }
            catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public String getTitulacionTexto()
    {
        return titulacionTexto;
    }

    public void setTitulacionTexto(String titulacionTexto)
    {
        this.titulacionTexto = titulacionTexto;
    }
}
