package es.uji.apps.cvn.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.uji.apps.cvn.builders.EntidadBuilder;
import es.uji.apps.cvn.builders.PersonaBuilder;
import es.uji.apps.cvn.model.ParticipacionProyecto;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ProyectoDAOTest
{
    @Autowired
    private ProyectoDAO proyectoDAO;

    @Autowired
    private PersonaDAO personaDAO;

    private boolean cambiosEntidades = false;

    private boolean cambiosPersonas = false;

    final private static Long ENTIDAD_ID = 242733L;

    final private static Long PROYECTO_ID = 11898L;

    final private static Long PERSONA_ID = 369L;

    @Before
    @Transactional
    public void init()
    {
        try
        {
            proyectoDAO.getEntidadById(ENTIDAD_ID);
        }
        catch (Exception e)
        {
            new EntidadBuilder(proyectoDAO).withId(ENTIDAD_ID).withAcronimo("E1")
                    .withNombre("Entidad 1").build();

            cambiosEntidades = true;
        }

        try
        {
            personaDAO.getPersonaById(PERSONA_ID);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            new PersonaBuilder(personaDAO).withId(PERSONA_ID).withNombre("María")
                    .withApellido1("Gómez").withApellido2("Sirvent")
                    .withIdentificacion("12345678X").withTipoIdentificacion("DNI").build();

            cambiosPersonas = true;
        }
    }

    @Test
    public void getProyectosEnLosQueParticipaUnInvestigadorTest()
            throws RegistroNoEncontradoException
    {
        List<ParticipacionProyecto> participacionesProyectos = proyectoDAO
                .getParticipacionesEnProyectosByPersonaId(PERSONA_ID);

        if (participacionesProyectos.size() > 0)
        {
            ParticipacionProyecto participacion = participacionesProyectos.get(0);
            assertNotNull(participacion.getProyectoInvestigacion());

            if (participacion.getProyectoInvestigacion().getId().equals(PROYECTO_ID))
            {
                assertTrue(participacion.getProyectoInvestigacion().getEntidadesFinanciadoras()
                        .size() >= 1);
            }
        }
    }

    @After
    @Transactional
    public void limpiaDatosBD()
    {
        if (cambiosEntidades)
        {
            proyectoDAO.eliminaEntidad(ENTIDAD_ID);
        }
        if (cambiosPersonas)
        {
            personaDAO.eliminaPersona(PERSONA_ID);
        }
    }
}
