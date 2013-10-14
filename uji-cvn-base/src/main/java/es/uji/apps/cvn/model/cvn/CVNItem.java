package es.uji.apps.cvn.model.cvn;

import java.util.ArrayList;

import javax.xml.datatype.XMLGregorianCalendar;

import es.uji.apps.cvn.ui.beans.CvnAuthorBean;
import es.uji.apps.cvn.ui.beans.CvnBoolean;
import es.uji.apps.cvn.ui.beans.CvnCodeGroup;
import es.uji.apps.cvn.ui.beans.CvnDateDayMonthYear;
import es.uji.apps.cvn.ui.beans.CvnDateMonthYear;
import es.uji.apps.cvn.ui.beans.CvnDateYear;
import es.uji.apps.cvn.ui.beans.CvnDouble;
import es.uji.apps.cvn.ui.beans.CvnDuration;
import es.uji.apps.cvn.ui.beans.CvnEntityBean;
import es.uji.apps.cvn.ui.beans.CvnExternalPKBean;
import es.uji.apps.cvn.ui.beans.CvnFamilyNameBean;
import es.uji.apps.cvn.ui.beans.CvnItemBean;
import es.uji.apps.cvn.ui.beans.CvnPageBean;
import es.uji.apps.cvn.ui.beans.CvnPhoneBean;
import es.uji.apps.cvn.ui.beans.CvnPhotoBean;
import es.uji.apps.cvn.ui.beans.CvnString;
import es.uji.apps.cvn.ui.beans.CvnTitleBean;
import es.uji.apps.cvn.ui.beans.CvnVolumeBean;

public class CVNItem extends CvnItemBean
{
    public CVNItem()
    {
        super();
        cvnAuthorBean = new ArrayList<CvnAuthorBean>();
        cvnBoolean = new ArrayList<CvnBoolean>();
        cvnCodeGroup = new ArrayList<CvnCodeGroup>();
        cvnDateDayMonthYear = new ArrayList<CvnDateDayMonthYear>();
        cvnDateMonthYear = new ArrayList<CvnDateMonthYear>();
        cvnDateYear = new ArrayList<CvnDateYear>();
        cvnDouble = new ArrayList<CvnDouble>();
        cvnDuration = new ArrayList<CvnDuration>();
        cvnEntityBean = new ArrayList<CvnEntityBean>();
        cvnExternalPKBean = new ArrayList<CvnExternalPKBean>();
        cvnFamilyNameBean = new ArrayList<CvnFamilyNameBean>();
        cvnPageBean = new ArrayList<CvnPageBean>();
        cvnPhoneBean = new ArrayList<CvnPhoneBean>();
        cvnPhotoBean = new ArrayList<CvnPhotoBean>();
        cvnString = new ArrayList<CvnString>();
        cvnTitleBean = new ArrayList<CvnTitleBean>();
        cvnVolumeBean = new ArrayList<CvnVolumeBean>();
    }

    public void addCvnBoolean(CvnBoolean cvnBoolean)
    {
        if (cvnBoolean.isValue() != null)
        {
            this.cvnBoolean.add(cvnBoolean);
        }
    }

    public void addCvnAuthorBean(CvnAuthorBean cvnAuthorBean)
    {
        if (cvnAuthorBean.getGivenName() != null)
        {
            this.cvnAuthorBean.add(cvnAuthorBean);
        }
    }

    public void addCvnCodeGroup(CvnCodeGroup cvnCodeGroup)
    {
        this.cvnCodeGroup.add(cvnCodeGroup);
    }

    public void addCvnDateDayMonthYear(CvnDateDayMonthYear cvnDateDayMonthYear)
    {
        if (cvnDateDayMonthYear.getValue() != null)
        {
            this.cvnDateDayMonthYear.add(cvnDateDayMonthYear);
        }
    }

    public void addCvnDateMonthYear(CvnDateMonthYear cvnDateMonthYear)
    {
        if (cvnDateMonthYear.getValue() != null)
        {
            this.cvnDateMonthYear.add(cvnDateMonthYear);
        }
    }

    public void addCvnDateYear(CvnDateYear cvnDateYear)
    {
        if (cvnDateYear.getValue() != null)
        {
            this.cvnDateYear.add(cvnDateYear);
        }
    }

    public void addCvnDouble(CvnDouble cvnDouble)
    {
        if (cvnDouble.getValue() != null)
        {
            this.cvnDouble.add(cvnDouble);
        }
    }

    public void addCvnDuration(CvnDuration cvnDuration)
    {
        if (cvnDuration.getValue() != null)
        {
            this.cvnDuration.add(cvnDuration);
        }
    }

