package es.uji.apps.cvn.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the CVN_VIEW_GRUPOS_INVESTIGACION database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CVN_VIEW_GRUPOS_INVESTIGACION")
public class GrupoInvestigacionDTO implements Serializable
{
    private String activo;

    private String ciudad;

    @Column(name = "CODIGO_NORMALIZADO")
    private String codigoNormalizado;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_ACTUALIZACION")
    private Date fechaActualizacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_ALTA")
    private Date fechaAlta;

    @Id
    private String id;

    private Long nComponentes;

    private String nombre;

    @Column(name = "NOMBRE_ENTIDAD")
    private String nombreEntidad;

    private String nPostDoc;

    private String nTesis;

    private String objetivo;

    private String pais;

    private String region;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RESPONSABLE", referencedColumnName = "ID")
    //private PersonaDTO responsable;
    private PersonaMiniDTO responsable;

    public GrupoInvestigacionDTO()
    {
    }

    public String getActivo()
    {
        return this.activo;
    }

    public void setActivo(String activo)
    {
        this.activo = activo;
    }

    public String getCiudad()
    {
        return this.ciudad;
    }

    public void setCiudad(String ciudad)
    {
        this.ciudad = ciudad;
    }

    public String getCodigoNormalizado()
    {
        return this.codigoNormalizado;
    }

    public void setCodigoNormalizado(String codigoNormalizado)
    {
        this.codigoNormalizado = codigoNormalizado;
    }

    public Date getFechaActualizacion()
    {
        return this.fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion)
    {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getFechaAlta()
    {
        return this.fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta)
    {
        this.fechaAlta = fechaAlta;
    }

    public String getId()
    {
        return this.id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Long getNComponentes()
    {
        return this.nComponentes;
    }

    public void setNComponentes(Long nComponentes)
    {
        this.nComponentes = nComponentes;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombreEntidad()
    {
        return this.nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad)
    {
        this.nombreEntidad = nombreEntidad;
    }

    public String getNPostDoc()
    {
        return this.nPostDoc;
    }

    public void setNPostDoc(String nPostDoc)
    {
        this.nPostDoc = nPostDoc;
    }

    public String getNTesis()
    {
        return this.nTesis;
    }

    public void setNTesis(String nTesis)
    {
        this.nTesis = nTesis;
    }

    public String getObjetivo()
    {
        return this.objetivo;
    }

    public void setObjetivo(String objetivo)
    {
        this.objetivo = objetivo;
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

    public PersonaMiniDTO getResponsable()
    {
        return this.responsable;
    }

    public void setResponsable(PersonaMiniDTO responsable)
    {
        this.responsable = responsable;
    }

}