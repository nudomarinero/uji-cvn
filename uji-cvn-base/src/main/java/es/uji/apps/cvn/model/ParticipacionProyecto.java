package es.uji.apps.cvn.model;

import java.util.Collections;
import java.util.List;

import es.uji.apps.cvn.model.comparators.ParticipacionProyectoComparator;
import es.uji.apps.cvn.translators.CalidadParticipacion;
import es.uji.apps.cvn.translators.DedicacionProyecto;

public class ParticipacionProyecto
{
    private ProyectoInvestigacion proyectoInvestigacion;

    private String aportaciones;

    private String calidadParticipacion;

    private String dedicacion;

    private Boolean responsable;

    private String tipoParticipacion;

    public ParticipacionProyecto()
    {
    }

    public ProyectoInvestigacion getProyectoInvestigacion()
    {
        return proyectoInvestigacion;
    }

    public void setProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion)
    {
        this.proyectoInvestigacion = proyectoInvestigacion;
    }

    public String getAportaciones()
    {
        return aportaciones;
    }

    public void setAportaciones(String aportaciones)
    {
        this.aportaciones = aportaciones;
    }

    public String getCalidadParticipacion()
    {
        return calidadParticipacion;
    }

    public void setCalidadParticipacion(String calidadParticipacion)
    {
        this.calidadParticipacion = CalidadParticipacion.getCodigoTipo(calidadParticipacion);
    }

    public String getDedicacion()
    {
        return dedicacion;
    }

    public void setDedicacion(Float dedicacion)
    {
        if (dedicacion != null && this.proyectoInvestigacion != null
                && this.proyectoInvestigacion.getTipo().equals(2L)
                && this.proyectoInvestigacion.getSubtipo().equals("P")
                && this.proyectoInvestigacion.getMicrotipo().equals("N"))
            this.dedicacion = DedicacionProyecto.getCodigoTipo(dedicacion.longValue());
    }

    public Boolean getResponsable()
    {
        return responsable;
    }

    public void setResponsable(String responsable)
    {
        this.responsable = ((responsable != null) ? responsable.equals("S") : false);
    }

    public String getTipoParticipacion()
    {
        return tipoParticipacion;
    }

    public void setTipoParticipacion(String tipoParticipacion)
    {
        this.tipoParticipacion = tipoParticipacion;
    }

    public static void ordenaListaParticipacionProyectosPorFechaInicio(
            List<ParticipacionProyecto> participacionProyectos)
    {
        Collections.sort(participacionProyectos, new ParticipacionProyectoComparator());
    }
}