    public void addCvnEntityBean(CvnEntityBean cvnEntityBean)
    {
        if (cvnEntityBean.getName() != null)
        {
            this.cvnEntityBean.add(cvnEntityBean);
        }
    }

    public void addCvnExternalPKBean(CvnExternalPKBean cvnExternalPKBean)
    {
        if (cvnExternalPKBean.getValue() != null)
        {
            this.cvnExternalPKBean.add(cvnExternalPKBean);
        }
    }

    public void addCvnFamilyNameBean(CvnFamilyNameBean cvnFamilyNameBean)
    {
        if (cvnFamilyNameBean.getFirstFamilyName() != null)
        {
            this.cvnFamilyNameBean.add(cvnFamilyNameBean);
        }
    }

    public void addCvnPageBean(CvnPageBean cvnPageBean)
    {
        if (cvnPageBean.getFinalPage() != null || cvnPageBean.getInitialPage() != null)
        {
            this.cvnPageBean.add(cvnPageBean);
        }
    }

    public void addCvnPhoneBean(CvnPhoneBean cvnPhoneBean)
    {
        if (cvnPhoneBean.getNumber() != null)
        {
            this.cvnPhoneBean.add(cvnPhoneBean);
        }
    }

    public void addCvnPhotoBean(CvnPhotoBean cvnPhotoBean)
    {
        if (cvnPhotoBean.getBytesInBase64() != null)
        {
            this.cvnPhotoBean.add(cvnPhotoBean);
        }
    }

    public void addCvnString(CvnString cvnString)
    {
        if (cvnString.getValue() != null)
        {
            this.cvnString.add(cvnString);
        }
    }

    public void addCvnTitleBean(CvnTitleBean cvnTitleBean)
    {
        if (cvnTitleBean.getIdentification() != null || cvnTitleBean.getName() != null)
        {
            this.cvnTitleBean.add(cvnTitleBean);
        }
    }

    public void addCvnVolumeBean(CvnVolumeBean cvnVolumeBean)
    {
        if (cvnVolumeBean.getNumber() != null || cvnVolumeBean.getVolume() != null)
        {
            this.cvnVolumeBean.add(cvnVolumeBean);
        }
    }

    public CvnString buildCvnString(String code, String value)
    {
        CvnString cvnString = new CvnString();
        cvnString.setCode(code);
        cvnString.setValue(value);

        return cvnString;
    }

    public CvnDouble buildCvnDouble(String code, Double value)
    {
        CvnDouble cvnDouble = new CvnDouble();
        cvnDouble.setCode(code);
        cvnDouble.setValue(value);

        return cvnDouble;
    }

    public CvnDateDayMonthYear buildCvnDateDayMonthYear(String code, XMLGregorianCalendar value)
    {
        CvnDateDayMonthYear cvnDateDayMonthYear = new CvnDateDayMonthYear();
        cvnDateDayMonthYear.setCode(code);
        cvnDateDayMonthYear.setValue(value);

        return cvnDateDayMonthYear;
    }

    public CvnDateYear buildCvnDateYear(String code, XMLGregorianCalendar value)
    {
        CvnDateYear cvnDateYear = new CvnDateYear();
        cvnDateYear.setCode(code);
        cvnDateYear.setValue(value);

        return cvnDateYear;
    }

    public CvnFamilyNameBean buildCvnFamilyNameBean(String code, String firstName, String secondName)
    {
        CvnFamilyNameBean cvnFamilyNameBean = new CvnFamilyNameBean();
        cvnFamilyNameBean.setCode(code);
        cvnFamilyNameBean.setFirstFamilyName(firstName);
        cvnFamilyNameBean.setSecondFamilyName(secondName);

        return cvnFamilyNameBean;
    }

    public CvnAuthorBean buildCvnAuthorBean(String code, String givenName, String firstName,
            String secondName)
    {
        CvnAuthorBean cvnAuthorBean = new CvnAuthorBean();
        cvnAuthorBean.setCode(code);
        cvnAuthorBean.setGivenName(givenName);
        cvnAuthorBean.setCvnFamilyNameBean(buildCvnFamilyNameBean(code, firstName, secondName));

        return cvnAuthorBean;
    }

    public CvnDuration buildCvnDuration(String code, String value)
    {
        CvnDuration cvnDuration = new CvnDuration();
        cvnDuration.setCode(code);
        cvnDuration.setValue(value);

        return cvnDuration;
    }
    
