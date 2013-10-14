package es.uji.apps.cvn.model.cvn.grupos;

import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class InvestigadorGrupo
{
    private CVNItem cvnItem;

    public InvestigadorGrupo(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    public void addFechaInicio(XMLGregorianCalendar fechaInicio)
    {
        cvnItem.addCvnDateDayMonthYear(cvnItem.buildCvnDateDayMonthYear("050.010.000.140",
                fechaInicio));
    }

    public void addDuracion(String duracion)
    {
        cvnItem.addCvnDuration(cvnItem.buildCvnDuration("050.010.000.150", duracion));
    }

    public void addClasesColaboracion(String tipoColaboracion)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.010.000.160", tipoColaboracion));
    }

    public void addResultadosOtros(String resultadosOtros)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.010.000.190", resultadosOtros));
    }

    public void addResultadosRelevantes(String resultadosRelevantes)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.010.000.200", resultadosRelevantes));
    }

    public void addResultadosPalabrasClave(String palabrasClave)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.010.000.210", palabrasClave));
    }
}
