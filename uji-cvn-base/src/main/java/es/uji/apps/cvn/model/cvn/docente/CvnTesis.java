package es.uji.apps.cvn.model.cvn.docente;


import javax.xml.datatype.XMLGregorianCalendar;
import es.uji.apps.cvn.model.cvn.CVNItem;

public class CvnTesis {
    private CVNItem cvnItem;

    public CvnTesis(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    public void addTipo(String s){
        cvnItem.addCvnString(cvnItem.buildCvnString("030.040.000.010", s));
    }

    public void addTitulo(String s) {
        cvnItem.addCvnString(cvnItem.buildCvnString("030.040.000.030", s));
    }

    public void addCodirector(String n, String a1, String a2){
        cvnItem.addCvnAuthorBean( cvnItem.buildCvnAuthorBean("030.040.000.180",n,a1,a2));
    }

    public void addPaisLectura(String s){
        cvnItem.addCvnString(cvnItem.buildCvnString("030.040.000.040", s));
    }

    public void addComunidadAutonoma(String s){
        cvnItem.addCvnString(cvnItem.buildCvnString("030.040.000.050", s));
    }

    public void addCiudadDireccion(String s){
        cvnItem.addCvnString(cvnItem.buildCvnString("030.040.000.070", s));
    }

    public void addEntidad(String s){
        cvnItem.addCvnEntityBean(cvnItem.buildCvnEntityBean("030.040.000.080", s));
    }
    public void addTipoEntidad(String s){
        cvnItem.addCvnString(cvnItem.buildCvnString("030.040.000.100", s));
    }
    public void addAlumno(String n, String a1, String a2){

        cvnItem.addCvnAuthorBean( cvnItem.buildCvnAuthorBean("030.040.000.120",n,a1,a2));
    }
    public void addFechaLectura(XMLGregorianCalendar d){

        cvnItem.addCvnDateDayMonthYear(cvnItem.buildCvnDateDayMonthYear("030.040.000.140", d));


    }
    public void addCalificacion(String s){
        cvnItem.addCvnString(cvnItem.buildCvnString("030.040.000.150", s));
    }

    public void addFechaDoctorEuropeo(XMLGregorianCalendar d){
        cvnItem.addCvnDateDayMonthYear(cvnItem.buildCvnDateDayMonthYear("030.040.000.160", d));
    }
    public void addDoctorEuropeo(Boolean b){

        if (b!=null)
            cvnItem.addCvnBoolean(cvnItem.buildCvnBoolean("030.040.000.190", b));
    }
    public void addMencionCalidad(Boolean b) {

        if (b!=null)
            cvnItem.addCvnBoolean(cvnItem.buildCvnBoolean("030.040.000.170", b));
    }
}
