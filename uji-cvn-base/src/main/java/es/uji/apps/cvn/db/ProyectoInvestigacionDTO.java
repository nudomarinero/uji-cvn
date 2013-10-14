package es.uji.apps.cvn.db;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the CVN_VIEW_PROY_INV database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CVN_VIEW_PROY_INV")
public class ProyectoInvestigacionDTO implements Serializable
{
    private String ambito;

    @Column(name = "CODIGO_EXTERNO")
    private String codigoExterno;

    @Column(name = "CODIGO_PROYECTO_FINANCIADORA")
    private String codigoProyectoFinanciadora;

    @Column(name = "DOTACION_SUBPROYECTO")
    private String dotacionSubproyecto;

    @Column(name = "DOTACION_TOTAL")
    private Float dotacionTotal;

    private Long duracion;

    @Column(name = "ENTIDAD_EJECUTORA")
    private String entidadEjecutora;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_FIN")
    private Date fechaFin;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_INICIO")
    private Date fechaInicio;

    @Id
    private Long id;

    private String microtipo;

    private String modalidad;

    private Long nInvestigadores;

    private Long nInvestigadoresExt;

    @Column(name = "NOMBRE_PROGRAMA_FINANCIACION")
    private String nombreProgramaFinanciacion;

    private String nPersonasAnyo;

    @Column(name = "PORCENTAJE_CREDITO")
    private String porcentajeCredito;

    @Column(name = "PORCENTAJE_MIXTO")
    private String porcentajeMixto;

    @Column(name = "PORCENTAJE_SUBVENCION")
    private String porcentajeSubvencion;

    @Column(name = "RESULTADOS_KEYWORDS")
    private String resultadosKeywords;

    @Column(name = "RESULTADOS_RELEVANTES")
    private String resultadosRelevantes;

    private String suborganismo;

    private String subtipo;

    private Long tipo;

    private String titulo;

    @Column(name = "TITULO_KEYWORDS")
    private String tituloKeywords;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CVN_VIEW_ENT_FINANCIA_PROY", joinColumns = @JoinColumn(name = "PROYECTO", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ENTIDAD", referencedColumnName = "ID"))
    private Set<EntidadDTO> entidadesFinanciadoras;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CVN_VIEW_ENT_PARTICIPA_PROY", joinColumns = @JoinColumn(name = "PROYECTO", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ENTIDAD", referencedColumnName = "ID"))
    private Set<EntidadDTO> entidadesParticipantes;

    public ProyectoInvestigacionDTO()
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

    public String getCodigoExterno()
    {
        return this.codigoExterno;
    }

    public void setCodigoExterno(String codigoExterno)
    {
        this.codigoExterno = codigoExterno;
    }

    public String getCodigoProyectoFinanciadora()
    {
        return this.codigoProyectoFinanciadora;
    }

    public void setCodigoProyectoFinanciadora(String codigoProyectoFinanciadora)
    {
        this.codigoProyectoFinanciadora = codigoProyectoFinanciadora;
    }

    public String getDotacionSubproyecto()
    {
        return this.dotacionSubproyecto;
    }

    public void setDotacionSubproyecto(String dotacionSubproyecto)
    {
        this.dotacionSubproyecto = dotacionSubproyecto;
    }

    public Float getDotacionTotal()
    {
        return this.dotacionTotal;
    }

    public void setDotacionTotal(Float dotacionTotal)
    {
        this.dotacionTotal = dotacionTotal;
    }

    public Long getDuracion()
    {
        return this.duracion;
    }

    public void setDuracion(Long duracion)
    {
        this.duracion = duracion;
    }

    public String getEntidadEjecutora()
    {
        return this.entidadEjecutora;
    }

    public void setEntidadEjecutora(String entidadEjecutora)
    {
        this.entidadEjecutora = entidadEjecutora;
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

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getMicrotipo()
    {
        return this.microtipo;
    }

    public void setMicrotipo(String microtipo)
    {
        this.microtipo = microtipo;
    }

    public String getModalidad()
    {
        return this.modalidad;
    }

    public void setModalidad(String modalidad)
    {
        this.modalidad = modalidad;
    }

    public Long getNInvestigadores()
    {
        return this.nInvestigadores;
    }

    public void setNinvestigadores(Long nInvestigadores)
    {
        this.nInvestigadores = nInvestigadores;
    }

    public Long getNInvestigadoresExt()
    {
        return this.nInvestigadoresExt;
    }

    public void setNInvestigadoresExt(Long nInvestigadoresExt)
    {
        this.nInvestigadoresExt = nInvestigadoresExt;
    }

    public String getNombreProgramaFinanciacion()
    {
        return this.nombreProgramaFinanciacion;
    }

    public void setNombreProgramaFinanciacion(String nombreProgramaFinanciacion)
    {
        this.nombreProgramaFinanciacion = nombreProgramaFinanciacion;
    }

    public String getNPersonasAnyo()
    {
        return this.nPersonasAnyo;
    }

    public void setNPersonasAnyo(String nPersonasAnyo)
    {
        this.nPersonasAnyo = nPersonasAnyo;
    }

    public String getPorcentajeCredito()
    {
        return this.porcentajeCredito;
    }

    public void setPorcentajeCredito(String porcentajeCredito)
    {
        this.porcentajeCredito = porcentajeCredito;
    }

    public String getPorcentajeMixto()
    {
        return this.porcentajeMixto;
    }

    public void setPorcentajeMixto(String porcentajeMixto)
    {
        this.porcentajeMixto = porcentajeMixto;
    }

    public String getPorcentajeSubvencion()
    {
        return this.porcentajeSubvencion;
    }

    public void setPorcentajeSubvencion(String porcentajeSubvencion)
    {
        this.porcentajeSubvencion = porcentajeSubvencion;
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

    public String getSuborganismo()
    {
        return suborganismo;
    }

    public void setSuborganismo(String suborganismo)
    {
        this.suborganismo = suborganismo;
    }

    public String getSubtipo()
    {
        return this.subtipo;
    }

    public void setSubtipo(String subtipo)
    {
        this.subtipo = subtipo;
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

    public String getTituloKeywords()
    {
        return this.tituloKeywords;
    }

    public void setTituloKeywords(String tituloKeywords)
    {
        this.tituloKeywords = tituloKeywords;
    }

    public Set<EntidadDTO> getEntidadesFinanciadoras()
    {
        return entidadesFinanciadoras;
    }

    public void setEntidadesFinanciadoras(Set<EntidadDTO> entidadesFinanciadoras)
    {
        this.entidadesFinanciadoras = entidadesFinanciadoras;
    }

    public Set<EntidadDTO> getEntidadesParticipantes()
    {
        return entidadesParticipantes;
    }

    public void setEntidadesParticipantes(Set<EntidadDTO> entidadesParticipantes)
    {
        this.entidadesParticipantes = entidadesParticipantes;
    }

}