package es.uji.apps.cvn.services.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.core.InjectParam;

import es.uji.apps.cvn.client.exceptions.PlantillaNoAutorizadaException;
import es.uji.apps.cvn.model.PlantillaCvn;
import es.uji.apps.cvn.model.plantilla.Plantilla;
import es.uji.apps.cvn.publicacion.Menu;
import es.uji.apps.cvn.publicacion.MenuFactory;
import es.uji.apps.cvn.publicacion.Pagina;
import es.uji.apps.cvn.services.CVNService;
import es.uji.apps.cvn.services.PlantillaCVNService;
import es.uji.commons.rest.ParamUtils;
import es.uji.commons.rest.UIEntity;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;
import es.uji.commons.sso.AccessManager;
import es.uji.commons.web.template.Template;

@Path("plantillas")
public class PlantillasCVNResource extends AppBaseCVNResource
{
    @InjectParam
    private PlantillaCVNService plantillaCVNService;

    @InjectParam
    private CVNService cvnService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Template getPaginaListaPlantillasCVN(
            @CookieParam("uji-lang") @DefaultValue("ca") String idioma,
            @QueryParam("idioma") String idiomaForzado) throws MalformedURLException,
            ParseException
    {
        Long connectedUserId = AccessManager.getConnectedUserId(request);
        String idiomaPagina = (idiomaForzado != null) ? idiomaForzado : idioma;

        List<PlantillaCvn> listaPlantillasCvn = plantillaCVNService
                .getListaPlantillasCvnByPersonaId(connectedUserId);
        PlantillaCvn.ordenaListaPlantillasPorFechaActualizacion(listaPlantillasCvn);

        Menu menu = MenuFactory.builMenuUsuario(idiomaPagina, cvnService.getCVNApplicationPath());
        Pagina pagina = buildPagina("CVN", "", idiomaPagina, menu,
                cvnService.getCVNApplicationPath());
        Template template = buildTemplate("plantillas", idiomaPagina, pagina);
        template.put("listadoPlantillas", listaPlantillasCvn);
        template.put("restBase", cvnService.getCVNApplicationPath());
        template.put("urlBase", cvnService.getCVNApplicationPath() + "plantillas");

        return template;
    }

    @DELETE
    @Path("delete/{id}")
    public Response detelePlantillaCVN(@PathParam("id") String plantillaId)
            throws RegistroNoEncontradoException, PlantillaNoAutorizadaException
    {
        Long connectedUserId = AccessManager.getConnectedUserId(request);

        plantillaCVNService.deletePlantillaByPlantillaId(ParamUtils.parseLong(plantillaId),
                connectedUserId);

        return Response.ok().build();
    }

    @GET
    @Path("form")
    public Template formPlantillaCVN(@CookieParam("uji-lang") @DefaultValue("ca") String idioma,
            @QueryParam("idioma") String idiomaForzado,
            @QueryParam("plantillaId") String plantillaId) throws MalformedURLException,
            ParseException
    {
        Long connectedUserId = AccessManager.getConnectedUserId(request);
        String idiomaPagina = (idiomaForzado != null) ? idiomaForzado : idioma;

        Menu menu = MenuFactory.builMenuUsuario(idiomaPagina, cvnService.getCVNApplicationPath());
        Pagina pagina = buildPagina("CVN", "", idiomaPagina, menu,
                cvnService.getCVNApplicationPath());
        Template template = buildTemplate("form-plantillas", idiomaPagina, pagina);
        template.put("restBase", cvnService.getCVNApplicationPath());

        String urlBase = cvnService.getCVNApplicationPath() + "plantillas/form";
        if (plantillaId != null)
        {
            urlBase = urlBase + "?plantillaId=" + plantillaId;
        }
        template.put("urlBase", urlBase);

        return template;
    }

    @POST
    @Path("form")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardaPlantillaCVN(@QueryParam("plantillaId") String plantillaId,
            Plantilla plantilla) throws Exception
    {
        Long connectedUserId = AccessManager.getConnectedUserId(request);

        plantillaCVNService.guardaPlantillaByPersonaId(plantilla, connectedUserId);

        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Plantilla getPlantillaCVN(@PathParam("id") String plantillaId)
            throws RegistroNoEncontradoException, PlantillaNoAutorizadaException,
            ClassNotFoundException, IOException
    {
        Long connectedUserId = AccessManager.getConnectedUserId(request);

        return plantillaCVNService.getPlantillaByPlantillaId(ParamUtils.parseLong(plantillaId),
                connectedUserId);
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UIEntity> getListaPlantillasUsuario()
    {
        Long connectedUserId = AccessManager.getConnectedUserId(request);
        List<PlantillaCvn> plantillasCvn = plantillaCVNService
                .getListaPlantillasCvnByPersonaId(connectedUserId);

        List<UIEntity> plantillas = new ArrayList<UIEntity>();

        for (PlantillaCvn plantillaCvn : plantillasCvn)
        {
            UIEntity plantilla = new UIEntity();
            plantilla.put("id", plantillaCvn.getId());
            plantilla.put("nombre", plantillaCvn.getNombre() + " (" + plantillaCvn.getIdioma()
                    + ")");

            plantillas.add(plantilla);
        }

        return plantillas;
    }
}
