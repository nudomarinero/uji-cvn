package es.uji.apps.cvn.model.cvn.laboral;

import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.cvn.CVNItem;
import es.uji.apps.cvn.ui.beans.CvnPhoneBean;

public class CvnDetalleSituacionProfesionalAntigua
{
    private CVNItem cvnItem;

    public CvnDetalleSituacionProfesionalAntigua(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    public void addGestionDocente(Boolean b)
    {

        if (b != null)
            cvnItem.addCvnBoolean(cvnItem.buildCvnBoolean("010.020.000.010", b));
    }

    public void addEntidad(String s)
    {
        cvnItem.addCvnEntityBean(cvnItem.buildCvnEntityBean("010.020.000.020", s));
    }

    public void addTipoEntidad(String s)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("010.020.000.040", s));
    }

    public void addCentro(String s)
    {
        cvnItem.addCvnEntityBean(cvnItem.buildCvnEntityBean("010.020.000.060", s));
    }

    public void addServicio(String s)
    {
        cvnItem.addCvnEntityBean(cvnItem.buildCvnEntityBean("010.020.000.080", s));
    }

    public void addCiudad(String s)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("010.020.000.100", s));
    }

    public void addPais(String s)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("010.020.000.110", s));
    }

    public void addRegion(String s)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("010.020.000.120", s));
    }


    private void addTelefono(String code, String numeroTelefono, String type)
    {
        CvnPhoneBean telefono = new CvnPhoneBean();
        telefono.setCode(code);
        telefono.setNumber(numeroTelefono);
        telefono.setType(type);

        cvnItem.addCvnPhoneBean(telefono);
    }

    public void addTelefono(String s)
    {
        addTelefono("010.020.000.140", s, "000");

    }

    public void addFax(String s)
    {
        addTelefono("010.020.000.150", s, "000");
    }

    public void addCorreo(String s)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("010.020.000.160", s));
    }

    public void addCategoria(String s)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("010.020.000.170", s));
    }

    public void addFechaInicio(XMLGregorianCalendar d)
    {
    cvnItem.addCvnDateDayMonthYear(cvnItem.buildCvnDateDayMonthYear("010.020.000.180", d));
    }

    public void addDuracion(String s)
    {
        cvnItem.addCvnDuration(cvnItem.buildCvnDuration("010.020.000.190", s));
    }


    public void addDedicacion(String s)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("010.020.000.220", s));
    }

    public void addUNESCO1(String s)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("010.020.000.230", s));
    }

    public void addUNESCO2(String s)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("010.020.000.240", s));
    }

    public void addUNESCO3(String s)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("010.020.000.250", s));
    }

    public void addDedicacinProfesional(String s)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("010.020.000.260", s));
    }

    public void addPalabrasClave(String s)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("010.020.000.270", s));
    }



}
