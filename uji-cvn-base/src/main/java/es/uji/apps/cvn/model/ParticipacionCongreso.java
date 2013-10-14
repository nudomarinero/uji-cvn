package es.uji.apps.cvn.model;

import java.util.Collections;
import java.util.List;

import es.uji.apps.cvn.model.comparators.ParticipacionCongresoComparator;
import es.uji.apps.cvn.translators.TipoParticipacionCongreso;

public class ParticipacionCongreso
{
    private Long id;

    private Congreso congreso;

    private String tipoParticipacion;

    public ParticipacionCongreso()
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

    public Congreso getCongreso()
    {
        return congreso;
    }

    public void setCongreso(Congreso congreso)
    {
        this.congreso = congreso;
    }

    public String getTipoParticipacion()
    {
        return tipoParticipacion;
    }

    public void setTipoParticipacion(String participacion)
    {
        this.tipoParticipacion = TipoParticipacionCongreso.getCodigoTipo(participacion);
    }

    public static void ordenaListaParticipacionCongresosPorFechaCongreso(
            List<ParticipacionCongreso> participacionCongresos)
    {
        Collections.sort(participacionCongresos, new ParticipacionCongresoComparator());
    }

}
