package es.uji.apps.cvn.model.plantilla.categorias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.uji.apps.cvn.model.Doctorado;
import es.uji.apps.cvn.model.plantilla.Plantilla;

@SuppressWarnings("serial")
public class Doctorados extends Categoria implements Serializable
{
    public static List<Doctorado> aplicaFiltros(
            List<Doctorado> doctorados,
            Plantilla plantilla)
    {
        List<Doctorado> doctoradosFiltrado = doctorados;

        if (plantilla.getDoctorados() == null)
        {
            doctoradosFiltrado = new ArrayList<Doctorado>();
        }
        else if (plantilla.getDoctorados().getMaxAnyos() != 0)
        {
            doctoradosFiltrado = aplicaFiltroAnyos(doctorados,
                    plantilla.getDoctorados().getMaxAnyos());
        }
        else if (plantilla.getDoctorados().getMaxItems() != 0)
        {
            doctoradosFiltrado = aplicaFiltroItems(doctorados,
                    plantilla.getDoctorados().getMaxItems());
        }

        return doctoradosFiltrado;
    }

    private static List<Doctorado> aplicaFiltroAnyos(
            List<Doctorado> doctorados, int maxAnyos)
    {
        List<Doctorado> doctoradosFiltrado = new ArrayList<Doctorado>();

        int anyoActual = Calendar.getInstance().get(Calendar.YEAR);

        for (Doctorado doctorado : doctorados)
        {
            if (anyoActual
                    - doctorado.getTitulacionFecha().getYear() < maxAnyos)
            {
                doctoradosFiltrado.add(doctorado);
            }
            else
            {
                break;
            }
        }

        return doctoradosFiltrado;
    }

    private static List<Doctorado> aplicaFiltroItems(
            List<Doctorado> doctorados, int maxItems)
    {
        List<Doctorado> doctoradosFiltrado = new ArrayList<Doctorado>();
        int count = 0;

        for (Doctorado doctorado : doctorados)
        {
            doctoradosFiltrado.add(doctorado);
            count++;

            if (count == maxItems)
            {
                break;
            }
        }

        return doctoradosFiltrado;
    }
}
