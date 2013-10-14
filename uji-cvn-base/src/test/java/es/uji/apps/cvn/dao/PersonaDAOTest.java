package es.uji.apps.cvn.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.uji.apps.cvn.builders.PersonaBuilder;
import es.uji.apps.cvn.model.Persona;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class PersonaDAOTest
{
    @Autowired
    private PersonaDAO personaDAO;

    final private static Long PERSONA_ID = 1L;
    final private static String IDENTIFICACION = "12345678X";

    @Before
    @Transactional
    public void init()
    {
        new PersonaBuilder(personaDAO).withId(PERSONA_ID).withNombre("María")
                .withApellido1("Gómez").withApellido2("Sirvent").withIdentificacion(IDENTIFICACION)
                .withTipoIdentificacion("DNI").build();
    }

    @Test
    public void getDatosPersonaByPersonaIdTest() throws RegistroNoEncontradoException
    {
        Persona persona = personaDAO.getPersonaByIdConDomicilio(PERSONA_ID);
        assertEquals(IDENTIFICACION, persona.getIdentificacion());
    }
    
    @After
    @Transactional
    public void limpiaDatosBD()
    {
        personaDAO.eliminaPersona(PERSONA_ID);
    }
}
