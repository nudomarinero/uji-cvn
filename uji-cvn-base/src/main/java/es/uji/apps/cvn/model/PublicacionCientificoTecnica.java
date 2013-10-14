package es.uji.apps.cvn.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PublicacionCientificoTecnica
{
    private Long id;

    private Publicacion publicacion;

    private List<Impacto> listaImpacto;

    private Double nResenyasEnRevistas;

    private List<String> traducciones;

    final private static String TRADUCCIONES_SEP = ";";

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Publicacion getPublicacion()
    {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion)
    {
        this.publicacion = publicacion;
    }

    public List<Impacto> getListaImpacto()
    {
        return listaImpacto;
    }

    public void setListaImpacto(List<Impacto> listaImpacto)
    {
        this.listaImpacto = listaImpacto;
    }

    public Double getNResenyasEnRevistas()
    {
        return nResenyasEnRevistas;
    }

    public void setNResenyasEnRevistas(Long nResenyasEnRevistas)
    {
        if (nResenyasEnRevistas != null)
        {
            this.nResenyasEnRevistas = nResenyasEnRevistas.doubleValue();
        }
    }

    public List<String> getTraducciones()
    {
        return traducciones;
    }

    public void setTraducciones(String traducciones)
    {
        if (traducciones != null)
        {
            this.traducciones = Arrays.asList(traducciones.split(TRADUCCIONES_SEP));
        }
        else
        {
            this.traducciones = new ArrayList<String>();
        }
    }
}
