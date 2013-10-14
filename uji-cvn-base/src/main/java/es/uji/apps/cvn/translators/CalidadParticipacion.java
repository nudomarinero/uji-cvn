package es.uji.apps.cvn.translators;

public enum CalidadParticipacion
{
    RESPONSABLE("S", "270"), INVESTIGADOR("N", "490");

    private final String tipo;
    private final String codigo;

    CalidadParticipacion(String tipo, String codigo)
    {
        this.tipo = tipo;
        this.codigo = codigo;
    }

    public static String getCodigoTipo(String responsable)
    {
        if (responsable != null)
        {
            if (responsable.equals(CalidadParticipacion.RESPONSABLE.tipo))
            {
                return CalidadParticipacion.RESPONSABLE.codigo;
            }
            else if (responsable.equals(CalidadParticipacion.INVESTIGADOR.tipo))
            {
                return CalidadParticipacion.INVESTIGADOR.codigo;
            }
        }

        return null;
    }
}
