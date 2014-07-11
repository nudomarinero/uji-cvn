package es.uji.apps.cvn.model.cvn.publicaciones;

import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class PublicacionCongresoDocente implements Publicacion
{
    private CVNItem cvnItem;

    public PublicacionCongresoDocente(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    @Override
    public void addTipo(String tipo)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.220", tipo));
    }

    @Override
    public void addTitulo(String titulo)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.230", titulo));
    }

    @Override
    public void addNombre(String nombre)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.330", nombre));
    }

    @Override
    public void addVolumen(String volumen)
    {
        cvnItem.addCvnVolumeBean(cvnItem.buildCvnVolumeBean("030.090.000.240", null, volumen));
    }

    @Override
    public void addPaginasInicioFin(String paginaInicio, String paginaFin)
    {
        cvnItem.addCvnPageBean(cvnItem.buildCvnPageBean("030.090.000.250", paginaInicio, paginaFin));
    }

    @Override
    public void addEditorial(String editorial)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.260", editorial));
    }

    @Override
    public void addPais(String pais)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.270", pais));
    }

    @Override
    public void addRegion(String region)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.280", region));
    }

    @Override
    public void addFecha(XMLGregorianCalendar fecha)
    {
        cvnItem.addCvnDateYear(cvnItem.buildCvnDateYear("030.090.000.300", fecha));
    }

    @Override
    public void addURL(String url)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.310", url));
    }

    @Override
    public void addISBN(String isbn)
    {
        cvnItem.addCvnExternalPKBean(cvnItem.buildExternalPKBean("030.090.000.180", isbn, "020"));
    }
    
    @Override
    public void addISSN(String issn)
    {
        cvnItem.addCvnExternalPKBean(cvnItem.buildExternalPKBean("030.090.000.180", issn, "010"));
    }

    @Override
    public void addDepositoLegal(String depositoLegal)
    {
        cvnItem.addCvnExternalPKBean(cvnItem.buildExternalPKBean("030.090.000.320", depositoLegal));
    }

    @Override
    public void addAutorPorOrdenFirma(String nombre, String apellido1, String apellido2, Long orden)
    {
    }

}
