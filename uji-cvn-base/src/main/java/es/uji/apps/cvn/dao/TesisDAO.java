package es.uji.apps.cvn.dao;


import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import es.uji.apps.cvn.db.ParticipacionPersonaEnCongresoDTO;
import es.uji.apps.cvn.db.QParticipacionPersonaEnCongresoDTO;
import es.uji.apps.cvn.db.QTesisDTO;
import es.uji.apps.cvn.db.TesisDTO;
import es.uji.apps.cvn.model.ParticipacionCongreso;
import es.uji.apps.cvn.model.Tesis;
import es.uji.commons.db.BaseDAODatabaseImpl;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

import java.util.ArrayList;
import java.util.List;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class TesisDAO extends BaseDAODatabaseImpl {


    private static final Logger log = Logger.getLogger(TesisDAO.class);

    public List<Tesis> getTesisPersonaId(Long personaId) throws RegistroNoEncontradoException{



        Long mili = System.currentTimeMillis();

        JPAQuery query = new JPAQuery(entityManager);
        QTesisDTO qTesis = QTesisDTO.tesisDTO;

        List<TesisDTO> listaTesisDTO = query
                .from(qTesis)
                .where(qTesis.persona.eq(personaId)).list(qTesis);

        List<Tesis> listaTesis = new ArrayList<Tesis>();

        for (TesisDTO tesisDTO : listaTesisDTO)
        {
            listaTesis.add(creaTesisDesde(tesisDTO));
        }

        mili = System.currentTimeMillis()-mili;
        log.info("TesisDAO.getTesisPersonaId " + mili);

        return listaTesis;

    }

    private Tesis creaTesisDesde(TesisDTO tesisDTO) {

        Tesis tesis = new Tesis();
        tesis.setId(tesisDTO.getId());
        tesis.setTipoId(tesisDTO.getTipoId());
        tesis.setTipoTexto(tesisDTO.getTipoTexto());
        tesis.setPersona(tesisDTO.getPersona());
        tesis.setTitulo(tesisDTO.getTitulo());
        tesis.setCodirectorNombre(tesisDTO.getCodirectorNombre());
        tesis.setCodirectorApellido1(tesisDTO.getCodirectorApellido1());
        tesis.setCodirectorApellido2(tesisDTO.getCodirectorApellido2());
        tesis.setPais(tesisDTO.getPais());
        tesis.setRegion(tesisDTO.getRegion());
        tesis.setCiudad(tesisDTO.getCiudad());
        tesis.setEntidad(tesisDTO.getEntidad());
        tesis.setTipoEntidad(tesisDTO.getTipoEntidad());
        tesis.setAlumnoNombre(tesisDTO.getAlumnoNombre());
        tesis.setAlumnoApellido1(tesisDTO.getAlumnoApellido1());
        tesis.setAlumnoApellido2(tesisDTO.getAlumnoApellido2());
        tesis.setFechaLectura(tesisDTO.getFechaLectura());
        tesis.setCalificacion(tesisDTO.getCalificacion());
        tesis.setDoctorEuropeo(tesisDTO.getDoctorEuropeo().equals("S"));
        tesis.setFechaDoctorEuropeo(tesisDTO.getFechaDoctorEuropeo());

        String mc = tesisDTO.getMencionCalidad();
        if (mc!=null)
           tesis.setMencionCalidad(mc.equals("S"));


        return tesis;
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
