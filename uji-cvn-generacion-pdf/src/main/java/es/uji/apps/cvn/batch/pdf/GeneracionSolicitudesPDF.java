package es.uji.apps.cvn.batch.pdf;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.uji.apps.cvn.db.CvnGeneradoDTO;
import es.uji.apps.cvn.model.CvnGenerado;
import es.uji.apps.cvn.service.SolicitudesGeneracionService;
import es.uji.apps.cvn.services.CVNService;

public class GeneracionSolicitudesPDF implements Runnable
{
    public static Logger log = Logger.getLogger(GeneracionSolicitudesPDF.class);

    private static final int TIEMPO_ESPERA_ENTRE_CONSULTAS = 10000;

    private CVNService cvnService;
    private SolicitudesGeneracionService solicitudesGeneracionService;

    public GeneracionSolicitudesPDF(CVNService cvnService,
            SolicitudesGeneracionService solicitudesGeneracionService)
    {
        this.cvnService = cvnService;
        this.solicitudesGeneracionService = solicitudesGeneracionService;
    }

    public static void main(String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:applicationContext.xml");

        CVNService cvnService = context.getBean(CVNService.class);
        SolicitudesGeneracionService solicitudesGeneracionService = context
                .getBean(SolicitudesGeneracionService.class);

        GeneracionSolicitudesPDF generacionSolicitudesPDF = new GeneracionSolicitudesPDF(
                cvnService, solicitudesGeneracionService);

        log.info("START GeneracionSolicitudesPDF " + new Date());

        new Thread(generacionSolicitudesPDF).start();
    }

    public void run()
    {
        while (true)
        {
            try
            {
                List<CvnGeneradoDTO> peticiones = solicitudesGeneracionService
                        .getSolicitudesPendientes();

                for (CvnGeneradoDTO peticion : peticiones)
                {
                    log.info("Procesando CVN del usuario: " + peticion.getSolicitante());

                    try
                    {
                        CvnGenerado cvnGenerado = cvnService.solicitudGeneracionDocumentoCVN(peticion.getPersonaId(), peticion.getSolicitante());

                        Long personaId = peticion.getPersonaId();
                        String template = peticion.getTemplate();
                        String idioma = peticion.getIdioma();
                        Long plantillaId = peticion.getPlantillaId();

                        if (peticion.getMensaje().startsWith("100"))
                        {
                            cvnService.generateCVNEnFormatoPDFAdminByPersonaId(personaId, template, idioma, cvnGenerado, plantillaId, personaId);
                        }
                        else
                        {
                            cvnService.generateCVNEnFormatoPDFByPersonaId(personaId, template, idioma, cvnGenerado, plantillaId);
                        }
                    }
                    catch (Exception e)
                    {
                        log.error("Error generando el CVN de " + peticion.getSolicitante(), e);
                    }

                    log.info("CVN de " + peticion.getSolicitante() + " procesado correctamente");
                }

                Thread.sleep(TIEMPO_ESPERA_ENTRE_CONSULTAS);
            }
            catch (InterruptedException e)
            {
                log.error("Error en la ejecución del thread principal");
            }
        }
    }
}
