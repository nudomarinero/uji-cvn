package es.uji.apps.cvn.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uji.apps.cvn.Utils;
import es.uji.apps.cvn.client.DocumentoCVN;
import es.uji.apps.cvn.client.GeneradorPDFWS;
import es.uji.apps.cvn.client.GeneradorPDFWSClient;
import es.uji.apps.cvn.client.exceptions.CvnNoGeneradoException;
import es.uji.apps.cvn.client.exceptions.GeneradorPDFWSException;
import es.uji.apps.cvn.dao.CongresoDAO;
import es.uji.apps.cvn.dao.CvnGeneradoDAO;
import es.uji.apps.cvn.dao.GrupoDAO;
import es.uji.apps.cvn.dao.LogDAO;
import es.uji.apps.cvn.dao.PersonaDAO;
import es.uji.apps.cvn.dao.PlantillaDAO;
import es.uji.apps.cvn.dao.ProyectoDAO;
import es.uji.apps.cvn.dao.PublicacionDAO;
import es.uji.apps.cvn.dao.TesisDAO;
import es.uji.apps.cvn.model.CvnGenerado;
import es.uji.apps.cvn.model.Domicilio;
import es.uji.apps.cvn.model.Log;
import es.uji.apps.cvn.model.ParticipacionCongreso;
import es.uji.apps.cvn.model.ParticipacionProyecto;
import es.uji.apps.cvn.model.ParticipacionPublicacionCientificoTecnica;
import es.uji.apps.cvn.model.ParticipacionPublicacionDocente;
import es.uji.apps.cvn.model.Persona;
import es.uji.apps.cvn.model.PlantillaCvn;
import es.uji.apps.cvn.model.ProyectoInvestigacion;
import es.uji.apps.cvn.model.Tesis;
import es.uji.apps.cvn.model.plantilla.Plantilla;
import es.uji.apps.cvn.ui.beans.CvnRootBean;
import es.uji.commons.rest.Role;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

@Service
public class CVNService
{
    private static final Logger log = Logger.getLogger(CVNService.class);

    final private static String NAME_WS = "cvn";
    final private static String SUCCESS_WS_CODE = "00";
    final private static String URL_DESCARGA_BASE = "doc/get";

    @Value("${uji.cvn.ws.user}")
    private String cvnWsUser;
    @Value("${uji.cvn.ws.passwd}")
    private String cvnWsPasswd;
    @Value("${uji.cvn.ws.template.default}")
    private String cvnWsTemplateDefault;
    @Value("${uji.cvn.ws.lang.default}")
    private String cvnWsLangDefault;
    @Value("${uji.cvn.ws.pdf.location}")
    private String wsdlDocumentLocation;
    @Value("${uji.cvn.ws.pdf.def.url}")
    private String wsdlDefUrl;
    @Value("${uji.cvn.ws.pdf.name}")
    private String wsdlName;
    @Value("${uji.cvn.ws.pdf.port.name}")
    private String wsdlPortName;
    @Value("${uji.cvn.app.path}")
    private String cvnAppPath;

    @Autowired
    private PersonaDAO personaDAO;
    @Autowired
    private ProyectoDAO proyectoDAO;
    @Autowired
    private GrupoDAO grupoDAO;
    @Autowired
    private PublicacionDAO publicacionDAO;
    @Autowired
    private CongresoDAO congresoDAO;
    @Autowired
    private TesisDAO tesisDAO;
    @Autowired
    private CvnGeneradoDAO cvnGeneradoDAO;
    @Autowired
    private PlantillaDAO plantillaDAO;
    @Autowired
    private LogDAO logDAO;

    public String getCode(String code, boolean admin)
    {
        if (!admin)
        {
            return code;
        }

        return String.valueOf(Integer.parseInt(code) + 1000);
    }

