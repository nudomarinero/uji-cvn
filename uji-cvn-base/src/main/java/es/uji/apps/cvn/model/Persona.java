package es.uji.apps.cvn.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.codec.binary.Base64;

public class Persona
{
    private Long id;

    private String nombre;

    private String apellido1;

    private String apellido2;

    private String identificacion;

    private String tipoIdentificacion;

    private String ciudadNacimiento;

    private String email;

    private String fax;

    private XMLGregorianCalendar fechaNacimiento;

    private String foto;

    private String mimeTypeFoto;

    private String genero;

    private String indiceH;

    private String indiceHFecha;

    private String nacionalidad;

    private String paisNacimiento;

    private String regionNacimiento;

    private String telefonoFijo;

    private String telefonoMovil;

    private String webpage;

    private List<Domicilio> domiciliosContacto;

    private List<ParticipacionGrupo> participacionesGrupos;

    private List<ParticipacionProyecto> participacionesProyectosCompetitivos;

    private List<ParticipacionProyecto> participacionesProyectosNoCompetitivos;

    private List<ParticipacionPublicacionCientificoTecnica> participacionesPublicaciones;

    private List<ParticipacionCongreso> participacionesCongresos;
    
    private List<ParticipacionPublicacionDocente> participacionesPublicacionesDocentes;
    
    private List<ParticipacionCongreso> participacionesCongresosDocentes;

    private List<DireccionTesis> tesis;

    private List<SituacionProfesional> situacionProfesionalActiva;

    private List<SituacionProfesional> situacionProfesionalAnterior;

    private List<DocenciaImpartida> docenciasImpartida;

    final private static String DATE_FORMATTER = "yyyy-MM-dd";

