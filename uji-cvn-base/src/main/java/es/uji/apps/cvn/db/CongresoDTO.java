package es.uji.apps.cvn.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the CVN_VIEW_PROD_CONGRESOS database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CVN_VIEW_PROD_CONGRESOS_PCI")
public class CongresoDTO implements Serializable
{
    private String ambito;

    private Long caracter;

    @Column(name = "CIUDAD_CONGRESO")
    private String ciudadCongreso;

    @Column(name = "DEP_LEGAL_PUB")
    private String depLegalPub;

    @Column(name = "EDITORIAL_PUB")
    private String editorialPub;

    @Column(name = "ENTIDAD_ORGANIZADORA")
    private String entidadOrganizadora;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_CONGRESO")
    private Date fechaCongreso;

    @Column(name = "ANO_CONGRESO")
    private Long anoCongreso;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_FIN")
    private Date fechaFin;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_PUB")
    private Date fechaPublicacion;

    @Id
    private Long id;

    @Transient
    //@Column(name = "IS_ACTA")
    private Boolean isActa;

    @Column(name = "IS_PUB_EVALUADA")
    private Boolean isPubEvaluada;

    @Column(name = "ISBN_PUB")
    private String isbnPub;

    @Column(name = "NOMBRE_CONGRESO")
    private String nombreCongreso;

    @Column(name = "NOMBRE_PUBLICACION")
    private String nombrePublicacion;

    @Column(name = "PAGINA_INICIO")
    private Integer paginaInicio;

    @Column(name = "PAGINA_FIN")
    private Integer paginaFin;

    @Column(name = "PAIS_CONGRESO")
    private String paisCongreso;

    @Column(name = "PAIS_PUB")
    private String paisPub;

    @Column(name = "REGION_CONGRESO")
    private String regionCongreso;

    @Column(name = "REGION_PUB")
    private String regionPub;

    @Column(name = "TIPO_ACCESO")
    private String tipoAcceso;

    @Column(name = "TIPO_EVENTO")
    private String tipoEvento;

    @Column(name = "TIPO_PUBLICACION")
    private String tipoPublicacion;

    private String titulo;

    @Column(name = "TITULO_PUBLICACION")
    private String tituloPublicacion;

    @Column(name = "VOLUMEN_PUB")
    private String volumenPub;

    @Column(name = "WEB_PUB")
    private String webPub;

    // ESpecíficos de congreso de caracter docente

    private String objetivos;

    private String destinatarios;

    private String idioma;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_PRESENTACION")
    private Date fechaPresentacion;

    private String horas;

    public CongresoDTO()
    {
    }

    public String getAmbito()
    {
        return this.ambito;
    }

    public void setAmbito(String ambito)
    {
        this.ambito = ambito;
    }

    public Long getCaracter()
    {
        return this.caracter;
    }

    public void setCaracter(Long caracter)
    {
        this.caracter = caracter;
    }

    public String getCiudadCongreso()
    {
        return this.ciudadCongreso;
    }

    public void setCiudadCongreso(String ciudadCongreso)
    {
        this.ciudadCongreso = ciudadCongreso;
    }

    public String getDepLegalPub()
    {
        return this.depLegalPub;
    }

    public void setDepLegalPub(String depLegalPub)
    {
        this.depLegalPub = depLegalPub;
    }

    public String getEditorialPub()
    {
        return this.editorialPub;
    }

    public void setEditorialPub(String editorialPub)
    {
        this.editorialPub = editorialPub;
    }

    public String getEntidadOrganizadora()
    {
        return this.entidadOrganizadora;
    }

    public void setEntidadOrganizadora(String entidadOrganizadora)
    {
        this.entidadOrganizadora = entidadOrganizadora;
    }

    public Date getFechaCongreso()
    {
        return this.fechaCongreso;
    }

    public void setFechaCongreso(Date fechaCongreso)
    {
        this.fechaCongreso = fechaCongreso;
    }

    public Long getAnoCongreso()
    {
        return this.anoCongreso;
    }

    public void setAnoCongreso(Long anoCongreso)
    {
        this.anoCongreso = anoCongreso;
    }

    public Date getFechaFin()
    {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin)
    {
        this.fechaFin = fechaFin;
    }

