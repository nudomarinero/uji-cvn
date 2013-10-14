package es.uji.apps.cvn.model.cvn.publicaciones;


import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class ParticipacionEnPublicacionDocente extends CVNItem
{
    private Publicacion publicacion;

    
    public ParticipacionEnPublicacionDocente()
    {
        this.code = "030.070.000.000";
    }

    public Publicacion getPublicacion()
    {
        if (publicacion == null)
        {
            publicacion = new PublicacionDocente(this);
        }
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion)
    {
        this.publicacion = publicacion;
    }


    public void addPosicionAutor(Double posicion)
    {
        this.addCvnDouble(this.buildCvnDouble("030.070.000.040", posicion));
    }

    public void addCalidad(String calidad)
    {
        this.addCvnString(this.buildCvnString("030.070.000.210", calidad));
    }
    
    public void addDenominacion(String denominacion)
    {
        this.addCvnString(this.buildCvnString("030.070.000.010", denominacion));
    }

    public void addDestinatarios(String destinatarios)
    {
    	this.addCvnString(this.buildCvnString("030.070.000.020", destinatarios));
    }
    
    public void addFechaCreacion(XMLGregorianCalendar fechaCreacion)
    {
        this.addCvnDateDayMonthYear(this.buildCvnDateDayMonthYear("030.070.000.050",
        		fechaCreacion));
    }
    
    public void addJustificacion(String justif)
    {
        this.addCvnString(this.buildCvnString("030.070.000.200", justif));
    }

}
