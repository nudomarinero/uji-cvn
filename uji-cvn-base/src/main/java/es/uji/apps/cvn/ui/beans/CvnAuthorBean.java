//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.03.13 at 12:11:50 PM CET 
//


package es.uji.apps.cvn.ui.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CvnAuthorBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CvnAuthorBean">
 *   &lt;complexContent>
 *     &lt;extension base="{http://codes.cvn.fecyt.es/beans}CvnBean">
 *       &lt;sequence>
 *         &lt;element name="CvnFamilyNameBean" type="{http://codes.cvn.fecyt.es/beans}CvnFamilyNameBean" minOccurs="0"/>
 *         &lt;element name="GivenName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Signature" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SignatureOrder" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CvnAuthorBean", propOrder = {
    "cvnFamilyNameBean",
    "givenName",
    "signature",
    "signatureOrder"
})
public class CvnAuthorBean
    extends CvnBean
{

    @XmlElement(name = "CvnFamilyNameBean")
    protected CvnFamilyNameBean cvnFamilyNameBean;
    @XmlElement(name = "GivenName")
    protected String givenName;
    @XmlElement(name = "Signature")
    protected String signature;
    @XmlElement(name = "SignatureOrder")
    protected Integer signatureOrder;

    /**
     * Gets the value of the cvnFamilyNameBean property.
     * 
     * @return
     *     possible object is
     *     {@link CvnFamilyNameBean }
     *     
     */
    public CvnFamilyNameBean getCvnFamilyNameBean() {
        return cvnFamilyNameBean;
    }

    /**
     * Sets the value of the cvnFamilyNameBean property.
     * 
     * @param value
     *     allowed object is
     *     {@link CvnFamilyNameBean }
     *     
     */
    public void setCvnFamilyNameBean(CvnFamilyNameBean value) {
        this.cvnFamilyNameBean = value;
    }

    /**
     * Gets the value of the givenName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets the value of the givenName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGivenName(String value) {
        this.givenName = value;
    }

    /**
     * Gets the value of the signature property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignature(String value) {
        this.signature = value;
    }

    /**
     * Gets the value of the signatureOrder property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSignatureOrder() {
        return signatureOrder;
    }

    /**
     * Sets the value of the signatureOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSignatureOrder(Integer value) {
        this.signatureOrder = value;
    }

}
