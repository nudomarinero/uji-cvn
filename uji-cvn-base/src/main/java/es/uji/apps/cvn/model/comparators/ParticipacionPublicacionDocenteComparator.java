package es.uji.apps.cvn.model.comparators;

import java.util.Comparator;

import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.ParticipacionPublicacionDocente;

public class ParticipacionPublicacionDocenteComparator implements Comparator<ParticipacionPublicacionDocente>
{
    @Override
    public int compare(ParticipacionPublicacionDocente participacionPublicacion1,
    		ParticipacionPublicacionDocente participacionPublicacion2)
    {
        XMLGregorianCalendar fecha1 = participacionPublicacion1.getPublicacionDocente()
                .getPublicacion().getFecha();
        XMLGregorianCalendar fecha2 = participacionPublicacion2.getPublicacionDocente()
                .getPublicacion().getFecha();

        if (fecha1 != null && fecha2 != null)
        {
            return fecha2.toGregorianCalendar().compareTo(fecha1.toGregorianCalendar());
        }
        else if (fecha1 != null)
        {
            return 1;
        }
        else if (fecha2 != null)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }

}