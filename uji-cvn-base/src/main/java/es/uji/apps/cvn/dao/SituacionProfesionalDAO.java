package es.uji.apps.cvn.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.impl.JPAQuery;

import es.uji.apps.cvn.db.QSituacionProfesionalDTO;
import es.uji.apps.cvn.db.SituacionProfesionalDTO;
import es.uji.apps.cvn.model.SituacionProfesional;
import es.uji.commons.db.BaseDAODatabaseImpl;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

@Repository
public class SituacionProfesionalDAO extends BaseDAODatabaseImpl
{

    private static final Logger log = Logger.getLogger(SituacionProfesionalDAO.class);

    public List<SituacionProfesional> getSituacionPersonalId(Long personaId, boolean activo)
            throws RegistroNoEncontradoException
    {

        Long mili = System.currentTimeMillis();

        JPAQuery query = new JPAQuery(entityManager);
        QSituacionProfesionalDTO qSituacionProfesional = QSituacionProfesionalDTO.situacionProfesionalDTO;

        List<SituacionProfesionalDTO> listaSituacionProfesionalDTO = null;
        if (activo)
        {
            listaSituacionProfesionalDTO = query
                    .from(qSituacionProfesional)
                    .where(qSituacionProfesional.perId.eq(personaId).and(
                            qSituacionProfesional.duracion.isNull())).list(qSituacionProfesional);
        }
        else
        {
            listaSituacionProfesionalDTO = query
                    .from(qSituacionProfesional)
                    .where(qSituacionProfesional.perId.eq(personaId).and(
                            qSituacionProfesional.duracion.isNotNull()))
                    .list(qSituacionProfesional);
        }

        List<SituacionProfesional> listaSituacionPersonal = new ArrayList<SituacionProfesional>();

        for (SituacionProfesionalDTO situacionPersonalDTO : listaSituacionProfesionalDTO)
        {
            listaSituacionPersonal.add(creaSituacionPersonalDesde(situacionPersonalDTO));
        }

        mili = System.currentTimeMillis() - mili;
        log.info("SituacionProfesionalDAO.getSituacionProfesionalPersonaId " + mili);

        return listaSituacionPersonal;

    }

    private SituacionProfesional creaSituacionPersonalDesde(SituacionProfesionalDTO spDTO)
    {

        SituacionProfesional sp = new SituacionProfesional();

        sp.setId(spDTO.getId());
        sp.setPerId(spDTO.getPerId());
        sp.setLineaId(spDTO.getLineaId());
        sp.setGestionDocente(spDTO.getGestionDocente().equals("S"));
        sp.setNombreEntidad(spDTO.getNombreEntidad());
        sp.setTipoEntidad(spDTO.getTipoEntidad());
        sp.setTipoEntidadOtros(spDTO.getTipoEntidadOtros());
        sp.setCentroVal(spDTO.getCentroVal());
        sp.setCentroCas(spDTO.getCentroCas());
        sp.setCentroIng(spDTO.getCentroIng());
        sp.setServicioVal(spDTO.getServicioVal());
        sp.setServicioCas(spDTO.getServicioCas());
        sp.setServicioIng(spDTO.getServicioIng());
        sp.setCiudad(spDTO.getCiudad());
        sp.setRegion(spDTO.getRegion());
        sp.setPais(spDTO.getPais());
        sp.setTelefono(spDTO.getTelefono());
        sp.setFax(spDTO.getFax());
        sp.setMail(spDTO.getMail());
        sp.setCategoria(spDTO.getCategoria());
        sp.setFechaInicio(spDTO.getFechaInicio());
        sp.setDuracion(spDTO.getDuracion());
        sp.setSituacion(spDTO.getSituacion());
        sp.setSituacionOtros(spDTO.getSituacionOtros());
        sp.setDedicacion(spDTO.getDedicacion());
        sp.setUnesco1(spDTO.getUnesco1());
        sp.setUnesco2(spDTO.getUnesco2());
        sp.setUnesco3(spDTO.getUnesco3());
        sp.setTextoDedicacion(spDTO.getTextoDedicacion());
        sp.setPalabrasClave(spDTO.getPalabrasClave());

        return sp;
    }
}
