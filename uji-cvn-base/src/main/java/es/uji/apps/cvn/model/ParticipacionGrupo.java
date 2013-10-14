package es.uji.apps.cvn.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class ParticipacionGrupo
{
    private GrupoInvestigacion grupoInvestigacion;

    private XMLGregorianCalendar fechaInicio;

    private String fechaFin;

    private String resultadosDesc;

    private String resultadosKeywords;

    private String resultadosRelevantes;

    private String tipoColaboracion;

    private String duracion;

    private static String DATE_FORMATTER = "yyyy-MM-dd";

    public ParticipacionGrupo()
    {
    }

    public GrupoInvestigacion getGrupoInvestigacion()
    {
        return grupoInvestigacion;
    }

    public void setGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion)
    {
        this.grupoInvestigacion = grupoInvestigacion;
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

    public String getFechaFin()
    {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin)
    {
        if (fechaFin != null)
        {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMATTER);
            this.fechaFin = formatter.format(fechaFin);
        }
    }

    public String getResultadosDesc()
    {
        return resultadosDesc;
    }

    public void setResultadosDesc(String resultadosDesc)
    {
        this.resultadosDesc = resultadosDesc;
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

    public String getTipoColaboracion()
    {
        return tipoColaboracion;
    }

    public void setTipoColaboracion(String tipoColaboracion)
    {
        this.tipoColaboracion = tipoColaboracion;
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

}
