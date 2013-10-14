package es.uji.apps.cvn.model.comparators;

import java.util.Comparator;

import es.uji.apps.cvn.model.PlantillaCvn;

public class PlantillaComparator implements Comparator<PlantillaCvn>
{
    @Override
    public int compare(PlantillaCvn plantilla1, PlantillaCvn plantilla2)
    {
        return plantilla2.getFechaUltimaActualizacion().compareTo(
                plantilla1.getFechaUltimaActualizacion());
    }

}
