package es.uji.apps.cvn.model.plantilla.categorias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.uji.apps.cvn.model.SituacionProfesional;
import es.uji.apps.cvn.model.plantilla.Plantilla;

@SuppressWarnings("serial")
public class SituacionProfesionalActiva extends Categoria implements Serializable
{
    public static List<SituacionProfesional> aplicaFiltros(
            List<SituacionProfesional> situacionProfesional,
            Plantilla plantilla)
    {
        List<SituacionProfesional> situacionProfesionalFiltrado = situacionProfesional;

        if (plantilla.getSituacionProfesionalActiva() == null)
        {
            situacionProfesionalFiltrado = new ArrayList<SituacionProfesional>();
        }
        else if (plantilla.getSituacionProfesionalActiva().getMaxAnyos() != 0)
        {
            situacionProfesionalFiltrado = aplicaFiltroAnyos(situacionProfesional,
                    plantilla.getSituacionProfesionalActiva().getMaxAnyos());
        }
        else if (plantilla.getSituacionProfesionalActiva().getMaxItems() != 0)
        {
            situacionProfesionalFiltrado = aplicaFiltroItems(situacionProfesional,
                    plantilla.getSituacionProfesionalActiva().getMaxItems());
        }

        return situacionProfesionalFiltrado;
    }

    private static List<SituacionProfesional> aplicaFiltroAnyos(
            List<SituacionProfesional> situacionProfesionalList, int maxAnyos)
    {
        List<SituacionProfesional> situacionProfesionalFiltrado = new ArrayList<SituacionProfesional>();

        int anyoActual = Calendar.getInstance().get(Calendar.YEAR);

        for (SituacionProfesional situacionProfesional : situacionProfesionalList)
        {
            if (anyoActual
                    - situacionProfesional.getFechaInicio().getYear() < maxAnyos)
            {
                situacionProfesionalFiltrado.add(situacionProfesional);
            }
            else
            {
                break;
            }
        }

        return situacionProfesionalFiltrado;
    }

    private static List<SituacionProfesional> aplicaFiltroItems(
            List<SituacionProfesional> situacionProfesional, int maxItems)
    {
        List<SituacionProfesional> situacionProfesionalFiltrado = new ArrayList<SituacionProfesional>();
        int count = 0;

        for (SituacionProfesional participacionPublicacion : situacionProfesional)
        {
            situacionProfesionalFiltrado.add(participacionPublicacion);
            count++;

            if (count == maxItems)
            {
                break;
            }
        }

        return situacionProfesionalFiltrado;
    }
}
