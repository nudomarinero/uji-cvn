package es.uji.apps.cvn.model.cvn.publicaciones;

import java.util.ArrayList;
import java.util.List;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class ParticipacionEnPublicacion extends CVNItem
{
    private Publicacion publicacion;

    private List<Impacto> listaImpacto;

    private List<Citas> listaCitas;

    public ParticipacionEnPublicacion()
    {
        this.code = "060.010.010.000";
    }

    public Publicacion getPublicacion()
    {
        if (publicacion == null)
        {
            publicacion = new PublicacionCientificoTecnica(this);
        }
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion)
    {
        this.publicacion = publicacion;
    }

    public List<Impacto> getListaImpacto()
    {
        if (listaImpacto == null)
        {
            listaImpacto = new ArrayList<Impacto>();
        }
        return listaImpacto;
    }

    public void setListaImpacto(List<Impacto> listaImpacto)
    {
        this.listaImpacto = listaImpacto;
    }

    public Impacto addNewImpacto()
    {
        Impacto impacto = new Impacto(this);
        this.getListaImpacto().add(impacto);
        this.addCvnCodeGroup(impacto);

        return impacto;
    }

    public List<Citas> getListaCitas()
    {
        if (listaCitas == null)
        {
            listaCitas = new ArrayList<Citas>();
        }
        return listaCitas;
    }

    public void setListaCitas(List<Citas> listaCitas)
    {
        this.listaCitas = listaCitas;
    }

    public Citas addNewCitas()
    {
        Citas citas = new Citas(this);
        this.getListaCitas().add(citas);
        this.addCvnCodeGroup(citas);

        return citas;
    }

    public void addPosicionAutor(Double posicion)
    {
        this.addCvnDouble(this.buildCvnDouble("060.010.010.050", posicion));
    }

    public void addCalidad(String calidad)
    {
        this.addCvnString(this.buildCvnString("060.010.010.060", calidad));
    }

    public void addResultadosDestacados(String resultadosDestacados)
    {
        this.addCvnString(this.buildCvnString("060.010.010.290", resultadosDestacados));
    }

    public void addPublicacionRelevante(Boolean isPublicacionRelevante)
    {
        this.addCvnBoolean(this.buildCvnBoolean("060.010.010.300", isPublicacionRelevante));
    }

    public void addResenyasEnRevistas(Double resenyaRevista)
    {
        this.addCvnDouble(this.buildCvnDouble("060.010.010.340", resenyaRevista));
    }

    public void addTraducciones(String traduccionId, String traduccion)
    {
        this.addCvnTitleBean(this.buildCvnTitleBean("060.010.010.350", traduccionId, traduccion));
    }

    public void addTraducciones(String traduccionId)
    {
        this.addTraducciones(traduccionId, null);
    }
}
