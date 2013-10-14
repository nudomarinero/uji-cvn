package es.uji.apps.cvn.db;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the CVN_EXT_PERSONAS database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CVN_EXT_PERSONAS")
public class PersonaDTO implements Serializable
{
    @Id
    private Long id;

    private String apellido1;

    private String apellido2;

    @Column(name = "CIUDAD_NACIMIENTO")
    private String ciudadNacimiento;

    private String email;

    private String fax;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_NACIMIENTO")
    private Date fechaNacimiento;

    @Lob()
    private byte[] foto;

    private String genero;

    private String identificacion;

    @Column(name = "INDICE_H")
    private String indiceH;

    @Temporal(TemporalType.DATE)
    @Column(name = "INDICE_H_FECHA")
    private Date indiceHFecha;

    @Column(name = "MIME_TYPE")
    private String mimeType;

    private String nacionalidad;

    private String nombre;

    @Column(name = "PAIS_NACIMIENTO")
    private String paisNacimiento;

    @Column(name = "REGION_NACIMIENTO")
    private String regionNacimiento;

    @Column(name = "TELEFONO_FIJO")
    private String telefonoFijo;

    @Column(name = "TELEFONO_MOVIL")
    private String telefonoMovil;

    @Column(name = "TIPO_IDENTIFICACION")
    private String tipoIdentificacion;

    private String webpage;

    @OneToMany
    @JoinColumn(name = "PER_ID", referencedColumnName = "ID")
    private List<DomicilioDTO> domicilios;

    public PersonaDTO()
    {
    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getApellido1()
    {
        return this.apellido1;
    }

    public void setApellido1(String apellido1)
    {
        this.apellido1 = apellido1;
    }

    public String getApellido2()
    {
        return this.apellido2;
    }

    public void setApellido2(String apellido2)
    {
        this.apellido2 = apellido2;
    }

    public String getCiudadNacimiento()
    {
        return this.ciudadNacimiento;
    }

    public void setCiudadNacimiento(String ciudadNacimiento)
    {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFax()
    {
        return this.fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public Date getFechaNacimiento()
    {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento)
    {
        this.fechaNacimiento = fechaNacimiento;
    }

    public byte[] getFoto()
    {
        return this.foto;
    }

    public void setFoto(byte[] foto)
    {
        this.foto = foto;
    }

    public String getGenero()
    {
        return this.genero;
    }

    public void setGenero(String genero)
    {
        this.genero = genero;
    }

    public String getIdentificacion()
    {
        return this.identificacion;
    }

    public void setIdentificacion(String identificacion)
    {
        this.identificacion = identificacion;
    }

    public String getIndiceH()
    {
        return this.indiceH;
    }

    public void setIndiceH(String indiceH)
    {
        this.indiceH = indiceH;
    }

    public Date getIndiceHFecha()
    {
        return this.indiceHFecha;
    }

    public void setIndiceHFecha(Date indiceHFecha)
    {
        this.indiceHFecha = indiceHFecha;
    }

    public String getMimeType()
    {
        return this.mimeType;
    }

    public void setMimeType(String mimeType)
    {
        this.mimeType = mimeType;
    }

    public String getNacionalidad()
    {
        return this.nacionalidad;
    }

    public void setNacionalidad(String nacionalidad)
    {
        this.nacionalidad = nacionalidad;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getPaisNacimiento()
    {
        return this.paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento)
    {
        this.paisNacimiento = paisNacimiento;
    }

    public String getRegionNacimiento()
    {
        return this.regionNacimiento;
    }

    public void setRegionNacimiento(String regionNacimiento)
    {
        this.regionNacimiento = regionNacimiento;
    }

    public String getTelefonoFijo()
    {
        return this.telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo)
    {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil()
    {
        return this.telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil)
    {
        this.telefonoMovil = telefonoMovil;
    }

    public String getTipoIdentificacion()
    {
        return this.tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion)
    {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getWebpage()
    {
        return this.webpage;
    }

    public void setWebpage(String webpage)
    {
        this.webpage = webpage;
    }

    public List<DomicilioDTO> getDomicilios()
    {
        return domicilios;
    }

    public void setDomicilios(List<DomicilioDTO> domicilios)
    {
        this.domicilios = domicilios;
    }

}