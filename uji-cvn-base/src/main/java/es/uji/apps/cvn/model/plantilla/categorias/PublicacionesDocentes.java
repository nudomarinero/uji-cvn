package es.uji.apps.cvn.model.plantilla.categorias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.uji.apps.cvn.model.ParticipacionPublicacionDocente;
import es.uji.apps.cvn.model.plantilla.Plantilla;

@SuppressWarnings("serial")
public class PublicacionesDocentes extends Categoria implements Serializable
{
    public static List<ParticipacionPublicacionDocente> aplicaFiltros(
            List<ParticipacionPublicacionDocente> participacionPublicaciones, Plantilla plantilla)
    {
        List<ParticipacionPublicacionDocente> participacionPublicacionesFiltrado = participacionPublicaciones;

        if (plantilla.getPublicacionesDocentes() == null)
        {
            participacionPublicacionesFiltrado = new ArrayList<ParticipacionPublicacionDocente>();
        }
        else if (plantilla.getPublicacionesDocentes().getMaxAnyos() != 0)
        {
            participacionPublicacionesFiltrado = aplicaFiltroAnyos(participacionPublicaciones,
                    plantilla.getPublicacionesDocentes().getMaxAnyos());
        }
        else if (plantilla.getPublicacionesDocentes().getMaxItems() != 0)
        {
            participacionPublicacionesFiltrado = aplicaFiltroItems(participacionPublicaciones,
                    plantilla.getPublicacionesDocentes().getMaxItems());
        }

        return participacionPublicacionesFiltrado;
    }

    private static List<ParticipacionPublicacionDocente> aplicaFiltroAnyos(
            List<ParticipacionPublicacionDocente> participacionPublicaciones, int maxAnyos)
    {
        List<ParticipacionPublicacionDocente> participacionPublicacionsFiltrado = new ArrayList<ParticipacionPublicacionDocente>();

        int anyoActual = Calendar.getInstance().get(Calendar.YEAR);

        for (ParticipacionPublicacionDocente participacionPublicacion : participacionPublicaciones)
        {
            if (anyoActual
                    - participacionPublicacion.getPublicacionDocente().getPublicacion().getFecha()
                            .getYear() < maxAnyos)
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

    private static List<ParticipacionPublicacionDocente> aplicaFiltroItems(
            List<ParticipacionPublicacionDocente> participacionPublicaciones, int maxItems)
    {
        List<ParticipacionPublicacionDocente> participacionPublicacionsFiltrado = new ArrayList<ParticipacionPublicacionDocente>();
        int count = 0;

        for (ParticipacionPublicacionDocente participacionPublicacion : participacionPublicaciones)
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
