package es.uji.apps.cvn.services.rest;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;

import javax.ws.rs.CookieParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.core.InjectParam;

import es.uji.apps.cvn.model.CvnGenerado;
import es.uji.apps.cvn.model.PlantillaCvn;
import es.uji.apps.cvn.publicacion.Menu;
import es.uji.apps.cvn.publicacion.MenuFactory;
import es.uji.apps.cvn.publicacion.Pagina;
import es.uji.apps.cvn.services.CVNService;
import es.uji.apps.cvn.services.PlantillaCVNService;
import es.uji.commons.sso.AccessManager;
import es.uji.commons.web.template.Template;

@Path("admin")
public class AdminCVNResource extends AppBaseCVNResource
{
    @InjectParam
    private CVNService cvnService;

    @InjectParam
    private PlantillaCVNService plantillaCVNService;

    @GET
    @Path("consulta")
    @Produces(MediaType.TEXT_HTML)
    public Template getPaginaConsultaCVNsAdmin(
            @CookieParam("uji-lang") @DefaultValue("ca") String idioma,
            @QueryParam("idioma") String idiomaForzado) throws MalformedURLException,
            ParseException
    {
        Long connectedUserId = AccessManager.getConnectedUserId(request);
        String idiomaPagina = (idiomaForzado != null) ? idiomaForzado : idioma;

        List<CvnGenerado> listaCvns = cvnService.getListaCvnsSolicitados(connectedUserId,
                idiomaPagina);
        CvnGenerado.ordenaListaCvnsGeneradosPorFechaActualizacion(listaCvns);

        Menu menu = MenuFactory.buildMenuAdmin(idiomaPagina, cvnService.getCVNApplicationPath()
                + "admin/");
        Pagina pagina = buildPagina("CVN", "", idiomaPagina, menu,
                cvnService.getCVNApplicationPath());
        Template template = buildTemplate("consulta-admin", idiomaPagina, pagina);
        template.put("listadoCvns", listaCvns);
        template.put("urlBase", cvnService.getCVNApplicationPath() + "admin/consulta");
        template.put("restBase", cvnService.getCVNApplicationPath());

        return template;
    }

    @GET
    @Path("plantillas")
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

        Menu menu = MenuFactory.buildMenuAdmin(idiomaPagina, cvnService.getCVNApplicationPath()
                + "admin/");
        Pagina pagina = buildPagina("CVN", "", idiomaPagina, menu,
                cvnService.getCVNApplicationPath());
        Template template = buildTemplate("plantillas", idiomaPagina, pagina);
        template.put("listadoPlantillas", listaPlantillasCvn);
        template.put("restBase", cvnService.getCVNApplicationPath());
        template.put("urlBase", cvnService.getCVNApplicationPath() + "admin/plantillas");

        return template;
    }

    @GET
    @Path("plantillas/form")
    public Template formPlantillaCVN(@CookieParam("uji-lang") @DefaultValue("ca") String idioma,
            @QueryParam("idioma") String idiomaForzado,
            @QueryParam("plantillaId") String plantillaId) throws MalformedURLException,
            ParseException
    {
        Long connectedUserId = AccessManager.getConnectedUserId(request);
        String idiomaPagina = (idiomaForzado != null) ? idiomaForzado : idioma;

        Menu menu = MenuFactory.buildMenuAdmin(idiomaPagina, cvnService.getCVNApplicationPath()
                + "admin/");
        Pagina pagina = buildPagina("CVN", "", idiomaPagina, menu,
                cvnService.getCVNApplicationPath());
        Template template = buildTemplate("form-plantillas", idiomaPagina, pagina);
        template.put("restBase", cvnService.getCVNApplicationPath());

        String urlBase = cvnService.getCVNApplicationPath() + "admin/plantillas/form";
        if (plantillaId != null)
        {
            urlBase = urlBase + "?plantillaId=" + plantillaId;
        }
        template.put("urlBase", urlBase);

        return template;
    }
}
