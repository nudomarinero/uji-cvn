package es.uji.apps.cvn.model;

import java.util.Collections;
import java.util.List;

import es.uji.apps.cvn.model.comparators.ParticipacionPublicacionCientificoTecnicaComparator;
import es.uji.apps.cvn.translators.CalidadParticipacionPublicacion;

public class ParticipacionPublicacionCientificoTecnica
{
    private PublicacionCientificoTecnica publicacionCientificoTecnica;

    private Double posicionAutor;

    private String calidad;

    private String resultadosDestacados;

    private Boolean isPublicacionRelevante;

    public PublicacionCientificoTecnica getPublicacionCientificoTecnica()
    {
        return publicacionCientificoTecnica;
    }

    public void setPublicacionCientificoTecnica(
            PublicacionCientificoTecnica publicacionCientificoTecnica)
    {
        this.publicacionCientificoTecnica = publicacionCientificoTecnica;
    }

    public Double getPosicionAutor()
    {
        return posicionAutor;
    }

    public void setPosicionAutor(Float posicionAutor, String calidadParticipacion)
    {
        if (posicionAutor != null && calidadParticipacion != null
                && !calidadParticipacion.equals(CalidadParticipacionPublicacion.EDITOR.getCodigo()))
        {
            this.posicionAutor = posicionAutor.doubleValue();
        }
    }

    public void setPosicionAutor(Long orden, int total, String calidadParticipacion)
    {
        if (calidadParticipacion != null
                && !calidadParticipacion.equals(CalidadParticipacionPublicacion.EDITOR.getCodigo()))
        {
            this.posicionAutor = new Double(orden);
        }
    }

    public String getCalidad()
    {
        return calidad;
    }

    public void setCalidad(String calidad)
    {
        this.calidad = CalidadParticipacionPublicacion.getTipo(calidad);
    }

    public String getResultadosDestacados()
    {
        return resultadosDestacados;
    }

    public void setResultadosDestacados(String resultadosDestacados)
    {
        this.resultadosDestacados = resultadosDestacados;
    }

    public Boolean getIsPublicacionRelevante()
    {
        return isPublicacionRelevante;
    }

    public void setIsPublicacionRelevante(Boolean isPublicacionRelevante)
    {
        this.isPublicacionRelevante = isPublicacionRelevante;
    }

    public static void ordenaListaParticipacionPublicacionesPorFechaInicio(
            List<ParticipacionPublicacionCientificoTecnica> participacionPublicaciones)
    {
        Collections.sort(participacionPublicaciones, new ParticipacionPublicacionCientificoTecnicaComparator());
    }

}
