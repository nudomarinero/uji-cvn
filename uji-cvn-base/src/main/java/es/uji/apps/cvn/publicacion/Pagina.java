package es.uji.apps.cvn.publicacion;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class Pagina extends Seccion
{
    private String titulo;
    private String subTitulo;
    private Menu menu;
    private String idioma;

    public Pagina(String urlBase, String url, String idioma)
    {
        super(urlBase, url);
        this.idioma = idioma;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getSubTitulo()
    {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo)
    {
        this.subTitulo = subTitulo;
    }

    public Menu getMenu()
    {
        return menu;
    }

    public void setMenu(Menu menu)
    {
        this.menu = menu;
    }

    public String getIdioma()
    {
        return idioma;
    }

    public void setIdioma(String idioma)
    {
        this.idioma = idioma;
    }

    public boolean existeNodo(List<Seccion> secciones, String path)
    {
        if (secciones != null)
        {
            for (Seccion seccion : secciones)
            {
                if (seccion.getUrl().equals(path))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public String getUrlEncode(String param) throws UnsupportedEncodingException
    {
        return URLEncoder.encode(param, "UTF-8");
    }

    @Override
    public String toString()
    {
        StringBuffer out = new StringBuffer();

        out.append("[PAGINA]").append("\n");
        out.append("titulo --------------> ").append(titulo).append("\n");
        out.append("subTitulo -----------> ").append(subTitulo).append("\n\n");

        out.append(super.toString());

        return out.toString();
    }
}