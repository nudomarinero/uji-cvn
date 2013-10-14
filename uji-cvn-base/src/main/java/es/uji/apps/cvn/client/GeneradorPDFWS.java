package es.uji.apps.cvn.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import es.uji.apps.cvn.client.exceptions.GeneradorPDFWSException;
import es.uji.apps.cvn.ui.beans.CvnRootBean;

//import es.fecyt.cvn.codes.beans.CvnRootBean;

/**
 * Servicio web de generacion de CVN-PDF. SEI (Service Endpoint Interfac
 */
@WebService(targetNamespace = "http://ws.cvnet.fecyt.es", name = "GenerarPDFWS", serviceName = "GenerarPDFWS")
public interface GeneradorPDFWS
{
    /**
     * Crea un PDF a partir de una cadena conteniendo el CVN-XML.
     * 
     * @param user
     *            Usuario del servicio web
     * @param passwd
     *            Passwd del servicio web
     * @param nameCVN
     *            Nombre del fichero original
     * @param sXmlIn
     *            Cadena conteniendo el CVN-XML
     * @param tipoPlantilla
     *            Plantilla utilizada para construir el CVN-PDF
     * @return CVN-PDF
     */
    @WebMethod
    @WebResult(name = "return", targetNamespace = "http://ws.cvnet.fecyt.es")
    DocumentoCVN crearPDFBean(
            @WebParam(name = "user", targetNamespace = "http://ws.cvnet.fecyt.es") String user,
            @WebParam(name = "passwd", targetNamespace = "http://ws.cvnet.fecyt.es") String passwd,
            @WebParam(name = "nameCVN", targetNamespace = "http://ws.cvnet.fecyt.es") String nameCVN,
            @WebParam(name = "sXmlIn", targetNamespace = "http://ws.cvnet.fecyt.es") String sXmlIn,
            @WebParam(name = "tipoPlantilla", targetNamespace = "http://ws.cvnet.fecyt.es") String tipoPlantilla);

    /**
     * Crea un PDF a partir de una cadena conteniendo el CVN-XML.
     * 
     * @param user
     *            Usuario del servicio web
     * @param passwd
     *            Passwd del servicio web
     * @param nameCVN
     *            Nombre del fichero original
     * @param bXmlIn
     *            Bytes conteniendo el CVN-XML
     * @param tipoPlantilla
     *            Plantilla utilizada para construir el CVN-PDF
     * @param language
     *            Idioma en que se genera el CVN-PDF
     * @return CVN-PDF
     * @throws GeneradorPDFWSException
     *             producida al generar el PDF
     */
    @WebMethod
    DocumentoCVN crearPDFBeanBytes(@WebParam(name = "user") String user,
            @WebParam(name = "passwd") String passwd, @WebParam(name = "nameCVN") String nameCVN,
            @WebParam(name = "bXmlIn") byte[] bXmlIn,
            @WebParam(name = "tipoPlantilla") String tipoPlantilla,
            @WebParam(name = "language") String language) throws GeneradorPDFWSException;

    /**
     * Crea un PDF a partir de una cadena conteniendo el CVN-XML.
     * 
     * @param user
     *            Usuario del servicio web
     * @param passwd
     *            Passwd del servicio web
     * @param nameCVN
     *            Nombre del fichero original
     * @param bXmlIn
     *            Bytes conteniendo el CVN-XML comprimido
     * @param tipoPlantilla
     *            Plantilla utilizada para construir el CVN-PDF
     * @param language
     *            Idioma en que se genera el CVN-PDF
     * @return CVN-PDF
     * @throws GeneradorPDFWSException
     *             producida al generar el PDF
     */
    @WebMethod
    DocumentoCVN crearPDFBeanZip(@WebParam(name = "user") String user,
            @WebParam(name = "passwd") String passwd, @WebParam(name = "nameCVN") String nameCVN,
            @WebParam(name = "bXmlIn") byte[] bXmlIn,
            @WebParam(name = "tipoPlantilla") String tipoPlantilla,
            @WebParam(name = "language") String language) throws GeneradorPDFWSException;

    /**
     * Crea un PDF a partir de una cadena conteniendo el CVN-XML.
     * 
     * @param user
     *            Usuario del servicio web
     * @param passwd
     *            Passwd del servicio web
     * @param nameCVN
     *            Nombre del fichero original
     * @param cvnRootBean
     *            Bean conteniendo el CVN-XML comprimido
     * @param tipoPlantilla
     *            Plantilla utilizada para construir el CVN-PDF
     * @param language
     *            Idioma en que se genera el CVN-PDF
     * @return CVN-PDF
     * @throws GeneradorPDFWSException
     *             producida al generar el PDF
     */
    @WebMethod
    DocumentoCVN crearPDFBeanCvnRootBean(@WebParam(name = "user") String user,
            @WebParam(name = "passwd") String passwd, @WebParam(name = "nameCVN") String nameCVN,
            @WebParam(name = "cvnRootBean") CvnRootBean cvnRootBean,
            @WebParam(name = "tipoPlantilla") String tipoPlantilla,
            @WebParam(name = "language") String language) throws GeneradorPDFWSException;
}
