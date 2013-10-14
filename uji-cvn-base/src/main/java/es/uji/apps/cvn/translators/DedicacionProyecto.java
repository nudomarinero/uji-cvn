package es.uji.apps.cvn.translators;

public enum DedicacionProyecto
{
    COMPLETO(6, "020"), PARCIAL(3, "030");

    private final int horas;
    private final String codigo;

    DedicacionProyecto(int horas, String codigo)
    {
        this.horas = horas;
        this.codigo = codigo;
    }

    public static String getCodigoTipo(Long horas)
    {
        if (horas != null)
        {
            if (horas.intValue() == DedicacionProyecto.COMPLETO.horas)
            {
                return DedicacionProyecto.COMPLETO.codigo;
            }
            else if (horas.intValue() == DedicacionProyecto.PARCIAL.horas)
            {
                return DedicacionProyecto.PARCIAL.codigo;
            }
        }

        return null;
    }
}
