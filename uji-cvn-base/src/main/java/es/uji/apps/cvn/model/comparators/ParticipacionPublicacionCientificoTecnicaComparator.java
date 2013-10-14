package es.uji.apps.cvn.model.comparators;

import java.util.Comparator;

import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.ParticipacionPublicacionCientificoTecnica;

public class ParticipacionPublicacionCientificoTecnicaComparator implements Comparator<ParticipacionPublicacionCientificoTecnica>
{
    @Override
    public int compare(ParticipacionPublicacionCientificoTecnica participacionPublicacion1,
            ParticipacionPublicacionCientificoTecnica participacionPublicacion2)
    {
        XMLGregorianCalendar fecha1 = participacionPublicacion1.getPublicacionCientificoTecnica()
                .getPublicacion().getFecha();
        XMLGregorianCalendar fecha2 = participacionPublicacion2.getPublicacionCientificoTecnica()
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
