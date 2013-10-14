//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.03.13 at 12:11:50 PM CET 
//


package es.uji.apps.cvn.ui.beans;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.uji.apps.cvn.ui.beans package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CvnRootBean_QNAME = new QName("http://codes.cvn.fecyt.es/beans", "CvnRootBean");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.uji.apps.cvn.ui.beans
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CvnDateMonthYear }
     * 
     */
    public CvnDateMonthYear createCvnDateMonthYear() {
        return new CvnDateMonthYear();
    }

    /**
     * Create an instance of {@link CvnDateDayMonthYear }
     * 
     */
    public CvnDateDayMonthYear createCvnDateDayMonthYear() {
        return new CvnDateDayMonthYear();
    }

    /**
     * Create an instance of {@link CvnVolumeBean }
     * 
     */
    public CvnVolumeBean createCvnVolumeBean() {
        return new CvnVolumeBean();
    }

    /**
     * Create an instance of {@link CvnString }
     * 
     */
    public CvnString createCvnString() {
        return new CvnString();
    }

    /**
     * Create an instance of {@link CvnPageBean }
     * 
     */
    public CvnPageBean createCvnPageBean() {
        return new CvnPageBean();
    }

    /**
     * Create an instance of {@link CvnPhotoBean }
     * 
     */
    public CvnPhotoBean createCvnPhotoBean() {
        return new CvnPhotoBean();
    }

    /**
     * Create an instance of {@link CvnAuthorBean }
     * 
     */
    public CvnAuthorBean createCvnAuthorBean() {
        return new CvnAuthorBean();
    }

    /**
     * Create an instance of {@link CvnExternalPKBean }
     * 
     */
    public CvnExternalPKBean createCvnExternalPKBean() {
        return new CvnExternalPKBean();
    }

    /**
     * Create an instance of {@link CvnTitleBean }
     * 
     */
    public CvnTitleBean createCvnTitleBean() {
        return new CvnTitleBean();
    }

    /**
     * Create an instance of {@link CvnEntityBean }
     * 
     */
    public CvnEntityBean createCvnEntityBean() {
        return new CvnEntityBean();
    }

    /**
     * Create an instance of {@link CvnPhoneBean }
     * 
     */
    public CvnPhoneBean createCvnPhoneBean() {
        return new CvnPhoneBean();
    }

    /**
     * Create an instance of {@link CvnDuration }
     * 
     */
    public CvnDuration createCvnDuration() {
        return new CvnDuration();
    }

    /**
     * Create an instance of {@link CvnBoolean }
     * 
     */
    public CvnBoolean createCvnBoolean() {
        return new CvnBoolean();
    }

    /**
     * Create an instance of {@link CvnDateYear }
     * 
     */
    public CvnDateYear createCvnDateYear() {
        return new CvnDateYear();
    }

    /**
     * Create an instance of {@link CvnFamilyNameBean }
     * 
     */
    public CvnFamilyNameBean createCvnFamilyNameBean() {
        return new CvnFamilyNameBean();
    }

    /**
     * Create an instance of {@link CvnItemBean }
     * 
     */
    public CvnItemBean createCvnItemBean() {
        return new CvnItemBean();
    }

    /**
     * Create an instance of {@link CvnBean }
     * 
     */
    public CvnBean createCvnBean() {
        return new CvnBean();
    }

    /**
     * Create an instance of {@link CvnRootBean }
     * 
     */
    public CvnRootBean createCvnRootBean() {
        return new CvnRootBean();
    }

    /**
     * Create an instance of {@link CvnCodeGroup }
     * 
     */
    public CvnCodeGroup createCvnCodeGroup() {
        return new CvnCodeGroup();
    }

    /**
     * Create an instance of {@link CvnDouble }
     * 
     */
    public CvnDouble createCvnDouble() {
        return new CvnDouble();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CvnRootBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://codes.cvn.fecyt.es/beans", name = "CvnRootBean")
    public JAXBElement<CvnRootBean> createCvnRootBean(CvnRootBean value) {
        return new JAXBElement<CvnRootBean>(_CvnRootBean_QNAME, CvnRootBean.class, null, value);
    }

}
