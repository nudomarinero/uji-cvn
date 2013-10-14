package es.uji.apps.cvn.publicacion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Seccion
{
    protected String urlBase;
    protected String url;
    protected List<Seccion> secciones;
    protected List<Recurso> recursos;
    protected List<Referencia> referencias;

    public Seccion(String urlBase, String url)
    {
        this.url = url;
        this.urlBase = urlBase;
        this.secciones = new ArrayList<Seccion>();
        this.recursos = new ArrayList<Recurso>();
        this.referencias = new ArrayList<Referencia>();
    }

    public String getUrl()
    {
        return this.url;
    }

    public String getUrlBase()
    {
        return this.urlBase;
    }

    public void add(Recurso recurso)
    {
        if (recurso == null)
        {
            throw new IllegalArgumentException("El recurso no puede ser nulo");
        }

        recursos.add(recurso);
    }

    public List<Recurso> getImagenes()
    {
        List<Recurso> imagenes = new ArrayList<Recurso>();

        for (Recurso recurso : recursos)
        {
            if (recurso.esImagen())
            {
                imagenes.add(recurso);
            }
        }

        return imagenes;
    }

    public List<Recurso> getVideos()
    {
        List<Recurso> videos = new ArrayList<Recurso>();

        for (Recurso recurso : recursos)
        {
            if (recurso.esVideo())
            {
                videos.add(recurso);
            }
        }

        return videos;
    }

    public Boolean getHasVideo()
    {
        return getVideos().size() > 0;
    }

    private String getUrlVideoMp4()
    {
        List<Recurso> videos = getVideos();

        for (Recurso video : videos)
        {
            if ("video/mp4".equals(video.getMimeType())
                    || (video.getUrlCompleta() != null && video.getUrlCompleta().toLowerCase()
                            .endsWith(".mp4")))
            {
                return (video.getUrlDestino() != null) ? video.getUrlDestino() : video
                        .getUrlDescarga();
            }
        }

        return null;
    }

    public String getUrlFirstImage()
    {
        List<Recurso> imagenes = getImagenes();

        if (imagenes.size() > 0)
        {
            Recurso imagen = imagenes.get(0);

            return (imagen.getUrlDestino() != null) ? imagen.getUrlDestino() : imagen
                    .getUrlDescarga();
        }

        return null;
    }

    public String getTitleFirstImage()
    {
        List<Recurso> imagenes = getImagenes();

        if (imagenes.size() > 0)
        {
            Recurso imagen = imagenes.get(0);

            return imagen.getTitulo();
        }

        return null;
    }

    public String getUrlSeccionPrimerVideo()
    {
        List<Seccion> secciones = getSecciones();

        for (Seccion seccion : secciones)
        {
            if (seccion.getHasVideo())
            {
                return seccion.getUrl();
            }
        }

        return null;
    }

    public String getValueForVideoPlayer()
    {
        String value = "controlbar=over&image=";

        String server = getUrlBase();

        if (server.indexOf("http://localhost:") > -1)
        {
            server = "http://localhost/";
        }
        else
        {
            server = "http://ujiapps.uji.es/";
        }

        value += server + getUrlFirstImage();
        value += "&file=";

        String urlVideoMp4 = getUrlVideoMp4();

        if (urlVideoMp4.indexOf("http://") == -1)
        {
            urlVideoMp4 = server + urlVideoMp4;
        }

        value += urlVideoMp4;

        value = value.replaceAll("/", "%2F");
        value = value.replaceAll(":", "%3A");
        value = value.replaceAll("\\?", "%3F");

        return value;
    }

    public Boolean existsImageThatStartsWith(String name)
    {
        List<Recurso> imagenes = getImagenes();

        for (Recurso imagen : imagenes)
        {
            if (imagen.getUrlNodo().startsWith(name))
            {
                return true;
            }
        }

        return false;
    }

    public List<Recurso> getTextos()
    {
        List<Recurso> textos = new ArrayList<Recurso>();

        for (Recurso recurso : recursos)
        {
            if (recurso.esTexto())
            {
                textos.add(recurso);
            }
        }

        return textos;
    }

    public Boolean existeTextoConAtributo(String nombreAtributo) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException
    {
        List<Recurso> textos = getTextos();

        String getter = "get" + nombreAtributo.substring(0, 1).toUpperCase()
                + nombreAtributo.substring(1);

        Method method = Recurso.class.getMethod(getter);

        for (Recurso texto : textos)
        {
            String varlorAtributo = (String) method.invoke(texto, (Object[]) null);

            if (varlorAtributo != null)
            {
                return true;
            }
        }

        return false;
    }

    public List<List<Recurso>> getDosColumnas()
    {
        List<List<Recurso>> grid = new ArrayList<List<Recurso>>();

        for (int i = 0; i < recursos.size(); i += 2)
        {
            List<Recurso> fila = new ArrayList<Recurso>();
            fila.add(recursos.get(i));

            if (i + 1 < recursos.size())
            {
                fila.add(recursos.get(i + 1));
            }

            grid.add(fila);
        }

        return grid;
    }

    public List<Referencia> getReferencias()
    {
        return referencias;
    }

    public void addReferencia(Referencia referencia)
    {
        this.referencias.add(referencia);
    }

    public List<Seccion> getSecciones()
    {
        return this.secciones;
    }

    public Seccion addSeccion(Seccion seccion)
    {
        if (!secciones.contains(seccion))
        {
            this.secciones.add(seccion);
        }

        return this.secciones.get(this.secciones.indexOf(seccion));
    }

    @Override
    public boolean equals(Object o)
    {
        return ((Seccion) o).getUrl().equalsIgnoreCase(getUrl());
    }

    @Override
    public String toString()
    {
        StringBuffer out = new StringBuffer();

        out.append("[SECCION]").append("\n");
        out.append("urlBase -------------> ").append(urlBase).append("\n");
        out.append("url -----------------> ").append(url).append("\n\n");

        for (Recurso recurso : recursos)
        {
            out.append(recurso.toString());
        }

        for (Seccion seccion : secciones)
        {
            out.append(seccion.toString());
        }

        return out.toString();
    }
}
