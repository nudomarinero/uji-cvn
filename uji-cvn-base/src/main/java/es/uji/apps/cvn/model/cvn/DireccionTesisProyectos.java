package es.uji.apps.cvn.model.cvn;

import javax.xml.datatype.XMLGregorianCalendar;

public class DireccionTesisProyectos extends CVNItem
{
    private Entidad entidad;

    public DireccionTesisProyectos()
    {
        this.code = "030.040.000.000";
    }

    public Entidad getEntidad()
    {
        if (entidad == null)
        {
            entidad = new EntidadTesisProyectos(this);
        }
        return entidad;
    }

    public void setEntidad(Entidad entidad)
    {
        this.entidad = entidad;
    }

    public void addTipo(String tipo)
    {
        this.addCvnString(this.buildCvnString("030.040.000.010", tipo));
    }

    public void addTipoOtros(String tipoOtros)
    {
        this.addCvnString(this.buildCvnString("030.040.000.020", tipoOtros));
    }

    public void addTitulo(String titulo)
    {
        this.addCvnString(this.buildCvnString("030.040.000.030", titulo));
    }

    public void addCodirector(String nombre, String apellido1, String apellido2)
    {
        this.addCvnAuthorBean(this.buildCvnAuthorBean("030.040.000.180", nombre, apellido1,
                apellido2));
    }

    public void addAlumno(String nombre, String apellido1, String apellido2)
    {
        this.addCvnAuthorBean(this.buildCvnAuthorBean("030.040.000.120", nombre, apellido1,
                apellido2));
    }

    public void addPalabarasClaveTitulo(String palabrasClaveTitulo)
    {
        this.addCvnString(this.buildCvnString("030.040.000.130", palabrasClaveTitulo));
    }

    public void addFechaLectura(XMLGregorianCalendar fechaLectura)
    {
        this.addCvnDateDayMonthYear(this.buildCvnDateDayMonthYear("030.040.000.140", fechaLectura));
    }

    public void addCalificacion(String calificacion)
    {
        this.addCvnString(this.buildCvnString("030.040.000.150", calificacion));
    }

    public void addFechaMencion(XMLGregorianCalendar fechaMencion)
    {
        this.addCvnDateDayMonthYear(this.buildCvnDateDayMonthYear("030.040.000.160", fechaMencion));
    }

    public void addMencionCalidad(Boolean mencionCalidad)
    {
        this.addCvnBoolean(this.buildCvnBoolean("030.040.000.170", mencionCalidad));
    }

    public void addDoctoradoEuropeo(Boolean doctoradoEuropeo)
    {
        this.addCvnBoolean(this.buildCvnBoolean("030.040.000.190", doctoradoEuropeo));
    }

    public void addFechaMencionCalidad(XMLGregorianCalendar fechaMencionCalidad)
    {
        this.addCvnDateDayMonthYear(this.buildCvnDateDayMonthYear("030.040.000.200",
                fechaMencionCalidad));
    }
    
}
