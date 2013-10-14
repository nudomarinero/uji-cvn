package es.uji.apps.cvn.model.cvn.proyectos;

import es.uji.apps.cvn.model.cvn.CVNItem;
import es.uji.apps.cvn.model.cvn.Entidad;

public class EntidadProyectoInvestigacionCompetitivo implements Entidad
{
    private CVNItem cvnItem;
    
    public EntidadProyectoInvestigacionCompetitivo(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }
    
    @Override
    public void addNombre(String nombre)
    {
        cvnItem.addCvnEntityBean(cvnItem.buildCvnEntityBean("050.020.010.100", nombre));
    }

    @Override
    public void addTipo(String tipo)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.120", tipo));
    }

    @Override
    public void addTipoOtros(String tipoOtros)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.130", tipoOtros));
    }

    @Override
    public void addPais(String pais)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.060", pais));
    }

    @Override
    public void addRegion(String region)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.070", region));
    }

    @Override
    public void addCiudad(String ciudad)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.090", ciudad));
    }

}