    public void generatePDF(Long personaId, String template, String lang, CvnGenerado cvnGenerado,
            Long plantillaId, boolean admin)
    {
        try
        {
            Plantilla plantilla;
            try
            {
                plantilla = Plantilla.unserialize(plantillaDAO.getPlantillaByPlantillaId(
                        plantillaId).getPlantilla());
            }
            catch (Exception e)
            {
                plantilla = Plantilla.getPlantillaPorDefecto(lang);
            }

            Persona persona = getDatosCVNPersona(personaId, plantilla);

            cvnGenerado.actualizaEstado(EstadoCvn.PENDIENTE.getEstado(),
                    getCode(InfoEstadoCVN.PENDIENTE_PREP_USER.getEstado(), admin));
            cvnGeneradoDAO.actualizaCvn(cvnGenerado);

            CvnRootBeanGenerator generator = new CvnRootBeanGenerator(persona, plantilla);
            CvnRootBean cvnRootBean = generator.generate();

            cvnGenerado.actualizaEstado(EstadoCvn.PENDIENTE.getEstado(),
                    getCode(InfoEstadoCVN.PENDIENTE_SOL_USER.getEstado(), admin));
            cvnGeneradoDAO.actualizaCvn(cvnGenerado);

            DocumentoCVN documentoCVN = getDocumentoCvnFromWSDL(cvnRootBean, template,
                    plantilla.getIdioma());
            if (documentoCVN.getReturnCode().equals(SUCCESS_WS_CODE))
            {
                cvnGenerado.setCvn(DocumentoCVN.serialize(documentoCVN));
                cvnGenerado.actualizaEstado(EstadoCvn.FINALIZADO.getEstado(),
                        getCode(InfoEstadoCVN.FINALIZADO_USER.getEstado(), admin));
                cvnGeneradoDAO.actualizaCvn(cvnGenerado);
            }
            else
            {
                throw new GeneradorPDFWSException(new String(documentoCVN.getDataHandler()));
            }

        }
        catch (RegistroNoEncontradoException re)
        {
            cvnGenerado.actualizaEstado(EstadoCvn.ERROR.getEstado(),
                    getCode(InfoEstadoCVN.ERROR_DATOS_USER.getEstado(), admin));
            cvnGeneradoDAO.actualizaCvn(cvnGenerado);

            logDAO.insertLog(Log.logError(personaId, personaId,
                    "Error al obtener los datos del usuario",
                    re.getMessage() + "\n" + Utils.exceptionStackTraceToString(re)));
            log.error(Utils.exceptionStackTraceToString(re));
        }
        catch (Exception e)
        {
            cvnGenerado.actualizaEstado(EstadoCvn.ERROR.getEstado(),
                    getCode(InfoEstadoCVN.ERROR_PDF_USER.getEstado(), admin));
            cvnGeneradoDAO.actualizaCvn(cvnGenerado);

            logDAO.insertLog(Log.logError(personaId, personaId,
                    "Error al descargar el PDF del usuario",
                    e.getMessage() + "\n" + Utils.exceptionStackTraceToString(e)));
            log.error(Utils.exceptionStackTraceToString(e));
        }
    }

    public void generateCVNEnFormatoPDFByPersonaId(Long personaId, String template, String lang,
            CvnGenerado cvnGenerado, Long plantillaId)
    {
        generatePDF(personaId, template, lang, cvnGenerado, plantillaId, false);
    }

    public void generateCVNEnFormatoPDFAdminByPersonaId(Long personaId, String template,
            String lang, CvnGenerado cvnGenerado, Long plantillaId, Long connectedUserId)
    {
        generatePDF(personaId, template, lang, cvnGenerado, plantillaId, true);
    }

    @Role({ "ADMIN" })
    public DocumentoCVN generateAndGetCVNEnFormatoPDFAdminByPersonaId(Long personaId,
            String template, String lang, Long connectedUserId, Long plantillaId)
            throws RegistroNoEncontradoException, MalformedURLException, GeneradorPDFWSException
    {
        Plantilla plantilla;
        try
        {
            plantilla = Plantilla.unserialize(plantillaDAO.getPlantillaByPlantillaId(plantillaId)
                    .getPlantilla());
        }
        catch (Exception e)
        {
            plantilla = Plantilla.getPlantillaPorDefecto(lang);
        }

        Persona persona = getDatosCVNPersona(personaId, plantilla);

        CvnRootBeanGenerator generator = new CvnRootBeanGenerator(persona, plantilla);
        CvnRootBean cvnRootBean = generator.generate();

        return getDocumentoCvnFromWSDL(cvnRootBean, template, plantilla.getIdioma());
    }

