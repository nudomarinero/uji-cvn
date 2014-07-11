package es.uji.apps.cvn.model.cvn.publicaciones;

import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class PublicacionDocente implements Publicacion
{
    private CVNItem cvnItem;

    public PublicacionDocente(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    @Override
    public void addTitulo(String titulo)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.070.000.080", titulo));
    }

    @Override
    public void addNombre(String nombre)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.070.000.190", nombre));
    }

    @Override
    public void addVolumen(String volumen)
    {
        cvnItem.addCvnVolumeBean(cvnItem.buildCvnVolumeBean("030.070.000.090", null, volumen));
    }

    @Override
    public void addPaginasInicioFin(String paginaInicio, String paginaFin)
    {
        cvnItem.addCvnPageBean(cvnItem.buildCvnPageBean("030.070.000.100", paginaInicio, paginaFin));
    }

    @Override
    public void addEditorial(String editorial)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.070.000.110", editorial));
    }

    @Override
    public void addPais(String pais)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.070.000.120", pais));
    }

    @Override
    public void addRegion(String region)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.070.000.130", region));
    }

    @Override
    public void addFecha(XMLGregorianCalendar fecha)
    {
        cvnItem.addCvnDateYear(cvnItem.buildCvnDateYear("030.070.000.150", fecha));
    }

    public void addURL(String url)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.070.000.160", url));
    }

    @Override
    public void addISBN(String isbn)
    {
        cvnItem.addCvnExternalPKBean(cvnItem.buildExternalPKBean("030.070.000.170", isbn, "020"));
    }

    @Override
    public void addISSN(String issn)
    {
        cvnItem.addCvnExternalPKBean(cvnItem.buildExternalPKBean("030.070.000.170", issn, "010"));
    }

    @Override
    public void addDepositoLegal(String depositoLegal)
    {
        cvnItem.addCvnExternalPKBean(cvnItem.buildExternalPKBean("030.070.000.180", depositoLegal));
    }

    public void addCiudad(String ciudad)
    {
    }

    @Override
    public void addAutorPorOrdenFirma(String nombre, String apellido1, String apellido2, Long orden)
    {
        cvnItem.addCvnAuthorBean(cvnItem.buildCvnAuthorBean("030.070.000.030", nombre, apellido1,
                apellido2, orden));
    }

    @Override
    public void addTipo(String tipoProduccion)
    {
    }

    public void addTipoOtros(String tipoProduccionOtros)
    {
    }

    public void addSoporte(String soporte)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.070.000.060", soporte));
    }

    public void addColeccion(String coleccion)
    {
    }
}
