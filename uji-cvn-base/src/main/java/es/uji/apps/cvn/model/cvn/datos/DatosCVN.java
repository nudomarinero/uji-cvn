package es.uji.apps.cvn.model.cvn.datos;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class DatosCVN   extends CVNItem
{

    private Idioma idioma;

    public Idioma getIdioma()
    {
        if (idioma == null)
        {
            idioma = new Idioma(this);
        }
        return idioma;
    }


}
