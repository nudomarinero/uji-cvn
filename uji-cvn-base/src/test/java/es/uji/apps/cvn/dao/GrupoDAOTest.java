package es.uji.apps.cvn.dao;

import static org.junit.Assert.assertNotNull;

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
import es.uji.apps.cvn.model.ParticipacionGrupo;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class GrupoDAOTest
{
    @Autowired
    private PersonaDAO personaDAO;

    @Autowired
    private GrupoDAO grupoDAO;

    private boolean cambios = false;

    final private static Long PERSONA_ID = 1334L; // 2 grupos

    final private static Long RESPONSABLE_1_ID = 65148L;

    final private static Long RESPONSABLE_2_ID = 60166L;

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

            new PersonaBuilder(personaDAO).withId(RESPONSABLE_1_ID).withNombre("Responsable")
                    .withApellido1("1").withApellido2("1").build();

            new PersonaBuilder(personaDAO).withId(RESPONSABLE_2_ID).withNombre("Responsable")
                    .withApellido1("2").withApellido2("2").build();

            cambios = true;
        }
    }

    @Test
    public void getParticipacionesDeInvestigadorEnGruposTest() throws RegistroNoEncontradoException
    {
        List<ParticipacionGrupo> participacionesGrupos = grupoDAO
                .getParticipacionesEnGruposByPersonaId(PERSONA_ID);

        for (ParticipacionGrupo participacionGrupo : participacionesGrupos)
        {
            assertNotNull(participacionGrupo.getGrupoInvestigacion());
            assertNotNull(participacionGrupo.getGrupoInvestigacion().getResponsable());
        }
    }

    @After
    @Transactional
    public void limpiaDatosBD()
    {
        if (cambios)
        {
            personaDAO.eliminaPersona(PERSONA_ID);
            personaDAO.eliminaPersona(RESPONSABLE_1_ID);
            personaDAO.eliminaPersona(RESPONSABLE_2_ID);
        }
    }
}
