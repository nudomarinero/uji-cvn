package es.uji.apps.cvn.model.cvn.publicaciones;

import es.uji.apps.cvn.model.cvn.CVNItem;
import es.uji.apps.cvn.model.cvn.Entidad;

public class EntidadOrganizadoraCongresoDocente implements Entidad
{
    private CVNItem cvnItem;

    public EntidadOrganizadoraCongresoDocente(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    @Override
    public void addNombre(String nombre)
    {
        cvnItem.addCvnEntityBean(cvnItem.buildCvnEntityBean("030.090.000.080", nombre));
    }

    @Override
    public void addTipo(String tipo)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.100", tipo));
    }

    @Override
    public void addTipoOtros(String tipoOtros)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.110", tipoOtros));
    }

    @Override
    public void addPais(String pais)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.190", pais));
    }

    @Override
    public void addRegion(String region)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.200", region));
    }

    @Override
    public void addCiudad(String ciudad)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.090.000.210", ciudad));
    }

}
