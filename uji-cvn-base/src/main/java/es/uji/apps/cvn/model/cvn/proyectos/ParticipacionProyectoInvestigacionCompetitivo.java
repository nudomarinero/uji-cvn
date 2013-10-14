package es.uji.apps.cvn.model.cvn.proyectos;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class ParticipacionProyectoInvestigacionCompetitivo extends CVNItem
{
    private ProyectoInvestigacion proyectoInvestigacion;

    private InvestigadorProyecto investigadorProyecto;

    public ParticipacionProyectoInvestigacionCompetitivo()
    {
        this.code = "050.020.010.000";
    }

    public ProyectoInvestigacion getProyectoInvestigacion()
    {
        if (proyectoInvestigacion == null)
        {
            proyectoInvestigacion = new ProyectoInvestigacionCompetitivo(this);
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
            investigadorProyecto = new InvestigadorProyectoCompetitivo(this);
        }
        return investigadorProyecto;
    }

    public void setInvestigadorProyecto(InvestigadorProyecto investigadorProyecto)
    {
        this.investigadorProyecto = investigadorProyecto;
    }

}
