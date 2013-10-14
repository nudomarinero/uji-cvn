package es.uji.apps.cvn.model;

import es.uji.apps.cvn.translators.CategoriaImpacto;
import es.uji.apps.cvn.translators.FuenteImpacto;

public class Impacto
{
    private String indice;

    private String fuente;

    private String fuenteOtros;

    private String categoria;

    private Double posicion;

    private Double nRevistas;

    private Boolean isDentroPorc25Top;

    public String getIndice()
    {
        return indice;
    }

    public void setIndice(Float indice)
    {
        if (indice != null)
        {
            this.indice = indice.toString();
        }
    }

    public String getFuente()
    {
        return fuente;
    }

    public void setFuente(String fuente)
    {
        this.fuente = FuenteImpacto.getCodigoTipo(fuente);
    }

    public String getFuenteOtros()
    {
        return fuenteOtros;
    }

    public void setFuenteOtros(String fuenteOtros)
    {
        this.fuenteOtros = fuenteOtros;
    }

    public String getCategoria()
    {
        return categoria;
    }

    public void setCategoria(String categoria1, String categoria2)
    {
        if (categoria1 != null && categoria2 != null)
        {
            this.categoria = CategoriaImpacto.getCodigoTipo(categoria1, categoria2);
        }
    }

    public Double getPosicion()
    {
        return posicion;
    }

    public void setPosicion(Long posicion)
    {
        if (posicion != null)
        {
            this.posicion = posicion.doubleValue();
        }
    }

    public Double getNRevistas()
    {
        return nRevistas;
    }

    public void setNRevistas(Long nRevistas)
    {
        if (nRevistas != null)
        {
            this.nRevistas = nRevistas.doubleValue();
        }
    }

    public Boolean getIsDentroPorc25Top()
    {
        return isDentroPorc25Top;
    }

    public void setIsDentroPorc25Top(Boolean isDentroPorc25Top)
    {
        this.isDentroPorc25Top = isDentroPorc25Top;
    }

}
