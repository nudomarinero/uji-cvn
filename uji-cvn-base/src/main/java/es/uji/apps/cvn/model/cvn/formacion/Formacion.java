package es.uji.apps.cvn.model.cvn.formacion;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class Formacion extends CVNItem
{
    private CvnDoctorado cvnDoctorado;

    public Formacion()
    {
        this.code = "020.010.020.000";
    }

    public CvnDoctorado getCvnDoctorado()
    {
        if (cvnDoctorado == null)
        {
            cvnDoctorado = new CvnDoctorado(this);
        }
        return cvnDoctorado;
    }

    public void setCvnDoctorado(CvnDoctorado cvnDoctorado)
    {
        this.cvnDoctorado = cvnDoctorado;
    }
}
