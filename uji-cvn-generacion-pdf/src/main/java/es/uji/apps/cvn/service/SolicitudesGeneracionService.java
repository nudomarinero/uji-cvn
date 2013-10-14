package es.uji.apps.cvn.service;

import es.uji.apps.cvn.dao.CvnGeneradoDAO;
import es.uji.apps.cvn.db.CvnGeneradoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudesGeneracionService
{
    @Autowired
    public CvnGeneradoDAO cvnGeneradoDAO;

    public List<CvnGeneradoDTO> getSolicitudesPendientes()
    {
        return cvnGeneradoDAO.getCvnsPendientesGeneracion();
    }
}
