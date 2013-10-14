package es.uji.apps.cvn.client.exceptions;

@SuppressWarnings("serial")
public class PlantillaNoAutorizadaException extends GeneralCvnException
{
    public PlantillaNoAutorizadaException()
    {
        super("La plantilla no pertany a l'usuari conectat");
    }
}
