package es.uji.apps.cvn.db;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the CVN_VIEW_PROD_PUBLICACIONES database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
// @Table(name = "CVN_VIEW_PROD_PUBLICACIONES")
@Table(name = "CVN_VIEW_PROD_PUBLI_PCI")
public class PublicacionDTO implements Serializable
{
    @Column(name = "CIUDAD_PUB")
    private String ciudadPublicacion;

    private String coleccion;

    @Column(name = "DEP_LEGAL_PUB")
    private String depositoLegalPublicacion;

    @Column(name = "EDITORIAL_PUB")
    private String editorialPublicacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_PUB")
    private Date fechaPublicacion;

    @Id
    private Long id;

    @Column(name = "ISBN_PUB")
    private String isbnPublicacion;

    @Column(name = "NOMBRE_PUB")
    private String nombrePublicacion;

    @Column(name = "NUM_RESE")
    private Long nResenyasEnRevistas;

    @Column(name = "PAGINA_INICIO")
    private Integer paginaInicio;

    @Column(name = "PAGINA_FIN")
    private Integer paginaFin;

    @Column(name = "PAIS_PUB")
    private String paisPublicacion;

    @Column(name = "REGION_PUB")
    private String regionPublicacion;

    private Long soporte;

    private Long tipo;

    private String titulo;

    private String traducciones;

    @Column(name = "VOLUMEN_PUB")
    private String volumenPublicacion;

    @Column(name = "DOI")
    private String doi;

    private Long caracter;

    @OneToMany
    @JoinColumn(name = "PRODUCCION", referencedColumnName = "ID")
    private List<ImpactoPublicacionDTO> listaImpactoPublicacion;

    // ESpecíficos de publicación de caracter docente

    private String denominacion;

    private String destinatarios;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;

    private String justificacion;

    private String handle;

    public Long getnResenyasEnRevistas()
    {
        return nResenyasEnRevistas;
    }

    public void setnResenyasEnRevistas(Long nResenyasEnRevistas)
    {
        this.nResenyasEnRevistas = nResenyasEnRevistas;
    }

    public String getDenominacion()
    {
        return denominacion;
    }

    public void setDenominacion(String denominacion)
    {
        this.denominacion = denominacion;
    }

    public String getDestinatarios()
    {
        return destinatarios;
    }

    public void setDestinatarios(String destinatarios)
    {
        this.destinatarios = destinatarios;
    }

    public Date getFechaCreacion()
    {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion)
    {
        this.fechaCreacion = fechaCreacion;
    }

    public String getJustificacion()
    {
        return justificacion;
    }

    public void setJustificacion(String justificacion)
    {
        this.justificacion = justificacion;
    }

    public PublicacionDTO()
    {
    }

    public String getCiudadPublicacion()
    {
        return this.ciudadPublicacion;
    }

    public void setCiudadPublicacion(String ciudadPublicacion)
    {
        this.ciudadPublicacion = ciudadPublicacion;
    }

    public String getColeccion()
    {
        return this.coleccion;
    }

    public void setColeccion(String coleccion)
    {
        this.coleccion = coleccion;
    }

    public String getDepositoLegalPublicacion()
    {
        return this.depositoLegalPublicacion;
    }

    public void setDepositoLegalPublicacion(String depositoLegalPublicacion)
    {
        this.depositoLegalPublicacion = depositoLegalPublicacion;
    }

    public String getEditorialPublicacion()
    {
        return this.editorialPublicacion;
    }

    public void setEditorialPublicacion(String editorialPublicacion)
    {
        this.editorialPublicacion = editorialPublicacion;
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

    public String getIsbnPublicacion()
    {
        return this.isbnPublicacion;
    }

    public void setIsbnPublicacion(String isbnPublicacion)
    {
        this.isbnPublicacion = isbnPublicacion;
    }

    public String getNombrePublicacion()
    {
        return this.nombrePublicacion;
    }

    public void setNombrePublicacion(String nombrePublicacion)
    {
        this.nombrePublicacion = nombrePublicacion;
    }

    public Long getNResenyasEnRevistas()
    {
        return this.nResenyasEnRevistas;
    }

    public void setNResenyasEnRevistas(Long nResenyasEnRevistas)
    {
        this.nResenyasEnRevistas = nResenyasEnRevistas;
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

    public String getPaisPublicacion()
    {
        return this.paisPublicacion;
    }

    public void setPaisPublicacion(String paisPublicacion)
    {
        this.paisPublicacion = paisPublicacion;
    }

    public String getRegionPublicacion()
    {
        return this.regionPublicacion;
    }

    public void setRegionPublicacion(String regionPublicacion)
    {
        this.regionPublicacion = regionPublicacion;
    }

    public Long getSoporte()
    {
        return this.soporte;
    }

    public void setSoporte(Long soporte)
    {
        this.soporte = soporte;
    }

    public Long getTipo()
    {
        return this.tipo;
    }

    public void setTipo(Long tipo)
    {
        this.tipo = tipo;
    }

    public String getTitulo()
    {
        return this.titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getTraducciones()
    {
        return this.traducciones;
    }

    public void setTraducciones(String traducciones)
    {
        this.traducciones = traducciones;
    }

    public String getVolumenPublicacion()
    {
        return this.volumenPublicacion;
    }

    public void setVolumenPublicacion(String volumenPublicacion)
    {
        this.volumenPublicacion = volumenPublicacion;
    }

    public String getDoi()
    {
        return this.doi;
    }

    public void setDoi(String doi)
    {
        this.doi = doi;
    }

    public List<ImpactoPublicacionDTO> getListaImpactoPublicacion()
    {
        return listaImpactoPublicacion;
    }

    public void setListaImpactoPublicacion(List<ImpactoPublicacionDTO> listaImpactoPublicacion)
    {
        this.listaImpactoPublicacion = listaImpactoPublicacion;
    }

    public Long getCaracter()
    {
        return caracter;
    }

    public void setCaracter(Long caracter)
    {
        this.caracter = caracter;
    }

    public String getHandle()
    {
        return handle;
    }

    public void setHandle(String handle)
    {
        this.handle = handle;
    }

    public String getUrl()
    {
        if (doi != null)
        {
            return doi;
        }

        return handle;
    }
}