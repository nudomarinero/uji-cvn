package es.uji.apps.cvn.model.plantilla;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

import es.uji.apps.cvn.client.exceptions.PlantillaNoAutorizadaException;
import es.uji.apps.cvn.model.plantilla.categorias.Congresos;
import es.uji.apps.cvn.model.plantilla.categorias.CongresosDocentes;
import es.uji.apps.cvn.model.plantilla.categorias.Contratos;
import es.uji.apps.cvn.model.plantilla.categorias.GruposInvestigacion;
import es.uji.apps.cvn.model.plantilla.categorias.Proyectos;
import es.uji.apps.cvn.model.plantilla.categorias.Publicaciones;
import es.uji.apps.cvn.model.plantilla.categorias.PublicacionesDocentes;
import es.uji.apps.cvn.model.plantilla.categorias.SituacionProfesionalActiva;
import es.uji.apps.cvn.model.plantilla.categorias.SituacionProfesionalAnterior;
import es.uji.apps.cvn.model.plantilla.categorias.Thesis;

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

    private Thesis tesis;

    private SituacionProfesionalActiva situacionProfesionalActiva;

    private SituacionProfesionalAnterior situacionProfesionalAnterior;

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

    public Thesis getTesis()
    {
        return tesis;
    }

    public void setTesis(Thesis tesis)
    {
        this.tesis = tesis;
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

    public static byte[] serialize(Plantilla plantilla) throws IOException
    {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bs);
        os.writeObject(plantilla);
        os.close();

        return bs.toByteArray();
    }

    public static Plantilla unserialize(byte[] plantillaBytes) throws IOException,
            ClassNotFoundException
    {
        ByteArrayInputStream bs = new ByteArrayInputStream(plantillaBytes);
        ObjectInputStream is = new ObjectInputStream(bs);
        Plantilla plantilla = (Plantilla) is.readObject();
        is.close();

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
        plantilla.setTesis(new Thesis());
        plantilla.setSituacionProfesionalActiva(new SituacionProfesionalActiva());
        plantilla.setSituacionProfesionalAnterior(new SituacionProfesionalAnterior());

        return plantilla;
    }
}
