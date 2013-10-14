package es.uji.apps.cvn.publicacion;

public class Referencia
{
    private String url;
    private String nombre;
    private String tipo;

    public Referencia(String url, String nombre, String tipo)
    {
        this.url = url;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
}