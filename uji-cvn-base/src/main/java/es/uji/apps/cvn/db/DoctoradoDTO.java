package es.uji.apps.cvn.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "CVN_VIEW_DIRECCION_TESIS_PCI")
public class DoctoradoDTO implements Serializable
{
   @Id
   private String id;

    @Column(name = "PROGRAMA")
    private String programa;

    @Column(name = "ENTIDAD_DEA")
    private String suficiencia;

    @Temporal(TemporalType.DATE)
    @Column(name = "F_OBTENCION_DEA")
    private Date suficienciaFecha;

    @Column(name = "TESIS_PROPIA")
    private Integer tesisPropia;

    @Column(name = "PERSONA")
    private Long persona;

    private String pais;

    private String region;

    private String ciudad;

    private String entidad;

    @Column(name = "TIPO_ENTIDAD")
    private String tipoEntidad;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_LECTURA")
    private Date titulacionFecha;

    @Temporal(TemporalType.DATE)
    @Column(name = "F_DOCTOR_EUROPEO")
    private Date fechaDoctorEuropeo;

    private String titulo;

    @Column(name = "DIRECTOR_NOMBRE")
    private String directorNombre;

    @Column(name = "DIRECTOR_APELLIDO1")
    private String directorApellido1;

    @Column(name = "DIRECTOR_APELLIDO2")
    private String directorApellido2;

    @Column(name = "CODIRECTOR_NOMBRE")
    private String codirectorNombre;

    @Column(name = "CODIRECTOR_APELLIDO1")
    private String codirectorApellido1;

    @Column(name = "CODIRECTOR_APELLIDO2")
    private String codirectorApellido2;

    private String calificacion;

    @Column(name = "DOCTOR_EUROPEO")
    private String doctorEuropeo;

    @Column(name = "MENCION_CALIDAD")
    private String mencionCalidad;

    @Temporal(TemporalType.DATE)
    @Column(name = "F_MENCION_CALIDAD")
    private Date fechaMencionCalidad;

    @Column(name = "PREMIO_EXTRAORDINARIO")
    private String premio;

    @Temporal(TemporalType.DATE)
    @Column(name = "F_PREMIO_EXTRAORDINARIO")
    private Date premioFecha;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPrograma()
    {
        return programa;
    }

    public void setPrograma(String programa)
    {
        this.programa = programa;
    }

    public String getSuficiencia()
    {
        return suficiencia;
    }

    public void setSuficiencia(String suficiencia)
    {
        this.suficiencia = suficiencia;
    }

    public Date getSuficienciaFecha()
    {
        return suficienciaFecha;
    }

    public void setSuficienciaFecha(Date suficienciaFecha)
    {
        this.suficienciaFecha = suficienciaFecha;
    }

    public Integer getTesisPropia()
    {
        return tesisPropia;
    }

    public void setTesisPropia(Integer tesisPropia)
    {
        this.tesisPropia = tesisPropia;
    }

    public String getPais()
    {
        return pais;
    }

    public void setPais(String pais)
    {
        this.pais = pais;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getCiudad()
    {
        return ciudad;
    }

    public void setCiudad(String ciudad)
    {
        this.ciudad = ciudad;
    }

    public String getEntidad()
    {
        return entidad;
    }

    public void setEntidad(String entidad)
    {
        this.entidad = entidad;
    }

    public String getTipoEntidad()
    {
        return tipoEntidad;
    }

    public void setTipoEntidad(String tipoEntidad)
    {
        this.tipoEntidad = tipoEntidad;
    }

    public Date getTitulacionFecha()
    {
        return titulacionFecha;
    }

    public void setTitulacionFecha(Date titulacionFecha)
    {
        this.titulacionFecha = titulacionFecha;
    }

    public Date getFechaDoctorEuropeo()
    {
        return fechaDoctorEuropeo;
    }

    public void setFechaDoctorEuropeo(Date fechaDoctorEuropeo)
    {
        this.fechaDoctorEuropeo = fechaDoctorEuropeo;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getDirectorNombre()
    {
        return directorNombre;
    }

    public void setDirectorNombre(String directorNombre)
    {
        this.directorNombre = directorNombre;
    }

    public String getDirectorApellido1()
    {
        return directorApellido1;
    }

    public void setDirectorApellido1(String directorApellido1)
    {
        this.directorApellido1 = directorApellido1;
    }

    public String getDirectorApellido2()
    {
        return directorApellido2;
    }

    public void setDirectorApellido2(String directorApellido2)
    {
        this.directorApellido2 = directorApellido2;
    }

    public String getCodirectorNombre()
    {
        return codirectorNombre;
    }

    public void setCodirectorNombre(String codirectorNombre)
    {
        this.codirectorNombre = codirectorNombre;
    }

    public String getCodirectorApellido1()
    {
        return codirectorApellido1;
    }

    public void setCodirectorApellido1(String codirectorApellido1)
    {
        this.codirectorApellido1 = codirectorApellido1;
    }

    public String getCodirectorApellido2()
    {
        return codirectorApellido2;
    }

    public void setCodirectorApellido2(String codirectorApellido2)
    {
        this.codirectorApellido2 = codirectorApellido2;
    }

    public String getCalificacion()
    {
        return calificacion;
    }

    public void setCalificacion(String calificacion)
    {
        this.calificacion = calificacion;
    }

    public String getDoctorEuropeo()
    {
        return doctorEuropeo;
    }

    public void setDoctorEuropeo(String doctorEuropeo)
    {
        this.doctorEuropeo = doctorEuropeo;
    }

    public String getMencionCalidad()
    {
        return mencionCalidad;
    }

    public void setMencionCalidad(String mencionCalidad)
    {
        this.mencionCalidad = mencionCalidad;
    }

    public Date getFechaMencionCalidad()
    {
        return fechaMencionCalidad;
    }

    public void setFechaMencionCalidad(Date fechaMencionCalidad)
    {
        this.fechaMencionCalidad = fechaMencionCalidad;
    }

    public String getPremio()
    {
        return premio;
    }

    public void setPremio(String premio)
    {
        this.premio = premio;
    }

    public Date getPremioFecha()
    {
        return premioFecha;
    }

    public void setPremioFecha(Date premioFecha)
    {
        this.premioFecha = premioFecha;
    }
}
