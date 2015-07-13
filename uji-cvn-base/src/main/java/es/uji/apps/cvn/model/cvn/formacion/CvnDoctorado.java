package es.uji.apps.cvn.model.cvn.formacion;

import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class CvnDoctorado
{
    private CVNItem cvnItem;

    public CvnDoctorado(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    public void addPrograma(String identificacion, String nombre)
    {
        cvnItem.addCvnTitleBean(cvnItem.buildCvnTitleBean("020.010.020.010", identificacion, nombre));
    }

    public void addSuficiencia(String suficiencia)
    {
        cvnItem.addCvnEntityBean(cvnItem.buildCvnEntityBean("020.010.020.040", suficiencia));
    }

    public void addSuficienciaFecha(XMLGregorianCalendar suficienciaFecha)
    {
        cvnItem.addCvnDateDayMonthYear(cvnItem.buildCvnDateDayMonthYear("020.010.020.050", suficienciaFecha));
    }

    public void addPais(String pais)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("020.010.020.060", pais));
    }

    public void addRegion(String region)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("020.010.020.070", region));
    }

    public void addCiudad(String ciudad)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("020.010.020.090", ciudad));
    }

    public void addEntidad(String entidad)
    {
        cvnItem.addCvnEntityBean(cvnItem.buildCvnEntityBean("020.010.020.100", entidad));
    }

    public void addTipoEntidad(String tipo)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("020.010.020.120", tipo));
    }

//    public void addTipoEntidadOtros(String tipo)
//    {
//        cvnItem.addCvnString(cvnItem.buildCvnString("020.010.020.130", tipo));
//    }

    public void addTitulacionFecha(XMLGregorianCalendar titulacionFecha)
    {
        cvnItem.addCvnDateDayMonthYear(cvnItem.buildCvnDateDayMonthYear("020.010.020.140", titulacionFecha));
    }

    public void addDoctoradoFecha(XMLGregorianCalendar doctoradoFecha)
    {
        cvnItem.addCvnDateDayMonthYear(cvnItem.buildCvnDateDayMonthYear("020.010.020.150", doctoradoFecha));
    }

    public void addTitulo(String s)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("020.010.020.160", s));
    }

    public void addDirector(String n, String a1, String a2)
    {
        cvnItem.addCvnAuthorBean(cvnItem.buildCvnAuthorBean("020.010.020.170", n, a1, a2));
    }

    public void addCoDirector(String n, String a1, String a2)
    {
        cvnItem.addCvnAuthorBean(cvnItem.buildCvnAuthorBean("020.010.020.180", n, a1, a2));
    }

    public void addCalificacion(String calificacion)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("020.010.020.190", calificacion));
    }

    public void addDoctorEuropeo(Boolean b)
    {

        if (b != null)
            cvnItem.addCvnBoolean(cvnItem.buildCvnBoolean("020.010.020.200", b));
    }

    public void addMencionCalidad(Boolean b)
    {

        if (b != null)
            cvnItem.addCvnBoolean(cvnItem.buildCvnBoolean("020.010.020.210", b));
    }

    public void addPremio(Boolean b)
    {

        if (b != null)
            cvnItem.addCvnBoolean(cvnItem.buildCvnBoolean("020.010.020.220", b));
    }

    public void addPremioFecha(XMLGregorianCalendar premioFecha)
    {
        cvnItem.addCvnDateDayMonthYear(cvnItem.buildCvnDateDayMonthYear("020.010.020.230", premioFecha));
    }

//    public void addHomologado(Boolean b)
//    {
//
//        if (b != null)
//            cvnItem.addCvnBoolean(cvnItem.buildCvnBoolean("020.010.020.240", b));
//    }
//
//    public void addHomologadoFecha(XMLGregorianCalendar homologadoFecha)
//    {
//        cvnItem.addCvnDateDayMonthYear(cvnItem.buildCvnDateDayMonthYear("020.010.020.250", homologadoFecha));
//    }
}
