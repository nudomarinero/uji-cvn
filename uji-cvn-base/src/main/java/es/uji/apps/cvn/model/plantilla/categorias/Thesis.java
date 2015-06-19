package es.uji.apps.cvn.model.plantilla.categorias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.uji.apps.cvn.model.Tesis;
import es.uji.apps.cvn.model.plantilla.Plantilla;

@SuppressWarnings("serial")
public class Thesis extends Categoria implements Serializable
{
    public static List<Tesis> aplicaFiltros(
            List<Tesis> tesis,
            Plantilla plantilla)
    {
        List<Tesis> tesisFiltrado = tesis;

        if (plantilla.getPublicaciones() == null)
        {
            tesisFiltrado = new ArrayList<Tesis>();
        }
        else if (plantilla.getPublicaciones().getMaxAnyos() != 0)
        {
            tesisFiltrado = aplicaFiltroAnyos(tesis,
                    plantilla.getPublicaciones().getMaxAnyos());
        }
        else if (plantilla.getPublicaciones().getMaxItems() != 0)
        {
            tesisFiltrado = aplicaFiltroItems(tesis,
                    plantilla.getPublicaciones().getMaxItems());
        }

        return tesisFiltrado;
    }

    private static List<Tesis> aplicaFiltroAnyos(
            List<Tesis> tesis, int maxAnyos)
    {
        List<Tesis> tesisFiltrado = new ArrayList<Tesis>();

        int anyoActual = Calendar.getInstance().get(Calendar.YEAR);

        for (Tesis tesis1 : tesis)
        {
            if (anyoActual
                    - tesis1.getFechaLectura().getYear() < maxAnyos)
            {
                tesisFiltrado.add(tesis1);
            }
            else
            {
                break;
            }
        }

        return tesisFiltrado;
    }

    private static List<Tesis> aplicaFiltroItems(
            List<Tesis> tesis, int maxItems)
    {
        List<Tesis> tesisFiltrado = new ArrayList<Tesis>();
        int count = 0;

        for (Tesis participacionPublicacion : tesis)
        {
            tesisFiltrado.add(participacionPublicacion);
            count++;

            if (count == maxItems)
            {
                break;
            }
        }

        return tesisFiltrado;
    }
}
