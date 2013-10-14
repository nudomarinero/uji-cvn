package es.uji.apps.cvn.model.cvn.publicaciones;

import javax.xml.datatype.XMLGregorianCalendar;

public interface Localizacion
{
    public void addPais(String pais);

    public void addRegion(String region);

    public void addCiudad(String ciudad);

    public void addFecha(XMLGregorianCalendar fecha);
}
