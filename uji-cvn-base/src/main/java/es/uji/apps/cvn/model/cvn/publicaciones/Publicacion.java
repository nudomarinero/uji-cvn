package es.uji.apps.cvn.model.cvn.publicaciones;

import javax.xml.datatype.XMLGregorianCalendar;

public interface Publicacion
{
    public void addTitulo(String titulo);

    public void addNombre(String nombre);

    public void addAutorPorOrdenFirma(String nombre, String apellido1, String apellido2);

    public void addVolumen(String volumen);

    public void addPaginasInicioFin(String paginaInicio, String paginaFin);

    public void addEditorial(String editorial);

    public void addPais(String pais);

    public void addRegion(String region);

    public void addFecha(XMLGregorianCalendar fecha);

    public void addURL(String url);

    public void addISBN(String isbn);
    
    public void addISSN(String issn);

    public void addDepositoLegal(String depositoLegal);

    public void addTipo(String tipo);
}
