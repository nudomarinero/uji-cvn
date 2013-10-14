package es.uji.apps.cvn.model.cvn.identificacion;

import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.model.cvn.CVNItem;
import es.uji.apps.cvn.translators.TipoIdentificacion;
import es.uji.apps.cvn.translators.TipoSexo;
import es.uji.apps.cvn.ui.beans.CvnPhotoBean;

public class Identificacion
{
    private CVNItem cvnItem;

    public Identificacion(CVNItem cvnItem)
    {
        this.cvnItem = cvnItem;
    }

    public void addApellidos(String apellido1, String apellido2)
    {
        cvnItem.addCvnFamilyNameBean(cvnItem.buildCvnFamilyNameBean("000.010.000.010", apellido1,
                apellido2));
    }

    public void addNombre(String nombre)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("000.010.000.020", nombre));
    }

    public void addSexo(String sexo)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("000.010.000.030", TipoSexo.getCodigoTipo(sexo)));
    }

    public void addNacionalidad(String nacionalidad)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("000.010.000.040", nacionalidad)); //Obtengo directamente el código numérico ISO
    }

    public void addFechaNacimiento(XMLGregorianCalendar fechaNacimiento)
    {
        cvnItem.addCvnDateDayMonthYear(cvnItem.buildCvnDateDayMonthYear("000.010.000.050",
                fechaNacimiento));
    }

    public void addPaisNacimiento(String paisNacimiento)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("000.010.000.060", paisNacimiento));  //Obtengo directamente el código numérico ISO
    }

    public void addRegionNacimiento(String regionNacimiento)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("000.010.000.070", regionNacimiento));
    }

    public void addCiudadNacimiento(String ciudadNacimiento)
    {
        cvnItem.addCvnString(cvnItem.buildCvnString("000.010.000.090", ciudadNacimiento));
    }

    public void addIdentificacion(String tipo, String identificacion)
    {
        String tipoIdentificacion = TipoIdentificacion.getCodigoTipo(tipo);
        String code = null;

        if (tipoIdentificacion == "DNI"){
            code = "000.010.000.100";
        } else if (tipoIdentificacion == "NIE"){
            code = "000.010.000.110";
    	} else if (tipoIdentificacion == "PASS"){
            code = "000.010.000.120";
        }

        if (code != null)
        {
            cvnItem.addCvnString(cvnItem.buildCvnString(code, identificacion));
        }
    }

    public void addFoto(String bytesFoto, String mimeType)
    {
        CvnPhotoBean foto = new CvnPhotoBean();
        foto.setCode("000.010.000.130");
        foto.setBytesInBase64(bytesFoto);
        foto.setMimeType(mimeType);
        cvnItem.addCvnPhotoBean(foto);
    }
}
