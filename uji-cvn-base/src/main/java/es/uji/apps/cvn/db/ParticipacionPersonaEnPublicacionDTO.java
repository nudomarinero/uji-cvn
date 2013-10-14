package es.uji.apps.cvn.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * The persistent class for the CVN_VIEW_PER_PART_PROD_PUBL database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@IdClass(ParticipacionPersonaEnPublicacionDTOId.class)
@Table(name = "CVN_VIEW_PER_PART_PROD_PUBL")
public class ParticipacionPersonaEnPublicacionDTO implements Serializable
{
    @Column(name = "CALIDAD_PARTICIPACION")
    private String calidadParticipacion;

    @Column(name = "IS_RELEVANTE_PUB")
    private Boolean isPublicacionRelevante;

    private Long orden;

    @Id
    private Long persona;

    @Column(name = "POS_SOBRE_TOTAL")
    private Float posSobreTotal;

    @Id
    private Long produccion;

    @Column(name = "RESULTADOS_DESTACADOS")
    private String resultadosDestacados;

    @Column(name = "CARACTER")
    private Long caracterPublicacion;

    public ParticipacionPersonaEnPublicacionDTO()
    {
    }

    public String getCalidadParticipacion()
    {
        return this.calidadParticipacion;
    }

    public void setCalidadParticipacion(String calidadParticipacion)
    {
        this.calidadParticipacion = calidadParticipacion;
    }

    public Boolean getIsPublicacionRelevante()
    {
        return this.isPublicacionRelevante;
    }

    public void setIsPublicacionRelevante(Boolean isPublicacionRelevante)
    {
        this.isPublicacionRelevante = isPublicacionRelevante;
    }

    public Long getOrden()
    {
        return this.orden;
    }

    public void setOrden(Long orden)
    {
        this.orden = orden;
    }

    public Long getPersona()
    {
        return this.persona;
    }

    public void setPersona(Long persona)
    {
        this.persona = persona;
    }

    public Float getPosSobreTotal()
    {
        return this.posSobreTotal;
    }

    public void setPosSobreTotal(Float posSobreTotal)
    {
        this.posSobreTotal = posSobreTotal;
    }

    public Long getProduccion()
    {
        return this.produccion;
    }

    public void setProduccion(Long produccion)
    {
        this.produccion = produccion;
    }

    public String getResultadosDestacados()
    {
        return this.resultadosDestacados;
    }

    public void setResultadosDestacados(String resultadosDestacados)
    {
        this.resultadosDestacados = resultadosDestacados;
    }

    public Long getCaracterPublicacion()
    {
        return caracterPublicacion;
    }

    public void setCaracterPublicacion(Long caracterPublicacion)
    {
        this.caracterPublicacion = caracterPublicacion;
    }

}