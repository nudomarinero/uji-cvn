package es.uji.apps.cvn.model.cvn.publicaciones;

import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class LocalizacionCongreso implements Localizacion
{
    private CVNItem cvnItem;

    public LocalizacionCongreso(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    public void addNombreCongreso(String nombreCongreso)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.100", nombreCongreso));
    }

    @Override
    public void addPais(String pais)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.150", pais));
    }

    @Override
    public void addRegion(String region)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.160", region));
    }

    @Override
    public void addCiudad(String ciudad)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.180", ciudad));
    }

    @Override
    public void addFecha(XMLGregorianCalendar fecha)
    {
        cvnItem.addCvnDateDayMonthYear(cvnItem.buildCvnDateDayMonthYear("060.010.020.190", fecha));
    }

    public void addAno(XMLGregorianCalendar ano)
    {
        cvnItem.addCvnDateYear(cvnItem.buildCvnDateYear("060.010.020.190", ano));
    }
}
