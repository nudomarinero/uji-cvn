package es.uji.apps.cvn.client.exceptions;

import javax.xml.ws.WebFault;

/**
 * Excepcion general producida al exportar un CVN-XML.
 */
@WebFault(name = "GenerarPDFWSException", targetNamespace = "http://ws.cvnet.fecyt.es")
public class GeneradorPDFWSException extends Exception
{
    /**
     * Serial.
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param mensaje
     *            Mensaje que define la excepcion
     */
    public GeneradorPDFWSException(final String mensaje)
    {
        super(mensaje);
    }

    /**
     * @param cause
     *            Excepcion padre
     */
    public GeneradorPDFWSException(final Throwable cause)
    {
        super(cause);
    }
}
