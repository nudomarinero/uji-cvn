package es.uji.apps.cvn.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the CVN_PDFS_GENERADOS database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CVN_PDFS_GENERADOS2")
public class CvnGeneradoDTO implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob()
    private byte[] cvn;

    private String estado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_ULTIMA_ACTUALIZACION")
    private Date fechaUltimaActualizacion;

    private String mensaje;

    @Column(name = "PER_ID")
    private Long personaId;

    private Long solicitante;

    private String template;

    private String idioma;

    @Column(name = "PLANTILLA_ID")
    private Long plantillaId;

    public CvnGeneradoDTO()
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

    public byte[] getCvn()
    {
        return this.cvn;
    }

    public void setCvn(byte[] cvn)
    {
        this.cvn = cvn;
    }

    public String getEstado()
    {
        return this.estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    public Date getFechaUltimaActualizacion()
    {
        return this.fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion)
    {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public String getMensaje()
    {
        return this.mensaje;
    }

    public void setMensaje(String mensaje)
    {
        this.mensaje = mensaje;
    }

    public Long getPersonaId()
    {
        return this.personaId;
    }

    public void setPersonaId(Long personaId)
    {
        this.personaId = personaId;
    }

    public Long getSolicitante()
    {
        return solicitante;
    }

    public void setSolicitante(Long solicitante)
    {
        this.solicitante = solicitante;
    }

    public String getTemplate()
    {
        return template;
    }

    public void setTemplate(String template)
    {
        this.template = template;
    }

    public String getIdioma()
    {
        return idioma;
    }

    public void setIdioma(String idioma)
    {
        this.idioma = idioma;
    }

    public Long getPlantillaId()
    {
        return plantillaId;
    }

    public void setPlantillaId(Long plantillaId)
    {
        this.plantillaId = plantillaId;
    }
}