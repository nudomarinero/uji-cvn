package es.uji.apps.cvn.model;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.Utils;

public class ProyectoInvestigacion
{
    private Long id;

    private String ambito;

    private String ambitoStr;

    private String codigoExterno;

    private String codigoProyectoFinanciadora;

    private Double dotacionSubproyecto;

    private Double dotacionTotal;

    private String duracion;

    private String entidadEjecutora;

    private XMLGregorianCalendar fechaFin;

    private XMLGregorianCalendar fechaInicio;

    private String microtipo;

    private String modalidad;

    private Double nInvestigadores;

    private String nombreProgramaFinanciacion;

    private Double nPersonasAnyo;

    private Double porcentajeCredito;

    private Double porcentajeMixto;

    private Double porcentajeSubvencion;

    private String resultadosKeywords;

    private String resultadosRelevantes;

    private String subtipo;

    private Long tipo;

    private String titulo;

    private String tituloKeywords;

    private List<Entidad> entidadesFinanciadoras;

    private List<Entidad> entidadesParticipantes;

    private List<Persona> responsables;

    final public static Long COMPETITIVO = 2L;

    final public static Long NO_COMPETITIVO = 1L;

    final private static String AMBITO_OTROS = "OTHERS";

    public ProyectoInvestigacion()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getAmbito()
    {
        return AMBITO_OTROS;
    }

    public void setAmbito(String ambito)
    {
        this.ambito = ambito;
    }

    public String getAmbitoStr()
    {
        return ambitoStr;
    }

    public void setAmbitoStr(String ambitoStr)
    {
        this.ambitoStr = ambitoStr;
    }

    public String getCodigoExterno()
    {
        return codigoExterno;
    }

    public void setCodigoExterno(String codigoExterno)
    {
        this.codigoExterno = codigoExterno;
    }

    public String getCodigoProyectoFinanciadora()
    {
        return codigoProyectoFinanciadora;
    }

    public void setCodigoProyectoFinanciadora(String codigoProyectoFinanciadora)
    {
        this.codigoProyectoFinanciadora = codigoProyectoFinanciadora;
    }

    public Double getDotacionSubproyecto()
    {
        return dotacionSubproyecto;
    }

    public void setDotacionSubproyecto(Float dotacionSubproyecto)
    {
        if (dotacionSubproyecto != null)
        {
            this.dotacionSubproyecto = dotacionSubproyecto.doubleValue();
        }
    }

    public Double getDotacionTotal()
    {
        return dotacionTotal;
    }

    public void setDotacionTotal(Float dotacionTotal)
    {
        if (dotacionTotal != null)
        {
            this.dotacionTotal = dotacionTotal.doubleValue();
        }
    }

    public String getDuracion()
    {
        return duracion;
    }

    public void setDuracion(Long duracion)
    {
        // Convertimos la duración de días a meses (o días) y formateamos a estándar ISO
        if (duracion != null)
        {
            int duracionMeses = duracion.intValue() / 30;
            if (duracionMeses > 0)
            {
                this.duracion = "P" + duracionMeses + "M";
            }
            else
            {
                this.duracion = "P" + duracion.intValue() + "D";
            }
        }
    }

    public String getEntidadEjecutora()
    {
        return entidadEjecutora;
    }

    public void setEntidadEjecutora(String entidadEjecutora)
    {
        this.entidadEjecutora = entidadEjecutora;
    }

