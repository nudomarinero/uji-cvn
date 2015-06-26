package es.uji.apps.cvn.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "CVN_VIEW_DIRECCION_TESIS")
public class DirrecionTesisDTO implements Serializable
{
   @Id
   private String id;

    @Column(name = "TIPO_ID")
    private String tipoId;

    @Column(name = "TIPO_TXT")
    private String tipoTexto;

    @Column(name = "Persona")
    private Long persona;

    private String titulo;

    private String pais;

    private String region;

    private String ciudad;

    private String entidad;

    @Column(name = "TIPO_ENTIDAD")
    private String tipoEntidad;

    @Column(name = "ALUMNO_NOMBRE")
    private String alumnoNombre;

    @Column(name = "ALUMNO_APELLIDO1")
    private String alumnoApellido1;

    @Column(name = "ALUMNO_APELLIDO2")
    private String alumnoApellido2;

    @Column(name = "CODIRECTOR_NOMBRE")
    private String codirectorNombre;

    @Column(name = "CODIRECTOR_APELLIDO1")
    private String codirectorApellido1;

    @Column(name = "CODIRECTOR_APELLIDO2")
    private String codirectorApellido2;

    @Column(name = "FECHA_LECTURA")
    private Date fechaLectura;

    private String calificacion;

    @Column(name = "DOCTOR_EUROPEO")
    private String doctorEuropeo;

    @Column(name = "F_DOCTOR_EUROPEO")
    private Date fechaDoctorEuropeo;

    @Column(name = "MENCION_CALIDAD")
    private String mencionCalidad;



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

    public Date getFechaLectura() {
        return fechaLectura;
    }

    public void setFechaLectura(Date fechaLectura) {
        this.fechaLectura = fechaLectura;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getDoctorEuropeo() {
        return doctorEuropeo;
    }

    public void setDoctorEuropeo(boolean doctorEuropeo) {
        doctorEuropeo = doctorEuropeo;
    }

    public Date getFechaDoctorEuropeo() {
        return fechaDoctorEuropeo;
    }

    public void setFechaDoctorEuropeo(Date fechaDoctorEuropeo) {
        this.fechaDoctorEuropeo = fechaDoctorEuropeo;
    }

    public String getMencionCalidad() {
        return mencionCalidad;
    }

    public void setMencionCalidad(boolean mencionCalidad) {
        mencionCalidad = mencionCalidad;
    }

    public String getAlumnoNombre() {
        return alumnoNombre;
    }

    public void setAlumnoNombre(String alumnoNombre) {
        this.alumnoNombre = alumnoNombre;
    }

    public String getCodirectorNombre() {
        return codirectorNombre;
    }

    public String getCodirectorApellido1() {
        return codirectorApellido1;
    }

    public String getCodirectorApellido2() {
        return codirectorApellido2;
    }


    public String getAlumnoApellido1() {
        return alumnoApellido1;
    }

    public String getAlumnoApellido2() {
        return alumnoApellido2;
    }
}
