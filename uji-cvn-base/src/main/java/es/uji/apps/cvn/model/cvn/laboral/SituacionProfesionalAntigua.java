package es.uji.apps.cvn.model.cvn.laboral;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class SituacionProfesionalAntigua extends CVNItem {

    private CvnDetalleSituacionProfesionalAntigua spa;

    public SituacionProfesionalAntigua()
    {
        this.code = "010.020.000.000";
    }


    public CvnDetalleSituacionProfesionalAntigua getCvnDetalleSituacionProfesionalAntigua()
    {
        if (spa == null)
        {
            spa = new CvnDetalleSituacionProfesionalAntigua(this);
        }
        return spa;
    }

    public void setSituacionProfesionalAntigua(CvnDetalleSituacionProfesionalAntigua spa) {
        this.spa=spa;
    }
}
