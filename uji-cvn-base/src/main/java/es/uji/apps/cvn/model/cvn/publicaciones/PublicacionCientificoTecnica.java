package es.uji.apps.cvn.model.cvn.publicaciones;

import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class PublicacionCientificoTecnica implements Publicacion
{
    private CVNItem cvnItem;

    public PublicacionCientificoTecnica(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    @Override
    public void addTitulo(String titulo)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.010.030", titulo));
    }

    @Override
    public void addNombre(String nombre)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.010.210", nombre));
    }

    @Override
    public void addVolumen(String volumen)
    {
        cvnItem.addCvnVolumeBean(cvnItem.buildCvnVolumeBean("060.010.010.080", null, volumen));
    }

    @Override
    public void addPaginasInicioFin(String paginaInicio, String paginaFin)
    {
        cvnItem.addCvnPageBean(cvnItem.buildCvnPageBean("060.010.010.090", paginaInicio, paginaFin));
    }

    @Override
    public void addEditorial(String editorial)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.010.100", editorial));
    }

    @Override
    public void addPais(String pais)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.010.110", pais));
    }

    @Override
    public void addRegion(String region)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.010.120", region));
    }

    @Override
    public void addFecha(XMLGregorianCalendar fecha)
    {
        cvnItem.addCvnDateYear(cvnItem.buildCvnDateYear("060.010.010.140", fecha));
    }

    public void addURL(String url)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.010.150", url));
    }

    @Override
    public void addISBN(String isbn)
    {
        cvnItem.addCvnExternalPKBean(cvnItem.buildExternalPKBean("060.010.010.160", isbn, "020"));
    }

    @Override
    public void addISSN(String issn)
    {
        cvnItem.addCvnExternalPKBean(cvnItem.buildExternalPKBean("060.010.010.160", issn, "010"));
    }

    @Override
    public void addDepositoLegal(String depositoLegal)
    {
        cvnItem.addCvnExternalPKBean(cvnItem.buildExternalPKBean("060.010.010.170", depositoLegal));
    }

    public void addCiudad(String ciudad)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.010.220", ciudad));
    }

    @Override
    public void addAutorPorOrdenFirma(String nombre, String apellido1, String apellido2, Long orden)
    {
        cvnItem.addCvnAuthorBean(cvnItem.buildCvnAuthorBean("060.010.010.040", nombre, apellido1,
                apellido2, orden));

    }


    @Override
    public void addTipo(String tipoProduccion)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.010.010", tipoProduccion));
    }

    public void addTipoOtros(String tipoProduccionOtros)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.010.020", tipoProduccionOtros));
    }

    public void addSoporte(String soporte)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.010.070", soporte));
    }

    public void addColeccion(String coleccion)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.010.270", coleccion));
    }
}
