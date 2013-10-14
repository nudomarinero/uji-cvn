package es.uji.apps.cvn.model;

public class AutorPublicacion
{
    String nombre;

    String apellido1;

    String apellido2;

    Long orden;

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
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

    public Long getOrden()
    {
        return orden;
    }

    public void setOrden(Long orden)
    {
        this.orden = orden;
    }
}
