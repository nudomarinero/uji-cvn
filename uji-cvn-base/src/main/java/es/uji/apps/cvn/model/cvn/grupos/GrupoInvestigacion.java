package es.uji.apps.cvn.model.cvn.grupos;

import es.uji.apps.cvn.model.cvn.CVNItem;
import es.uji.apps.cvn.model.cvn.Entidad;

public class GrupoInvestigacion
{
    private CVNItem cvnItem;

    private Entidad entidad;

    public GrupoInvestigacion(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    public Entidad getEntidad()
    {
        if (entidad == null)
        {
            entidad = new EntidadGrupoInvestigacion(cvnItem);
        }
        return entidad;
    }

    public void setEntidad(Entidad entidad)
    {
        this.entidad = entidad;
    }

    public void addObjetivo(String objetivo)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.010.000.010", objetivo));
    }

    public void addNombre(String nombre)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.010.000.020", nombre));
    }

    public void addCodigoNormalizado(String codigoNormalizado)
    {
        cvnItem.addCvnExternalPKBean(cvnItem.buildExternalPKBean("050.010.000.030",
                codigoNormalizado));
    }

    public void addResponsable(String nombre, String apellido1, String apellido2)
    {
        cvnItem.addCvnAuthorBean(cvnItem.buildCvnAuthorBean("050.010.000.080", nombre, apellido1,
                apellido2));
    }

    public void addNumeroComponentes(Double nComponentes)
    {
        cvnItem.addCvnDouble(cvnItem.buildCvnDouble("050.010.000.130", nComponentes));
    }

    public void addNumeroTesisDirigidas(Double nTesis)
    {
        cvnItem.addCvnDouble(cvnItem.buildCvnDouble("050.010.000.170", nTesis));
    }

    public void addNumeroPostDocDirigidas(Double nPostDoc)
    {
        cvnItem.addCvnDouble(cvnItem.buildCvnDouble("050.010.000.180", nPostDoc));
    }

}
