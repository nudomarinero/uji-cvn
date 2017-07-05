package es.uji.apps.cvn.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
// @Table(name = "CVN_VW_PER_DOCENCIA_IMPARTIDA")
@Table(name = "CVN_VW_PER_DOCENCIA_IMPART_M")
public class DocenciaImpartidaDTO implements Serializable
{
    @Id
    @Column(name = "PER_ID")
    private Long perId;

    @Column(name = "TIPO")
    private String tipo;

    @Column(name = "TITULACION")
    private String titulacion;
    @Id
    @Column(name = "TITULACION_TEXTO")
    private String titulacionTexto;

    @Column(name = "TITULACION_TEXTO_ES")
    private String titulacionTextoEs;

    @Column(name = "TITULACION_TEXTO_UK")
    private String titulacionTextoUk;

    @Column(name = "PAIS")
    private String pais;

    @Column(name = "REGION")
    private String region;
    @Id
    @Column(name = "CIUDAD")
    private String ciudad;

    @Column(name = "ENTIDAD")
    private String entidad;

    @Column(name = "TIPO_ENTIDAD")
    private String tipoEntidad;
    @Id
    @Column(name = "DEPARTAMENTO")
    private String departamento;

    @Column(name = "DEPARTAMENTO_ES")
    private String departamentoEs;

    @Id
    @Column(name = "NOMBRE_ASIGNATURA")
    private String nombreAsignatura;

    @Column(name = "NOMBRE_ASIGNATURA_ES")
    private String nombreAsignaturaEs;

    @Column(name = "NOMBRE_ASIGNATURA_UK")
    private String nombreAsignaturaUk;

    @Column(name = "TIPO_HORAS_CREDITOS")
    private String tipoHorasCreditos;

    @Column(name = "CREDITOS")
    private Double creditos;

    @Id
    @Column(name = "IDIOMA")
    private String idioma;

    @Column(name = "N_VECES")
    private Double numeroVeces;

    @Temporal(TemporalType.DATE)
    @Column(name = "ULTIMA_VEZ")
    private Date ultimaVez;

    public Long getPerId()
    {
        return perId;
    }

    public void setPerId(Long perId)
    {
        this.perId = perId;
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public String getTitulacion()
    {
        return titulacion;
    }

    public void setTitulacion(String titulacion)
    {
        this.titulacion = titulacion;
    }

    public String getTitulacionTexto()
    {
        return titulacionTexto;
    }

    public void setTitulacionTexto(String titulacionTexto)
    {
        this.titulacionTexto = titulacionTexto;
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

    public String getDepartamento()
    {
        return departamento;
    }

    public void setDepartamento(String departamento)
    {
        this.departamento = departamento;
    }

    public String getNombreAsignatura()
    {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura)
    {
        this.nombreAsignatura = nombreAsignatura;
    }

    public String getTipoHorasCreditos()
    {
        return tipoHorasCreditos;
    }

    public void setTipoHorasCreditos(String tipoHorasCreditos)
    {
        this.tipoHorasCreditos = tipoHorasCreditos;
    }

    public Double getCreditos()
    {
        return creditos;
    }

    public void setCreditos(Double creditos)
    {
        this.creditos = creditos;
    }

    public String getIdioma()
    {
        return idioma;
    }

    public void setIdioma(String idioma)
    {
        this.idioma = idioma;
    }

    public Double getNumeroVeces()
    {
        return numeroVeces;
    }

    public void setNumeroVeces(Double numeroVeces)
    {
        this.numeroVeces = numeroVeces;
    }

    public Date getUltimaVez()
    {
        return ultimaVez;
    }

    public void setUltimaVez(Date ultimaVez)
    {
        this.ultimaVez = ultimaVez;
    }

    public String getTitulacionTextoEs()
    {
        return titulacionTextoEs;
    }

    public void setTitulacionTextoEs(String titulacionTextoEs)
    {
        this.titulacionTextoEs = titulacionTextoEs;
    }

    public String getTitulacionTextoUk()
    {
        return titulacionTextoUk;
    }

    public void setTitulacionTextoUk(String titulacionTextoUk)
    {
        this.titulacionTextoUk = titulacionTextoUk;
    }

    public String getDepartamentoEs()
    {
        return departamentoEs;
    }

    public void setDepartamentoEs(String departamentoEs)
    {
        this.departamentoEs = departamentoEs;
    }

    public String getNombreAsignaturaEs()
    {
        return nombreAsignaturaEs;
    }

    public void setNombreAsignaturaEs(String nombreAsignaturaEs)
    {
        this.nombreAsignaturaEs = nombreAsignaturaEs;
    }

    public String getNombreAsignaturaUk()
    {
        return nombreAsignaturaUk;
    }

    public void setNombreAsignaturaUk(String nombreAsignaturaUk)
    {
        this.nombreAsignaturaUk = nombreAsignaturaUk;
    }
}
