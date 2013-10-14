package es.uji.apps.cvn.translators;

public enum TipoIdentificacion
{
    DNI("1", "DNI"), NIE("4", "NIE"), PASS("5", "PASS");

    private final String tipo;
    private final String codigo;

    TipoIdentificacion(String tipo, String codigo)
    {
        this.tipo = tipo;
        this.codigo = codigo;
    }

    public static String getCodigoTipo(String tipoIdentificacion)
    {
        if (tipoIdentificacion != null)
        {
            if (tipoIdentificacion.equals(TipoIdentificacion.DNI.tipo))
            {
                return TipoIdentificacion.DNI.codigo;
            }
            else if (tipoIdentificacion.equals(TipoIdentificacion.NIE.tipo))
            {
                return TipoIdentificacion.NIE.codigo;
            }
            else if (tipoIdentificacion.equals(TipoIdentificacion.PASS.tipo))
            {
                return TipoIdentificacion.PASS.codigo;
            }
        }

        return null;
    }

}