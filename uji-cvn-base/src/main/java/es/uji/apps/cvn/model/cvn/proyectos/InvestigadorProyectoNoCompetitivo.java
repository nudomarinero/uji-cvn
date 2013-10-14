package es.uji.apps.cvn.model.cvn.proyectos;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class InvestigadorProyectoNoCompetitivo implements InvestigadorProyecto
{
    private CVNItem cvnItem;
    
    public InvestigadorProyectoNoCompetitivo(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }
    
    @Override
    public void addCalidadParticipacion(String calidadParticipacion)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.020.280", calidadParticipacion));
    }

    @Override
    public void addCalidadParticipacionOtros(String calidadParticipacionOtros)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.020.290", calidadParticipacionOtros));
    }

}
