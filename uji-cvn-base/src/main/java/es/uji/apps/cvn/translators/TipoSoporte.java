package es.uji.apps.cvn.translators;

public enum TipoSoporte
{
    REVISTA(1L, "057"), LIBRO(2L, "032"), CAPITULOLIBRO(3L, "032");

    private final Long tipoId;
    private final String tipo;

    TipoSoporte(Long tipoId, String tipo)
    {
        this.tipoId = tipoId;
        this.tipo = tipo;
    }

    public static String getTipo(Long tipoId)
    {
        if (tipoId != null)
        {
            if (tipoId.equals(TipoSoporte.REVISTA.tipoId))
            {
                return TipoSoporte.REVISTA.tipo;
            }
            else if (tipoId.equals(TipoSoporte.LIBRO.tipoId))
            {
                return TipoSoporte.LIBRO.tipo;
            }
            else if (tipoId.equals(TipoSoporte.CAPITULOLIBRO.tipoId))
            {
                return TipoSoporte.CAPITULOLIBRO.tipo;
            }

        }

        return null;
    }
}
