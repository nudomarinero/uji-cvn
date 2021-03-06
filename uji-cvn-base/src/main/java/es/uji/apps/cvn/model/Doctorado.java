package es.uji.apps.cvn.model;


import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class Doctorado
{
    private String id;
    private String programa;
    private String suficiencia;
    private XMLGregorianCalendar suficienciaFecha;
    private Integer tesisPropia;
    private String pais;
    private String region;
    private String ciudad;
    private String entidad;
    private String tipoEntidad;
    private XMLGregorianCalendar titulacionFecha;
    private XMLGregorianCalendar fechaDoctorEuropeo;
    private String titulo;
    private String directorNombre;
    private String directorApellido1;
    private String directorApellido2;
    private String codirectorNombre;
    private String codirectorApellido1;
    private String codirectorApellido2;
    private String calificacion;
    private boolean isDoctorEuropeo;
    private boolean isMencion;
    private XMLGregorianCalendar mencionFecha;
    private boolean isPremiado;
    private XMLGregorianCalendar premioFecha;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPrograma()
    {
        return programa;
    }

    public void setPrograma(String programa)
    {
        this.programa = programa;
    }

    public String getSuficiencia()
    {
        return suficiencia;
    }

    public void setSuficiencia(String suficiencia)
    {
        this.suficiencia = suficiencia;
    }

    public XMLGregorianCalendar getSuficienciaFecha()
    {
        return suficienciaFecha;
    }

    public void setSuficienciaFecha(Date suficienciaFecha)
    {
        if (suficienciaFecha != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(suficienciaFecha);
            try
            {
                this.suficienciaFecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            } catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public Integer getTesisPropia()
    {
        return tesisPropia;
    }

    public void setTesisPropia(Integer tesisPropia)
    {
        this.tesisPropia = tesisPropia;
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

    public XMLGregorianCalendar getTitulacionFecha()
    {
        return titulacionFecha;
    }

    public void setTitulacionFecha(Date titulacionFecha)
    {
        if (titulacionFecha != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(titulacionFecha);
            try
            {
                this.titulacionFecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            } catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public XMLGregorianCalendar getFechaDoctorEuropeo()
    {
        return fechaDoctorEuropeo;
    }

    public void setFechaDoctorEuropeo(Date fechaDoctorEuropeo)
    {
        if (fechaDoctorEuropeo != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(fechaDoctorEuropeo);
            try
            {
                this.fechaDoctorEuropeo = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            } catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
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

    public boolean isDoctorEuropeo()
    {
        return isDoctorEuropeo;
    }

    public void setIsDoctorEuropeo(boolean isDoctorEuropeo)
    {
        this.isDoctorEuropeo = isDoctorEuropeo;
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

    public XMLGregorianCalendar getPremioFecha()
    {
        return premioFecha;
    }

    public XMLGregorianCalendar getMencionFecha()
    {
        return mencionFecha;
    }

    public void setMencionFecha(Date mencionFecha)
    {
        if (mencionFecha != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(mencionFecha);
            try
            {
                this.mencionFecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            } catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public void setPremioFecha(Date premioFecha)
    {
        if (premioFecha != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(premioFecha);
            try
            {
                this.premioFecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            } catch (DatatypeConfigurationException e)
            {
            }
        }
    }
}
