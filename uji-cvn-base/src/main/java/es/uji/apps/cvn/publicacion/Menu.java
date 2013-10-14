package es.uji.apps.cvn.publicacion;

import java.util.ArrayList;
import java.util.List;

public class Menu
{
    private List<GrupoMenu> grupos;

    public Menu()
    {
        grupos = new ArrayList<GrupoMenu>();
    }

    public List<GrupoMenu> getGrupos()
    {
        return grupos;
    }

    public void addGrupo(GrupoMenu grupo)
    {
        grupos.add(grupo);
    }
}
