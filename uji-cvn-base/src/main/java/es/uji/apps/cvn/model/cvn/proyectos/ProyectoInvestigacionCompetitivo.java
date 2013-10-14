package es.uji.apps.cvn.model.cvn.proyectos;

import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.cvn.CVNItem;
import es.uji.apps.cvn.model.cvn.Entidad;

public class ProyectoInvestigacionCompetitivo implements ProyectoInvestigacion
{
    private CVNItem cvnItem;

    private Entidad entidad;

    private FinanciacionProyecto financiacionProyecto;

    public ProyectoInvestigacionCompetitivo(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    public Entidad getEntidad()
    {
        if (entidad == null)
        {
            entidad = new EntidadProyectoInvestigacionCompetitivo(cvnItem);
        }
        return entidad;
    }

    public void setEntidad(Entidad entidad)
    {
        this.entidad = entidad;
    }

    public FinanciacionProyecto getFinanciacionProyecto()
    {
        if (financiacionProyecto == null)
        {
            financiacionProyecto = new FinanciacionProyectoCompetitivo(cvnItem);
        }
        return financiacionProyecto;
    }

    public void setFinanciacionProyecto(FinanciacionProyecto financiacionProyecto)
    {
        this.financiacionProyecto = financiacionProyecto;
    }

    @Override
    public void addTitulo(String titulo)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.010", titulo));
    }

    @Override
    public void addIdentificacionPalabrasClave(String palabrasClave)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.020", palabrasClave));
    }

    @Override
    public void addModalidad(String modalidad)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.030", modalidad));
    }

    @Override
    public void addAmbito(String ambito)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.040", ambito));
    }

    @Override
    public void addAmbitoOtros(String ambitoOtros)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.050", ambitoOtros));
    }

    @Override
    public void addResponsable(String nombre, String apellido1, String apellido2)
    {
        cvnItem.addCvnAuthorBean(cvnItem.buildCvnAuthorBean("050.020.010.140", nombre, apellido1,
                apellido2));
    }

    @Override
    public void addNumeroInvestigadoresParticipantes(Double nParticipantes)
    {
        cvnItem.addCvnDouble(cvnItem.buildCvnDouble("050.020.010.150", nParticipantes));
    }

    @Override
    public void addNumeroPersonasAnyo(Double nPersonasAnyo)
    {
        cvnItem.addCvnDouble(cvnItem.buildCvnDouble("050.020.010.160", nPersonasAnyo));
    }

    @Override
    public void addFechaInicio(XMLGregorianCalendar fechaInicio)
    {
        cvnItem.addCvnDateDayMonthYear(cvnItem.buildCvnDateDayMonthYear("050.020.010.270",
                fechaInicio));
    }

    @Override
    public void addDuracion(String duracion)
    {
        cvnItem.addCvnDuration(cvnItem.buildCvnDuration("050.020.010.280", duracion));
    }

    @Override
    public void addResultadosRelevantes(String resultados)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.340", resultados));
    }

    @Override
    public void addResultadosPalabrasClave(String palabrasClave)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.350", palabrasClave));
    }

    @Override
    public void addEntidadParticipante(String entidadParticipante)
    {
        cvnItem.addCvnEntityBean(cvnItem.buildCvnEntityBean("050.020.010.400", entidadParticipante));
    }

    public void addFechaFin(XMLGregorianCalendar fechaFin)
    {
        cvnItem.addCvnDateDayMonthYear(cvnItem
                .buildCvnDateDayMonthYear("050.020.010.410", fechaFin));
    }

}
