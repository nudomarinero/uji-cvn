package es.uji.apps.cvn.model.cvn.publicaciones;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class ParticipacionEnCongreso extends CVNItem
{
    private Congreso congreso;

    private Publicacion publicacion;

    public ParticipacionEnCongreso()
    {
        this.code = "060.010.020.000";
    }

    public Congreso getCongreso()
    {
        if (congreso == null)
        {
            congreso = new Congreso(this);
        }
        return congreso;
    }

    public Publicacion getPublicacion()
    {
        if (publicacion == null)
        {
            publicacion = new PublicacionCongreso(this);
        }
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion)
    {
        this.publicacion = publicacion;
    }

}
