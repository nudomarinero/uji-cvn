package es.uji.apps.cvn.model.cvn.proyectos;

import java.util.ArrayList;
import java.util.List;

import es.uji.apps.cvn.model.cvn.CVNItem;
import es.uji.apps.cvn.model.cvn.Entidad;

public class FinanciacionProyectoNoCompetitivo implements FinanciacionProyecto
{
    private CVNItem cvnItem;

    private List<Entidad> entidadesFinanciadoras;

    public FinanciacionProyectoNoCompetitivo(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    public List<Entidad> getEntidadesFinanciadoras()
    {
        if (entidadesFinanciadoras == null)
        {
            entidadesFinanciadoras = new ArrayList<Entidad>();
        }
        return entidadesFinanciadoras;
    }

    public void setEntidadesFinanciadoras(List<Entidad> entidadesFinanciadoras)
    {
        this.entidadesFinanciadoras = entidadesFinanciadoras;
    }

    @Override
    public void addNombrePrograma(String nombrePrograma)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.020.170", nombrePrograma));
    }

    @Override
    public void addCodigoProyecto(String codigoProyecto)
    {
        cvnItem.addCvnExternalPKBean(cvnItem.buildExternalPKBean("050.020.020.110", codigoProyecto));
    }

    @Override
    public void addFinanciacionTotal(Double presupuesto)
    {
        cvnItem.addCvnDouble(cvnItem.buildCvnDouble("050.020.020.200", presupuesto));
    }

    @Override
    public void addFinanciacionSubproyecto(Double presupuesto)
    {
        cvnItem.addCvnDouble(cvnItem.buildCvnDouble("050.020.020.210", presupuesto));
    }

    @Override
    public void addFinanciacionPorcentaje(Double porcentaje)
    {
        cvnItem.addCvnDouble(cvnItem.buildCvnDouble("050.020.020.220", porcentaje));
    }

    @Override
    public void addFinanciacionCredito(Double credito)
    {
        cvnItem.addCvnDouble(cvnItem.buildCvnDouble("050.020.020.230", credito));
    }

    @Override
    public void addFinanciacionMixto(Double mixto)
    {
        cvnItem.addCvnDouble(cvnItem.buildCvnDouble("050.020.020.240", mixto));
    }

    @Override
    public Entidad addEntidadFinanciadora()
    {
        Entidad entidadFinanciadora = new EntidadFinanciadoraProyectoNoCompetitivo(
                cvnItem);
        this.getEntidadesFinanciadoras().add(entidadFinanciadora);
        cvnItem.addCvnCodeGroup((EntidadFinanciadoraProyectoNoCompetitivo) entidadFinanciadora);

        return entidadFinanciadora;
    }

}
