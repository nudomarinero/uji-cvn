package es.uji.apps.cvn.model.cvn.publicaciones;

import es.uji.apps.cvn.model.cvn.CVNItem;

public class ParticipacionEnCongresoDocente extends CVNItem {
		
	    private CongresoDocente congreso;

	    private Publicacion publicacion;


	    public ParticipacionEnCongresoDocente()
	    {
	        this.code = "030.090.000.000";
	    }

	    public CongresoDocente getCongreso()
	    {
	        if (congreso == null)
	        {
	            congreso = new CongresoDocente(this);
	        }
	        return congreso;
	    }

	    public Publicacion getPublicacion()
	    {
	        if (publicacion == null)
	        {
	            publicacion = new PublicacionCongresoDocente(this);
	        }
	        return publicacion;
	    }

	    public void setPublicacion(Publicacion publicacion)
	    {
	        this.publicacion = publicacion;
	    }
}