    public Persona()
    {
        participacionesProyectosCompetitivos = new ArrayList<ParticipacionProyecto>();
        participacionesProyectosNoCompetitivos = new ArrayList<ParticipacionProyecto>();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getApellido1()
    {
        return apellido1;
    }

    public void setApellido1(String apellido1)
    {
        this.apellido1 = apellido1;
    }

    public String getApellido2()
    {
        return apellido2;
    }

    public void setApellido2(String apellido2)
    {
        this.apellido2 = apellido2;
    }

    public String getIdentificacion()
    {
        return identificacion;
    }

    public void setIdentificacion(String identificacion)
    {
        this.identificacion = identificacion;
    }

    public String getTipoIdentificacion()
    {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion)
    {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getCiudadNacimiento()
    {
        return ciudadNacimiento;
    }

    public void setCiudadNacimiento(String ciudadNacimiento)
    {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public XMLGregorianCalendar getFechaNacimiento()
    {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento)
    {
        if (fechaNacimiento != null)
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(fechaNacimiento);
            try
            {
                this.fechaNacimiento = DatatypeFactory.newInstance().newXMLGregorianCalendar(
                        calendar);
            }
            catch (DatatypeConfigurationException e)
            {
            }
        }
    }

    public String getFoto()
    {
        return foto;
    }

    public void setFoto(byte[] foto)
    {
        if (foto != null)
        {
            this.foto = new String(Base64.encodeBase64(foto));
        }
    }

    public String getMimeTypeFoto()
    {
        return mimeTypeFoto;
    }

    public void setMimeTypeFoto(String mimeTypeFoto)
    {
        this.mimeTypeFoto = mimeTypeFoto;
    }

    public String getGenero()
    {
        return genero;
    }

    public void setGenero(String genero)
    {
        this.genero = genero;
    }

    public String getIndiceH()
    {
        return indiceH;
    }

    public void setIndiceH(String indiceH)
    {
        this.indiceH = indiceH;
    }

    public String getIndiceHFecha()
    {
        return indiceHFecha;
    }

    public void setIndiceHFecha(Date indiceHFecha)
    {
        if (indiceHFecha != null)
        {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMATTER);
            this.indiceHFecha = formatter.format(indiceHFecha);
        }
    }

    public String getNacionalidad()
    {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad)
    {
        this.nacionalidad = nacionalidad;
    }

    public String getPaisNacimiento()
    {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento)
    {
        this.paisNacimiento = paisNacimiento;
    }

    public String getRegionNacimiento()
    {
        return regionNacimiento;
    }

    public void setRegionNacimiento(String regionNacimiento)
    {
        this.regionNacimiento = regionNacimiento;
    }

    public String getTelefonoFijo()
    {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo)
    {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil()
    {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil)
    {
        this.telefonoMovil = telefonoMovil;
    }

    public String getWebpage()
    {
        return webpage;
    }

    public void setWebpage(String webpage)
    {
        this.webpage = webpage;
    }

    public List<Domicilio> getDomiciliosContacto()
    {
        return domiciliosContacto;
    }

    public void setDomiciliosContacto(List<Domicilio> domiciliosContacto)
    {
        this.domiciliosContacto = domiciliosContacto;
    }

    public List<ParticipacionGrupo> getParticipacionesGrupos()
    {
        return participacionesGrupos;
    }

    public void setParticipacionesGrupos(List<ParticipacionGrupo> participacionesGrupos)
    {
        this.participacionesGrupos = participacionesGrupos;
    }

    public List<ParticipacionProyecto> getParticipacionesProyectosCompetitivos()
    {
        return participacionesProyectosCompetitivos;
    }

    public void setParticipacionesProyectosCompetitivos(
            List<ParticipacionProyecto> participacionesProyectosCompetitivos)
    {
        this.participacionesProyectosCompetitivos = participacionesProyectosCompetitivos;
    }

    public List<ParticipacionProyecto> getParticipacionesProyectosNoCompetitivos()
    {
        return participacionesProyectosNoCompetitivos;
    }

    public void setParticipacionesProyectosNoCompetitivos(
            List<ParticipacionProyecto> participacionesProyectosNoCompetitivos)
    {
        this.participacionesProyectosNoCompetitivos = participacionesProyectosNoCompetitivos;
    }

    public List<ParticipacionPublicacionCientificoTecnica> getParticipacionesPublicaciones()
    {
        return participacionesPublicaciones;
    }

    public void setParticipacionesPublicaciones(
            List<ParticipacionPublicacionCientificoTecnica> participacionesPublicaciones)
    {
        this.participacionesPublicaciones = participacionesPublicaciones;
    }

    public List<ParticipacionCongreso> getParticipacionesCongresos()
    {
        return participacionesCongresos;
    }

    public void setParticipacionesCongresos(List<ParticipacionCongreso> participacionesCongresos)
    {
        this.participacionesCongresos = participacionesCongresos;
    }

    public String getNombreCompleto()
    {
        String nombreCompleto = "";
        String apellido2 = ((this.apellido2 != null) ? (" " + this.apellido2) : "");

        if (nombre != null && apellido1 != null)
        {
            nombreCompleto = apellido1 + apellido2 + ", " + nombre;
        }
        else if (nombre != null)
        {
            nombreCompleto = nombre + apellido2;
        }
        else if (apellido1 != null)
        {
            nombreCompleto = apellido1 + apellido2;
        }

        return nombreCompleto;
    }

    public List<ParticipacionPublicacionDocente> getParticipacionesPublicacionesDocentes() {
		return participacionesPublicacionesDocentes;
	}

	public void setParticipacionesPublicacionesDocentes(
			List<ParticipacionPublicacionDocente> participacionesPublicacionesDocentes) {
		this.participacionesPublicacionesDocentes = participacionesPublicacionesDocentes;
	}

	public List<ParticipacionCongreso> getParticipacionesCongresosDocentes() {
		return participacionesCongresosDocentes;
	}

	public void setParticipacionesCongresosDocentes(
			List<ParticipacionCongreso> participacionesCongresosDocentes) {
		this.participacionesCongresosDocentes = participacionesCongresosDocentes;
	}

    public void setTesis(List<DireccionTesis> listaTesis) {
        this.tesis=listaTesis;
    }

    public List<DireccionTesis> getTesis() {
        return tesis;
    }

    public void setSituacionProfesionalActiva(List<SituacionProfesional> situacionProfesionalActiva) {
        this.situacionProfesionalActiva = situacionProfesionalActiva;
    }

    public List<SituacionProfesional> getSituacionProfesionalActiva() {
        return situacionProfesionalActiva;
    }

    public void setSituacionProfesionalAnterior(List<SituacionProfesional> situacionProfesionalAnterior) {
        this.situacionProfesionalAnterior = situacionProfesionalAnterior;
    }

    public List<SituacionProfesional> getSituacionProfesionalAnterior(){
        return situacionProfesionalAnterior;
    }

    public List<DocenciaImpartida> getDocenciasImpartida()
    {
        return docenciasImpartida;
    }

    public void setDocenciasImpartida(List<DocenciaImpartida> docenciasImpartida)
    {
        this.docenciasImpartida = docenciasImpartida;
    }
}
