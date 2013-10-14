package es.uji.apps.cvn.model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import es.uji.apps.cvn.model.comparators.CvnGeneradoComparator;

public class CvnGenerado
{
    private Long id;

    private Long personaId;

    private byte[] cvn;

    private Date fechaUltimaActualizacion;

    private String estado;

    private String template;

    private String idioma;

    private Long plantillaId;

    private String mensaje;

    private Long solicitante;

    private String fichero;

    private String urlDescarga;

    private String usuario;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getPersonaId()
    {
        return personaId;
    }

    public void setPersonaId(Long personaId)
    {
        this.personaId = personaId;
    }

    public byte[] getCvn()
    {
        return cvn;
    }

    public void setCvn(byte[] cvn)
    {
        this.cvn = cvn;
    }

    public Date getFechaUltimaActualizacion()
    {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion)
    {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    public String getMensaje()
    {
        return mensaje;
    }

    public void setMensaje(String mensaje)
    {
        this.mensaje = mensaje;
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

    public void actualizaEstado(String estado, String mensaje)
    {
        this.estado = estado;
        this.mensaje = mensaje;
        this.fechaUltimaActualizacion = new Date();
    }

    public static CvnGenerado iniciaSolicitud(Long personaId, Long solicitante, String estado,
            String mensaje)
    {
        CvnGenerado cvnGenerado = new CvnGenerado();
        cvnGenerado.setPersonaId(personaId);
        cvnGenerado.setSolicitante(solicitante);
        cvnGenerado.setEstado(estado);
        cvnGenerado.setFechaUltimaActualizacion(new Date());
        cvnGenerado.setMensaje(mensaje);

        return cvnGenerado;
    }

    public String getFichero()
    {
        return fichero;
    }

    public void setFichero(String fichero)
    {
        this.fichero = fichero;
    }

    public String getUrlDescarga()
    {
        return urlDescarga;
    }

    public void setUrlDescarga(String urlDescarga)
    {
        this.urlDescarga = urlDescarga;
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public static void ordenaListaCvnsGeneradosPorFechaActualizacion(List<CvnGenerado> cvnsGenerados)
    {
        Collections.sort(cvnsGenerados, new CvnGeneradoComparator());
    }
}
