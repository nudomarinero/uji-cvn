package es.uji.apps.cvn.model.cvn.publicaciones;

import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.cvn.CVNItem;
import es.uji.apps.cvn.model.cvn.Entidad;

public class CongresoDocente {
	    private CVNItem cvnItem;

	    private Entidad entidadOrganizadora;

	    private Localizacion localizacion;

	    public CongresoDocente(CVNItem cvnItem)
	    {
	        this.cvnItem = cvnItem;
	    }

	    public Entidad getEntidadOrganizadora()
	    {
	        if (entidadOrganizadora == null)
	        {
	            entidadOrganizadora = new EntidadOrganizadoraCongresoDocente(cvnItem);
	        }
	        return entidadOrganizadora;
	    }

	    public void setEntidadOrganizadora(Entidad entidadOrganizadora)
	    {
	        this.entidadOrganizadora = entidadOrganizadora;
	    }

	    public Localizacion getLocalizacion()
	    {
	        if (localizacion == null)
	        {
	            localizacion = new LocalizacionCongresoDocente(cvnItem);
	        }
	        return localizacion;
	    }

	    public void setLocalizacion(Localizacion localizacion)
	    {
	        this.localizacion = localizacion;
	    }

	    public void addTipoEvento(String tipoEvento)
	    {
	        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.010", tipoEvento));
	    }

	    public void addTipoEventoOtros(String tipoEventoOtros)
	    {
	        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.020", tipoEventoOtros));
	    }

	    public void addTipoParticipacion(String tipoParticion)
	    {
	        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.160", tipoParticion));
	    }

	    public void addFechaFinalizacion(XMLGregorianCalendar fechaFinalizacion)
	    {
	        cvnItem.addCvnDateDayMonthYear(cvnItem.buildCvnDateDayMonthYear("030.090.000.360",
	                fechaFinalizacion));
	    }
	
	    	    
	    public void addObjetivos(String objetivos)
	    {
	        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.120", objetivos));
	    }
	    
	    public void addDestinatarios(String destinatarios)
	    {
	        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.130", destinatarios));
	    }
	    
	    public void addIdioma(String idioma)
	    {
	        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.140", idioma));
	    }
	    
	    public void addFechaPresentacion(XMLGregorianCalendar fechaPresentacion)
	    {
	        cvnItem.addCvnDateDayMonthYear(cvnItem.buildCvnDateDayMonthYear("030.090.000.150",
	        		fechaPresentacion));
	    }
	    
	    public void addHoras(String horas)
	    {
	        cvnItem.addCvnDuration(cvnItem.buildCvnDuration("030.090.000.340", horas));
	    }

}
