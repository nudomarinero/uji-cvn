package es.uji.apps.cvn.model.cvn.publicaciones;

import es.uji.apps.cvn.model.cvn.CVNItem;
import es.uji.apps.cvn.ui.beans.CvnCodeGroup;

public class Impacto extends CvnCodeGroup
{
    private CVNItem cvnItem;

    public Impacto(CVNItem cvnItem)
    {
        super();
        this.code = "060.010.010.180";
        this.cvnItem = cvnItem;
    }

    public void addIndice(String indice)
    {
        cvnItem.addCvnStringToCvnCodeGroup(cvnItem.buildCvnString("060.010.010.180", indice), this);
    }

    public void addFuente(String fuente)
    {
        cvnItem.addCvnStringToCvnCodeGroup(cvnItem.buildCvnString("060.010.010.190", fuente), this);
    }

    public void addFuenteOtros(String fuenteOtros)
    {
        cvnItem.addCvnStringToCvnCodeGroup(cvnItem.buildCvnString("060.010.010.200", fuenteOtros),
                this);
    }

    public void addCategoria(String categoriaId, String categoria)
    {
        cvnItem.addCvnTitleBeanToCvnCodeGroup(
                cvnItem.buildCvnTitleBean("060.010.010.240", categoriaId, categoria), this);
    }

    public void addCategoria(String categoriaId)
    {
        cvnItem.addCvnTitleBeanToCvnCodeGroup(
                cvnItem.buildCvnTitleBean("060.010.010.240", categoriaId, null), this);
    }

    public void addPosicionCategoria(Double posicion)
    {
        cvnItem.addCvnDoubleToCvnCodeGroup(cvnItem.buildCvnDouble("060.010.010.250", posicion),
                this);
    }

    public void addNRevistasCategoria(Double nRevistas)
    {
        cvnItem.addCvnDoubleToCvnCodeGroup(cvnItem.buildCvnDouble("060.010.010.260", nRevistas),
                this);
    }

    public void addDentroPorc25MayorIndice(Boolean isDentroPorc25MayorIndice)
    {
        cvnItem.addCvnBooleanToCvnCodeGroup(
                cvnItem.buildCvnBoolean("060.010.010.330", isDentroPorc25MayorIndice), this);
    }
}
