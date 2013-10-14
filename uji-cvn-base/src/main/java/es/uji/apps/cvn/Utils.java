package es.uji.apps.cvn;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Utils
{
    public static String exceptionStackTraceToString(Exception e)
    {
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));

        return writer.toString();
    }

    public static String limpiaCadena(String cadena)
    {
        if (cadena != null)
        {
            cadena = cadena.replaceAll("\\p{C}", "");
        }

        return cadena;
    }
}
