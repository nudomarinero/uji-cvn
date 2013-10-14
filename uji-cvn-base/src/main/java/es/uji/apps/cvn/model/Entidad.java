package es.uji.apps.cvn.model;

public class Entidad
{
    private Long id;

    private String acronimo;

    private String ciudad;

    private String nombre;

    private String pais;

    private String region;

    private String regionStr;

    private String subtipo;

    private String tipo;

    private String tipoDescr;
    
    public static Long GV_ID = 67671L;

    public Entidad()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getAcronimo()
    {
        return acronimo;
    }

    public void setAcronimo(String acronimo)
    {
        this.acronimo = acronimo;
    }

    public String getCiudad()
    {
        return ciudad;
    }

    public void setCiudad(String ciudad)
    {
        this.ciudad = ciudad;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
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

    public String getRegionStr()
    {
        return regionStr;
    }

    public void setRegionStr(String regionStr)
    {
        this.regionStr = regionStr;
    }

    public String getSubtipo()
    {
        return subtipo;
    }

    public void setSubtipo(String subtipo)
    {
        this.subtipo = subtipo;
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public String getTipoDescr()
    {
        return tipoDescr;
    }

    public void setTipoDescr(String tipoDescr)
    {
        this.tipoDescr = tipoDescr;
    }

}
