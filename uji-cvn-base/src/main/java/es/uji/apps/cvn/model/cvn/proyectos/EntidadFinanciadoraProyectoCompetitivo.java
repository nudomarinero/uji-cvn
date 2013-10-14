package es.uji.apps.cvn.model.cvn.proyectos;

import es.uji.apps.cvn.model.cvn.CVNItem;
import es.uji.apps.cvn.model.cvn.Entidad;
import es.uji.apps.cvn.ui.beans.CvnCodeGroup;

public class EntidadFinanciadoraProyectoCompetitivo extends CvnCodeGroup implements Entidad
{
    private CVNItem cvnItem;

    public EntidadFinanciadoraProyectoCompetitivo(CVNItem cvnItem)
    {
        super();
        this.code = "050.020.010.190";
        this.cvnItem = cvnItem;
    }

    @Override
    public void addNombre(String nombre)
    {
        cvnItem.addCvnEntityBeanToCvnCodeGroup(
                cvnItem.buildCvnEntityBean("050.020.010.190", nombre), this);
    }

    @Override
    public void addTipo(String tipo)
    {
        cvnItem.addCvnStringToCvnCodeGroup(cvnItem.buildCvnString("050.020.010.210", tipo), this);
    }

    @Override
    public void addTipoOtros(String tipoOtros)
    {
        cvnItem.addCvnStringToCvnCodeGroup(cvnItem.buildCvnString("050.020.010.220", tipoOtros),
                this);
    }

    @Override
    public void addPais(String pais)
    {
        cvnItem.addCvnStringToCvnCodeGroup(cvnItem.buildCvnString("050.020.010.360", pais), this);
    }

    @Override
    public void addRegion(String region)
    {
        cvnItem.addCvnStringToCvnCodeGroup(cvnItem.buildCvnString("050.020.010.370", region), this);
    }

    @Override
    public void addCiudad(String ciudad)
    {
        cvnItem.addCvnStringToCvnCodeGroup(cvnItem.buildCvnString("050.020.010.390", ciudad), this);
    }

}
