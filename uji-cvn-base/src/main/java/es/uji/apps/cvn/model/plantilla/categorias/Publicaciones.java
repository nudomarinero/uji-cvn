package es.uji.apps.cvn.model.plantilla.categorias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.uji.apps.cvn.model.ParticipacionPublicacionCientificoTecnica;
import es.uji.apps.cvn.model.plantilla.Plantilla;

@SuppressWarnings("serial")
public class Publicaciones extends Categoria implements Serializable
{
    public static List<ParticipacionPublicacionCientificoTecnica> aplicaFiltros(
            List<ParticipacionPublicacionCientificoTecnica> participacionPublicaciones,
            Plantilla plantilla)
    {
        List<ParticipacionPublicacionCientificoTecnica> participacionPublicacionesFiltrado = participacionPublicaciones;

        if (plantilla.getPublicaciones() == null)
        {
            participacionPublicacionesFiltrado = new ArrayList<ParticipacionPublicacionCientificoTecnica>();
        }
        else if (plantilla.getPublicaciones().getMaxAnyos() != 0)
        {
            participacionPublicacionesFiltrado = aplicaFiltroAnyos(participacionPublicaciones,
                    plantilla.getPublicaciones().getMaxAnyos());
        }
        else if (plantilla.getPublicaciones().getMaxItems() != 0)
        {
            participacionPublicacionesFiltrado = aplicaFiltroItems(participacionPublicaciones,
                    plantilla.getPublicaciones().getMaxItems());
        }

        return participacionPublicacionesFiltrado;
    }

    private static List<ParticipacionPublicacionCientificoTecnica> aplicaFiltroAnyos(
            List<ParticipacionPublicacionCientificoTecnica> participacionPublicaciones, int maxAnyos)
    {
        List<ParticipacionPublicacionCientificoTecnica> participacionPublicacionsFiltrado = new ArrayList<ParticipacionPublicacionCientificoTecnica>();

        int anyoActual = Calendar.getInstance().get(Calendar.YEAR);

        for (ParticipacionPublicacionCientificoTecnica participacionPublicacion : participacionPublicaciones)
        {
            if (anyoActual
                    - participacionPublicacion.getPublicacionCientificoTecnica().getPublicacion()
                            .getFecha().getYear() < maxAnyos)
            {
                participacionPublicacionsFiltrado.add(participacionPublicacion);
            }
            else
            {
                break;
            }
        }

        return participacionPublicacionsFiltrado;
    }

    private static List<ParticipacionPublicacionCientificoTecnica> aplicaFiltroItems(
            List<ParticipacionPublicacionCientificoTecnica> participacionPublicaciones, int maxItems)
    {
        List<ParticipacionPublicacionCientificoTecnica> participacionPublicacionsFiltrado = new ArrayList<ParticipacionPublicacionCientificoTecnica>();
        int count = 0;

        for (ParticipacionPublicacionCientificoTecnica participacionPublicacion : participacionPublicaciones)
        {
            participacionPublicacionsFiltrado.add(participacionPublicacion);
            count++;

            if (count == maxItems)
            {
                break;
            }
        }

        return participacionPublicacionsFiltrado;
    }
}