    public Date getFechaPublicacion()
    {
        return this.fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion)
    {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Boolean getIsActa()
    {
        return this.isActa;
    }

    public void setIsActa(Boolean isActa)
    {
        this.isActa = isActa;
    }

    public Boolean getIsPubEvaluada()
    {
        return this.isPubEvaluada;
    }

    public void setIsPubEvaluada(Boolean isPubEvaluada)
    {
        this.isPubEvaluada = isPubEvaluada;
    }

    public String getIsbnPub()
    {
        return this.isbnPub;
    }

    public void setIsbnPub(String isbnPub)
    {
        this.isbnPub = isbnPub;
    }

    public String getNombreCongreso()
    {
        return this.nombreCongreso;
    }

    public void setNombreCongreso(String nombreCongreso)
    {
        this.nombreCongreso = nombreCongreso;
    }

    public String getNombrePublicacion()
    {
        return this.nombrePublicacion;
    }

    public void setNombrePublicacion(String nombrePublicacion)
    {
        this.nombrePublicacion = nombrePublicacion;
    }

    public Integer getPaginaInicio()
    {
        return paginaInicio;
    }

    public void setPaginaInicio(Integer paginaInicio)
    {
        this.paginaInicio = paginaInicio;
    }

    public Integer getPaginaFin()
    {
        return paginaFin;
    }

    public void setPaginaFin(Integer paginaFin)
    {
        this.paginaFin = paginaFin;
    }

    public String getPaisCongreso()
    {
        return this.paisCongreso;
    }

    public void setPaisCongreso(String paisCongreso)
    {
        this.paisCongreso = paisCongreso;
    }

    public String getPaisPub()
    {
        return this.paisPub;
    }

    public void setPaisPub(String paisPub)
    {
        this.paisPub = paisPub;
    }

    public String getRegionCongreso()
    {
        return this.regionCongreso;
    }

    public void setRegionCongreso(String regionCongreso)
    {
        this.regionCongreso = regionCongreso;
    }

    public String getRegionPub()
    {
        return this.regionPub;
    }

    public void setRegionPub(String regionPub)
    {
        this.regionPub = regionPub;
    }

    public String getTipoAcceso()
    {
        return this.tipoAcceso;
    }

    public void setTipoAcceso(String tipoAcceso)
    {
        this.tipoAcceso = tipoAcceso;
    }

    public String getTipoEvento()
    {
        return this.tipoEvento;
    }

    public void setTipoEvento(String tipoEvento)
    {
        this.tipoEvento = tipoEvento;
    }

    public String getTipoPublicacion()
    {
        return this.tipoPublicacion;
    }

    public void setTipoPublicacion(String tipoPublicacion)
    {
        this.tipoPublicacion = tipoPublicacion;
    }

    public String getTitulo()
    {
        return this.titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getTituloPublicacion()
    {
        return this.tituloPublicacion;
    }

    public void setTituloPublicacion(String tituloPublicacion)
    {
        this.tituloPublicacion = tituloPublicacion;
    }

    public String getVolumenPub()
    {
        return this.volumenPub;
    }

    public void setVolumenPub(String volumenPub)
    {
        this.volumenPub = volumenPub;
    }

    public String getWebPub()
    {
        return this.webPub;
    }

    public void setWebPub(String webPub)
    {
        this.webPub = webPub;
    }

    public String getObjetivos()
    {
        return objetivos;
    }

    public void setObjetivos(String objetivos)
    {
        this.objetivos = objetivos;
    }

    public String getDestinatarios()
    {
        return destinatarios;
    }

    public void setDestinatarios(String destinatarios)
    {
        this.destinatarios = destinatarios;
    }

    public String getIdioma()
    {
        return idioma;
    }

    public void setIdioma(String idioma)
    {
        this.idioma = idioma;
    }

    public Date getFechaPresentacion()
    {
        return fechaPresentacion;
    }

    public void setFechaPresentacion(Date fechaPresentacion)
    {
        this.fechaPresentacion = fechaPresentacion;
    }

    public String getHoras()
    {
        return horas;
    }

    public void setHoras(String horas)
    {
        this.horas = horas;
    }

}
