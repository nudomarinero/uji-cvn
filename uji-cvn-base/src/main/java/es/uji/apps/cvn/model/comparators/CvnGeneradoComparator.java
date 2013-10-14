package es.uji.apps.cvn.model.comparators;

import java.util.Comparator;

import es.uji.apps.cvn.model.CvnGenerado;

public class CvnGeneradoComparator implements Comparator<CvnGenerado>
{
    @Override
    public int compare(CvnGenerado cvnGenerado1, CvnGenerado cvnGenerado2)
    {
        return cvnGenerado2.getFechaUltimaActualizacion().compareTo(
                cvnGenerado1.getFechaUltimaActualizacion());
    }

}
