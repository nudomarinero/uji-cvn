package es.uji.apps.cvn.services;

public enum EstadoCvn
{
    SOLICITADO("100", "SOLICITADO"), PENDIENTE("200", "PENDIENTE"), FINALIZADO("300", "FINALIZADO"), ERROR(
            "400", "ERROR");

    private String estado;
    private String mensajeEstado;

    private EstadoCvn(String estado, String mensajeEstado)
    {
        this.estado = estado;
        this.mensajeEstado = mensajeEstado;
    }

    public String getEstado()
    {
        return estado;
    }

    public String getMensajeEstado()
    {
        return mensajeEstado;
    }

    public static String getMensajeEstado(String estado)
    {
        if (estado.equals(EstadoCvn.SOLICITADO.estado))
        {
            return EstadoCvn.SOLICITADO.mensajeEstado;
        }
        else if (estado.equals(EstadoCvn.PENDIENTE.estado))
        {
            return EstadoCvn.PENDIENTE.mensajeEstado;
        }
        else if (estado.equals(EstadoCvn.FINALIZADO.estado))
        {
            return EstadoCvn.FINALIZADO.mensajeEstado;
        }
        else
        {
            return EstadoCvn.ERROR.mensajeEstado;
        }
    }
}
