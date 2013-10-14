package es.uji.apps.cvn.client.exceptions;

@SuppressWarnings("serial")
public class PlantillaNoValidaException extends GeneralCvnException
{   
    public PlantillaNoValidaException()
    {
        super("La plantilla generada no és vàlida");
    }
}
