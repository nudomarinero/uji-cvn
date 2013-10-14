package es.uji.apps.cvn.model.cvn;

import javax.xml.datatype.XMLGregorianCalendar;

public class ProduccionCientifica extends CVNItem
{
    public ProduccionCientifica()
    {
        this.code = "060.010.000.000";
    }

    public void addIndiceH(Double indiceH)
    {
        this.addCvnDouble(this.buildCvnDouble("060.010.000.010", indiceH));
    }

    public void addFechaIndiceH(XMLGregorianCalendar fechaIndiceH)
    {
        this.addCvnDateDayMonthYear(this.buildCvnDateDayMonthYear("060.010.000.020", fechaIndiceH));
    }
}
