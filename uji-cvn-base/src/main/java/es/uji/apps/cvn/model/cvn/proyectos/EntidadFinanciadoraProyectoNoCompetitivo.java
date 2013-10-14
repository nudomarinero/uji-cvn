package es.uji.apps.cvn.model.cvn.proyectos;

import es.uji.apps.cvn.model.cvn.CVNItem;
import es.uji.apps.cvn.model.cvn.Entidad;
import es.uji.apps.cvn.ui.beans.CvnCodeGroup;

public class EntidadFinanciadoraProyectoNoCompetitivo extends CvnCodeGroup implements Entidad
{
    private CVNItem cvnItem;

    public EntidadFinanciadoraProyectoNoCompetitivo(CVNItem cvnItem)
    {
        super();
        this.code = "050.020.020.120";
        this.cvnItem = cvnItem;
    }

    @Override
    public void addNombre(String nombre)
    {
        cvnItem.addCvnEntityBeanToCvnCodeGroup(
                cvnItem.buildCvnEntityBean("050.020.020.120", nombre), this);
    }

    @Override
    public void addTipo(String tipo)
    {
        cvnItem.addCvnStringToCvnCodeGroup(cvnItem.buildCvnString("050.020.020.140", tipo), this);
    }

    @Override
    public void addTipoOtros(String tipoOtros)
    {
        cvnItem.addCvnStringToCvnCodeGroup(cvnItem.buildCvnString("050.020.020.150", tipoOtros),
                this);
    }

    @Override
    public void addPais(String pais)
    {
        cvnItem.addCvnStringToCvnCodeGroup(cvnItem.buildCvnString("050.020.020.350", pais), this);
    }

    @Override
    public void addRegion(String region)
    {
        cvnItem.addCvnStringToCvnCodeGroup(cvnItem.buildCvnString("050.020.020.360", region), this);
    }

    @Override
    public void addCiudad(String ciudad)
    {
        cvnItem.addCvnStringToCvnCodeGroup(cvnItem.buildCvnString("050.020.020.340", ciudad), this);
    }

}
