package es.uji.apps.cvn.services.rest;

import com.sun.jersey.api.core.InjectParam;
import es.uji.apps.cvn.model.CvnGenerado;
import es.uji.apps.cvn.model.PlantillaCvn;
import es.uji.apps.cvn.publicacion.Menu;
import es.uji.apps.cvn.publicacion.MenuFactory;
import es.uji.apps.cvn.publicacion.Pagina;
import es.uji.apps.cvn.services.CVNService;
import es.uji.commons.sso.AccessManager;
import es.uji.commons.web.template.Template;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.CookieParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("consulta")
public class ConsultaCVNResource extends AppBaseCVNResource
{

    @InjectParam
    private CVNService cvnService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Template getPaginaConsultaCVNs(
            @CookieParam("uji-lang") @DefaultValue("ca") String idioma,
            @QueryParam("idioma") String idiomaForzado) throws MalformedURLException,
            ParseException
    {
        Long connectedUserId = AccessManager.getConnectedUserId(request);
        String idiomaPagina = (idiomaForzado != null) ? idiomaForzado : idioma;

        List<PlantillaCvn> plantillasCvn = cvnService.getListaPlantillasByPersonaId(connectedUserId);


        List<CvnGenerado> listaCvns = new ArrayList<CvnGenerado>();
        try
        {
            CvnGenerado cvnGenerado = cvnService.getCvnGeneradoByPersonaId(connectedUserId,
                    idiomaPagina);
            listaCvns.add(cvnGenerado);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Menu menu = MenuFactory.builMenuUsuario(idiomaPagina, cvnService.getCVNApplicationPath());
        Pagina pagina = buildPagina("CVN", "", idiomaPagina, menu,
                cvnService.getCVNApplicationPath());
        Template template = buildTemplate("consulta", idiomaPagina, pagina);
        template.put("listadoCvns", listaCvns);
        template.put("urlBase", cvnService.getCVNApplicationPath() + "consulta");
        template.put("restBase", cvnService.getCVNApplicationPath());
        template.put("listaPlantillas", getListaPlantillasFromPlantillasCvn(plantillasCvn));

        return template;
    }

    private List<Map<String, String>> getListaPlantillasFromPlantillasCvn(List<PlantillaCvn> plantillasCvn) {
        List<Map<String, String>> listaMapaPlantillas = new ArrayList<>();

        for (PlantillaCvn plantillaCvn: plantillasCvn) {
            Map<String, String> mapPlantilla = new HashMap<>();
            mapPlantilla.put("id", plantillaCvn.getId().toString());
            mapPlantilla.put("nombre", plantillaCvn.getNombre());
            listaMapaPlantillas.add(mapPlantilla);
        }
        return listaMapaPlantillas;
    }
}
