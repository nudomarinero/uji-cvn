package es.uji.apps.cvn.client.exceptions;

import java.util.Date;

@SuppressWarnings("serial")
public class CvnNoGeneradoException extends GeneralCvnException
{
    public CvnNoGeneradoException(String mensaje)
    {
        super(mensaje);
    }

    public CvnNoGeneradoException(String mensaje, Date fecha)
    {

    }
}
