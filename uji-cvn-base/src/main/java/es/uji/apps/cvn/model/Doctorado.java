package es.uji.apps.cvn.model;


import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class Doctorado
{
    private String id;
    private String identificacionPrograma;
    private String identificacionNombre;
    private String suficiencia;
    private XMLGregorianCalendar fechaSuficiencia;
    private String pais;
    private String region;
    private String ciudad;
    private String entidad;
    private String tipoEntidad;
    private String tipoEntidadOtros;
    private XMLGregorianCalendar fechaTitulacion;
    private XMLGregorianCalendar fechaDoctorado;
    private String tituloTesis;
    private String directorNombre;
    private String directorApellido1;
    private String directorApellido2;
    private String codirectorNombre;
    private String codirectorApellido1;
    private String codirectorApellido2;
    private String calificacion;
    private boolean isDoctoradoEuropeo;
    private boolean isMencion;
    private boolean isPremiado;
    private XMLGregorianCalendar fechaPremiado;
    private boolean isHomologado;
    private XMLGregorianCalendar fechaHomologado;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getIdentificacionPrograma()
    {
        return identificacionPrograma;
    }

    public void setIdentificacionPrograma(String identificacionPrograma)
    {
        this.identificacionPrograma = identificacionPrograma;
    }

    public String getIdentificacionNombre()
    {
        return identificacionNombre;
    }

    public void setIdentificacionNombre(String identificacionNombre)
    {
        this.identificacionNombre = identificacionNombre;
    }

    public String getSuficiencia()
    {
        return suficiencia;
    }

    public void setSuficiencia(String suficiencia)
    {
        this.suficiencia = suficiencia;
    }

    public XMLGregorianCalendar getFechaSuficiencia()
    {
        return fechaSuficiencia;
    }

    public void setFechaSuficiencia(Date fechaSuficiencia)
    {
        if (fechaSuficiencia != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(fechaSuficiencia);
            try
            {
                this.fechaSuficiencia = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            } catch (DatatypeConfigurationException e)
            {
            }
        }
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

    public String getTipoEntidadOtros()
    {
        return tipoEntidadOtros;
    }

    public void setTipoEntidadOtros(String tipoEntidadOtros)
    {
        this.tipoEntidadOtros = tipoEntidadOtros;
    }

    public XMLGregorianCalendar getFechaTitulacion()
    {
        return fechaTitulacion;
    }

    public void setFechaTitulacion(Date fechaTitulacion)
    {
        if (fechaTitulacion != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(fechaTitulacion);
            try
            {
                this.fechaTitulacion = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            } catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public XMLGregorianCalendar getFechaDoctorado()
    {
        return fechaDoctorado;
    }

    public void setFechaDoctorado(Date fechaDoctorado)
    {
        if (fechaDoctorado != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(fechaDoctorado);
            try
            {
                this.fechaDoctorado = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            } catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public String getTituloTesis()
    {
        return tituloTesis;
    }

    public void setTituloTesis(String tituloTesis)
    {
        this.tituloTesis = tituloTesis;
    }

    public String getDirectorNombre()
    {
        return directorNombre;
    }

    public void setDirectorNombre(String directorNombre)
    {
        this.directorNombre = directorNombre;
    }

    public String getDirectorApellido1()
    {
        return directorApellido1;
    }

    public void setDirectorApellido1(String directorApellido1)
    {
        this.directorApellido1 = directorApellido1;
    }

    public String getDirectorApellido2()
    {
        return directorApellido2;
    }

    public void setDirectorApellido2(String directorApellido2)
    {
        this.directorApellido2 = directorApellido2;
    }

    public String getCodirectorNombre()
    {
        return codirectorNombre;
    }

    public void setCodirectorNombre(String codirectorNombre)
    {
        this.codirectorNombre = codirectorNombre;
    }

    public String getCodirectorApellido1()
    {
        return codirectorApellido1;
    }

    public void setCodirectorApellido1(String codirectorApellido1)
    {
        this.codirectorApellido1 = codirectorApellido1;
    }

    public String getCodirectorApellido2()
    {
        return codirectorApellido2;
    }

    public void setCodirectorApellido2(String codirectorApellido2)
    {
        this.codirectorApellido2 = codirectorApellido2;
    }

    public String getCalificacion()
    {
        return calificacion;
    }

    public void setCalificacion(String calificacion)
    {
        this.calificacion = calificacion;
    }

    public boolean isDoctoradoEuropeo()
    {
        return isDoctoradoEuropeo;
    }

    public void setIsDoctoradoEuropeo(boolean isDoctoradoEuropeo)
    {
        this.isDoctoradoEuropeo = isDoctoradoEuropeo;
    }

    public boolean isMencion()
    {
        return isMencion;
    }

    public void setIsMencion(boolean isMencion)
    {
        this.isMencion = isMencion;
    }

    public boolean isPremiado()
    {
        return isPremiado;
    }

    public void setIsPremiado(boolean isPremiado)
    {
        this.isPremiado = isPremiado;
    }

    public XMLGregorianCalendar getFechaPremiado()
    {
        return fechaPremiado;
    }

    public void setFechaPremiado(Date fechaPremiado)
    {
        if (fechaPremiado != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(fechaPremiado);
            try
            {
                this.fechaPremiado = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            } catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public boolean isHomologado()
    {
        return isHomologado;
    }

    public void setIsHomologado(boolean isHomologado)
    {
        this.isHomologado = isHomologado;
    }

    public XMLGregorianCalendar getFechaHomologado()
    {
        return fechaHomologado;
    }

    public void setFechaHomologado(Date fechaHomologado)
    {
        if (fechaHomologado != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(fechaHomologado);
            try
            {
                this.fechaHomologado = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            } catch (DatatypeConfigurationException e)
            {
            }
        }
    }
}
