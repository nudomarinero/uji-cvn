package es.uji.apps.cvn.model.cvn.publicaciones;

import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.cvn.CVNItem;
import es.uji.apps.cvn.model.cvn.Entidad;

public class Congreso
{
    private CVNItem cvnItem;

    private Entidad entidadOrganizadora;

    private Localizacion localizacion;

    public Congreso(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    public Entidad getEntidadOrganizadora()
    {
        if (entidadOrganizadora == null)
        {
            entidadOrganizadora = new EntidadOrganizadoraCongreso(cvnItem);
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
            localizacion = new LocalizacionCongreso(cvnItem);
        }
        return localizacion;
    }

    public void setLocalizacion(Localizacion localizacion)
    {
        this.localizacion = localizacion;
    }

    public void addTipoEvento(String tipoEvento)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.010", tipoEvento));
    }

    public void addTipoEventoOtros(String tipoEventoOtros)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.020", tipoEventoOtros));
    }

    public void addTitulo(String titulo)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.030", titulo));
    }

    public void addTipoParticipacion(String tipoParticion)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.050", tipoParticion));
    }

    public void addIntervencion(String intervencion)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.060", intervencion));
    }

    public void addIntervencionOtros(String intervencionOtros)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.070", intervencionOtros));
    }

    public void addAmbito(String ambito)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.080", ambito));
    }

    public void addAmbitoOtros(String ambitoOtros)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.090", ambitoOtros));
    }

    public void addFechaFinalizacion(XMLGregorianCalendar fechaFinalizacion)
    {
        cvnItem.addCvnDateDayMonthYear(cvnItem.buildCvnDateDayMonthYear("060.010.020.380",
                fechaFinalizacion));
    }
    
    public void addPublicacionActa(Boolean isPublicadoActas)
    {
        cvnItem.addCvnBoolean(cvnItem.buildCvnBoolean("060.010.020.200", isPublicadoActas));
    }

    public void addComiteExterno(Boolean hasComiteExterno)
    {
        cvnItem.addCvnBoolean(cvnItem.buildCvnBoolean("060.010.020.210", hasComiteExterno));
    }
}
