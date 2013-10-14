package es.uji.apps.cvn.model.cvn.proyectos;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class InvestigadorProyectoCompetitivo implements InvestigadorProyecto
{
    private CVNItem cvnItem;

    public InvestigadorProyectoCompetitivo(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    @Override
    public void addCalidadParticipacion(String calidadParticipacion)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.170", calidadParticipacion));
    }

    @Override
    public void addCalidadParticipacionOtros(String calidadParticipacionOtros)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.180", calidadParticipacionOtros));
    }

    public void addTipoParticipacion(String tipoParticipacion)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.230", tipoParticipacion));
    }

    public void addTipoParticipacionOtros(String tipoParticipacionOtros)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.240", tipoParticipacionOtros));
    }

    public void addAportacion(String aportacion)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.420", aportacion));
    }

    public void addDedicacion(String dedicacion)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.430", dedicacion));
    }

}
