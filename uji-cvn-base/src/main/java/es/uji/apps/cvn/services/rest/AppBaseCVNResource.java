package es.uji.apps.cvn.services.rest;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import es.uji.commons.rest.CoreBaseService;
import es.uji.commons.web.template.HTMLTemplate;
import es.uji.commons.web.template.Template;
import es.uji.commons.web.template.model.Menu;
import es.uji.commons.web.template.model.Pagina;

public class AppBaseCVNResource extends CoreBaseService
{
    public static final String PLANTILLA_DEFECTO = "base";

    public static final String URL_TEMPLATES_BASE = "cvn/";

    protected String getUrlBase(HttpServletRequest clientRequest) throws MalformedURLException
    {
        String urlReference = clientRequest.getRequestURL().toString();

        URL result = new URL(urlReference);
        int port = result.getPort();

        if (port <= 0)
        {
            port = 80;
        }

        return MessageFormat.format("{0}://{1}:{2,number,#}", result.getProtocol(),
                result.getHost(), port);
    }

    protected Template buildTemplate(String seccion, String idioma, Pagina pagina)
            throws MalformedURLException, ParseException
    {
        Template template = new HTMLTemplate(URL_TEMPLATES_BASE + PLANTILLA_DEFECTO, new Locale(
                idioma.toLowerCase()), "cvn");
        template.put("pagina", pagina);
        template.put("seccion", URL_TEMPLATES_BASE + seccion);

        return template;
    }

    protected Pagina buildPagina(String titulo, String subtitulo, String idioma, Menu menu,
            String urlBase) throws ParseException, MalformedURLException
    {
        Pagina pagina = new Pagina(urlBase, PLANTILLA_DEFECTO, idioma);
        pagina.setTitulo(titulo);
        pagina.setSubTitulo(subtitulo);
        pagina.setIdioma(idioma);
        pagina.setMenu(menu);

        return pagina;
    }
}
