package es.uji.apps.cvn.model.plantilla.categorias;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Categoria implements Serializable
{
    private int maxAnyos;

    private int maxItems;

    public int getMaxAnyos()
    {
        return maxAnyos;
    }

    public void setMaxAnyos(int maxAnyos)
    {
        this.maxAnyos = maxAnyos;
    }

    public int getMaxItems()
    {
        return maxItems;
    }

    public void setMaxItems(int maxItems)
    {
        this.maxItems = maxItems;
    }
}
