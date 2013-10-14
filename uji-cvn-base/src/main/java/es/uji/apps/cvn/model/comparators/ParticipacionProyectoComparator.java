package es.uji.apps.cvn.model.comparators;

import java.util.Comparator;

import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.ParticipacionProyecto;

public class ParticipacionProyectoComparator implements Comparator<ParticipacionProyecto>
{
    @Override
    public int compare(ParticipacionProyecto participacionProyecto1,
            ParticipacionProyecto participacionProyecto2)
    {
        XMLGregorianCalendar fecha1 = participacionProyecto1.getProyectoInvestigacion()
                .getFechaInicio();
        XMLGregorianCalendar fecha2 = participacionProyecto2.getProyectoInvestigacion()
                .getFechaInicio();

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
