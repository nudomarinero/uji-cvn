package es.uji.apps.cvn.model;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


public class PublicacionDocente {
	
    private Long id;

    private Publicacion publicacion;
    
    private String denominacion;
    
    private String destinatarios;

    private XMLGregorianCalendar fechaCreacion;
    
    private String justificacion;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(String destinatarios) {
		this.destinatarios = destinatarios;
	}

	public XMLGregorianCalendar getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		  if (fechaCreacion != null)
	        {
	            GregorianCalendar calendar = new GregorianCalendar();
	            calendar.setTime(fechaCreacion);
	            try
	            {
	                this.fechaCreacion = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
	            }
	            catch (DatatypeConfigurationException e)
	            {
	            }
	        }
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

}
