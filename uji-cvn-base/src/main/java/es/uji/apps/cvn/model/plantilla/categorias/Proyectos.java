package es.uji.apps.cvn.model.plantilla.categorias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.uji.apps.cvn.model.ParticipacionProyecto;
import es.uji.apps.cvn.model.plantilla.Plantilla;

@SuppressWarnings("serial")
public class Proyectos extends Categoria implements Serializable
{
    public static List<ParticipacionProyecto> aplicaFiltros(
            List<ParticipacionProyecto> participacionProyectos, Plantilla plantilla)
    {
        List<ParticipacionProyecto> participacionProyectosFiltrado = participacionProyectos;

        if (plantilla.getProyectos() == null)
        {
            participacionProyectosFiltrado = new ArrayList<ParticipacionProyecto>();
        }
        else if (plantilla.getProyectos().getMaxAnyos() != 0)
        {
            participacionProyectosFiltrado = aplicaFiltroAnyos(participacionProyectos, plantilla
                    .getProyectos().getMaxAnyos());
        }
        else if (plantilla.getProyectos().getMaxItems() != 0)
        {
            participacionProyectosFiltrado = aplicaFiltroItems(participacionProyectos, plantilla
                    .getProyectos().getMaxItems());
        }

        return participacionProyectosFiltrado;
    }

    private static List<ParticipacionProyecto> aplicaFiltroAnyos(
            List<ParticipacionProyecto> participacionProyectos, int maxAnyos)
    {
        List<ParticipacionProyecto> participacionProyectosFiltrado = new ArrayList<ParticipacionProyecto>();

        int anyoActual = Calendar.getInstance().get(Calendar.YEAR);

        for (ParticipacionProyecto participacionProyecto : participacionProyectos)
        {
            if (anyoActual
                    - participacionProyecto.getProyectoInvestigacion().getFechaInicio().getYear() < maxAnyos)
            {
                participacionProyectosFiltrado.add(participacionProyecto);
            }
            else
            {
                break;
            }
        }

        return participacionProyectosFiltrado;
    }

    private static List<ParticipacionProyecto> aplicaFiltroItems(
            List<ParticipacionProyecto> participacionProyectos, int maxItems)
    {
        List<ParticipacionProyecto> participacionProyectosFiltrado = new ArrayList<ParticipacionProyecto>();
        int count = 0;

        for (ParticipacionProyecto participacionProyecto : participacionProyectos)
        {
            participacionProyectosFiltrado.add(participacionProyecto);
            count++;

            if (count == maxItems)
            {
                break;
            }
        }

        return participacionProyectosFiltrado;
    }
}
