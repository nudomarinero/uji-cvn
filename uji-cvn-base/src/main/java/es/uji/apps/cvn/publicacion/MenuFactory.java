package es.uji.apps.cvn.publicacion;

import es.uji.commons.web.template.model.GrupoMenu;
import es.uji.commons.web.template.model.ItemMenu;
import es.uji.commons.web.template.model.Menu;

public class MenuFactory
{
    private static final String IDIOMA_CA = "ca";
    private static final String IDIOMA_ES = "es";
    private static final String IDIOMA_EN = "en";

    public static Menu builMenuUsuario(String idioma)
    {
        return builMenuUsuario(idioma, "");
    }

    public static Menu builMenuUsuario(String idioma, String path)
    {
        Menu menu = new Menu();

        switch (idioma)
        {
        case IDIOMA_CA:
            menu.addGrupo(getMenuUsuarioCa(path));
            break;
        case IDIOMA_ES:
            menu.addGrupo(getMenuUsuarioEs(path));
            break;
        default:
            menu.addGrupo(getMenuUsuarioEn(path));
        }

        return menu;
    }

    public static Menu buildMenuAdmin(String idioma)
    {
        return buildMenuAdmin(idioma, "");
    }

    public static Menu buildMenuAdmin(String idioma, String path)
    {
        Menu menu = new Menu();

        switch (idioma)
        {
        case IDIOMA_CA:
            menu.addGrupo(getMenuAdminCa(path));
            break;
        case IDIOMA_ES:
            menu.addGrupo(getMenuAdminEs(path));
            break;
        default:
            menu.addGrupo(getMenuAdminEn(path));
        }

        return menu;
    }

    private static GrupoMenu getMenuUsuarioCa(String path)
    {
        GrupoMenu grupoMenu = new GrupoMenu("Ferramentes CVN");

        ItemMenu consulta = new ItemMenu("Consulta", path + "consulta");
        grupoMenu.addItem(consulta);

        ItemMenu plantillas = new ItemMenu("Plantilles", path + "plantillas");
        grupoMenu.addItem(plantillas);

        return grupoMenu;
    }

    private static GrupoMenu getMenuUsuarioEs(String path)
    {
        GrupoMenu grupoMenu = new GrupoMenu("Herramientas CVN");

        ItemMenu consulta = new ItemMenu("Consulta", path + "consulta");
        grupoMenu.addItem(consulta);

        ItemMenu plantillas = new ItemMenu("Plantillas", path + "plantillas");
        grupoMenu.addItem(plantillas);

        return grupoMenu;
    }

    private static GrupoMenu getMenuUsuarioEn(String path)
    {
        GrupoMenu grupoMenu = new GrupoMenu("CVN Tools");

        ItemMenu consulta = new ItemMenu("Obtention", path + "consulta");
        grupoMenu.addItem(consulta);

        ItemMenu plantillas = new ItemMenu("Templates", path + "plantillas");
        grupoMenu.addItem(plantillas);

        return grupoMenu;
    }

    private static GrupoMenu getMenuAdminCa(String path)
    {
        GrupoMenu grupoMenu = new GrupoMenu("Administració CVN");

        ItemMenu consulta = new ItemMenu("Consulta", path + "consulta");
        grupoMenu.addItem(consulta);

        ItemMenu plantillas = new ItemMenu("Plantilles", path + "plantillas");
        grupoMenu.addItem(plantillas);

        return grupoMenu;
    }

    private static GrupoMenu getMenuAdminEs(String path)
    {
        GrupoMenu grupoMenu = new GrupoMenu("Administración CVN");

        ItemMenu consulta = new ItemMenu("Consulta", path + "consulta");
        grupoMenu.addItem(consulta);

        ItemMenu plantillas = new ItemMenu("Plantillas", path + "plantillas");
        grupoMenu.addItem(plantillas);

        return grupoMenu;
    }

    private static GrupoMenu getMenuAdminEn(String path)
    {
        GrupoMenu grupoMenu = new GrupoMenu("CVN Admin");

        ItemMenu consulta = new ItemMenu("Obtention", path + "consulta");
        grupoMenu.addItem(consulta);

        ItemMenu plantillas = new ItemMenu("Templates", path + "plantillas");
        grupoMenu.addItem(plantillas);

        return grupoMenu;
    }

}