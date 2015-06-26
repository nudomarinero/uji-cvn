package es.uji.apps.cvn.model.cvn.docente;


import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class DocenciaImpartida
{
    private CVNItem cvnItem;

    public DocenciaImpartida(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }
    public void addTipo(String s){
        cvnItem.addCvnString(cvnItem.buildCvnString("030.010.000.010", s));
    }
    public void addTitulacion(String i, String n) {
        cvnItem.addCvnTitleBean(cvnItem.buildCvnTitleBean("030.010.000.020", null, n));
    }
    public void addPais(String s){
        cvnItem.addCvnString(cvnItem.buildCvnString("030.010.000.040", s));
    }
    public void addRegion(String s){
        cvnItem.addCvnString(cvnItem.buildCvnString("030.010.000.050", s));
    }
    public void addCiudad(String s){
        cvnItem.addCvnString(cvnItem.buildCvnString("030.010.000.070", s));
    }
    public void addEntidad(String s){
        cvnItem.addCvnEntityBean(cvnItem.buildCvnEntityBean("030.010.000.080", s));
    }
    public void addTipoEntidad(String s){
        cvnItem.addCvnString(cvnItem.buildCvnString("030.010.000.110", s));
    }
    public void addDepartamento(String s){

        cvnItem.addCvnString(cvnItem.buildCvnString("030.010.000.130", s));
    }
    public void addNombreAsignatura(String s){

        cvnItem.addCvnString(cvnItem.buildCvnString("030.010.000.160", s));
    }
    public void addCreditos(Double s){
        cvnItem.addCvnDouble(cvnItem.buildCvnDouble("030.010.000.220", s));
    }
    public void addTipoHorasCreditos(String s){
        cvnItem.addCvnString(cvnItem.buildCvnString("030.010.000.210", s));
    }
    public void addIdioma(String s){
        cvnItem.addCvnString(cvnItem.buildCvnString("030.010.000.230", s));
    }
    public void addNumeroVeces(Double s){
        cvnItem.addCvnDouble(cvnItem.buildCvnDouble("030.010.000.240", s));
    }
    public void addUltimaVez(XMLGregorianCalendar s){
        cvnItem.addCvnDateDayMonthYear(cvnItem.buildCvnDateDayMonthYear("030.010.000.250", s));
    }
}
