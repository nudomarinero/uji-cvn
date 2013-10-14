package es.uji.apps.cvn.translators;

public enum TipoParticipacionCongreso
{
    PONENCIA_INVITADA("730"), PONENCIA("960"), COMITE_ORGANIZADOR("980"), COMITE_CIENTIFICO("990");

    private final String codigo;

    TipoParticipacionCongreso(String codigo)
    {
        this.codigo = codigo;
    }

    public static String getCodigoTipo(String tipoId)
    {
        if (tipoId != null)
        {
            if (tipoId.equals(TipoParticipacionCongreso.PONENCIA_INVITADA.codigo))
            {
                return TipoParticipacionCongreso.PONENCIA_INVITADA.codigo;
            }
            else if (tipoId.equals(TipoParticipacionCongreso.PONENCIA.codigo))
            {
                return TipoParticipacionCongreso.PONENCIA.codigo;
            }   
            else if (tipoId.equals(TipoParticipacionCongreso.COMITE_ORGANIZADOR.codigo))
            {
                return TipoParticipacionCongreso.COMITE_ORGANIZADOR.codigo;
            }
            else if (tipoId.equals(TipoParticipacionCongreso.COMITE_CIENTIFICO.codigo))
            {
                return TipoParticipacionCongreso.COMITE_CIENTIFICO.codigo;
            }   
        }

        return null;
    }
}
