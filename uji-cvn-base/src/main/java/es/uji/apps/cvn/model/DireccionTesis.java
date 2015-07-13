package es.uji.apps.cvn.model;


import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DireccionTesis
{

    private String id;

    private String tipoId;

    private String tipoTexto;

    private Long persona;

    private String titulo;

    private String codirectorNombre;

    private String codirectorApellido1;

    private String codirectorApellido2;

    private String pais;

    private String region;

    private String ciudad;

    private String entidad;

    private String tipoEntidad;

    private String alumnoNombre;

    private String alumnoApellido1;

    private String alumnoApellido2;

    private XMLGregorianCalendar fechaLectura;

    private String calificacion;

    private boolean isDoctorEuropeo;

    private XMLGregorianCalendar fechaDoctorEuropeo;

    private boolean isMencionCalidad;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public String getTipoTexto() {
        return tipoTexto;
    }

    public void setTipoTexto(String tipoTexto) {
        this.tipoTexto = tipoTexto;
    }

    public Long getPersona() {
        return persona;
    }

    public void setPersona(Long persona) {
        this.persona = persona;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }



    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(String tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }



    public XMLGregorianCalendar getFechaLectura() {
        return fechaLectura;
    }

    public void setFechaLectura(Date fechaLectura) {
        if (fechaLectura != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(fechaLectura);
            try
            {
                this.fechaLectura = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            }
            catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public boolean isDoctorEuropeo() {
        return isDoctorEuropeo;
    }

    public void setDoctorEuropeo(boolean doctorEuropeo) {
        isDoctorEuropeo = doctorEuropeo;
    }

    public XMLGregorianCalendar getFechaDoctorEuropeo() {
        return fechaDoctorEuropeo;
    }

    public void setFechaDoctorEuropeo(Date fechaDoctorEuropeo) {
        if (fechaDoctorEuropeo != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(fechaDoctorEuropeo);
            try
            {
                this.fechaDoctorEuropeo = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            }
            catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public boolean isMencionCalidad() {
        return isMencionCalidad;
    }

    public void setMencionCalidad(boolean mencionCalidad) {
        isMencionCalidad = mencionCalidad;
    }

    public String getAlumnoNombre() {
        return alumnoNombre;
    }

    public String getAlumnoApellido1() {
        return alumnoApellido1;
    }

    public String getAlumnoApellido2() {
        return alumnoApellido2;
    }

    public String getCodirectorApellido2() {
        return codirectorApellido2;
    }

    public String getCodirectorApellido1() {
        return codirectorApellido1;
    }

    public String getCodirectorNombre() {
        return codirectorNombre;
    }

    public void setAlumnoApellido2(String alumnoApellido2) {
        this.alumnoApellido2 = alumnoApellido2;
    }

    public void setAlumnoApellido1(String alumnoApellido1) {
        this.alumnoApellido1 = alumnoApellido1;
    }

    public void setAlumnoNombre(String alumnoNombre) {
        this.alumnoNombre = alumnoNombre;
    }

    public void setCodirectorApellido2(String codirectorApellido2) {
        this.codirectorApellido2 = codirectorApellido2;
    }

    public void setCodirectorApellido1(String codirectorApellido1) {
        this.codirectorApellido1 = codirectorApellido1;
    }

    public void setCodirectorNombre(String codirectorNombre) {
        this.codirectorNombre = codirectorNombre;
    }
}
