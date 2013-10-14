package es.uji.apps.cvn.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uji.apps.cvn.client.exceptions.PlantillaNoAutorizadaException;
import es.uji.apps.cvn.dao.PlantillaDAO;
import es.uji.apps.cvn.model.PlantillaCvn;
import es.uji.apps.cvn.model.plantilla.Plantilla;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

@Service
public class PlantillaCVNService
{
    @Autowired
    private PlantillaDAO plantillaDAO;

    public List<PlantillaCvn> getListaPlantillasCvnByPersonaId(Long personaId)
    {
        return plantillaDAO.getListaPlantillasByPersonaId(personaId);
    }

    public void deletePlantillaByPlantillaId(Long plantillaId, Long personaId)
            throws RegistroNoEncontradoException, PlantillaNoAutorizadaException
    {
        PlantillaCvn plantillaCvn = plantillaDAO.getPlantillaByPlantillaId(plantillaId);
        plantillaCvn.compruebaPropietarioPlantilla(personaId);
        plantillaDAO.deletePlantilla(plantillaCvn);
    }

    public void guardaPlantillaByPersonaId(Plantilla plantilla, Long personaId)
            throws RegistroNoEncontradoException, PlantillaNoAutorizadaException, IOException
    {
        plantilla.compruebaValidez();

        if (plantilla.getId() != null)
        {
            PlantillaCvn plantillaCvn = plantillaDAO.getPlantillaByPlantillaId(plantilla.getId());
            plantillaCvn.compruebaPropietarioPlantilla(personaId);
            plantillaCvn.updatePlantilla(plantilla.getNombre(), plantilla.getIdioma(),
                    Plantilla.serialize(plantilla));
            plantillaDAO.updatePlantilla(plantillaCvn);
        }
        else
        {
            PlantillaCvn plantillaCvn = new PlantillaCvn();
            plantillaCvn.setNombre(plantilla.getNombre());
            plantillaCvn.setIdioma(plantilla.getIdioma());
            plantillaCvn.setPlantilla(Plantilla.serialize(plantilla));
            plantillaCvn.setPersona(personaId);
            plantillaCvn.setFechaUltimaActualizacion(new Date());
            plantillaDAO.insertPlantilla(plantillaCvn);
        }
    }

    public Plantilla getPlantillaByPlantillaId(Long plantillaId, Long personaId)
            throws RegistroNoEncontradoException, PlantillaNoAutorizadaException,
            ClassNotFoundException, IOException
    {
        PlantillaCvn plantillaCvn = plantillaDAO.getPlantillaByPlantillaId(plantillaId);
        plantillaCvn.compruebaPropietarioPlantilla(personaId);

        Plantilla plantilla = Plantilla.unserialize(plantillaCvn.getPlantilla());
        plantilla.setId(plantillaCvn.getId());

        return plantilla;
    }
}
