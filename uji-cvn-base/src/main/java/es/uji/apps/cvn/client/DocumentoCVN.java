package es.uji.apps.cvn.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlRootElement(name = "ResultadoBean")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoBean", namespace = "http://ws.cvnet.fecyt.es", propOrder = {
        "dataHandler", "filename", "returnCode" })
public class DocumentoCVN implements Serializable
{
    @XmlElement(name = "dataHandler", namespace = "http://bean.cvnet.fecyt.es/xsd")
    byte[] dataHandler;

    @XmlElement(name = "filename", namespace = "http://bean.cvnet.fecyt.es/xsd")
    String filename;

    @XmlElement(name = "returnCode", namespace = "http://bean.cvnet.fecyt.es/xsd")
    String returnCode;

    public byte[] getDataHandler()
    {
        return dataHandler;
    }

    public void setDataHandler(byte[] data)
    {
        this.dataHandler = data;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public String getReturnCode()
    {
        return returnCode;
    }

    public void setReturnCode(String returnCode)
    {
        this.returnCode = returnCode;
    }

    public DocumentoCVN()
    {
    }

    public DocumentoCVN(byte[] dataHandler, String filename)
    {
        this.dataHandler = dataHandler;
        this.filename = filename;
    }

    public DocumentoCVN(byte[] dataHandler, String filename, String returnCode)
    {
        this.dataHandler = dataHandler;
        this.filename = filename;
        this.returnCode = returnCode;
    }

    public static byte[] serialize(DocumentoCVN documentoCVN) throws IOException
    {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bs);
        os.writeObject(documentoCVN);
        os.close();

        return bs.toByteArray();
    }

    public static DocumentoCVN unserialize(byte[] documentoCVNBytes) throws IOException,
            ClassNotFoundException
    {
        ByteArrayInputStream bs = new ByteArrayInputStream(documentoCVNBytes);
        ObjectInputStream is = new ObjectInputStream(bs);
        DocumentoCVN documentoCVN = (DocumentoCVN) is.readObject();
        is.close();

        return documentoCVN;
    }
}
