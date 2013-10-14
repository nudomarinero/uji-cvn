package es.uji.apps.cvn.publicacion;

public class ItemMenu
{
    private String nombre;
    private String url;

    public ItemMenu(String nombre, String url)
    {
        this.nombre = nombre;
        this.url = url;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}