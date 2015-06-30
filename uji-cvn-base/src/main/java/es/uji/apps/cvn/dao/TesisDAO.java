package es.uji.apps.cvn.dao;


import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.impl.JPAQuery;

import es.uji.apps.cvn.db.DirrecionTesisDTO;
import es.uji.apps.cvn.db.QDirrecionTesisDTO;
import es.uji.apps.cvn.model.DireccionTesis;
import es.uji.commons.db.BaseDAODatabaseImpl;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

@Repository
public class TesisDAO extends BaseDAODatabaseImpl {


    private static final Logger log = Logger.getLogger(TesisDAO.class);

    public List<DireccionTesis> getTesisPersonaId(Long personaId) throws RegistroNoEncontradoException{



        Long mili = System.currentTimeMillis();

        JPAQuery query = new JPAQuery(entityManager);
        QDirrecionTesisDTO qTesis = QDirrecionTesisDTO.dirrecionTesisDTO;
        List<DirrecionTesisDTO> listaDirrecionTesisDTO = query
                .from(qTesis)
                .where(qTesis.persona.eq(personaId)).list(qTesis);

        List<DireccionTesis> listaTesis = new ArrayList<DireccionTesis>();

        for (DirrecionTesisDTO dirrecionTesisDTO : listaDirrecionTesisDTO)
        {
            listaTesis.add(creaTesisDesde(dirrecionTesisDTO));
        }

        mili = System.currentTimeMillis()-mili;
        log.info("TesisDAO.getTesisPersonaId " + mili);

        return listaTesis;

    }

    private DireccionTesis creaTesisDesde(DirrecionTesisDTO dirrecionTesisDTO) {

        DireccionTesis direccionTesis = new DireccionTesis();
        direccionTesis.setId(dirrecionTesisDTO.getId());
        direccionTesis.setTipoId(dirrecionTesisDTO.getTipoId());
        direccionTesis.setTipoTexto(dirrecionTesisDTO.getTipoTexto());
        direccionTesis.setPersona(dirrecionTesisDTO.getPersona());
        direccionTesis.setTitulo(dirrecionTesisDTO.getTitulo());
        direccionTesis.setCodirectorNombre(dirrecionTesisDTO.getCodirectorNombre());
        direccionTesis.setCodirectorApellido1(dirrecionTesisDTO.getCodirectorApellido1());
        direccionTesis.setCodirectorApellido2(dirrecionTesisDTO.getCodirectorApellido2());
        direccionTesis.setPais(dirrecionTesisDTO.getPais());
        direccionTesis.setRegion(dirrecionTesisDTO.getRegion());
        direccionTesis.setCiudad(dirrecionTesisDTO.getCiudad());
        direccionTesis.setEntidad(dirrecionTesisDTO.getEntidad());
        direccionTesis.setTipoEntidad(dirrecionTesisDTO.getTipoEntidad());
        direccionTesis.setAlumnoNombre(dirrecionTesisDTO.getAlumnoNombre());
        direccionTesis.setAlumnoApellido1(dirrecionTesisDTO.getAlumnoApellido1());
        direccionTesis.setAlumnoApellido2(dirrecionTesisDTO.getAlumnoApellido2());
        direccionTesis.setFechaLectura(dirrecionTesisDTO.getFechaLectura());
        direccionTesis.setCalificacion(dirrecionTesisDTO.getCalificacion());
        direccionTesis.setDoctorEuropeo(dirrecionTesisDTO.getDoctorEuropeo().equals("S"));
        direccionTesis.setFechaDoctorEuropeo(dirrecionTesisDTO.getFechaDoctorEuropeo());

        String mc = dirrecionTesisDTO.getMencionCalidad();
        if (mc!=null)
           direccionTesis.setMencionCalidad(mc.equals("S"));


        return direccionTesis;
    }


    private String id;

    private String tipoId;

    private String tipoTexto;

    private Long persona;

    private String titulo;

    private String codirector;

    private String pais;

    private String region;

    private String ciudad;

    private String entidad;

    private String tipoEntidad;

    private String alumno;

    private XMLGregorianCalendar fechaLectura;

    private String calificacion;

    private boolean isDoctorEuropeo;

    private XMLGregorianCalendar fechaDoctorEuropeo;

    private boolean isMencionCalidad;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public String getTipoTexto() {
        return tipoTexto;
    }

    public void setTipoTexto(String tipoTexto) {
        this.tipoTexto = tipoTexto;
    }

    public Long getPersona() {
        return persona;
    }

    public void setPersona(Long persona) {
        this.persona = persona;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCodirector() {
        return codirector;
    }

    public void setCodirector(String codirector) {
        this.codirector = codirector;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(String tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public XMLGregorianCalendar getFechaLectura() {
        return fechaLectura;
    }


    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public boolean isDoctorEuropeo() {
        return isDoctorEuropeo;
    }

    public void setDoctorEuropeo(boolean doctorEuropeo) {
        isDoctorEuropeo = doctorEuropeo;
    }

    public XMLGregorianCalendar getFechaDoctorEuropeo() {
        return fechaDoctorEuropeo;
    }

    public void setFechaDoctorEuropeo(XMLGregorianCalendar fechaDoctorEuropeo) {
        this.fechaDoctorEuropeo = fechaDoctorEuropeo;
    }

    public boolean isMencionCalidad() {
        return isMencionCalidad;
    }

    public void setMencionCalidad(boolean mencionCalidad) {
        isMencionCalidad = mencionCalidad;
    }
}
