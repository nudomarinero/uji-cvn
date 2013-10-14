package es.uji.apps.cvn.model.cvn;

import java.util.ArrayList;

import es.uji.apps.cvn.ui.beans.CvnItemBean;
import es.uji.apps.cvn.ui.beans.CvnRootBean;

public class CVN extends CvnRootBean
{
    public CVN()
    {
        super();
        this.cvnItemBean = new ArrayList<CvnItemBean>();
    }

    public void addCvnItemBean(CvnItemBean cvnItem)
    {
        cvnItemBean.add(cvnItem);
    }
}
