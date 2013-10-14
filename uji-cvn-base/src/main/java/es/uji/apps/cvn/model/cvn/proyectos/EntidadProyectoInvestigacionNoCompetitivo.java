package es.uji.apps.cvn.model.cvn.proyectos;

import es.uji.apps.cvn.model.cvn.CVNItem;
import es.uji.apps.cvn.model.cvn.Entidad;

public class EntidadProyectoInvestigacionNoCompetitivo implements Entidad
{
    private CVNItem cvnItem;

    public EntidadProyectoInvestigacionNoCompetitivo(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    @Override
    public void addNombre(String nombre)
    {
        cvnItem.addCvnEntityBean(cvnItem.buildCvnEntityBean("050.020.020.370", nombre));
    }

    @Override
    public void addTipo(String tipo)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.020.330", tipo));
    }

    @Override
    public void addTipoOtros(String tipoOtros)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.020.380", tipoOtros));
    }

    @Override
    public void addPais(String pais)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.020.060", pais));
    }

    @Override
    public void addRegion(String region)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.020.070", region));
    }

    @Override
    public void addCiudad(String ciudad)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.020.090", ciudad));
    }

}
