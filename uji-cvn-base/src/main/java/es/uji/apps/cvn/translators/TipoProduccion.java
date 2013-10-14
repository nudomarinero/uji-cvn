package es.uji.apps.cvn.translators;

public enum TipoProduccion
{
    ARTICULO(32L, "020"), LIBRO(33L, "032"), CAPITULOLIBRO(34L, "004"), OTHERS(null, "OTHERS");

    private final Long tipoId;
    private final String tipo;

    TipoProduccion(Long tipoId, String tipo)
    {
        this.tipoId = tipoId;
        this.tipo = tipo;
    }

    public static String getTipo(Long tipoId)
    {
        if (tipoId != null)
        {
            if (tipoId.equals(TipoProduccion.ARTICULO.tipoId))
            {
                return TipoProduccion.ARTICULO.tipo;
            }
            else if (tipoId.equals(TipoProduccion.LIBRO.tipoId))
            {
                return TipoProduccion.LIBRO.tipo;
            }
            else if (tipoId.equals(TipoProduccion.CAPITULOLIBRO.tipoId))
            {
                return TipoProduccion.CAPITULOLIBRO.tipo;
            }
        }

        return TipoProduccion.OTHERS.tipo;
    }

    public Long getTipoId()
    {
        return tipoId;
    }
    
    public String getTipo()
    {
        return tipo;
    }
}
