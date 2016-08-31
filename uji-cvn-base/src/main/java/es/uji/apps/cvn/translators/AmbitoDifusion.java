package es.uji.apps.cvn.translators;

/**
 * Created by teclab on 31/08/16.
 */
public enum AmbitoDifusion
{
    NACIONAL("269", "010"), INTERNACIONAL("270", "030");

    private final String tipo;
    private final String codigo;

    AmbitoDifusion(String tipo, String codigo)
    {
        this.tipo = tipo;
        this.codigo = codigo;
    }

    public static String getCodigoTipo(String tipo)
    {
        if (tipo != null)
        {
            if (tipo.equals(AmbitoDifusion.NACIONAL.tipo))
            {
                return AmbitoDifusion.NACIONAL.codigo;
            }
            else if (tipo.equals(AmbitoDifusion.INTERNACIONAL.tipo))
            {
                return AmbitoDifusion.INTERNACIONAL.codigo;
            }
        }

        return null;
    }
}
