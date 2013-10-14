package es.uji.apps.cvn.publicacion;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Recurso
{
    protected String origenNombre;
    protected String origenUrl;
    protected String lugar;
    private String proximaFechaVigencia;
    private String proximaHoraVigencia;
    private String primeraFechaVigencia;
    private String primeraHoraVigencia;
    private Long id;
    private String titulo;
    private String subTitulo;
    private String tituloLargo;
    private String resumen;
    private String autor;
    private Date fechaModificacion;
    private byte[] contenido;
    private List<Tag> tags;
    private String mimeType;
    private String esHtml;
    private String urlBase;
    private String urlNodo;
    private String urlNodoCompleta;
    private String urlDescarga;
    private String urlDestino;
    private String urlCompleta;

    public Recurso()
    {
        tags = new ArrayList<Tag>();
    }

    public boolean esTexto()
    {
        return (mimeType != null && mimeType.startsWith("text/") && "S".equals(esHtml));
    }

    public boolean esVideo()
    {
        return ((mimeType != null && mimeType.startsWith("video/"))
                || urlCompleta.toLowerCase().endsWith(".ogv")
                || urlCompleta.toLowerCase().endsWith(".mp4") || urlCompleta.toLowerCase()
                .endsWith(".flv"));
    }

    public boolean esImagen()
    {
        return ((mimeType != null && mimeType.startsWith("image/"))
                || urlCompleta.toLowerCase().endsWith(".jpg")
                || urlCompleta.toLowerCase().endsWith(".png") || urlCompleta.toLowerCase()
                .endsWith(".gif"));
    }

    private String getFecha(Date date) throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try
        {
            return formatter.format(date);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private String getHora(Date date) throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("H:mm");

        try
        {
            return formatter.format(date);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getTituloLargo()
    {
        return tituloLargo;
    }

    public void setTituloLargo(String tituloLargo)
    {
        this.tituloLargo = tituloLargo;
    }

    public String getSubTitulo()
    {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo)
    {
        this.subTitulo = subTitulo;
    }

    public String getResumen()
    {
        return resumen;
    }

    public void setResumen(String resumen)
    {
        this.resumen = resumen;
    }

    public String getContenido() throws UnsupportedEncodingException
    {
        if (contenido == null)
        {
            return null;
        }

        return new String(contenido, "UTF-8");
    }

    public void setContenido(byte[] contenido)
    {
        this.contenido = contenido;
    }

    public void setContenido(String contenido)
    {
        this.contenido = contenido.getBytes();
    }

    public Date getFechaModificacion()
    {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion)
    {
        this.fechaModificacion = fechaModificacion;
    }

    public String getUrlNodo()
    {
        return urlNodo;
    }

    public void setUrlNodo(String urlNodo)
    {
        this.urlNodo = urlNodo;
    }

    public List<Tag> getTags()
    {
        return tags;
    }

    public void addTag(Tag tag)
    {
        tags.add(tag);
    }

    public String getMimeType()
    {
        return mimeType;
    }

    public void setMimeType(String mimeType)
    {
        this.mimeType = mimeType;
    }

    public String getEsHtml()
    {
        return esHtml;
    }

    public void setEsHtml(String esHtml)
    {
        this.esHtml = esHtml;
    }

    public String getUrlBase()
    {
        return urlBase;
    }

    public void setUrlBase(String urlBase)
    {
        this.urlBase = urlBase;
    }

    public String getUrlDescarga()
    {
        return urlDescarga;
    }

    public void setUrlDescarga(String urlDescarga)
    {
        this.urlDescarga = urlDescarga;
    }

    public String getUrlDestino()
    {
        return urlDestino;
    }

    public void setUrlDestino(String urlDestino)
    {
        this.urlDestino = urlDestino;
    }

    public String getUrlCompleta()
    {
        return urlCompleta;
    }

    public void setUrlCompleta(String urlCompleta)
    {
        this.urlCompleta = urlCompleta;
    }

    public String getUrlNodoCompleta()
    {
        return urlNodoCompleta;
    }

    public void setUrlNodoCompleta(String urlNodoCompleta)
    {
        this.urlNodoCompleta = urlNodoCompleta;
    }

    public String getAutor()
    {
        return autor;
    }

    public void setAutor(String autor)
    {
        this.autor = autor;
    }

    public String getOrigenNombre()
    {
        return origenNombre;
    }

    public void setOrigenNombre(String origenNombre)
    {
        this.origenNombre = origenNombre;
    }

    public String getOrigenUrl()
    {
        return origenUrl;
    }

    public void setOrigenUrl(String origenUrl)
    {
        this.origenUrl = origenUrl;
    }

    public String getLugar()
    {
        return this.lugar;
    }

    public void setLugar(String lugar)
    {
        this.lugar = lugar;
    }

    public String getProximaFechaVigencia()
    {
        return this.proximaFechaVigencia;
    }

    public void setProximaFechaVigencia(String proximaFechaVigencia)
    {
        this.proximaFechaVigencia = proximaFechaVigencia;
    }

    public String getProximaHoraVigencia()
    {
        return this.proximaHoraVigencia;
    }

    public void setProximaHoraVigencia(String proximaHoraVigencia)
    {
        this.proximaHoraVigencia = proximaHoraVigencia;
    }

    public String getPrimeraFechaVigencia()
    {
        return this.primeraFechaVigencia;
    }

    public void setPrimeraFechaVigencia(String primeraFechaVigencia)
    {
        this.primeraFechaVigencia = primeraFechaVigencia;
    }

    public String getPrimeraHoraVigencia()
    {
        return this.primeraHoraVigencia;
    }

    public void setPrimeraHoraVigencia(String primeraHoraVigencia)
    {
        this.primeraHoraVigencia = primeraHoraVigencia;
    }

    @Override
    public boolean equals(Object o)
    {
        return ((Recurso) o).getUrlCompleta().equalsIgnoreCase(getUrlCompleta());
    }

    @Override
    public String toString()
    {
        StringBuffer out = new StringBuffer();

        out.append("[RECURSO]").append("\n");
        out.append("id ------------------> ").append(id).append("\n");
        out.append("subTitulo -----------> ").append(subTitulo).append("\n");
        out.append("tituloLargo ---------> ").append(tituloLargo).append("\n");
        out.append("resumen -------------> ").append(resumen).append("\n");
        out.append("autor ---------------> ").append(autor).append("\n");
        out.append("fechaModificacion ---> ").append(fechaModificacion).append("\n");
        out.append("mimeType ------------> ").append(mimeType).append("\n");
        out.append("urlBase -------------> ").append(urlBase).append("\n");
        out.append("urlNodo -------------> ").append(urlNodo).append("\n");
        out.append("urlNodoCompleta -----> ").append(urlNodoCompleta).append("\n");
        out.append("urlDescarga ---------> ").append(urlDescarga).append("\n");
        out.append("urlDestino ----------> ").append(urlDestino).append("\n");
        out.append("urlCompleta ---------> ").append(urlCompleta).append("\n\n");
        out.append("origenNombre --------> ").append(origenNombre).append("\n\n");
        out.append("origenURl -----------> ").append(origenUrl).append("\n\n");
        out.append("lugar ---------------> ").append(lugar).append("\n\n");
        out.append("proximaFechaVigencia > ").append(proximaFechaVigencia).append("\n\n");
        out.append("proximaHoraVigencia -> ").append(proximaHoraVigencia).append("\n\n");

        return out.toString();
    }
}