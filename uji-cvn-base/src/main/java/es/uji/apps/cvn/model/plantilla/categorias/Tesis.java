package es.uji.apps.cvn.model.plantilla.categorias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.uji.apps.cvn.model.DireccionTesis;
import es.uji.apps.cvn.model.plantilla.Plantilla;

@SuppressWarnings("serial")
public class Tesis extends Categoria implements Serializable
{
    public static List<DireccionTesis> aplicaFiltros(
            List<DireccionTesis> direccionTesis,
            Plantilla plantilla)
    {
        List<DireccionTesis> direccionTesisFiltrado = direccionTesis;

        if (plantilla.getPublicaciones() == null)
        {
            direccionTesisFiltrado = new ArrayList<DireccionTesis>();
        }
        else if (plantilla.getPublicaciones().getMaxAnyos() != 0)
        {
            direccionTesisFiltrado = aplicaFiltroAnyos(direccionTesis,
                    plantilla.getPublicaciones().getMaxAnyos());
        }
        else if (plantilla.getPublicaciones().getMaxItems() != 0)
        {
            direccionTesisFiltrado = aplicaFiltroItems(direccionTesis,
                    plantilla.getPublicaciones().getMaxItems());
        }

        return direccionTesisFiltrado;
    }

    private static List<DireccionTesis> aplicaFiltroAnyos(
            List<DireccionTesis> direccionTesis, int maxAnyos)
    {
        List<DireccionTesis> direccionTesisFiltrado = new ArrayList<DireccionTesis>();

        int anyoActual = Calendar.getInstance().get(Calendar.YEAR);

        for (DireccionTesis direccionTesis1 : direccionTesis)
        {
            if (anyoActual
                    - direccionTesis1.getFechaLectura().getYear() < maxAnyos)
            {
                direccionTesisFiltrado.add(direccionTesis1);
            }
            else
            {
                break;
            }
        }

        return direccionTesisFiltrado;
    }

    private static List<DireccionTesis> aplicaFiltroItems(
            List<DireccionTesis> tesis, int maxItems)
    {
        List<DireccionTesis> direccionTesisFiltrado = new ArrayList<DireccionTesis>();
        int count = 0;

        for (DireccionTesis direccionTesis : tesis)
        {
            direccionTesisFiltrado.add(direccionTesis);
            count++;

            if (count == maxItems)
            {
                break;
            }
        }

        return direccionTesisFiltrado;
    }
}
