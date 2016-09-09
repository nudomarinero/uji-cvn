package es.uji.apps.cvn.model;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.Utils;
import es.uji.apps.cvn.translators.AmbitoDifusion;
import es.uji.apps.cvn.translators.TipoProduccion;

public class Congreso
{
    private String tipoEvento;

    private String tipoEventoOtros;

    private String titulo;

    private String intervencion;

    private String intervencionOtros;

    private String ambito;

    private String ambitoOtros;

    private String nombre;

    private String pais;

    private String region;

    private String ciudad;

    private XMLGregorianCalendar fecha;

    private XMLGregorianCalendar ano;

    private Boolean isPublicadoActas;

    private Boolean hasComiteExterno;

    private Entidad entidadOrganizadora;

    private XMLGregorianCalendar fechaFinalizacion;

    private Publicacion publicacion;

    private String objetivos;

    private String destinatarios;

    private String idioma;

    private XMLGregorianCalendar fechaPresentacion;

    private String horas;

    public Congreso()
    {
    }

    public String getTipoEvento()
    {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento)
    {
        this.tipoEvento = tipoEvento;
    }

    public String getTipoEventoOtros()
    {
        return tipoEventoOtros;
    }

    public void setTipoEventoOtros(String tipoEventoOtros)
    {
        this.tipoEventoOtros = tipoEventoOtros;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = Utils.limpiaCadena(titulo);
    }

    public String getIntervencion()
    {
        return intervencion;
    }

    public void setIntervencion(String intervencion)
    {
        this.intervencion = intervencion;
    }

    public String getIntervencionOtros()
    {
        return intervencionOtros;
    }

    public void setIntervencionOtros(String intervencionOtros)
    {
        this.intervencionOtros = intervencionOtros;
    }

    public String getAmbito()
    {
        return ambito;
    }

    public void setAmbito(String ambito)
    {
        this.ambito = AmbitoDifusion.getCodigoTipo(ambito);
    }

    public String getAmbitoOtros()
    {
        return ambitoOtros;
    }

    public void setAmbitoOtros(String ambitoOtros)
    {
        this.ambitoOtros = ambitoOtros;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = Utils.limpiaCadena(nombre);
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

    public String getCiudad()
    {
        return ciudad;
    }

    public void setCiudad(String ciudad)
    {
        this.ciudad = ciudad;
    }

    public XMLGregorianCalendar getFecha()
    {
        return fecha;
    }

    public void setFecha(Date fecha)
    {
        if (fecha != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(fecha);
            try
            {
                this.fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            }
            catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public XMLGregorianCalendar getAno()
    {
        return ano;
    }

    public void setAno(Long ano)
    {
        if (ano != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.set(GregorianCalendar.YEAR, ano.intValue());
            calendar.set(GregorianCalendar.MONTH, GregorianCalendar.JANUARY);
            calendar.set(GregorianCalendar.DATE, 1);
            try
            {
                this.ano = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            }
            catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public Boolean getIsPublicadoActas()
    {
        return isPublicadoActas;
    }

    public void setIsPublicadoActas(Boolean isPublicadoActas)
    {
        this.isPublicadoActas = isPublicadoActas;
    }

    public Boolean getHasComiteExterno()
    {
        return hasComiteExterno;
    }

    public void setHasComiteExterno(Boolean hasComiteExterno)
    {
        this.hasComiteExterno = hasComiteExterno;
    }

    public Entidad getEntidadOrganizadora()
    {
        return entidadOrganizadora;
    }

    public void setEntidadOrganizadora(Entidad entidadOrganizadora)
    {
        this.entidadOrganizadora = entidadOrganizadora;
    }

    public XMLGregorianCalendar getFechaFinalizacion()
    {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion)
    {
        if (fechaFinalizacion != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(fechaFinalizacion);
            try
            {
                this.fechaFinalizacion = DatatypeFactory.newInstance()
                        .newXMLGregorianCalendar(calendar);
            }
            catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public Publicacion getPublicacion()
    {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion)
    {
        this.publicacion = publicacion;
    }

    public static Long getTipoPublicacionFrom(String isbn)
    {
        if (isbn != null)
        {
            Pattern patternIssn = Pattern
                    .compile("^[0-9]{4}[-][0-9]{3}[0-9xX]");
            Matcher matcherIssn = patternIssn.matcher(isbn);

            if (matcherIssn.matches())
            {
                return TipoProduccion.ARTICULO.getTipoId();
            }

            Pattern patternIsbn = Pattern
                    .compile("^([0-9]{9}|[0-9]{12})[0-9xX]");
            Matcher matcherIsbn = patternIsbn.matcher(isbn);

            if (matcherIsbn.matches())
            {
                return TipoProduccion.CAPITULOLIBRO.getTipoId();
            }
        }

        return TipoProduccion.OTHERS.getTipoId();
    }

    public static String getEditorialPublicacionIfCapituloLibro(String editorial,
            String tipoPublicacion)
    {
        if (tipoPublicacion != null
                && tipoPublicacion.equals(TipoProduccion.CAPITULOLIBRO.getTipo()))
        {
            return editorial;
        }

        return null;
    }

    public static String getIsbnOrIssnFrom(String isbn)
    {
        if (isbn != null)
        {
            Pattern patternIssn = Pattern
                    .compile("^[0-9]{4}[-][0-9]{3}[0-9xX]");
            Matcher matcherIssn = patternIssn.matcher(isbn);

            Pattern patternIsbn = Pattern
                    .compile("^([0-9]{9}|[0-9]{12})[0-9xX]");
            Matcher matcherIsbn = patternIsbn.matcher(isbn);

            if (matcherIssn.matches() || matcherIsbn.matches())
            {
                return isbn;
            }
        }

        return null;
    }

    public String getObjetivos()
    {
        return objetivos;
    }

    public void setObjetivos(String objetivos)
    {
        this.objetivos = objetivos;
    }

    public String getDestinatarios()
    {
        return destinatarios;
    }

    public void setDestinatarios(String destinatarios)
    {
        this.destinatarios = destinatarios;
    }

    public String getIdioma()
    {
        return idioma;
    }

    public void setIdioma(String idioma)
    {
        this.idioma = idioma;
    }

    public XMLGregorianCalendar getFechaPresentacion()
    {
        return fechaPresentacion;
    }

    public void setFechaPresentacion(Date fechaPresentacion)
    {

        if (fechaPresentacion != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(fechaPresentacion);
            try
            {
                this.fechaPresentacion = DatatypeFactory.newInstance()
                        .newXMLGregorianCalendar(calendar);
            }
            catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public String getHoras()
    {
        return horas;
    }

    public void setHoras(String horas)
    {
        this.horas = horas;
    }

}
