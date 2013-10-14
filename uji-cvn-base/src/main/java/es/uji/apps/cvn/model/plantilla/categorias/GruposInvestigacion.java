package es.uji.apps.cvn.model.plantilla.categorias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.uji.apps.cvn.model.ParticipacionGrupo;
import es.uji.apps.cvn.model.plantilla.Plantilla;

@SuppressWarnings("serial")
public class GruposInvestigacion extends Categoria implements Serializable
{
    public static List<ParticipacionGrupo> aplicaFiltros(
            List<ParticipacionGrupo> participacionGrupos, Plantilla plantilla)
    {
        List<ParticipacionGrupo> participacionGruposFiltrado = participacionGrupos;

        if (plantilla.getGruposInvestigacion() == null)
        {
            participacionGruposFiltrado = new ArrayList<ParticipacionGrupo>();
        }
        else if (plantilla.getGruposInvestigacion().getMaxAnyos() != 0)
        {
            participacionGruposFiltrado = aplicaFiltroAnyos(participacionGrupos, plantilla
                    .getGruposInvestigacion().getMaxAnyos());
        }
        else if (plantilla.getGruposInvestigacion().getMaxItems() != 0)
        {
            participacionGruposFiltrado = aplicaFiltroItems(participacionGrupos, plantilla
                    .getGruposInvestigacion().getMaxItems());
        }

        return participacionGruposFiltrado;
    }

    private static List<ParticipacionGrupo> aplicaFiltroAnyos(
            List<ParticipacionGrupo> participacionGrupos, int maxAnyos)
    {
        List<ParticipacionGrupo> participacionGruposFiltrado = new ArrayList<ParticipacionGrupo>();

        int anyoActual = Calendar.getInstance().get(Calendar.YEAR);

        for (ParticipacionGrupo participacionGrupo : participacionGrupos)
        {
            if (anyoActual - participacionGrupo.getFechaInicio().getYear() < maxAnyos)
            {
                participacionGruposFiltrado.add(participacionGrupo);
            }
            else
            {
                break;
            }
        }

        return participacionGruposFiltrado;
    }

    private static List<ParticipacionGrupo> aplicaFiltroItems(
            List<ParticipacionGrupo> participacionGrupos, int maxItems)
    {
        List<ParticipacionGrupo> participacionGruposFiltrado = new ArrayList<ParticipacionGrupo>();
        int count = 0;

        for (ParticipacionGrupo participacionGrupo : participacionGrupos)
        {
            participacionGruposFiltrado.add(participacionGrupo);
            count++;

            if (count == maxItems)
            {
                break;
            }
        }

        return participacionGruposFiltrado;
    }
}
