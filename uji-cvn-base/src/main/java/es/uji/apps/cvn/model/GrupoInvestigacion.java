package es.uji.apps.cvn.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GrupoInvestigacion
{
    private String id;

    private String nombre;

    private String codigoNormalizado;

    private String fechaActualizacion;

    private String fechaAlta;

    private Boolean activo;

    private Double nComponentes;

    private Double nPostDoc;

    private Double nTesis;

    private String nombreEntidad;

    private String objetivo;

    private String pais;

    private String ciudad;

    private String region;

    private Persona responsable;

    private static String DATE_FORMATTER = "yyyy-MM-dd";

    public GrupoInvestigacion()
    {
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getCodigoNormalizado()
    {
        return codigoNormalizado;
    }

    public void setCodigoNormalizado(String codigoNormalizado)
    {
        this.codigoNormalizado = codigoNormalizado;
    }

    public String getFechaActualizacion()
    {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion)
    {
        if (fechaActualizacion != null)
        {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMATTER);
            this.fechaActualizacion = formatter.format(fechaActualizacion);
        }
    }

    public String getFechaAlta()
    {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta)
    {
        if (fechaAlta != null)
        {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMATTER);
            this.fechaAlta = formatter.format(fechaAlta);
        }
    }

    public Boolean getActivo()
    {
        return activo;
    }

    public void setActivo(Boolean activo)
    {
        this.activo = activo;
    }

    public Double getNComponentes()
    {
        return nComponentes;
    }

    public void setNComponentes(Long nComponentes)
    {
        if (nComponentes != null && nComponentes.compareTo(0L)>0)
        {
            this.nComponentes = nComponentes.doubleValue();
        }
    }

    public Double getNPostDoc()
    {
        return nPostDoc;
    }

    public void setNPostDoc(Long nPostDoc)
    {
        if (nPostDoc != null)
        {
            this.nPostDoc = nPostDoc.doubleValue();
        }
    }

    public Double getNTesis()
    {
        return nTesis;
    }

    public void setNTesis(Long nTesis)
    {
        if (nTesis != null)
        {
            this.nTesis = nTesis.doubleValue();
        }
    }

    public String getNombreEntidad()
    {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad)
    {
        this.nombreEntidad = nombreEntidad;
    }

    public String getObjetivo()
    {
        return objetivo;
    }

    public void setObjetivo(String objetivo)
    {
        this.objetivo = objetivo;
    }

    public String getPais()
    {
        return pais;
    }

    public void setPais(String pais)
    {
        this.pais = pais;
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

    public Persona getResponsable()
    {
        return responsable;
    }

    public void setResponsable(Persona responsable)
    {
        this.responsable = responsable;
    }

}
