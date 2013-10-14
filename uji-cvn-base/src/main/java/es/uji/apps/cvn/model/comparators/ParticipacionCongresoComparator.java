package es.uji.apps.cvn.model.comparators;

import java.util.Comparator;

import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.ParticipacionCongreso;

public class ParticipacionCongresoComparator implements Comparator<ParticipacionCongreso>
{
    @Override
    public int compare(ParticipacionCongreso participacionCongreso1,
            ParticipacionCongreso participacionCongreso2)
    {
        XMLGregorianCalendar fecha1 = ((participacionCongreso1.getCongreso().getFecha() != null) ? participacionCongreso1
                .getCongreso().getFecha() : participacionCongreso1.getCongreso().getAno());

        XMLGregorianCalendar fecha2 = ((participacionCongreso2.getCongreso().getFecha() != null) ? participacionCongreso2
                .getCongreso().getFecha() : participacionCongreso2.getCongreso().getAno());

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
