package es.uji.apps.cvn.dao;

import org.springframework.stereotype.Repository;

import es.uji.apps.cvn.db.LogDTO;
import es.uji.apps.cvn.model.Log;
import es.uji.commons.db.BaseDAODatabaseImpl;

@Repository
public class LogDAO extends BaseDAODatabaseImpl
{
    public void insertLog(Log log)
    {
        LogDTO logDTO = new LogDTO();
        logDTO.setPersonaCvnId(log.getPersonaCvnId());
        logDTO.setSolicitante(log.getSolicitante());
        logDTO.setFecha(log.getFecha());
        logDTO.setMensaje(log.getMensaje());
        logDTO.setError(log.getError());

        insert(logDTO);
    }
}
