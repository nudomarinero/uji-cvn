package es.uji.apps.cvn.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the CVN_VIEW_PER_PARTICIPA_GRUPO database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@IdClass(ParticipacionPersonaEnGrupoDTOId.class)
@Table(name = "CVN_VIEW_PER_PARTICIPA_GRUPO")
public class ParticipacionPersonaEnGrupoDTO implements Serializable
{
    private Long duracion;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_FIN")
    private Date fechaFin;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_INICIO")
    private Date fechaInicio;

    @Id
    private String grupo;

    @Id
    private Long persona;

    @Column(name = "RESULTADOS_DESC")
    private String resultadosDesc;

    @Column(name = "RESULTADOS_KEYWORDS")
    private String resultadosKeywords;

    @Column(name = "RESULTADOS_RELEVANTES")
    private String resultadosRelevantes;

    @Column(name = "TIPO_COLABORACION")
    private String tipoColaboracion;

    public ParticipacionPersonaEnGrupoDTO()
    {
    }

    public Long getDuracion()
    {
        return this.duracion;
    }

    public void setDuracion(Long duracion)
    {
        this.duracion = duracion;
    }

    public Date getFechaFin()
    {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin)
    {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio()
    {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio)
    {
        this.fechaInicio = fechaInicio;
    }

    public String getGrupo()
    {
        return this.grupo;
    }

    public void setGrupo(String grupo)
    {
        this.grupo = grupo;
    }

    public Long getPersona()
    {
        return this.persona;
    }

    public void setPersona(Long persona)
    {
        this.persona = persona;
    }

    public String getResultadosDesc()
    {
        return this.resultadosDesc;
    }

    public void setResultadosDesc(String resultadosDesc)
    {
        this.resultadosDesc = resultadosDesc;
    }

    public String getResultadosKeywords()
    {
        return this.resultadosKeywords;
    }

    public void setResultadosKeywords(String resultadosKeywords)
    {
        this.resultadosKeywords = resultadosKeywords;
    }

    public String getResultadosRelevantes()
    {
        return this.resultadosRelevantes;
    }

    public void setResultadosRelevantes(String resultadosRelevantes)
    {
        this.resultadosRelevantes = resultadosRelevantes;
    }

    public String getTipoColaboracion()
    {
        return this.tipoColaboracion;
    }

    public void setTipoColaboracion(String tipoColaboracion)
    {
        this.tipoColaboracion = tipoColaboracion;
    }

}