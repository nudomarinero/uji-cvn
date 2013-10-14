package es.uji.apps.cvn.model.cvn;

public class EntidadTesisProyectos implements Entidad
{
    private CVNItem cvnItem;

    public EntidadTesisProyectos(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    @Override
    public void addNombre(String nombre)
    {
        cvnItem.addCvnEntityBean(cvnItem.buildCvnEntityBean("030.040.000.080", nombre));
    }

    @Override
    public void addTipo(String tipo)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.040.000.100", tipo));
    }

    @Override
    public void addTipoOtros(String tipoOtros)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.040.000.110", tipoOtros));
    }

    @Override
    public void addPais(String pais)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.040.000.040", pais));
    }

    @Override
    public void addRegion(String region)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.040.000.050", region));
    }

    @Override
    public void addCiudad(String ciudad)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.040.000.070", ciudad));
    }

}
