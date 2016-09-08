package es.uji.apps.cvn.model;

import java.util.Collections;
import java.util.List;

import es.uji.apps.cvn.model.comparators.ParticipacionPublicacionDocenteComparator;
import es.uji.apps.cvn.translators.CalidadParticipacionPublicacion;

public class ParticipacionPublicacionDocente
{
    final public static Long CARACTER_DOCENTE_LIBRO = 277L;

    final public static Long CARACTER_DOCENTE_ARTICULO = 268L;

    final public static Long CARACTER_DOCENTE_PONENCIA = 286L;

    private PublicacionDocente publicacionDocente;

    private Double posicionAutor;

    private String calidad;

    public PublicacionDocente getPublicacionDocente()
    {
        return publicacionDocente;
    }

    public void setPublicacionDocente(PublicacionDocente publicacionDocente)
    {
        this.publicacionDocente = publicacionDocente;
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

    public static void ordenaListaParticipacionPublicacionesPorFechaInicio(
            List<ParticipacionPublicacionDocente> participacionPublicaciones)
    {
        Collections.sort(participacionPublicaciones,
                new ParticipacionPublicacionDocenteComparator());
    }

}
