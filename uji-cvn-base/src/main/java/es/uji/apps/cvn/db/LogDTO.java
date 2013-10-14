package es.uji.apps.cvn.db;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the CVN_LOGS database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CVN_LOGS")
public class LogDTO implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob()
    private String error;

    @Temporal(TemporalType.TIME)
    private Date fecha;

    private String mensaje;

    @Column(name = "PER_CVN_ID")
    private Long personaCvnId;

    private Long solicitante;

    public LogDTO()
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

    public String getError()
    {
        return this.error;
    }

    public void setError(String error)
    {
        this.error = error;
    }

    public Date getFecha()
    {
        return this.fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    public String getMensaje()
    {
        return this.mensaje;
    }

    public void setMensaje(String mensaje)
    {
        this.mensaje = mensaje;
    }

    public Long getPersonaCvnId()
    {
        return this.personaCvnId;
    }

    public void setPersonaCvnId(Long personaCvnId)
    {
        this.personaCvnId = personaCvnId;
    }

    public Long getSolicitante()
    {
        return this.solicitante;
    }

    public void setSolicitante(Long solicitante)
    {
        this.solicitante = solicitante;
    }

}