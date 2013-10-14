package es.uji.apps.cvn.translators;

public enum FuenteImpacto
{
    WOS("WOS (JCR)", "000"), SCOPUS("SCOPUS (SJR)", "010"), INRECS("INRECS", "020"), OTHERS(null,
            "OTHERS");

    private final String tipo;
    private final String codigo;

    FuenteImpacto(String tipo, String codigo)
    {
        this.tipo = tipo;
        this.codigo = codigo;
    }

    public static String getCodigoTipo(String fuenteImpacto)
    {
        if (fuenteImpacto != null)
        {
            if (fuenteImpacto.equals(FuenteImpacto.WOS.tipo))
            {
                return FuenteImpacto.WOS.codigo;
            }
            else if (fuenteImpacto.equals(FuenteImpacto.SCOPUS.tipo))
            {
                return FuenteImpacto.SCOPUS.codigo;
            }
            else if (fuenteImpacto.equals(FuenteImpacto.INRECS.tipo))
            {
                return FuenteImpacto.INRECS.codigo;
            }
        }

        return FuenteImpacto.OTHERS.codigo;
    }
}
