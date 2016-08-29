package es.uji.apps.cvn.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * The persistent class for the CVN_VIEW_PER_EXT_PART_PUBL database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@IdClass(ParticipacionPersonaExternaEnPublicacionDTOId.class)
//@Table(name = "CVN_VIEW_PER_EXT_PART_PUBL")
@Table(name = "CVN_VIEW_PER_EXT_PART_PUBL_PCI")
public class ParticipacionPersonaExternaEnPublicacionDTO implements Serializable
{
    @Column(name = "CALIDAD_PARTICIPACION")
    private String calidadParticipacion;

    @Column(name = "IS_RELEVANTE_PUB")
    private String isPublicacionRelevante;

    private Long orden;

    @Id
    private String persona;

    @Column(name = "POS_SOBRE_TOTAL")
    private String posSobreTotal;

    @Id
    private Long produccion;

    @Column(name = "RESULTADOS_DESTACADOS")
    private String resultadosDestacados;

    public ParticipacionPersonaExternaEnPublicacionDTO()
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

    public String getIsPublicacionRelevante()
    {
        return this.isPublicacionRelevante;
    }

    public void setIsPublicacionRelevante(String isPublicacionRelevante)
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

    public String getPersona()
    {
        return this.persona;
    }

    public void setPersona(String persona)
    {
        this.persona = persona;
    }

    public String getPosSobreTotal()
    {
        return this.posSobreTotal;
    }

    public void setPosSobreTotal(String posSobreTotal)
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

}