    public CvnExternalPKBean buildExternalPKBean(String code, String value)
    {
        CvnExternalPKBean cvnExternalPKBean = new CvnExternalPKBean();
        cvnExternalPKBean.setCode(code);
        cvnExternalPKBean.setValue(value);

        return cvnExternalPKBean;
    }

    public CvnExternalPKBean buildExternalPKBean(String code, String value, String type)
    {
        CvnExternalPKBean cvnExternalPKBean = new CvnExternalPKBean();
        cvnExternalPKBean.setCode(code);
        cvnExternalPKBean.setValue(value);
        cvnExternalPKBean.setType(type);

        return cvnExternalPKBean;
    }

    public CvnEntityBean buildCvnEntityBean(String code, String name)
    {
        CvnEntityBean cvnEntityBean = new CvnEntityBean();
        cvnEntityBean.setCode(code);
        cvnEntityBean.setName(name);

        return cvnEntityBean;
    }

    public CvnBoolean buildCvnBoolean(String code, Boolean value)
    {
        CvnBoolean cvnBoolean = new CvnBoolean();
        cvnBoolean.setCode(code);
        cvnBoolean.setValue(value);

        return cvnBoolean;
    }

    public CvnTitleBean buildCvnTitleBean(String code, String identification, String name)
    {
        CvnTitleBean cvnTitleBean = new CvnTitleBean();
        cvnTitleBean.setCode(code);
        cvnTitleBean.setIdentification(identification);
        cvnTitleBean.setName(name);

        return cvnTitleBean;
    }

    public CvnVolumeBean buildCvnVolumeBean(String code, String number, String volume)
    {
        CvnVolumeBean cvnVolumeBean = new CvnVolumeBean();
        cvnVolumeBean.setCode(code);
        cvnVolumeBean.setNumber(number);
        cvnVolumeBean.setVolume(volume);

        return cvnVolumeBean;
    }

    public CvnPageBean buildCvnPageBean(String code, String initialPage, String finalPage)
    {
        CvnPageBean cvnPageBean = new CvnPageBean();
        cvnPageBean.setCode(code);
        cvnPageBean.setInitialPage(initialPage);
        cvnPageBean.setFinalPage(finalPage);

        return cvnPageBean;
    }

    public void addCvnEntityBeanToCvnCodeGroup(CvnEntityBean cvnEntityBean,
            CvnCodeGroup cvnCodeGroup)
    {
        if (cvnEntityBean.getName() != null)
        {
            if (cvnCodeGroup.getCvnEntityBean() == null)
            {
                cvnCodeGroup.setCvnEntityBean(new ArrayList<CvnEntityBean>());
            }
            cvnCodeGroup.getCvnEntityBean().add(cvnEntityBean);
        }
    }

    public void addCvnStringToCvnCodeGroup(CvnString cvnString, CvnCodeGroup cvnCodeGroup)
    {
        if (cvnString.getValue() != null)
        {
            if (cvnCodeGroup.getCvnString() == null)
            {
                cvnCodeGroup.setCvnString(new ArrayList<CvnString>());
            }
            cvnCodeGroup.getCvnString().add(cvnString);
        }
    }

    public void addCvnTitleBeanToCvnCodeGroup(CvnTitleBean cvnTitleBean, CvnCodeGroup cvnCodeGroup)
    {
        if (cvnTitleBean.getIdentification() != null || cvnTitleBean.getName() != null)
        {
            if (cvnCodeGroup.getCvnTitleBean() == null)
            {
                cvnCodeGroup.setCvnTitleBean(new ArrayList<CvnTitleBean>());
            }
            cvnCodeGroup.getCvnTitleBean().add(cvnTitleBean);
        }
    }

    public void addCvnDoubleToCvnCodeGroup(CvnDouble cvnDouble, CvnCodeGroup cvnCodeGroup)
    {
        if (cvnDouble.getValue() != null)
        {
            if (cvnCodeGroup.getCvnDouble() == null)
            {
                cvnCodeGroup.setCvnDouble(new ArrayList<CvnDouble>());
            }
            cvnCodeGroup.getCvnDouble().add(cvnDouble);
        }
    }

    public void addCvnBooleanToCvnCodeGroup(CvnBoolean cvnBoolean, CvnCodeGroup cvnCodeGroup)
    {
        if (cvnBoolean.isValue() != null)
        {
            if (cvnCodeGroup.getCvnBoolean() == null)
            {
                cvnCodeGroup.setCvnBoolean(new ArrayList<CvnBoolean>());
            }
            cvnCodeGroup.getCvnBoolean().add(cvnBoolean);
        }
    }

}
