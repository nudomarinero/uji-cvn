package es.uji.apps.cvn.translators;

public enum CalidadParticipacionPublicacion
{
    AUTOR_ARTICULO_COMITE("070"),AUTOR_CAPITULO_LIBRO("090"), AUTOR_LIBRO("120"), AUTOR_OBRA_ARTISTICA("140"),EDITOR("390");

    private final String codigo;

    CalidadParticipacionPublicacion(String codigo)
    {
        this.codigo = codigo;
    }

    public String getCodigo()
    {
        return codigo;
    }
    
    
    public static String getTipo(String tipoId)
    {
        if (tipoId != null)
        {
            if (tipoId.equals(CalidadParticipacionPublicacion.AUTOR_ARTICULO_COMITE.codigo))
            {
                return CalidadParticipacionPublicacion.AUTOR_ARTICULO_COMITE.codigo;
            }
            else if (tipoId.equals(CalidadParticipacionPublicacion.AUTOR_CAPITULO_LIBRO.codigo))
            {
                return CalidadParticipacionPublicacion.AUTOR_CAPITULO_LIBRO.codigo;
            }   
            else if (tipoId.equals(CalidadParticipacionPublicacion.AUTOR_LIBRO.codigo))
            {
                return CalidadParticipacionPublicacion.AUTOR_LIBRO.codigo;
            }
            else if (tipoId.equals(CalidadParticipacionPublicacion.AUTOR_OBRA_ARTISTICA.codigo))
            {
                return CalidadParticipacionPublicacion.AUTOR_OBRA_ARTISTICA.codigo;
            }   
            else if (tipoId.equals(CalidadParticipacionPublicacion.EDITOR.codigo))
            {
                return CalidadParticipacionPublicacion.EDITOR.codigo;
            }
        }

        return null;
    }
    
}
