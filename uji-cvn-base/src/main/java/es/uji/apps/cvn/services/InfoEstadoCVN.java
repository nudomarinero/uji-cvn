package es.uji.apps.cvn.services;

import java.util.Hashtable;

public enum InfoEstadoCVN
{
    SOLICITADO("100"), PENDIENTE_DATOS_USER("0201"), PENDIENTE_DATOS_ADMIN("1201"), PENDIENTE_PREP_USER(
            "0202"), PENDIENTE_PREP_ADMIN("1202"), PENDIENTE_SOL_USER("0203"), PENDIENTE_SOL_ADMIN(
            "1203"), FINALIZADO_USER("0300"), FINALIZADO_ADMIN("1300"), ERROR_DATOS_USER("0401"), ERROR_DATOS_ADMIN(
            "1401"), ERROR_PDF_USER("0402"), ERROR_PDF_ADMIN("1402");

    private String estado;
    private static Hashtable<String, String> diccionario;

    static
    {
        diccionario = new Hashtable<String, String>();
        diccionario.put("100_es", "Iniciada su solicitud");
        diccionario.put("100_ca", "Iniciada la seva sol·licitud");
        diccionario.put("100_en", "Initiated request");

        diccionario.put("0201_es", "Obteniendo los datos de su currículum");
        diccionario.put("0201_ca", "Obtenint les dades del seu currículum");
        diccionario.put("0201_en", "Getting your curriculum data");
        diccionario.put("1201_es", "Obteniendo los datos del curriculum para el usuario ");
        diccionario.put("1201_ca", "Obtenint les dades del curriculum per a l'usuari ");
        diccionario.put("1201_en", "Getting the curriculum data for the user ");

        diccionario.put("0202_es", "Preparando su petición");
        diccionario.put("0202_ca", "Preparant la seva petició");
        diccionario.put("0202_en", "Preparing your request");
        diccionario.put("1202_es", "Preparando la petición para el usuario ");
        diccionario.put("1202_ca", "Preparant la petició per a l'usuari ");
        diccionario.put("1202_en", "Preparing the request for the user ");

        diccionario.put("0203_es", "Solicitando su PDF");
        diccionario.put("0203_ca", "Sol·licitant el seu PDF");
        diccionario.put("0203_en", "Requesting your PDF");
        diccionario.put("1203_es", "Solicitando el PDF del usuario ");
        diccionario.put("1203_ca", "Sol·licitant el PDF per al'usuari ");
        diccionario.put("1203_en", "Requesting the PDF for the user ");

        diccionario.put("0300_es", "Ya puede descargar su CVN");
        diccionario.put("0300_ca", "Ja pot descarregar el seu CVN");
        diccionario.put("0300_en", "You can download your CVN");
        diccionario.put("1300_es", "Ya puede descargar el CVN del usuario ");
        diccionario.put("1300_ca", "Ja pot descarregar el CVN de l'usuari ");
        diccionario.put("1300_en", "You can download your CVN for the user ");

        diccionario.put("0401_es", "Error al obtener sus datos");
        diccionario.put("0401_ca", "Error en obtenir les seves dades");
        diccionario.put("0401_en", "Failed to get your data");
        diccionario.put("1401_es", "Error al obtener los datos del usuario ");
        diccionario.put("1401_ca", "Error en obtenir les dades de l'usuari ");
        diccionario.put("1401_en", "Failed to ge the data for the user ");

        diccionario.put("0402_es", "Error al descargar su PDF");
        diccionario.put("0402_ca", "Error en descarregar el seu PDF");
        diccionario.put("0402_en", "Failed to download your PDF");
        diccionario.put("1402_es", "Error al descargar el PDF del usuario ");
        diccionario.put("1402_ca", "Error en descarregar el PDF de l'usuari ");
        diccionario.put("1402_en", "Failed to download the PDF for the user ");
    }

    private InfoEstadoCVN(String estado)
    {
        this.estado = estado;
    }

    public String getEstado()
    {
        return estado;
    }

    public static String getInfoEstado(String estado, String idioma)
    {
        String info = diccionario.get(estado + "_" + idioma);

        if (info == null)
        {
            info = "";
        }
        return info;
    }
}
