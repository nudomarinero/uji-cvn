package es.uji.apps.cvn.publicacion;

import java.util.ArrayList;
import java.util.List;

public class GrupoMenu
{
    private String nombre;
    private List<ItemMenu> items;
    
    public GrupoMenu(String nombre)
    {
        this.nombre = nombre;
        items = new ArrayList<ItemMenu>();
    }
    
    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public List<ItemMenu> getItems()
    {
        return items;
    }
    
    public void addItem(ItemMenu item)
    {
        items.add(item);
    }
}
