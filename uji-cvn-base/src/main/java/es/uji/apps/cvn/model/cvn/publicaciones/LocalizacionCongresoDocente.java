package es.uji.apps.cvn.model.cvn.publicaciones;

import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class LocalizacionCongresoDocente implements Localizacion
{
    private CVNItem cvnItem;

    public LocalizacionCongresoDocente(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    public void addNombreCongreso(String nombreCongreso)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.030", nombreCongreso));
    }

    @Override
    public void addPais(String pais)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.040", pais));
    }

    @Override
    public void addRegion(String region)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.050", region));
    }

    @Override
    public void addCiudad(String ciudad)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.070", ciudad));
    }

    @Override
    public void addFecha(XMLGregorianCalendar fecha)
    {
        cvnItem.addCvnDateDayMonthYear(cvnItem.buildCvnDateDayMonthYear("030.090.000.350", fecha));
    }

    public void addAno(XMLGregorianCalendar ano)
    {
        cvnItem.addCvnDateYear(cvnItem.buildCvnDateYear("030.090.000.350", ano));
    }
}
