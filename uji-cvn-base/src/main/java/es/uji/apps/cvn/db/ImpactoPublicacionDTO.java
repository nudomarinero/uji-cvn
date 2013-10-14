package es.uji.apps.cvn.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * The persistent class for the CVN_VIEW_IMPACTO_PROD_PUBL database table.
 * 
 */
@Entity
@IdClass(ImpactoPublicacionDTOId.class)
@Table(name = "CVN_VIEW_IMPACTO_PROD_PUBL")
public class ImpactoPublicacionDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    private String categoria1;

    @Id
    private String categoria2;

    private Long cuartil;

    @Id
    @Column(name = "FUENTE_IMPACTO")
    private String fuenteImpacto;

    @Column(name = "INDICE_IMPACTO")
    private Float indiceImpacto;

    @Column(name = "IS_25P_TOP")
    private Boolean is25pTop;

    private Long posicion;

    @Id
    private Long produccion;

    private Long total;

    public ImpactoPublicacionDTO()
    {
    }

    public String getCategoria1()
    {
        return this.categoria1;
    }

    public void setCategoria1(String categoria1)
    {
        this.categoria1 = categoria1;
    }

    public String getCategoria2()
    {
        return this.categoria2;
    }

    public void setCategoria2(String categoria2)
    {
        this.categoria2 = categoria2;
    }

    public Long getCuartil()
    {
        return this.cuartil;
    }

    public void setCuartil(Long cuartil)
    {
        this.cuartil = cuartil;
    }

    public String getFuenteImpacto()
    {
        return this.fuenteImpacto;
    }

    public void setFuenteImpacto(String fuenteImpacto)
    {
        this.fuenteImpacto = fuenteImpacto;
    }

    public Float getIndiceImpacto()
    {
        return this.indiceImpacto;
    }

    public void setIndiceImpacto(Float indiceImpacto)
    {
        this.indiceImpacto = indiceImpacto;
    }

    public Boolean getIs25pTop()
    {
        return this.is25pTop;
    }

    public void setIs25pTop(Boolean is25pTop)
    {
        this.is25pTop = is25pTop;
    }

    public Long getPosicion()
    {
        return this.posicion;
    }

    public void setPosicion(Long posicion)
    {
        this.posicion = posicion;
    }

    public Long getProduccion()
    {
        return this.produccion;
    }

    public void setProduccion(Long produccion)
    {
        this.produccion = produccion;
    }

    public Long getTotal()
    {
        return this.total;
    }

    public void setTotal(Long total)
    {
        this.total = total;
    }

}