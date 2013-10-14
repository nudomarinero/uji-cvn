package es.uji.apps.cvn.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.impl.JPAQuery;

import es.uji.apps.cvn.db.PlantillaDTO;
import es.uji.apps.cvn.db.QPlantillaDTO;
import es.uji.apps.cvn.model.PlantillaCvn;
import es.uji.commons.db.BaseDAODatabaseImpl;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

@Repository
public class PlantillaDAO extends BaseDAODatabaseImpl
{
    public List<PlantillaCvn> getListaPlantillasByPersonaId(Long personaId)
    {
        JPAQuery query = new JPAQuery(entityManager);
        QPlantillaDTO qPlantilla = QPlantillaDTO.plantillaDTO;

        List<PlantillaDTO> listaPlantillasDTO = query.from(qPlantilla)
                .where(qPlantilla.persona.eq(personaId)).list(qPlantilla);

        List<PlantillaCvn> listaPlantillas = new ArrayList<PlantillaCvn>();

        for (PlantillaDTO plantillaDTO : listaPlantillasDTO)
        {
            listaPlantillas.add(creaPlantillaCvnFrom(plantillaDTO));
        }

        return listaPlantillas;
    }

    private PlantillaCvn creaPlantillaCvnFrom(PlantillaDTO plantillaDTO)
    {
        PlantillaCvn plantilla = new PlantillaCvn();
        plantilla.setId(plantillaDTO.getId());
        plantilla.setPersona(plantillaDTO.getPersona());
        plantilla.setFechaUltimaActualizacion(plantillaDTO.getFechaUltimaActualizacion());
        plantilla.setNombre(plantillaDTO.getNombre());
        plantilla.setIdioma(plantillaDTO.getIdioma());
        plantilla.setPlantilla(plantillaDTO.getPlantilla());

        return plantilla;
    }

    public PlantillaCvn getPlantillaByPlantillaId(Long plantillaId)
            throws RegistroNoEncontradoException
    {
        PlantillaDTO plantillaDTO;

        try
        {
            plantillaDTO = get(PlantillaDTO.class, plantillaId).get(0);
        }
        catch (Exception e)
        {
            throw new RegistroNoEncontradoException();
        }

        return creaPlantillaCvnFrom(plantillaDTO);
    }

    public void deletePlantilla(PlantillaCvn plantilla)
    {
        delete(PlantillaDTO.class, plantilla.getId());
    }

    public PlantillaCvn insertPlantilla(PlantillaCvn plantillaCvn)
    {
        PlantillaDTO plantillaDTO = creaPlantillaDTOFrom(plantillaCvn);
        plantillaDTO = insert(plantillaDTO);
        plantillaCvn.setId(plantillaDTO.getId());

        return plantillaCvn;
    }

    public void updatePlantilla(PlantillaCvn plantillaCvn)
    {
        update(creaPlantillaDTOFrom(plantillaCvn));
    }

    private PlantillaDTO creaPlantillaDTOFrom(PlantillaCvn plantillaCvn)
    {
        PlantillaDTO plantillaDTO = new PlantillaDTO();
        plantillaDTO.setId(plantillaCvn.getId());
        plantillaDTO.setPersona(plantillaCvn.getPersona());
        plantillaDTO.setNombre(plantillaCvn.getNombre());
        plantillaDTO.setIdioma(plantillaCvn.getIdioma());
        plantillaDTO.setPlantilla(plantillaCvn.getPlantilla());
        plantillaDTO.setFechaUltimaActualizacion(plantillaCvn.getFechaUltimaActualizacion());

        return plantillaDTO;
    }
}
