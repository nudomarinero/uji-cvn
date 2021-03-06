package es.uji.apps.cvn.model.plantilla;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import es.uji.apps.cvn.client.exceptions.PlantillaNoAutorizadaException;
import es.uji.apps.cvn.model.plantilla.categorias.Congresos;
import es.uji.apps.cvn.model.plantilla.categorias.CongresosDocentes;
import es.uji.apps.cvn.model.plantilla.categorias.Contratos;
import es.uji.apps.cvn.model.plantilla.categorias.Docencia;
import es.uji.apps.cvn.model.plantilla.categorias.Doctorados;
import es.uji.apps.cvn.model.plantilla.categorias.GruposInvestigacion;
import es.uji.apps.cvn.model.plantilla.categorias.Proyectos;
import es.uji.apps.cvn.model.plantilla.categorias.Publicaciones;
import es.uji.apps.cvn.model.plantilla.categorias.PublicacionesDocentes;
import es.uji.apps.cvn.model.plantilla.categorias.SituacionProfesionalActiva;
import es.uji.apps.cvn.model.plantilla.categorias.SituacionProfesionalAnterior;
import es.uji.apps.cvn.model.plantilla.categorias.Tesis;

@SuppressWarnings("serial")
@XmlRootElement
public class Plantilla implements Serializable
{
    final private static String[] listaIdiomas = { "cat", "spa", "glg", "eus", "eng", "fra" };

    private Long id;

    private String nombre;

    private String idioma;

    private PublicacionesDocentes publicacionesDocentes;

    private CongresosDocentes congresosDocentes;

    private GruposInvestigacion gruposInvestigacion;

    private Proyectos proyectos;

    private Contratos contratos;

    private Publicaciones publicaciones;

    private Congresos congresos;

    private Tesis tesis;

    private Doctorados doctorados;

    private SituacionProfesionalActiva situacionProfesionalActiva;

    private SituacionProfesionalAnterior situacionProfesionalAnterior;

    private Docencia docencia;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getIdioma()
    {
        return idioma;
    }

    public void setIdioma(String idioma)
    {
        this.idioma = idioma;
    }

    public PublicacionesDocentes getPublicacionesDocentes()
    {
        return publicacionesDocentes;
    }

    public void setPublicacionesDocentes(PublicacionesDocentes publicacionesDocentes)
    {
        this.publicacionesDocentes = publicacionesDocentes;
    }

    public CongresosDocentes getCongresosDocentes()
    {
        return congresosDocentes;
    }

    public void setCongresosDocentes(CongresosDocentes congresosDocentes)
    {
        this.congresosDocentes = congresosDocentes;
    }

    public GruposInvestigacion getGruposInvestigacion()
    {
        return gruposInvestigacion;
    }

    public void setGruposInvestigacion(GruposInvestigacion gruposInvestigacion)
    {
        this.gruposInvestigacion = gruposInvestigacion;
    }

    public Proyectos getProyectos()
    {
        return proyectos;
    }

    public void setProyectos(Proyectos proyectos)
    {
        this.proyectos = proyectos;
    }

    public Contratos getContratos()
    {
        return contratos;
    }

    public void setContratos(Contratos contratos)
    {
        this.contratos = contratos;
    }

    public Publicaciones getPublicaciones()
    {
        return publicaciones;
    }

    public void setPublicaciones(Publicaciones publicaciones)
    {
        this.publicaciones = publicaciones;
    }

    public Congresos getCongresos()
    {
        return congresos;
    }

    public void setCongresos(Congresos congresos)
    {
        this.congresos = congresos;
    }

    public Tesis getTesis()
    {
        return tesis;
    }

    public void setTesis(Tesis tesis)
    {
        this.tesis = tesis;
    }

    public Doctorados getDoctorados()
    {
        return doctorados;
    }

    public void setDoctorados(Doctorados doctorados)
    {
        this.doctorados = doctorados;
    }

    public SituacionProfesionalActiva getSituacionProfesionalActiva()
    {
        return situacionProfesionalActiva;
    }

    public void setSituacionProfesionalActiva(SituacionProfesionalActiva situacionProfesionalActiva)
    {
        this.situacionProfesionalActiva = situacionProfesionalActiva;
    }

    public SituacionProfesionalAnterior getSituacionProfesionalAnterior()
    {
        return situacionProfesionalAnterior;
    }

    public void setSituacionProfesionalAnterior(SituacionProfesionalAnterior situacionProfesionalAnterior)
    {
        this.situacionProfesionalAnterior = situacionProfesionalAnterior;
    }

    public Docencia getDocencia()
    {
        return docencia;
    }

    public void setDocencia(Docencia docencia)
    {
        this.docencia = docencia;
    }

    public static byte[] serialize(Plantilla plantilla) throws IOException
    {
//        ByteArrayOutputStream bs = new ByteArrayOutputStream();
//        ObjectOutputStream os = new ObjectOutputStream(bs);
//        os.writeObject(plantilla);
//        os.close();
        ByteArrayOutputStream o = new ByteArrayOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(o, plantilla);

        return o.toByteArray();
    }

    public static Plantilla unserialize(byte[] plantillaBytes) throws IOException,
            ClassNotFoundException
    {
//        ByteArrayInputStream bs = new ByteArrayInputStream(plantillaBytes);
//        ObjectInputStream is = new ObjectInputStream(bs);
//        Plantilla plantilla = (Plantilla) is.readObject();
//        is.close();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Plantilla plantilla = mapper.readValue(plantillaBytes, Plantilla.class);

        return plantilla;
    }

    public void compruebaValidez() throws PlantillaNoAutorizadaException
    {
        if (nombre == null || idioma == null || !Arrays.asList(listaIdiomas).contains(idioma))
        {
            throw new PlantillaNoAutorizadaException();
        }
    }
    
    public static Plantilla getPlantillaPorDefecto(String idioma)
    {
        if (idioma == null || !Arrays.asList(listaIdiomas).contains(idioma))
        {
            idioma = "cat";
        }

        Plantilla plantilla = new Plantilla();
        plantilla.setIdioma(idioma);
        plantilla.setPublicacionesDocentes(new PublicacionesDocentes());
        plantilla.setCongresosDocentes(new CongresosDocentes());
        plantilla.setGruposInvestigacion(new GruposInvestigacion());
        plantilla.setProyectos(new Proyectos());
        plantilla.setContratos(new Contratos());
        plantilla.setPublicaciones(new Publicaciones());
        plantilla.setCongresos(new Congresos());
        plantilla.setTesis(new Tesis());
        plantilla.setDoctorados(new Doctorados());
        plantilla.setSituacionProfesionalActiva(new SituacionProfesionalActiva());
        plantilla.setSituacionProfesionalAnterior(new SituacionProfesionalAnterior());
        plantilla.setDocencia(new Docencia());

        return plantilla;
    }
}
