package es.uji.apps.cvn.db;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CVN_PETICIONES_GENERACION")
public class PeticionGeneracionDTO
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PER_ID")
    private Long personaId;

    @Column(name = "PLANTILLA_ID")
    private Long plantillaId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_GENERACION")
    private Date fechaGeneracion;

    private String idioma;

    private String template;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getPersonaId()
    {
        return personaId;
    }

    public void setPersonaId(Long personaId)
    {
        this.personaId = personaId;
    }

    public Long getPlantillaId()
    {
        return plantillaId;
    }

    public void setPlantillaId(Long plantillaId)
    {
        this.plantillaId = plantillaId;
    }

    public Date getFechaGeneracion()
    {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion)
    {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getIdioma()
    {
        return idioma;
    }

    public void setIdioma(String idioma)
    {
        this.idioma = idioma;
    }

    public String getTemplate()
    {
        return template;
    }

    public void setTemplate(String template)
    {
        this.template = template;
    }
}