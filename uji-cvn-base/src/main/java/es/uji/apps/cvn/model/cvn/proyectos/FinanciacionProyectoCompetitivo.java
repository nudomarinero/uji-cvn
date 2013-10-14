package es.uji.apps.cvn.model.cvn.proyectos;

import java.util.ArrayList;
import java.util.List;

import es.uji.apps.cvn.model.cvn.CVNItem;
import es.uji.apps.cvn.model.cvn.Entidad;

public class FinanciacionProyectoCompetitivo implements FinanciacionProyecto
{
    private CVNItem cvnItem;

    private List<Entidad> entidadesFinanciadoras;

    public FinanciacionProyectoCompetitivo(CVNItem cvnItem)
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
        cvnItem.addCvnString(cvnItem.buildCvnString("050.020.010.250", nombrePrograma));
    }

    @Override
    public void addCodigoProyecto(String codigoProyecto)
    {
        cvnItem.addCvnExternalPKBean(cvnItem.buildExternalPKBean("050.020.010.260", codigoProyecto));
    }

    @Override
    public void addFinanciacionTotal(Double presupuesto)
    {
        cvnItem.addCvnDouble(cvnItem.buildCvnDouble("050.020.010.290", presupuesto));
    }

    @Override
    public void addFinanciacionSubproyecto(Double presupuesto)
    {
        cvnItem.addCvnDouble(cvnItem.buildCvnDouble("050.020.010.300", presupuesto));
    }

    @Override
    public void addFinanciacionPorcentaje(Double porcentaje)
    {
        cvnItem.addCvnDouble(cvnItem.buildCvnDouble("050.020.010.310", porcentaje));
    }

    @Override
    public void addFinanciacionCredito(Double credito)
    {
        cvnItem.addCvnDouble(cvnItem.buildCvnDouble("050.020.010.320", credito));
    }

    @Override
    public void addFinanciacionMixto(Double mixto)
    {
        cvnItem.addCvnDouble(cvnItem.buildCvnDouble("050.020.010.330", mixto));
    }

    @Override
    public Entidad addEntidadFinanciadora()
    {
        Entidad entidadFinanciadora = new EntidadFinanciadoraProyectoCompetitivo(cvnItem);
        this.getEntidadesFinanciadoras().add(entidadFinanciadora);
        cvnItem.addCvnCodeGroup((EntidadFinanciadoraProyectoCompetitivo) entidadFinanciadora);

        return entidadFinanciadora;
    }

}
