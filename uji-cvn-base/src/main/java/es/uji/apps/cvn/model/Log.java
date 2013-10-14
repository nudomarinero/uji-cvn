package es.uji.apps.cvn.model;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

public class Log
{
    private Long id;

    private Long personaCvnId;

    private Long solicitante;

    private Date fecha;

    private String mensaje;

    private String error;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getPersonaCvnId()
    {
        return personaCvnId;
    }

    public void setPersonaCvnId(Long personaCvnId)
    {
        this.personaCvnId = personaCvnId;
    }

    public Long getSolicitante()
    {
        return solicitante;
    }

    public void setSolicitante(Long solicitante)
    {
        this.solicitante = solicitante;
    }

    public Date getFecha()
    {
        return fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    public String getMensaje()
    {
        return mensaje;
    }

    public void setMensaje(String mensaje)
    {
        this.mensaje = mensaje;
    }

    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }

    public static Log logError(Long personaCvnId, Long solicitante, String mensaje, String error)
    {
        Log log = new Log();
        log.setPersonaCvnId(personaCvnId);
        log.setSolicitante(solicitante);
        log.setFecha(new Date());
        log.setMensaje(mensaje);
        log.setError(error);

        return log;
    }

}
