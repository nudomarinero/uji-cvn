package es.uji.apps.cvn.translators;

public enum TipoSoporteDocente
{
    REVISTA(32L, "074"), LIBRO(33L, "032"), CAPITULOLIBRO(34L, "004");

    private final Long tipoId;
    private final String tipo;

    TipoSoporteDocente(Long tipoId, String tipo)
    {
        this.tipoId = tipoId;
        this.tipo = tipo;
    }

    public static String getTipo(Long tipoId)
    {
        if (tipoId != null)
        {
            if (tipoId.equals(TipoSoporteDocente.REVISTA.tipoId))
            {
                return TipoSoporteDocente.REVISTA.tipo;
            }
            else if (tipoId.equals(TipoSoporteDocente.LIBRO.tipoId))
            {
                return TipoSoporteDocente.LIBRO.tipo;
            }
            else if (tipoId.equals(TipoSoporteDocente.CAPITULOLIBRO.tipoId))
            {
                return TipoSoporteDocente.CAPITULOLIBRO.tipo;
            }

        }

        return null;
    }
}