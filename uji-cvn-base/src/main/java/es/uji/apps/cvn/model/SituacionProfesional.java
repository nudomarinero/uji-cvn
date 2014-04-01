package es.uji.apps.cvn.model;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class SituacionProfesional
{

    private String id;

    private Long perId;

    private Long lineaId;

    private Boolean isGestionDocente;

    private String nombreEntidad;

    private String tipoEntidad;

    private String tipoEntidadOtros;

    private String centroVal;

    private String centroCas;

    private String centroIng;

    private String servicioVal;

    private String servicioCas;

    private String servicioIng;

    private String ciudad;

    private String region;

    private Long pais;

    private String telefono;

    private String fax;

    private String mail;

    private String categoria;

    private XMLGregorianCalendar fechaInicio;

    private String duracion;

    private String situacion;

    private String situacionOtros;

    private String dedicacion;

    private String unesco1;

    private String unesco2;

    private String unesco3;

    private String textoDedicacion;

    private String palabrasClave;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Long getPerId()
    {
        return perId;
    }

    public void setPerId(Long perId)
    {
        this.perId = perId;
    }

    public Long getLineaId()
    {
        return lineaId;
    }

    public void setLineaId(Long lineaId)
    {
        this.lineaId = lineaId;
    }

    public Boolean getGestionDocente()
    {
        return isGestionDocente;
    }

    public void setGestionDocente(Boolean gestionDocente)
    {
        isGestionDocente = gestionDocente;
    }

    public String getNombreEntidad()
    {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad)
    {
        this.nombreEntidad = nombreEntidad;
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

    public String getCentroVal()
    {
        return centroVal;
    }

    public void setCentroVal(String centroVal)
    {
        this.centroVal = centroVal;
    }

    public String getCentroCas()
    {
        return centroCas;
    }

    public void setCentroCas(String centroCas)
    {
        this.centroCas = centroCas;
    }

    public String getCentroIng()
    {
        return centroIng;
    }

    public void setCentroIng(String centroIng)
    {
        this.centroIng = centroIng;
    }

    public String getServicioVal()
    {
        return servicioVal;
    }

    public void setServicioVal(String servicioVal)
    {
        this.servicioVal = servicioVal;
    }

    public String getServicioCas()
    {
        return servicioCas;
    }

    public void setServicioCas(String servicioCas)
    {
        this.servicioCas = servicioCas;
    }

    public String getServicioIng()
    {
        return servicioIng;
    }

    public void setServicioIng(String servicioIng)
    {
        this.servicioIng = servicioIng;
    }

    public String getCiudad()
    {
        return ciudad;
    }

    public void setCiudad(String ciudad)
    {
        this.ciudad = ciudad;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public Long getPais()
    {
        return pais;
    }

    public void setPais(Long pais)
    {
        this.pais = pais;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getCategoria()
    {
        return categoria;
    }

    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }

    public XMLGregorianCalendar getFechaInicio()
    {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio)
    {
        if (fechaInicio != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(fechaInicio);
            try
            {
                this.fechaInicio = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            }
            catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public String getDuracion()
    {
        return duracion;
    }

    public void setDuracion(Long duracion)
    {
        // Convertimos la duración de días a meses (o días) y formateamos a estándar ISO
        if (duracion != null)
        {
            int duracionMeses = duracion.intValue() / 30;
            if (duracionMeses > 0)
            {
                this.duracion = "P" + duracionMeses + "M";
            }
            else
            {
                this.duracion = "P" + duracion.intValue() + "D";
            }
        }
    }

    public String getSituacion()
    {
        return situacion;
    }

    public void setSituacion(String situacion)
    {
        this.situacion = situacion;
    }

    public String getSituacionOtros()
    {
        return situacionOtros;
    }

    public void setSituacionOtros(String situacionOtros)
    {
        this.situacionOtros = situacionOtros;
    }

    public String getDedicacion()
    {
        return dedicacion;
    }

    public void setDedicacion(String dedicacion)
    {
        this.dedicacion = dedicacion;
    }

    public String getUnesco1()
    {
        return unesco1;
    }

    public void setUnesco1(String unesco1)
    {
        this.unesco1 = unesco1;
    }

    public String getUnesco2()
    {
        return unesco2;
    }

    public void setUnesco2(String unesco2)
    {
        this.unesco2 = unesco2;
    }

    public String getUnesco3()
    {
        return unesco3;
    }

    public void setUnesco3(String unesco3)
    {
        this.unesco3 = unesco3;
    }

    public String getTextoDedicacion()
    {
        return textoDedicacion;
    }

    public void setTextoDedicacion(String textoDedicacion)
    {
        this.textoDedicacion = textoDedicacion;
    }

    public String getPalabrasClave()
    {
        return palabrasClave;
    }

    public void setPalabrasClave(String palabrasClave)
    {
        this.palabrasClave = palabrasClave;
    }

}
