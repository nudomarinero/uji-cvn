package es.uji.apps.cvn.dao;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.impl.JPAQuery;

import es.uji.apps.cvn.db.QSituacionProfesionalDTO;
import es.uji.apps.cvn.db.SituacionProfesionalDTO;
import es.uji.apps.cvn.model.SituacionProfesional;
import es.uji.commons.db.BaseDAODatabaseImpl;
import es.uji.commons.rest.exceptions.RegistroNoEncontradoException;

@Repository
public class SituacionProfesionalDAO extends BaseDAODatabaseImpl
{

    private static final Logger log = Logger.getLogger(SituacionProfesionalDAO.class);

    public List<SituacionProfesional> getSituacionPersonalId(Long personaId, boolean activo)
            throws RegistroNoEncontradoException
    {

        Long mili = System.currentTimeMillis();

        JPAQuery query = new JPAQuery(entityManager);
        QSituacionProfesionalDTO qSituacionProfesional = QSituacionProfesionalDTO.situacionProfesionalDTO;

        List<SituacionProfesionalDTO> listaSituacionProfesionalDTO = null;
        if (activo)
        {
            listaSituacionProfesionalDTO = query
                    .from(qSituacionProfesional)
                    .where(qSituacionProfesional.perId.eq(personaId).and(
                            qSituacionProfesional.duracion.isNull())).list(qSituacionProfesional);
        }
        else
        {
            listaSituacionProfesionalDTO = query
                    .from(qSituacionProfesional)
                    .where(qSituacionProfesional.perId.eq(personaId).and(
                            qSituacionProfesional.duracion.isNotNull()))
                    .list(qSituacionProfesional);
        }

        List<SituacionProfesional> listaSituacionPersonal = new ArrayList<SituacionProfesional>();

        for (SituacionProfesionalDTO situacionPersonalDTO : listaSituacionProfesionalDTO)
        {
            listaSituacionPersonal.add(creaSituacionPersonalDesde(situacionPersonalDTO));
        }

        mili = System.currentTimeMillis() - mili;
        log.info("SituacionProfesionalDAO.getSituacionProfesionalPersonaId " + mili);

        return listaSituacionPersonal;

    }

    private SituacionProfesional creaSituacionPersonalDesde(SituacionProfesionalDTO spDTO)
    {

        SituacionProfesional sp = new SituacionProfesional();

        sp.setId(spDTO.getId());
        sp.setPerId(spDTO.getPerId());
        sp.setLineaId(spDTO.getLineaId());
        sp.setGestionDocente(spDTO.getGestionDocente().equals("S"));
        sp.setNombreEntidad(spDTO.getNombreEntidad());
        sp.setTipoEntidad(spDTO.getTipoEntidad());
        sp.setTipoEntidadOtros(spDTO.getTipoEntidadOtros());
        sp.setCentroVal(spDTO.getCentroVal());
        sp.setCentroCas(spDTO.getCentroCas());
        sp.setCentroIng(spDTO.getCentroIng());
        sp.setServicioVal(spDTO.getServicioVal());
        sp.setServicioCas(spDTO.getServicioCas());
        sp.setServicioIng(spDTO.getServicioIng());
        sp.setCiudad(spDTO.getCiudad());
        sp.setRegion(spDTO.getRegion());
        sp.setPais(spDTO.getPais());
        sp.setTelefono(spDTO.getTelefono());
        sp.setFax(spDTO.getFax());
        sp.setMail(spDTO.getMail());
        sp.setCategoria(spDTO.getCategoria());
        sp.setFechaInicio(spDTO.getFechaInicio());
        sp.setDuracion(spDTO.getDuracion());
        sp.setSituacion(spDTO.getSituacion());
        sp.setSituacionOtros(spDTO.getSituacionOtros());
        sp.setDedicacion(spDTO.getDedicacion());
        sp.setUnesco1(spDTO.getUnesco1());
        sp.setUnesco2(spDTO.getUnesco2());
        sp.setUnesco3(spDTO.getUnesco3());
        sp.setTextoDedicacion(spDTO.getTextoDedicacion());
        sp.setPalabrasClave(spDTO.getPalabrasClave());

        return sp;
    }

