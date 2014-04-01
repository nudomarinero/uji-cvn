package es.uji.apps.cvn.model.cvn.laboral;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class SituacionProfesionalActual extends CVNItem {

    private CvnDetalleSituacionProfesionalActual spa;

    public SituacionProfesionalActual()
    {
        this.code = "010.010.000.000";
    }


    public CvnDetalleSituacionProfesionalActual getCvnDetalleSituacionProfesionalActual()
    {
        if (spa == null)
        {
            spa = new CvnDetalleSituacionProfesionalActual(this);
        }
        return spa;
    }

    public void setSituacionProfesionalActual(CvnDetalleSituacionProfesionalActual spa) {
        this.spa=spa;
    }
}
