package es.uji.apps.cvn.db;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the CVN_VIEW_PROD_PUBLICACIONES database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CVN_VIEW_PROD_PUBLICACIONES")
public class PublicacionDTO implements Serializable
{
    @Column(name = "CIUDAD_PUB")
    private String ciudadPublicacion;

    private String coleccion;

    @Column(name = "DEP_LEGAL_PUB")
    private String depositoLegalPublicacion;

    @Column(name = "EDITORIAL_PUB")
    private String editorialPublicacion;

    @Column(name = "FECHA_PUB")
    private Long fechaPublicacion;

    @Id
    private Long id;

    @Column(name = "ISBN_PUB")
    private String isbnPublicacion;

    @Column(name = "NOMBRE_PUB")
    private String nombrePublicacion;

    @Column(name = "NUM_RESE")
    private Long nResenyasEnRevistas;

    @Column(name = "PAGINAS_PUB")
    private String paginasPublicacion;

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

    @Column(name = "WEB_PUB")
    private String webPublicacion;

    private Long caracter;
        
    @OneToMany
    @JoinColumn(name = "PRODUCCION", referencedColumnName = "ID")
    private List<ImpactoPublicacionDTO> listaImpactoPublicacion;

    //ESpecíficos de publicación de caracter docente
    
    private String denominacion;
    
    private String destinatarios;

    @Column(name="FECHA_CREACION")
    private Date fechaCreacion;
    
    private String justificacion;

    public Long getnResenyasEnRevistas() {
		return nResenyasEnRevistas;
	}

	public void setnResenyasEnRevistas(Long nResenyasEnRevistas) {
		this.nResenyasEnRevistas = nResenyasEnRevistas;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(String destinatarios) {
		this.destinatarios = destinatarios;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
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

    public Long getFechaPublicacion()
    {
        return this.fechaPublicacion;
    }

    public void setFechaPublicacion(Long fechaPublicacion)
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

    public String getPaginasPublicacion()
    {
        return this.paginasPublicacion;
    }

    public void setPaginasPublicacion(String paginasPublicacion)
    {
        this.paginasPublicacion = paginasPublicacion;
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

    public String getWebPublicacion()
    {
        return this.webPublicacion;
    }

    public void setWebPublicacion(String webPublicacion)
    {
        this.webPublicacion = webPublicacion;
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

}