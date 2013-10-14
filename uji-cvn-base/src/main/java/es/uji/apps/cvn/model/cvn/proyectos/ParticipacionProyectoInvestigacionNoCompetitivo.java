package es.uji.apps.cvn.model.cvn.proyectos;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class ParticipacionProyectoInvestigacionNoCompetitivo extends CVNItem
{
    private ProyectoInvestigacion proyectoInvestigacion;

    private InvestigadorProyecto investigadorProyecto;

    public ParticipacionProyectoInvestigacionNoCompetitivo()
    {
        this.code = "050.020.020.000";
    }

    public ProyectoInvestigacion getProyectoInvestigacion()
    {
        if (proyectoInvestigacion == null)
        {
            proyectoInvestigacion = new ProyectoInvestigacionNoCompetitivo(this);
        }
        return proyectoInvestigacion;
    }

    public void setProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion)
    {
        this.proyectoInvestigacion = proyectoInvestigacion;
    }

    public InvestigadorProyecto getInvestigadorProyecto()
    {
        if (investigadorProyecto == null)
        {
            investigadorProyecto = new InvestigadorProyectoNoCompetitivo(this);
        }
        return investigadorProyecto;
    }

    public void setInvestigadorProyecto(InvestigadorProyecto investigadorProyecto)
    {
        this.investigadorProyecto = investigadorProyecto;
    }
}
