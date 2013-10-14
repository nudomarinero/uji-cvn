package es.uji.apps.cvn.model.comparators;

import java.util.Comparator;

import es.uji.apps.cvn.model.AutorPublicacion;

public class AutorPublicacionComparator implements Comparator<AutorPublicacion>
{
    @Override
    public int compare(AutorPublicacion autorPublicacion1, AutorPublicacion autorPublicacion2)
    {
        if (autorPublicacion1.getOrden() != null && autorPublicacion2.getOrden() != null
                && !autorPublicacion1.getOrden().equals(autorPublicacion2.getOrden()))
        {
            return autorPublicacion1.getOrden().compareTo(autorPublicacion2.getOrden());
        }
        else if (autorPublicacion1.getOrden() != null)
        {
            return 1;
        }
        else if (autorPublicacion2.getOrden() != null)
        {
            return -1;
        }
        else
        {
            if (autorPublicacion1.getApellido1() != null
                    && autorPublicacion2.getApellido1() != null)
            {
                if (!autorPublicacion1.getApellido1().equals(autorPublicacion2.getApellido2()))
                {
                    return autorPublicacion1.getApellido1().compareTo(
                            autorPublicacion2.getApellido1());
                }
            }
            if (autorPublicacion1.getApellido2() != null
                    && autorPublicacion2.getApellido2() != null)
            {
                if (!autorPublicacion1.getApellido2().equals(autorPublicacion2.getApellido2()))
                {
                    return autorPublicacion1.getApellido2().compareTo(
                            autorPublicacion2.getApellido2());
                }
            }
            if (autorPublicacion1.getNombre() != null && autorPublicacion2.getNombre() != null)
            {
                if (!autorPublicacion1.getNombre().equals(autorPublicacion2.getNombre()))
                {
                    return autorPublicacion1.getNombre().compareTo(autorPublicacion2.getNombre());
                }
            }
        }

        return 0;
    }
}