    private String id;

    private Long perId;

    private Long lineaId;

    private boolean isGestionDocente;

    private String nombreEntidad;

    private String tipoEntidad;

    private String tipoEntidadOtros;

    private String centroVal;

    private String centroCas;

    private String centroIng;

    private String servicioVal;

    private String servicioCas;

    private String servicioIng;

    private String ciudad;

    private String region;

    private Long pais;

    private String telefono;

    private String fax;

    private String mail;

    private String categoria;

    private XMLGregorianCalendar fechaInicio;

    private Long duracion;

    private String situacion;

    private String situacionOtros;

    private Long dedicacion;

    private String unesco1;

    private String unesco2;

    private String unesco3;

    private String textoDedicacion;

    private String palabrasClave;

    public static Logger getLog() {
        return log;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPerId() {
        return perId;
    }

    public void setPerId(Long perId) {
        this.perId = perId;
    }

    public Long getLineaId() {
        return lineaId;
    }

    public void setLineaId(Long lineaId) {
        this.lineaId = lineaId;
    }

    public boolean isGestionDocente() {
        return isGestionDocente;
    }

    public void setGestionDocente(boolean gestionDocente) {
        isGestionDocente = gestionDocente;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public String getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(String tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }

    public String getTipoEntidadOtros() {
        return tipoEntidadOtros;
    }

    public void setTipoEntidadOtros(String tipoEntidadOtros) {
        this.tipoEntidadOtros = tipoEntidadOtros;
    }

    public String getCentroVal() {
        return centroVal;
    }

    public void setCentroVal(String centroVal) {
        this.centroVal = centroVal;
    }

    public String getCentroCas() {
        return centroCas;
    }

    public void setCentroCas(String centroCas) {
        this.centroCas = centroCas;
    }

    public String getCentroIng() {
        return centroIng;
    }

    public void setCentroIng(String centroIng) {
        this.centroIng = centroIng;
    }

    public String getServicioVal() {
        return servicioVal;
    }

    public void setServicioVal(String servicioVal) {
        this.servicioVal = servicioVal;
    }

    public String getServicioCas() {
        return servicioCas;
    }

    public void setServicioCas(String servicioCas) {
        this.servicioCas = servicioCas;
    }

    public String getServicioIng() {
        return servicioIng;
    }

    public void setServicioIng(String servicioIng) {
        this.servicioIng = servicioIng;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Long getPais() {
        return pais;
    }

    public void setPais(Long pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public XMLGregorianCalendar getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(XMLGregorianCalendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Long getDuracion() {
        return duracion;
    }

    public void setDuracion(Long duracion) {
        this.duracion = duracion;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getSituacionOtros() {
        return situacionOtros;
    }

    public void setSituacionOtros(String situacionOtros) {
        this.situacionOtros = situacionOtros;
    }

    public Long getDedicacion() {
        return dedicacion;
    }

    public void setDedicacion(Long dedicacion) {
        this.dedicacion = dedicacion;
    }

    public String getUnesco1() {
        return unesco1;
    }

    public void setUnesco1(String unesco1) {
        this.unesco1 = unesco1;
    }

    public String getUnesco2() {
        return unesco2;
    }

    public void setUnesco2(String unesco2) {
        this.unesco2 = unesco2;
    }

    public String getUnesco3() {
        return unesco3;
    }

    public void setUnesco3(String unesco3) {
        this.unesco3 = unesco3;
    }

    public String getTextoDedicacion() {
        return textoDedicacion;
    }

    public void setTextoDedicacion(String textoDedicacion) {
        this.textoDedicacion = textoDedicacion;
    }

    public String getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }
}
