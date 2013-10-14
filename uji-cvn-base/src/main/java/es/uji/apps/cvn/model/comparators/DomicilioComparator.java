package es.uji.apps.cvn.model.comparators;

import java.util.Comparator;

import es.uji.apps.cvn.model.Domicilio;

public class DomicilioComparator implements Comparator<Domicilio>
{
    @Override
    public int compare(Domicilio domicilio1, Domicilio domicilio2)
    {
        if (domicilio1.getOrdenPreferencia() != null && domicilio2.getOrdenPreferencia() != null)
        {
            return domicilio1.getOrdenPreferencia().compareTo(domicilio2.getOrdenPreferencia());
        }
        else if (domicilio1.getOrdenPreferencia() != null)
        {
            return 1;
        }
        else if (domicilio2.getOrdenPreferencia() != null)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }

}
