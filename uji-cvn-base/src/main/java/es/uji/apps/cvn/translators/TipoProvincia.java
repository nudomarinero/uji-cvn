package es.uji.apps.cvn.translators;

import java.util.Hashtable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class TipoProvincia
{

    private static Hashtable<String, String> diccionario;

    public TipoProvincia()
    {
        diccionario = new Hashtable<String, String>();

        // Código provincia UJI , Código provincia CVN
        diccionario.put("01", "060");
        diccionario.put("02", "240");
        diccionario.put("03", "350");
        diccionario.put("04", "390");
        diccionario.put("33", "040");
        diccionario.put("05", "150");
        diccionario.put("06", "290");
        diccionario.put("08", "310");
        diccionario.put("09", "160");
        diccionario.put("10", "300");
        diccionario.put("11", "400");
        diccionario.put("39", "050");
        diccionario.put("12", "360");
        diccionario.put("13", "250");
        diccionario.put("14", "410");
        diccionario.put("16", "260");
        diccionario.put("17", "320");
        diccionario.put("18", "420");
        diccionario.put("19", "270");
        diccionario.put("20", "070");
        diccionario.put("21", "430");
        diccionario.put("22", "110");
        diccionario.put("07", "380");
        diccionario.put("23", "440");
        diccionario.put("15", "000");
        diccionario.put("26", "100");
        diccionario.put("35", "480");
        diccionario.put("24", "170");
        diccionario.put("25", "330");
        diccionario.put("27", "010");
        diccionario.put("28", "140");
        diccionario.put("29", "450");
        diccionario.put("31", "090");
        diccionario.put("32", "020");
        diccionario.put("34", "180");
        diccionario.put("36", "030");
        diccionario.put("30", "470");
        diccionario.put("37", "190");
        diccionario.put("38", "490");
        diccionario.put("40", "200");
        diccionario.put("41", "460");
        diccionario.put("42", "210");
        diccionario.put("43", "340");
        diccionario.put("44", "120");
        diccionario.put("45", "280");
        diccionario.put("46", "370");
        diccionario.put("47", "220");
        diccionario.put("48", "080");
        diccionario.put("49", "230");
        diccionario.put("50", "130");
    }

    public static String getCodigoTipo(String provinciaCode)
    {
        String codigo = diccionario.get(provinciaCode);

        return codigo;
    }
}
