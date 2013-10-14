package es.uji.apps.cvn.model.cvn.proyectos;

import javax.xml.datatype.XMLGregorianCalendar;

public interface ProyectoInvestigacion
{
    public void addTitulo(String titulo);

    public void addIdentificacionPalabrasClave(String palabrasClave);

    public void addModalidad(String modalidad);

    public void addAmbito(String ambito);

    public void addAmbitoOtros(String ambitoOtros);

    public void addResponsable(String nombre, String apellido1, String apellido2);

    public void addNumeroInvestigadoresParticipantes(Double nParticipantes);

    public void addNumeroPersonasAnyo(Double nPersonasAnyo);

    public void addFechaInicio(XMLGregorianCalendar fechaInicio);

    public void addDuracion(String duracion);

    public void addResultadosRelevantes(String resultados);

    public void addResultadosPalabrasClave(String palabrasClave);

    public void addEntidadParticipante(String entidadParticipante);

}
