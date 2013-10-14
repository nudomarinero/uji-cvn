package es.uji.apps.cvn.translators;

public enum TipoSexo {
	  HOMBRE("1","000"), MUJER("2","010");
	    
	    private final String tipo;
	    private final String codigo;

	    TipoSexo(String tipo, String codigo)
	    {	
	    	this.tipo = tipo;
	    	this.codigo = codigo;
	    }

	    public static String getCodigoTipo(String tipoSexo)
	    {
	    	if (tipoSexo != null)
	    	{
	    		if (tipoSexo.equals(TipoSexo.HOMBRE.tipo))
	    			{
	    				return TipoSexo.HOMBRE.codigo;
	    			}
	    		else if (tipoSexo.equals(TipoSexo.MUJER.tipo))
	    			{
	    				return TipoSexo.MUJER.codigo;
	    			}
	    	}

	    	return null;
	    }
}
