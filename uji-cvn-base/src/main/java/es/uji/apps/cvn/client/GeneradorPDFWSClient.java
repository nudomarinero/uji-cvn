package es.uji.apps.cvn.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class GeneradorPDFWSClient extends Service
{
    private String wsdlDefUrl;

    protected GeneradorPDFWSClient(URL wsdlDocumentLocation, QName serviceName)
    {
        super(wsdlDocumentLocation, serviceName);
    }

    // Aquí está consultando al servidor con Web Service Description Language y le responde con qué
    // parámetros y qué variates puede consultarle
    public GeneradorPDFWSClient(String wsdlDocumentLocation, String wsdlDefUrl, String wsdlName)
            throws MalformedURLException
    {
        super(new URL(wsdlDocumentLocation), new QName(wsdlDefUrl, wsdlName));
        this.wsdlDefUrl = wsdlDefUrl;
    }

    public GeneradorPDFWS getGeneraradorPDFWSPort(String wsdlPortName)
    {
        return super.getPort(new QName(wsdlDefUrl, wsdlPortName), GeneradorPDFWS.class);
    }
}
