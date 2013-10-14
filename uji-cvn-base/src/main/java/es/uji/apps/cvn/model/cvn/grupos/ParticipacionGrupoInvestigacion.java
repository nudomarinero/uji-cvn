package es.uji.apps.cvn.model.cvn.grupos;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class ParticipacionGrupoInvestigacion extends CVNItem
{
    private GrupoInvestigacion grupoInvestigacion;

    private InvestigadorGrupo investigadorGrupo;

    public ParticipacionGrupoInvestigacion()
    {
        this.code = "050.010.000.000";
    }

    public GrupoInvestigacion getGrupoInvestigacion()
    {
        if (grupoInvestigacion == null)
        {
            grupoInvestigacion = new GrupoInvestigacion(this);
        }
        return grupoInvestigacion;
    }

    public void setGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion)
    {
        this.grupoInvestigacion = grupoInvestigacion;
    }

    public InvestigadorGrupo getInvestigadorGrupo()
    {
        if (investigadorGrupo == null)
        {
            investigadorGrupo = new InvestigadorGrupo(this);
        }
        return investigadorGrupo;
    }

    public void setInvestigadorGrupo(InvestigadorGrupo investigadorGrupo)
    {
        this.investigadorGrupo = investigadorGrupo;
    }
}
