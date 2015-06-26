package es.uji.apps.cvn.model.plantilla.categorias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.uji.apps.cvn.model.DocenciaImpartida;
import es.uji.apps.cvn.model.plantilla.Plantilla;

@SuppressWarnings("serial")
public class Docencia extends Categoria implements Serializable
{
    public static List<DocenciaImpartida> aplicaFiltros(
            List<DocenciaImpartida> docenciaImpartida,
            Plantilla plantilla)
    {
        List<DocenciaImpartida> docenciaImpartidaFiltrado = docenciaImpartida;

        if (plantilla.getDocencia() == null)
        {
            docenciaImpartidaFiltrado = new ArrayList<DocenciaImpartida>();
        }
        else if (plantilla.getDocencia().getMaxAnyos() != 0)
        {
            docenciaImpartidaFiltrado = aplicaFiltroAnyos(docenciaImpartida,
                    plantilla.getDocencia().getMaxAnyos());
        }
        else if (plantilla.getDocencia().getMaxItems() != 0)
        {
            docenciaImpartidaFiltrado = aplicaFiltroItems(docenciaImpartida,
                    plantilla.getDocencia().getMaxItems());
        }

        return docenciaImpartidaFiltrado;
    }

    private static List<DocenciaImpartida> aplicaFiltroAnyos(
            List<DocenciaImpartida> docenciaImpartida, int maxAnyos)
    {
        List<DocenciaImpartida> docenciaImpartidaFiltrado = new ArrayList<DocenciaImpartida>();

        int anyoActual = Calendar.getInstance().get(Calendar.YEAR);

        for (DocenciaImpartida docenciaImpartida1 : docenciaImpartida)
        {
            if (anyoActual
                    - docenciaImpartida1.getUltimaVez().getYear() < maxAnyos)
            {
                docenciaImpartidaFiltrado.add(docenciaImpartida1);
            }
            else
            {
                break;
            }
        }

        return docenciaImpartidaFiltrado;
    }

    private static List<DocenciaImpartida> aplicaFiltroItems(
            List<DocenciaImpartida> docenciaImpartida, int maxItems)
    {
        List<DocenciaImpartida> docenciaImpartidaFiltrado = new ArrayList<DocenciaImpartida>();
        int count = 0;

        for (DocenciaImpartida docenciaImpartida1 : docenciaImpartida)
        {
            docenciaImpartidaFiltrado.add(docenciaImpartida1);
            count++;

            if (count == maxItems)
            {
                break;
            }
        }

        return docenciaImpartidaFiltrado;
    }
}
