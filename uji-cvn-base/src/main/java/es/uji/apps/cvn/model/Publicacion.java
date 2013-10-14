package es.uji.apps.cvn.model;

import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.Utils;
import es.uji.apps.cvn.model.comparators.AutorPublicacionComparator;
import es.uji.apps.cvn.translators.TipoProduccion;
import es.uji.apps.cvn.translators.TipoSoporte;

public class Publicacion
{
    private String tipo;

    private String tipoOtros;

    private List<AutorPublicacion> autores;

    private String soporte;

    private String titulo;

    private String nombre;

    private String volumen;

    private String paginaInicio;

    private String paginaFin;

    private String editorial;

    private String pais;

    private String region;

    private XMLGregorianCalendar fecha;

    private String url;

    private List<String> isbns;

    private String depositoLegal;

    private String ciudad;

    private String coleccion;

    final private static String DOI_URL = "http://dx.doi.org/";

    public Publicacion()
    {
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(Long tipo)
    {
        this.tipo = TipoProduccion.getTipo(tipo);
    }

    public String getTipoOtros()
    {
        return tipoOtros;
    }

    public void setTipoOtros(String tipoOtros)
    {
        this.tipoOtros = tipoOtros;
    }

    public List<AutorPublicacion> getAutores()
    {
        return autores;
    }

    public void setAutores(List<AutorPublicacion> autores)
    {
        Collections.sort(autores, new AutorPublicacionComparator());
        this.autores = autores;
    }

    public String getSoporte()
    {
        return soporte;
    }

    public void setSoporte(Long soporte)
    {
        this.soporte = TipoSoporte.getTipo(soporte);
    }
    
    public void setSoporte(String soporte)
    {
        this.soporte = soporte;
    }
    

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = Utils.limpiaCadena(titulo);
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = Utils.limpiaCadena(nombre);
    }

    public String getVolumen()
    {
        return volumen;
    }

    public void setVolumen(String volumen)
    {
        this.volumen = volumen;
    }

    public String getPaginaInicio()
    {
        return paginaInicio;
    }

    public void setPaginaInicio(String paginaInicio)
    {
        this.paginaInicio = paginaInicio;
    }

    public String getPaginaFin()
    {
        return paginaFin;
    }

    public void setPaginaFin(String paginaFin)
    {
        this.paginaFin = paginaFin;
    }

    public String getEditorial()
    {
        return editorial;
    }

    public void setEditorial(String editorial)
    {
        this.editorial = editorial;
    }

    public String getPais()
    {
        return pais;
    }

    public void setPais(String pais)
    {
        this.pais = pais;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public XMLGregorianCalendar getFecha()
    {
        return fecha;
    }

    public void setFecha(Long fecha)
    {
        if (fecha != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.set(GregorianCalendar.YEAR, fecha.intValue());
            try
            {
                this.fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            }
            catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        if (url != null)
        {
            Pattern pattern = Pattern.compile("^[^0-9]*(10\\.[^/]+/[^ ]+).*$");
            Matcher matcher = pattern.matcher(url);

            if (matcher.matches())
            {
                this.url = DOI_URL + matcher.group(1);
            }
            else
            {
                this.url = url;
            }
        }
    }

    public List<String> getIsbns()
    {
        return isbns;
    }

    public void setIsbns(List<String> isbns)
    {
        this.isbns = isbns;
    }

    public String getDepositoLegal()
    {
        return depositoLegal;
    }

    public void setDepositoLegal(String depositoLegal)
    {
        this.depositoLegal = depositoLegal;
    }

    public String getCiudad()
    {
        return ciudad;
    }

    public void setCiudad(String ciudad)
    {
        this.ciudad = ciudad;
    }

    public String getColeccion()
    {
        return coleccion;
    }

    public void setColeccion(String coleccion)
    {
        this.coleccion = coleccion;
    }

    public static String getDoiUrl()
    {
        return DOI_URL;
    }

    public void setPaginasInicioFin(String paginas)
    {
        if (paginas != null)
        {
            Pattern pattern = Pattern.compile("^[^0-9]*([0-9]+)\\s*[-aA]*\\s*([0-9]+)?");
            Matcher matcher = pattern.matcher(paginas);

            if (matcher.find())
            {
                this.paginaInicio = matcher.group(1);
                if (matcher.groupCount() > 1)
                {
                    this.paginaFin = matcher.group(2);
                }
            }
        }
    }
}