    public XMLGregorianCalendar getFechaFin()
    {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin)
    {
        if (fechaFin != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(fechaFin);
            try
            {
                this.fechaFin = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            }
            catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public XMLGregorianCalendar getFechaInicio()
    {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio)
    {
        if (fechaInicio != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(fechaInicio);
            try
            {
                this.fechaInicio = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            }
            catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public String getMicrotipo()
    {
        return microtipo;
    }

    public void setMicrotipo(String microtipo)
    {
        this.microtipo = microtipo;
    }

    public String getModalidad()
    {
        return modalidad;
    }

    public void setModalidad(String modalidad)
    {
        this.modalidad = modalidad;
    }

    public Double getNInvestigadores()
    {
        return nInvestigadores;
    }

    public void setNInvestigadores(Long nInvestigadores, Long nInvestigadoresExt)
    {
        if (nInvestigadores.longValue() > 0 || nInvestigadoresExt.longValue() > 0)
        {
            this.nInvestigadores = nInvestigadores.doubleValue() + nInvestigadoresExt.doubleValue();
        }
    }

    public String getNombreProgramaFinanciacion()
    {
        return nombreProgramaFinanciacion;
    }

    public void setNombreProgramaFinanciacion(String nombreProgramaFinanciacion)
    {
        this.nombreProgramaFinanciacion = nombreProgramaFinanciacion;
    }

    public Double getNPersonasAnyo()
    {
        return nPersonasAnyo;
    }

    public void setNPersonasAnyo(Long nPersonasAnyo)
    {
        if (nPersonasAnyo != null)
        {
            this.nPersonasAnyo = nPersonasAnyo.doubleValue();
        }
    }

    public Double getPorcentajeCredito()
    {
        return porcentajeCredito;
    }

    public void setPorcentajeCredito(String porcentajeCredito)
    {
        if (porcentajeCredito != null)
        {
            this.porcentajeCredito = new Double(porcentajeCredito);
        }
    }

    public Double getPorcentajeMixto()
    {
        return porcentajeMixto;
    }

    public void setPorcentajeMixto(String porcentajeMixto)
    {
        if (porcentajeMixto != null)
        {
            this.porcentajeMixto = new Double(porcentajeMixto);
        }
    }

    public Double getPorcentajeSubvencion()
    {
        return porcentajeSubvencion;
    }

    public void setPorcentajeSubvencion(String porcentajeSubvencion)
    {
        if (porcentajeSubvencion != null)
        {
            this.porcentajeSubvencion = new Double(porcentajeSubvencion);
        }
    }

    public String getResultadosKeywords()
    {
        return resultadosKeywords;
    }

    public void setResultadosKeywords(String resultadosKeywords)
    {
        this.resultadosKeywords = resultadosKeywords;
    }

    public String getResultadosRelevantes()
    {
        return resultadosRelevantes;
    }

    public void setResultadosRelevantes(String resultadosRelevantes)
    {
        this.resultadosRelevantes = resultadosRelevantes;
    }

    public String getSubtipo()
    {
        return subtipo;
    }

    public void setSubtipo(String subtipo)
    {
        this.subtipo = subtipo;
    }

    public Long getTipo()
    {
        return tipo;
    }

    public void setTipo(Long tipo)
    {
        this.tipo = tipo;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = Utils.limpiaCadena(titulo);
    }

    public String getTituloKeywords()
    {
        return tituloKeywords;
    }

    public void setTituloKeywords(String tituloKeywords)
    {
        this.tituloKeywords = tituloKeywords;
    }

    public List<Entidad> getEntidadesFinanciadoras()
    {
        return entidadesFinanciadoras;
    }

    public void setEntidadesFinanciadoras(List<Entidad> entidadesFinanciadoras)
    {
        this.entidadesFinanciadoras = entidadesFinanciadoras;
    }

    public List<Entidad> getEntidadesParticipantes()
    {
        return entidadesParticipantes;
    }

    public void setEntidadesParticipantes(List<Entidad> entidadesParticipantes)
    {
        this.entidadesParticipantes = entidadesParticipantes;
    }

    public List<Persona> getResponsables()
    {
        return responsables;
    }

    public void setResponsables(List<Persona> responsables)
    {
        this.responsables = responsables;
    }

    public void normalizaNombreProgramaFinanciacion()
    {
        if (nombreProgramaFinanciacion != null)
        {
            int indiceSep = nombreProgramaFinanciacion.indexOf("*");
            if (indiceSep != -1)
            {
                nombreProgramaFinanciacion = nombreProgramaFinanciacion.substring(0, indiceSep)
                        .trim();
            }
        }
    }
}
