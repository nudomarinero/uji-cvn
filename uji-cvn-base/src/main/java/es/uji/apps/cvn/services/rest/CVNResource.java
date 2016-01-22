package es.uji.apps.cvn.services.rest;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;
import com.sun.jersey.api.core.InjectParam;

import es.uji.apps.cvn.Utils;
import es.uji.apps.cvn.client.DocumentoCVN;
import es.uji.apps.cvn.client.exceptions.CvnNoGeneradoException;
import es.uji.apps.cvn.client.exceptions.GeneradorPDFWSException;
import es.uji.apps.cvn.services.CVNService;
import es.uji.commons.rest.CoreBaseService;
import es.uji.commons.rest.ParamUtils;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;
import es.uji.commons.sso.AccessManager;

@Path("doc")
public class CVNResource extends CoreBaseService
{
    private static final Logger log = Logger.getLogger(CVNResource.class);


    @Context
    protected HttpServletResponse servletResponse;

    @InjectParam
    private CVNService cvnService;

    @GET
    @Path("generate")
    public void generateCVNEnFormatoPDF(@QueryParam("template") String template,
            @QueryParam("lang") String idioma, @QueryParam("plantilla") String plantilla)
            throws IOException, RegistroNoEncontradoException
    {
        Long connectedUserId = AccessManager.getConnectedUserId(request);
        Long plantillaId = ParamUtils.parseLong(plantilla);

        cvnService.registraSolicitudGeneracionUsuario(connectedUserId, idioma, template, plantillaId);
    }

    @GET
    @Path("generate/{personaId}")
    @Produces(MediaType.TEXT_PLAIN)
    public void generateCVNEnFormatoPDF(@PathParam("personaId") String persona,
            @QueryParam("template") String template, @QueryParam("lang") String idioma,
            @QueryParam("plantilla") String plantilla) throws IOException, RegistroNoEncontradoException
    {
        ParamUtils.checkNotNull(persona);
        Long personaId = ParamUtils.parseLong(persona);
        Long plantillaId = ParamUtils.parseLong(plantilla);

        Long connectedUserId = AccessManager.getConnectedUserId(request);

        cvnService.registraSolicitudGeneracionAdmin(connectedUserId, connectedUserId, idioma, template, plantillaId);
    }

    @POST
    @Path("generate")
    public void generateVariosCVNsEnFormatoPDF(@FormParam("usuarios") String usuarios,
            @QueryParam("template") String template, @QueryParam("lang") String idioma,
            @QueryParam("plantilla") String plantilla) throws IOException
    {
        ParamUtils.checkNotNull(usuarios);
        Long plantillaId = ParamUtils.parseLong(plantilla);
        Long connectedUserId = AccessManager.getConnectedUserId(request);

        for (String usuario : usuarios.split("\n"))
        {
            try
            {
                Long usuarioId = ParamUtils.parseLong(usuario.trim());
                cvnService.registraSolicitudGeneracionAdmin(connectedUserId, usuarioId, idioma, template, plantillaId);
            }
            catch (Exception e)
            {
            }
        }
    }

    @GET
    @Path("get")
    @Produces("application/pdf")
    public Response getCVNEnFormatoPDF() throws CvnNoGeneradoException, ClassNotFoundException,
            IOException
    {
        Long connectedUserId = AccessManager.getConnectedUserId(request);

        DocumentoCVN documentoCVN = cvnService
                .getDocumentoCVNEnFormatoPDFByPersonaId(connectedUserId);

        return Response.ok(documentoCVN.getDataHandler()).header(
                "Content-Disposition",
                "attachment; filename=\"" + documentoCVN.getFilename() + "\"").build();
    }

    @GET
    @Path("get/{personaId}")
    public Response getCVNEnFormatoPDF(@PathParam("personaId") String persona)
            throws ClassNotFoundException, IOException
    {
        ParamUtils.checkNotNull(persona);
        Long personaId = ParamUtils.parseLong(persona);

        Long connectedUserId = AccessManager.getConnectedUserId(request);

        DocumentoCVN documentoCVN;

        try
        {
            documentoCVN = cvnService.getDocumentoCVNEnFormatoPDFAdminByPersonaId(personaId,
                    connectedUserId);
        }
        catch (CvnNoGeneradoException e)
        {
            return Response.ok(e.getMessage(), MediaType.TEXT_PLAIN).build();
        }

        ResponseBuilder builder = Response.ok(documentoCVN.getDataHandler(), "application/pdf")
                .header("Content-Disposition",
                        "attachment; filename=\"" + documentoCVN.getFilename() + "\"");

        return builder.build();
    }

    @GET
    @Path("{personaId}")
    @Produces("application/pdf")
    public Response generateAndGetCVNEnFormatoPDF(@PathParam("personaId") String persona,
            @QueryParam("template") String template, @QueryParam("lang") String lang,
            @QueryParam("plantilla") String plantilla)
    {
        ParamUtils.checkNotNull(persona);
        Long personaId = ParamUtils.parseLong(persona);
        Long plantillaId = ParamUtils.parseLong(plantilla);

        Long connectedUserId = AccessManager.getConnectedUserId(request);

        DocumentoCVN documentoCVN = null;
        ResponseBuilder builder=null;
        try {
            documentoCVN = cvnService.generateAndGetCVNEnFormatoPDFAdminByPersonaId(
                    personaId, template, lang, connectedUserId, plantillaId);

            builder = Response.ok(documentoCVN.getDataHandler()).header(
                    "Content-Disposition",
                    "attachment; filename=\"" + documentoCVN.getFilename() + "\"");

        } catch (RegistroNoEncontradoException e) {
            log.error(Utils.exceptionStackTraceToString(e));
        } catch (MalformedURLException e) {
            log.error(Utils.exceptionStackTraceToString(e));
        } catch (GeneradorPDFWSException e) {
            log.error(Utils.exceptionStackTraceToString(e));
        }


        if (builder!=null)
          return builder.build();
        else
          return null;

    }

    @DELETE
    public void deleteCvnsGeneradosBySolicitante()
    {
        Long connectedUserId = AccessManager.getConnectedUserId(request);

        cvnService.deleteListaCvnsBySolicitante(connectedUserId);
    }

}
