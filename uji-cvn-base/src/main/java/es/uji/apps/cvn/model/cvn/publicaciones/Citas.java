package es.uji.apps.cvn.model.cvn.publicaciones;

import es.uji.apps.cvn.model.cvn.CVNItem;
import es.uji.apps.cvn.ui.beans.CvnCodeGroup;

public class Citas extends CvnCodeGroup
{
    private CVNItem cvnItem;

    public Citas(CVNItem cvnItem)
    {
        super();
        this.code = "060.010.010.310";
        this.cvnItem = cvnItem;
    }

    public void addNumero(Double numero)
    {
        cvnItem.addCvnDoubleToCvnCodeGroup(cvnItem.buildCvnDouble("060.010.010.310", numero), this);
    }

    public void addFuente(String fuente)
    {
        cvnItem.addCvnStringToCvnCodeGroup(cvnItem.buildCvnString("060.010.010.320", fuente), this);
    }

    public void addFuenteOtros(String fuenteOtros)
    {
        cvnItem.addCvnStringToCvnCodeGroup(cvnItem.buildCvnString("060.010.010.323", fuenteOtros),
                this);
    }
}
