package es.uji.apps.cvn.model.cvn.docente;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class ParticipacionEnTesis extends CVNItem {

    private CvnTesis tesis;

    public ParticipacionEnTesis()
    {
        this.code = "030.040.000.000";
    }


    public CvnTesis getCvnTesis()
    {
        if (tesis == null)
        {
            tesis = new CvnTesis(this);
        }
        return tesis;
    }

    public void setTesis(CvnTesis tesis) {
        this.tesis=tesis;
    }
}
