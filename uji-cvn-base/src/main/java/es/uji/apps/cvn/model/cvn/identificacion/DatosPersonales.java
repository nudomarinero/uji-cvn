package es.uji.apps.cvn.model.cvn.identificacion;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class DatosPersonales extends CVNItem
{
    private Contacto contacto;

    private Identificacion identificacion;

    public DatosPersonales()
    {
        this.code = "000.010.000.000";
    }

    public Contacto getContacto()
    {
        if (contacto == null)
        {
            contacto = new Contacto(this);
        }
        return contacto;
    }

    public void setContacto(Contacto contacto)
    {
        this.contacto = contacto;
    }

    public Identificacion getIdentificacion()
    {
        if (identificacion == null)
        {
            this.identificacion = new Identificacion(this);
        }
        return identificacion;
    }

    public void setIdentificacion(Identificacion identificacion)
    {
        this.identificacion = identificacion;
    }
}