    private Persona getDatosCVNPersona(Long personaId, Plantilla plantilla)
            throws RegistroNoEncontradoException
    {
        Persona persona = personaDAO.getPersonaByIdConDomicilio(personaId);
        Domicilio.ordenaListaDomiciliosPorOrdenPreferencia(persona.getDomiciliosContacto());

        if (plantilla.getProyectos() != null || plantilla.getContratos() != null)
        {
            List<ParticipacionProyecto> participacionProyectos = proyectoDAO
                    .getParticipacionesEnProyectosByPersonaId(personaId);
            ParticipacionProyecto
                    .ordenaListaParticipacionProyectosPorFechaInicio(participacionProyectos);
            for (ParticipacionProyecto participacionProyecto : participacionProyectos)
            {
                if (participacionProyecto.getProyectoInvestigacion().getTipo()
                        .equals(ProyectoInvestigacion.COMPETITIVO)
                        && plantilla.getProyectos() != null)
                {
                    persona.getParticipacionesProyectosCompetitivos().add(participacionProyecto);
                }
                else if (plantilla.getContratos() != null)
                {
                    participacionProyecto.getProyectoInvestigacion()
                            .normalizaNombreProgramaFinanciacion();
                    persona.getParticipacionesProyectosNoCompetitivos().add(participacionProyecto);
                }
            }
        }

        if (plantilla.getGruposInvestigacion() != null)
        {
            persona.setParticipacionesGrupos(grupoDAO
                    .getParticipacionesEnGruposByPersonaId(personaId));
        }

        if (plantilla.getPublicaciones() != null)
        {
            List<ParticipacionPublicacionCientificoTecnica> participacionPublicaciones = publicacionDAO
                    .getParticipacionesEnPublicacionesCientificoTecnicasByPersonaId(personaId);
            ParticipacionPublicacionCientificoTecnica
                    .ordenaListaParticipacionPublicacionesPorFechaInicio(participacionPublicaciones);
            persona.setParticipacionesPublicaciones(participacionPublicaciones);
        }

        if (plantilla.getCongresos() != null)
        {
            List<ParticipacionCongreso> participacionCongresos = congresoDAO
                    .getParticipacionesEnCongresosByPersonaId(personaId);
            ParticipacionCongreso
                    .ordenaListaParticipacionCongresosPorFechaCongreso(participacionCongresos);
            persona.setParticipacionesCongresos(participacionCongresos);
        }

        if (plantilla.getPublicacionesDocentes() != null)
        {
            List<ParticipacionPublicacionDocente> participacionPublicacionesDocentes = publicacionDAO
                    .getParticipacionesEnPublicacionesDocentesByPersonaId(personaId);
            ParticipacionPublicacionDocente
                    .ordenaListaParticipacionPublicacionesPorFechaInicio(participacionPublicacionesDocentes);
            persona.setParticipacionesPublicacionesDocentes(participacionPublicacionesDocentes);
        }

        if (plantilla.getCongresosDocentes() != null)
        {
            List<ParticipacionCongreso> participacionCongresosDocentes = congresoDAO
                    .getParticipacionesEnCongresosDocentesByPersonaId(personaId);
            ParticipacionCongreso
                    .ordenaListaParticipacionCongresosPorFechaCongreso(participacionCongresosDocentes);
            persona.setParticipacionesCongresosDocentes(participacionCongresosDocentes);
        }

        List<Tesis> listaTesis = tesisDAO.getTesisPersonaId(personaId);
        persona.setTesis(listaTesis);

        return persona;
    }

    private DocumentoCVN getDocumentoCvnFromWSDL(CvnRootBean cvnRootBean, String template,
            String lang) throws MalformedURLException, GeneradorPDFWSException
    {
        GeneradorPDFWSClient pdfService = new GeneradorPDFWSClient(wsdlDocumentLocation,
                wsdlDefUrl, wsdlName);
        GeneradorPDFWS port = pdfService.getGeneraradorPDFWSPort(wsdlPortName);

        Long mili = System.currentTimeMillis();
        log.info("Inici de sol·licitud del PDF a Madrid: ");
        DocumentoCVN documentoCVN = port.crearPDFBeanCvnRootBean(cvnWsUser, cvnWsPasswd, NAME_WS,
                cvnRootBean, (template != null) ? template : cvnWsTemplateDefault,
                (lang != null) ? lang : cvnWsLangDefault);
        mili = System.currentTimeMillis() - mili;
        mili = mili / 1000;
        log.info("Fi de sol·licitud del PDF a Madrid: " + mili);
        return documentoCVN;
    }

    public DocumentoCVN getDocumentoCVNEnFormatoPDFByPersonaId(Long personaId)
            throws CvnNoGeneradoException, ClassNotFoundException, IOException
    {
        CvnGenerado cvnGenerado;

        try
        {
            cvnGenerado = cvnGeneradoDAO.getCvnGeneradoByPersonaId(personaId, personaId);
        }
        catch (RegistroNoEncontradoException e)
        {
            throw new CvnNoGeneradoException("Todavía no tiene ningún cvn generado");
        }

        if (!cvnGenerado.getEstado().equals(EstadoCvn.FINALIZADO.getEstado()))
        {
            throw new CvnNoGeneradoException("Su cvn está pendiente de ser generado");
        }

        return DocumentoCVN.unserialize(cvnGenerado.getCvn());
    }

