package es.uji.apps.cvn.model.cvn.identificacion;

import es.uji.apps.cvn.model.cvn.CVNItem;
import es.uji.apps.cvn.ui.beans.CvnPhoneBean;

public class Contacto
{
    private CVNItem cvnItem;

    public Contacto(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    private void addTelefono(String code, String numeroTelefono, String type)
    {
        CvnPhoneBean telefono = new CvnPhoneBean();
        telefono.setCode(code);
        telefono.setNumber(numeroTelefono);
        telefono.setType(type);

        cvnItem.addCvnPhoneBean(telefono);
    }
    
    public void addDireccion(String direccion)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("000.010.000.140", direccion));
    }
    
    public void addDireccionAux(String direccionAux)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("000.010.000.150", direccionAux));
    }
    
    public void addCodigoPostal(String codigoPostal)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("000.010.000.160", codigoPostal));
    }
    
    public void addCiudad(String ciudad)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("000.010.000.170", ciudad));
    }
    
    public void addPais(String pais)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("000.010.000.180", pais));
    }
    
    public void addRegion(String region)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("000.010.000.190", region));
    }
    
    public void addProvincia(String provincia)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("000.010.000.200", provincia));
    }

    public void addTelefonoFijo(String numeroTelefono)
    {
        addTelefono("000.010.000.210", numeroTelefono, "000");
    }

    public void addFax(String numeroFax)
    {
        addTelefono("000.010.000.220", numeroFax, "000");
    }

    public void addEmail(String email)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("000.010.000.230", email));
    }

    public void addTelefonoMovil(String telefonoMovil)
    {
        addTelefono("000.010.000.240", telefonoMovil, "010");
    }

    public void addPaginaWeb(String webpage)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("000.010.000.250", webpage));
    }
}
