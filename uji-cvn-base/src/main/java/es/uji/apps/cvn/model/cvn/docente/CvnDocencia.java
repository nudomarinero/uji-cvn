package es.uji.apps.cvn.model.cvn.docente;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class CvnDocencia extends CVNItem {

    private DocenciaImpartida docenciaImpartida;

    public CvnDocencia()
    {
        this.code = "030.010.000.000";
    }


    public DocenciaImpartida getDocenciaImpartida()
    {
        if (docenciaImpartida == null)
        {
            docenciaImpartida = new DocenciaImpartida(this);
        }
        return docenciaImpartida;
    }

    public void setDocenciaImpartida(DocenciaImpartida docenciaImpartida) {
        this.docenciaImpartida=docenciaImpartida;
    }
}