    @Role({ "ADMIN" })
    public DocumentoCVN getDocumentoCVNEnFormatoPDFAdminByPersonaId(Long personaId,
            Long connectedUserId) throws CvnNoGeneradoException, ClassNotFoundException,
            IOException
    {
        CvnGenerado cvnGenerado;

        try
        {
            cvnGenerado = cvnGeneradoDAO.getCvnGeneradoByPersonaId(personaId, connectedUserId);
        }
        catch (RegistroNoEncontradoException e)
        {
            throw new CvnNoGeneradoException("Todavía no hay ningún cvn generado para el usuario "
                    + personaId);
        }

        if (!cvnGenerado.getEstado().equals(EstadoCvn.FINALIZADO.getEstado()))
        {
            throw new CvnNoGeneradoException("El cvn para el usuario " + personaId
                    + " está pendiente de ser generado");
        }

        return DocumentoCVN.unserialize(cvnGenerado.getCvn());
    }

    public CvnGenerado solicitudGeneracionDocumentoCVN(Long personaId)
    {
        return solicitudGeneracionDocumentoCVN(personaId, personaId);
    }

    public CvnGenerado solicitudGeneracionDocumentoCVN(Long personaId, Long solicitante)
    {
        CvnGenerado cvnGenerado = CvnGenerado.iniciaSolicitud(personaId, solicitante,
                EstadoCvn.SOLICITADO.getEstado(), InfoEstadoCVN.SOLICITADO.getEstado());
        cvnGeneradoDAO.resetCvnGenerado(cvnGenerado);

        return cvnGenerado;
    }

    public CvnGenerado getCvnGeneradoByPersonaId(Long personaId, String idioma)
            throws RegistroNoEncontradoException, ClassNotFoundException, IOException
    {
        CvnGenerado cvnGenerado = cvnGeneradoDAO.getCvnGeneradoByPersonaId(personaId, personaId);
        cvnGenerado.setMensaje(InfoEstadoCVN.getInfoEstado(cvnGenerado.getMensaje(), idioma));
        cvnGenerado.setUrlDescarga(URL_DESCARGA_BASE);

        return cvnGenerado;
    }

    @Role({ "ADMIN" })
    public List<CvnGenerado> getListaCvnsSolicitados(Long connectedUserId, String idioma)
    {
        List<CvnGenerado> listaCvnsGenerados = cvnGeneradoDAO
                .getListaCvnsGeneradosBySolicitante(connectedUserId);

        for (CvnGenerado cvnGenerado : listaCvnsGenerados)
        {
            String mensaje = InfoEstadoCVN.getInfoEstado(cvnGenerado.getMensaje(), idioma);
            if (!cvnGenerado.getEstado().equals(EstadoCvn.SOLICITADO.getEstado()))
            {
                mensaje = mensaje + cvnGenerado.getPersonaId();
            }
            cvnGenerado.setMensaje(mensaje);
            cvnGenerado.setUrlDescarga(URL_DESCARGA_BASE + "/" + cvnGenerado.getPersonaId());
            try
            {
                Persona persona = personaDAO.getPersonaById(cvnGenerado.getPersonaId());
                cvnGenerado.setUsuario(persona.getNombreCompleto());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                cvnGenerado.setUsuario("");
            }
        }

        return listaCvnsGenerados;
    }

    @Role({ "ADMIN" })
    public void deleteListaCvnsBySolicitante(Long connectedUserId)
    {
        cvnGeneradoDAO.deleteListaCvnsGeneradosBySolicitante(connectedUserId);
    }

    public String getCVNApplicationPath()
    {
        return cvnAppPath;
    }

    public List<PlantillaCvn> getListaPlantillasByPersonaId(Long connectedUserId)
    {
        return plantillaDAO.getListaPlantillasByPersonaId(connectedUserId);
    }

    @Transactional
    public void registraSolicitudGeneracionUsuario(Long userId, String idioma, String template,
            Long plantillaId)
    {
        registraSolicitudGeneracion(userId, userId, idioma, template, plantillaId,
                InfoEstadoCVN.PENDIENTE_DATOS_USER.getEstado());
    }

    @Transactional
    public void registraSolicitudGeneracionAdmin(Long connectedUserId, Long userId, String idioma,
            String template, Long plantillaId)
    {
        registraSolicitudGeneracion(connectedUserId, userId, idioma, template, plantillaId,
                InfoEstadoCVN.PENDIENTE_DATOS_ADMIN.getEstado());
    }

    private void registraSolicitudGeneracion(Long connectedUserId, Long userId, String idioma,
            String template, Long plantillaId, String estado)
    {
        CvnGenerado cvnGenerado = solicitudGeneracionDocumentoCVN(userId, connectedUserId);
        cvnGenerado.setIdioma(idioma);
        cvnGenerado.setTemplate(template);
        cvnGenerado.setPlantillaId(plantillaId);
        cvnGenerado.actualizaEstado(EstadoCvn.PENDIENTE.getEstado(), estado);

        cvnGeneradoDAO.actualizaCvn(cvnGenerado);
    }
}
