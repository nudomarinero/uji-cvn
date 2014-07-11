package es.uji.apps.cvn.model.cvn.publicaciones;

import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class PublicacionCongreso implements Publicacion
{
    private CVNItem cvnItem;

    public PublicacionCongreso(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    @Override
    public void addTipo(String tipo)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.220", tipo));
    }

    @Override
    public void addTitulo(String titulo)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.230", titulo));
    }

    @Override
    public void addNombre(String nombre)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.370", nombre));
    }

    @Override
    public void addVolumen(String volumen)
    {
        cvnItem.addCvnVolumeBean(cvnItem.buildCvnVolumeBean("060.010.020.240", null, volumen));
    }

    @Override
    public void addPaginasInicioFin(String paginaInicio, String paginaFin)
    {
        cvnItem.addCvnPageBean(cvnItem.buildCvnPageBean("060.010.020.250", paginaInicio, paginaFin));
    }

    @Override
    public void addEditorial(String editorial)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.260", editorial));
    }

    @Override
    public void addPais(String pais)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.270", pais));
    }

    @Override
    public void addRegion(String region)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.280", region));
    }

    @Override
    public void addFecha(XMLGregorianCalendar fecha)
    {
        cvnItem.addCvnDateYear(cvnItem.buildCvnDateYear("060.010.020.300", fecha));
    }

    @Override
    public void addURL(String url)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("060.010.020.310", url));
    }

    @Override
    public void addISBN(String isbn)
    {
        cvnItem.addCvnExternalPKBean(cvnItem.buildExternalPKBean("060.010.020.320", isbn, "020"));
    }
    
    @Override
    public void addISSN(String issn)
    {
        cvnItem.addCvnExternalPKBean(cvnItem.buildExternalPKBean("060.010.020.320", issn, "010"));
    }

    @Override
    public void addDepositoLegal(String depositoLegal)
    {
        cvnItem.addCvnExternalPKBean(cvnItem.buildExternalPKBean("060.010.020.330", depositoLegal));
    }

    @Override
    public void addAutorPorOrdenFirma(String nombre, String apellido1, String apellido2, Long orden)
    {
        cvnItem.addCvnAuthorBean(cvnItem.buildCvnAuthorBean("060.010.020.040", nombre, apellido1,
                apellido2,orden));
    }

}
