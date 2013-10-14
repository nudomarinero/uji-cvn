package es.uji.apps.cvn.model.plantilla.categorias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.uji.apps.cvn.model.ParticipacionCongreso;
import es.uji.apps.cvn.model.plantilla.Plantilla;

@SuppressWarnings("serial")
public class Congresos extends Categoria implements Serializable
{
    public static List<ParticipacionCongreso> aplicaFiltros(
            List<ParticipacionCongreso> participacionCongresos, Plantilla plantilla)
    {
        List<ParticipacionCongreso> participacionCongresosFiltrado = participacionCongresos;

        if (plantilla.getCongresos() == null)
        {
            participacionCongresosFiltrado = new ArrayList<ParticipacionCongreso>();
        }
        else if (plantilla.getCongresos().getMaxAnyos() != 0)
        {
            participacionCongresosFiltrado = aplicaFiltroAnyos(participacionCongresos, plantilla
                    .getCongresos().getMaxAnyos());
        }
        else if (plantilla.getCongresos().getMaxItems() != 0)
        {
            participacionCongresosFiltrado = aplicaFiltroItems(participacionCongresos, plantilla
                    .getCongresos().getMaxItems());
        }

        return participacionCongresosFiltrado;
    }

    private static List<ParticipacionCongreso> aplicaFiltroAnyos(
            List<ParticipacionCongreso> participacionCongresos, int maxAnyos)
    {
        List<ParticipacionCongreso> participacionCongresosFiltrado = new ArrayList<ParticipacionCongreso>();

        int anyoActual = Calendar.getInstance().get(Calendar.YEAR);

        for (ParticipacionCongreso participacionCongreso : participacionCongresos)
        {
            if (anyoActual - participacionCongreso.getCongreso().getAno().getYear() < maxAnyos)
            {
                participacionCongresosFiltrado.add(participacionCongreso);
            }
            else
            {
                break;
            }
        }

        return participacionCongresosFiltrado;
    }

    private static List<ParticipacionCongreso> aplicaFiltroItems(
            List<ParticipacionCongreso> participacionCongresos, int maxItems)
    {
        List<ParticipacionCongreso> participacionCongresosFiltrado = new ArrayList<ParticipacionCongreso>();
        int count = 0;

        for (ParticipacionCongreso participacionCongreso : participacionCongresos)
        {
            participacionCongresosFiltrado.add(participacionCongreso);
            count++;

            if (count == maxItems)
            {
                break;
            }
        }

        return participacionCongresosFiltrado;
    }
}
