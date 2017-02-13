package es.uji.apps.cvn.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the CVN_EXT_PERSONAS database table.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CVN_EXT_PERSONAS_MIN")
public class PersonaMiniDTO
{
    @Id
    private Long id;

    private String apellido1;

    private String apellido2;

    private String identificacion;

    private String nombre;

    @Column(name = "TIPO_IDENTIFICACION")
    private String tipoIdentificacion;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getApellido1()
    {
        return apellido1;
    }

    public void setApellido1(String apellido1)
    {
        this.apellido1 = apellido1;
    }

    public String getApellido2()
    {
        return apellido2;
    }

    public void setApellido2(String apellido2)
    {
        this.apellido2 = apellido2;
    }

    public String getIdentificacion()
    {
        return identificacion;
    }

    public void setIdentificacion(String identificacion)
    {
        this.identificacion = identificacion;
    }

    public String getTipoIdentificacion()
    {
        return tipoIdentificacion;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setTipoIdentificacion(String tipoIdentificacion)
    {
        this.tipoIdentificacion = tipoIdentificacion;
    }
}
