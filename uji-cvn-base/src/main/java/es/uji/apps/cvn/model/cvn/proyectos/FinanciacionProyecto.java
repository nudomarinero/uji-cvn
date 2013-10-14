package es.uji.apps.cvn.model.cvn.proyectos;

import es.uji.apps.cvn.model.cvn.Entidad;

public interface FinanciacionProyecto
{
    public void addNombrePrograma(String nombrePrograma);

    public void addCodigoProyecto(String codigoProyecto);

    public void addFinanciacionTotal(Double presupuesto);

    public void addFinanciacionSubproyecto(Double presupuesto);

    public void addFinanciacionPorcentaje(Double porcentaje);

    public void addFinanciacionCredito(Double credito);

    public void addFinanciacionMixto(Double mixto);

    public Entidad addEntidadFinanciadora();
}
