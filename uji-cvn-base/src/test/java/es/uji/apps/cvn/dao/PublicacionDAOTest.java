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

import es.uji.apps.cvn.builders.PersonaBuilder;
import es.uji.apps.cvn.model.ParticipacionPublicacionCientificoTecnica;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class PublicacionDAOTest
{
    @Autowired
    private PublicacionDAO publicacionDAO;

    @Autowired
    private PersonaDAO personaDAO;

    private boolean cambios = false;

    final private static Long PERSONA_ID = 65097L;

    @Before
    @Transactional
    public void init()
    {
        try
        {
            personaDAO.getPersonaById(PERSONA_ID);
        }
        catch (Exception e)
        {
            new PersonaBuilder(personaDAO).withId(PERSONA_ID).withNombre("María")
                    .withApellido1("Gómez").withApellido2("Sirvent")
                    .withIdentificacion("12345678X").withTipoIdentificacion("DNI").build();

            cambios = true;
        }
    }

    @Test
    public void getPublicacionesEnLasQueParticipaUnInvestigadorTest()
            throws RegistroNoEncontradoException
    {
        List<ParticipacionPublicacionCientificoTecnica> participacionesPublicaciones = publicacionDAO
                .getParticipacionesEnPublicacionesCientificoTecnicasByPersonaId(PERSONA_ID);

        if (participacionesPublicaciones.size() > 0)
        {
            for (ParticipacionPublicacionCientificoTecnica participacion : participacionesPublicaciones)
            {
                assertNotNull(participacion.getPublicacionCientificoTecnica());
                assertTrue(participacion.getPublicacionCientificoTecnica().getPublicacion()
                        .getAutores().size() > 0);
            }
        }
    }

    @After
    @Transactional
    public void limpiaDatosBD()
    {
        if (cambios)
        {
            personaDAO.eliminaPersona(PERSONA_ID);
        }
    }
}
