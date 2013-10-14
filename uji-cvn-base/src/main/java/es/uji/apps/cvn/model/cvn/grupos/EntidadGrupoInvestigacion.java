package es.uji.apps.cvn.model.cvn.grupos;

import es.uji.apps.cvn.model.cvn.CVNItem;
import es.uji.apps.cvn.model.cvn.Entidad;

public class EntidadGrupoInvestigacion implements Entidad
{
    private CVNItem cvnItem;

    public EntidadGrupoInvestigacion(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    @Override
    public void addNombre(String nombre)
    {
        cvnItem.addCvnEntityBean(cvnItem.buildCvnEntityBean("050.010.000.090", nombre));
    }

    @Override
    public void addTipo(String tipo)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.010.000.110", tipo));
    }

    @Override
    public void addTipoOtros(String tipoOtros)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.010.000.120", tipoOtros));
    }

    @Override
    public void addPais(String pais)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.010.000.040", pais));
    }

    @Override
    public void addRegion(String region)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.010.000.050", region));
    }

    @Override
    public void addCiudad(String ciudad)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.010.000.070", ciudad));
    }

}
