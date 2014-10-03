package es.uji.apps.cvn.services;

import java.util.Calendar;
import java.util.Collections;

import es.uji.apps.cvn.model.AutorPublicacion;
import es.uji.apps.cvn.model.Domicilio;
import es.uji.apps.cvn.model.Impacto;
import es.uji.apps.cvn.model.ParticipacionCongreso;
import es.uji.apps.cvn.model.ParticipacionGrupo;
import es.uji.apps.cvn.model.ParticipacionProyecto;
import es.uji.apps.cvn.model.ParticipacionPublicacionCientificoTecnica;
import es.uji.apps.cvn.model.ParticipacionPublicacionDocente;
import es.uji.apps.cvn.model.Persona;
import es.uji.apps.cvn.model.SituacionProfesional;
import es.uji.apps.cvn.model.Tesis;
import es.uji.apps.cvn.model.cvn.CVN;
import es.uji.apps.cvn.model.cvn.Entidad;
import es.uji.apps.cvn.model.cvn.datos.DatosCVN;
import es.uji.apps.cvn.model.cvn.datos.Idioma;
import es.uji.apps.cvn.model.cvn.grupos.GrupoInvestigacion;
import es.uji.apps.cvn.model.cvn.grupos.InvestigadorGrupo;
import es.uji.apps.cvn.model.cvn.grupos.ParticipacionGrupoInvestigacion;
import es.uji.apps.cvn.model.cvn.identificacion.Contacto;
import es.uji.apps.cvn.model.cvn.identificacion.DatosPersonales;
import es.uji.apps.cvn.model.cvn.identificacion.Identificacion;
import es.uji.apps.cvn.model.cvn.docente.CvnTesis;
import es.uji.apps.cvn.model.cvn.docente.ParticipacionEnTesis;
import es.uji.apps.cvn.model.cvn.laboral.CvnDetalleSituacionProfesionalActual;
import es.uji.apps.cvn.model.cvn.laboral.CvnDetalleSituacionProfesionalAntigua;
import es.uji.apps.cvn.model.cvn.laboral.SituacionProfesionalActual;
import es.uji.apps.cvn.model.cvn.laboral.SituacionProfesionalAntigua;
import es.uji.apps.cvn.model.cvn.proyectos.FinanciacionProyecto;
import es.uji.apps.cvn.model.cvn.proyectos.InvestigadorProyecto;
import es.uji.apps.cvn.model.cvn.proyectos.InvestigadorProyectoCompetitivo;
import es.uji.apps.cvn.model.cvn.proyectos.ParticipacionProyectoInvestigacionCompetitivo;
import es.uji.apps.cvn.model.cvn.proyectos.ParticipacionProyectoInvestigacionNoCompetitivo;
import es.uji.apps.cvn.model.cvn.proyectos.ProyectoInvestigacion;
import es.uji.apps.cvn.model.cvn.proyectos.ProyectoInvestigacionCompetitivo;
import es.uji.apps.cvn.model.cvn.proyectos.ProyectoInvestigacionNoCompetitivo;
import es.uji.apps.cvn.model.cvn.publicaciones.Congreso;
import es.uji.apps.cvn.model.cvn.publicaciones.CongresoDocente;
import es.uji.apps.cvn.model.cvn.publicaciones.Localizacion;
import es.uji.apps.cvn.model.cvn.publicaciones.LocalizacionCongreso;
import es.uji.apps.cvn.model.cvn.publicaciones.LocalizacionCongresoDocente;
import es.uji.apps.cvn.model.cvn.publicaciones.ParticipacionEnCongreso;
import es.uji.apps.cvn.model.cvn.publicaciones.ParticipacionEnCongresoDocente;
import es.uji.apps.cvn.model.cvn.publicaciones.ParticipacionEnPublicacion;
import es.uji.apps.cvn.model.cvn.publicaciones.ParticipacionEnPublicacionDocente;
import es.uji.apps.cvn.model.cvn.publicaciones.Publicacion;
import es.uji.apps.cvn.model.cvn.publicaciones.PublicacionCientificoTecnica;
import es.uji.apps.cvn.model.cvn.publicaciones.PublicacionDocente;
import es.uji.apps.cvn.model.plantilla.Plantilla;
import es.uji.apps.cvn.model.plantilla.categorias.Congresos;
import es.uji.apps.cvn.model.plantilla.categorias.CongresosDocentes;
import es.uji.apps.cvn.model.plantilla.categorias.Contratos;
import es.uji.apps.cvn.model.plantilla.categorias.GruposInvestigacion;
import es.uji.apps.cvn.model.plantilla.categorias.Proyectos;
import es.uji.apps.cvn.model.plantilla.categorias.Publicaciones;
import es.uji.apps.cvn.model.plantilla.categorias.PublicacionesDocentes;
import es.uji.apps.cvn.translators.TipoProduccion;
import es.uji.apps.cvn.ui.beans.CvnItemBean;
import es.uji.apps.cvn.ui.beans.CvnRootBean;
import es.uji.apps.cvn.ui.beans.CvnString;

public class CvnRootBeanGenerator
{
    private CVN cvn;
    private Persona persona;
    private Plantilla plantilla;
    private int anyoActual;

    public CvnRootBeanGenerator(Persona persona, Plantilla plantilla)
    {
        this.persona = persona;
        this.plantilla = plantilla;

        this.anyoActual = Calendar.getInstance().get(Calendar.YEAR);
    }

    public CvnRootBean generate()
    {
        this.cvn = new CVN();

        cvn.addCvnItemBean(generateDatosPersonaBean());

        //cvn.addCvnItemBean(generaDatosCVN(plantilla));

        CvnString idioma = new CvnString();
        idioma.setCode("000.020.000.070");
        idioma.setValue(plantilla.getIdioma());

        CvnItemBean identificacionCV = new CvnItemBean();
        identificacionCV.setCode("000.020.000.000");
        identificacionCV.setCvnString(Collections.singletonList(idioma));

        cvn.addCvnItemBean(identificacionCV);


        for (ParticipacionGrupo participacionGrupo : GruposInvestigacion.aplicaFiltros(
                persona.getParticipacionesGrupos(), plantilla))
        {
            cvn.addCvnItemBean(generateDatosGrupoInvestigacionBean(participacionGrupo));
        }

        for (ParticipacionProyecto participacionProyectoCompetitivo : Proyectos.aplicaFiltros(
                persona.getParticipacionesProyectosCompetitivos(), plantilla))
        {
            cvn.addCvnItemBean(generateDatosProyectoInvestigacionCompetitivo(participacionProyectoCompetitivo));
        }

        for (ParticipacionProyecto participacionProyectoNoCompetitivo : Contratos.aplicaFiltros(
                persona.getParticipacionesProyectosNoCompetitivos(), plantilla))
        {
            cvn.addCvnItemBean(generateDatosProyectoInvestigacionNoCompetitivo(participacionProyectoNoCompetitivo));
        }

        for (ParticipacionPublicacionCientificoTecnica participacionPublicacion : Publicaciones
                .aplicaFiltros(persona.getParticipacionesPublicaciones(), plantilla))
        {
            cvn.addCvnItemBean(generateDatosPublicacion(participacionPublicacion));
        }

        for (ParticipacionCongreso participacionCongreso : Congresos.aplicaFiltros(
                persona.getParticipacionesCongresos(), plantilla))
        {
            cvn.addCvnItemBean(generateDatosCongreso(participacionCongreso));
        }

        for (ParticipacionPublicacionDocente participacionPublicacion : PublicacionesDocentes
                .aplicaFiltros(persona.getParticipacionesPublicacionesDocentes(), plantilla))
        {
            cvn.addCvnItemBean(generateDatosPublicacionDocente(participacionPublicacion));
        }

        for (ParticipacionCongreso participacionCongreso : CongresosDocentes.aplicaFiltros(
                persona.getParticipacionesCongresosDocentes(), plantilla))
        {
            cvn.addCvnItemBean(generateDatosCongresoDocente(participacionCongreso));
        }

        for (Tesis tesis : persona.getTesis())
        {
            cvn.addCvnItemBean(generateDatosTesis(tesis));
        }
        /*
        for (SituacionProfesional sp : persona.getSituacionProfesionalActiva())
        {
            cvn.addCvnItemBean(generateDatosSituacionProfesionalActiva(sp));
        }

        for (SituacionProfesional sp : persona.getSituacionProfesionalAnterior())
        {
            cvn.addCvnItemBean(generateDatosSituacionProfesionalAntigua(sp));
        }
          */

        return this.cvn;
    }

    private CvnItemBean generaDatosCVN(Plantilla plantilla) {
        DatosCVN cvnItemBean = new DatosCVN();

        Idioma idioma = cvnItemBean.getIdioma();
        if (plantilla.getIdioma().equals("spa")) {
          idioma.addIdioma("Español", "spa");}
        if (plantilla.getIdioma().equals("cat")) {
            idioma.addIdioma("Catalan","cat");}
        if (plantilla.getIdioma().equals("eus")) {
            idioma.addIdioma("Vasco","eus");}
        if (plantilla.getIdioma().equals("glg")) {
            idioma.addIdioma("Gallego","glg");}


        return cvnItemBean;

    }

    private void addGruposInvestigacion()
    {
        if (plantilla.getGruposInvestigacion() != null)
        {
            int maxAnyos = plantilla.getGruposInvestigacion().getMaxAnyos();
            int maxItems = plantilla.getGruposInvestigacion().getMaxItems();

            int count = 0;

            for (ParticipacionGrupo participacionGrupo : persona.getParticipacionesGrupos())
            {
                if ((maxItems != 0 && count == maxItems)
                        || (maxAnyos != 0 && anyoActual - maxAnyos <= 0))
                {
                    break;
                }

                cvn.addCvnItemBean(generateDatosGrupoInvestigacionBean(participacionGrupo));
                count++;
            }
        }
    }

    private CvnItemBean generateDatosPersonaBean()
    {
        DatosPersonales cvnItemBean = new DatosPersonales();

        Identificacion identificacion = cvnItemBean.getIdentificacion();
        identificacion.addApellidos(persona.getApellido1(), persona.getApellido2());
        identificacion.addNombre(persona.getNombre());
        identificacion.addSexo(persona.getGenero());
        identificacion.addNacionalidad(persona.getNacionalidad());
        identificacion.addFechaNacimiento(persona.getFechaNacimiento());
        identificacion.addPaisNacimiento(persona.getPaisNacimiento());
        identificacion.addRegionNacimiento(persona.getRegionNacimiento());
        identificacion.addCiudadNacimiento(persona.getCiudadNacimiento());
        identificacion.addIdentificacion(persona.getTipoIdentificacion(),
                persona.getIdentificacion());
        identificacion.addFoto(persona.getFoto(), persona.getMimeTypeFoto());

        Contacto contacto = cvnItemBean.getContacto();
        if (persona.getDomiciliosContacto().size() > 0)
        {
            Domicilio domicilio = persona.getDomiciliosContacto().get(0);
            contacto.addDireccion(domicilio.getDireccionContacto());
            contacto.addDireccionAux(domicilio.getDireccionContactoAux());
            contacto.addCodigoPostal(domicilio.getCodigoPostal());
            contacto.addCiudad(domicilio.getCiudadContacto());
            contacto.addPais(domicilio.getPaisContacto());
            contacto.addRegion(domicilio.getRegionContacto());
            contacto.addProvincia(domicilio.getProvinciaContacto());
        }
        contacto.addTelefonoFijo(persona.getTelefonoFijo());
        contacto.addFax(persona.getFax());
        contacto.addEmail(persona.getEmail());
        contacto.addTelefonoMovil(persona.getTelefonoMovil());
        contacto.addPaginaWeb(persona.getWebpage());

        return cvnItemBean;
    }

    private CvnItemBean generateDatosGrupoInvestigacionBean(ParticipacionGrupo participacionGrupo)
    {
        ParticipacionGrupoInvestigacion cvnItemBean = new ParticipacionGrupoInvestigacion();

        GrupoInvestigacion grupoInvestigacion = cvnItemBean.getGrupoInvestigacion();
        grupoInvestigacion.addObjetivo(participacionGrupo.getGrupoInvestigacion().getObjetivo());
        grupoInvestigacion.addNombre(participacionGrupo.getGrupoInvestigacion().getNombre());
        grupoInvestigacion.addCodigoNormalizado(participacionGrupo.getGrupoInvestigacion()
                .getCodigoNormalizado());
        Persona responsable = participacionGrupo.getGrupoInvestigacion().getResponsable();
        if (responsable != null)
        {
            grupoInvestigacion.addResponsable(responsable.getNombre(), responsable.getApellido1(),
                    responsable.getApellido2());
        }
        grupoInvestigacion.addNumeroComponentes(participacionGrupo.getGrupoInvestigacion()
                .getNComponentes());
        grupoInvestigacion.addNumeroTesisDirigidas(participacionGrupo.getGrupoInvestigacion()
                .getNTesis());
        grupoInvestigacion.addNumeroPostDocDirigidas(participacionGrupo.getGrupoInvestigacion()
                .getNPostDoc());

        Entidad entidad = grupoInvestigacion.getEntidad();
        entidad.addNombre(participacionGrupo.getGrupoInvestigacion().getNombreEntidad());
        entidad.addPais(participacionGrupo.getGrupoInvestigacion().getPais());
        entidad.addRegion(participacionGrupo.getGrupoInvestigacion().getRegion());
        entidad.addCiudad(participacionGrupo.getGrupoInvestigacion().getCiudad());

        InvestigadorGrupo investigadorGrupo = cvnItemBean.getInvestigadorGrupo();
        investigadorGrupo.addFechaInicio(participacionGrupo.getFechaInicio());
        investigadorGrupo.addDuracion(participacionGrupo.getDuracion());
        investigadorGrupo.addClasesColaboracion(participacionGrupo.getTipoColaboracion());
        investigadorGrupo.addResultadosOtros(participacionGrupo.getResultadosDesc());
        investigadorGrupo.addResultadosRelevantes(participacionGrupo.getResultadosRelevantes());
        investigadorGrupo.addResultadosPalabrasClave(participacionGrupo.getResultadosKeywords());

        return cvnItemBean;
    }

    private CvnItemBean generateDatosProyectoInvestigacionCompetitivo(
            ParticipacionProyecto participacionProyecto)
    {
        ParticipacionProyectoInvestigacionCompetitivo cvnItemBean = new ParticipacionProyectoInvestigacionCompetitivo();

        ProyectoInvestigacion proyectoInvestigacion = cvnItemBean.getProyectoInvestigacion();
        proyectoInvestigacion.addTitulo(participacionProyecto.getProyectoInvestigacion()
                .getTitulo());
        proyectoInvestigacion.addIdentificacionPalabrasClave(participacionProyecto
                .getProyectoInvestigacion().getTituloKeywords());
        proyectoInvestigacion.addModalidad(participacionProyecto.getProyectoInvestigacion()
                .getModalidad());
        proyectoInvestigacion.addAmbito(participacionProyecto.getProyectoInvestigacion()
                .getAmbito());
        proyectoInvestigacion.addAmbitoOtros(participacionProyecto.getProyectoInvestigacion()
                .getAmbitoStr());

        for (Persona responsable : participacionProyecto.getProyectoInvestigacion()
                .getResponsables())
        {
            proyectoInvestigacion.addResponsable(responsable.getNombre(),
                    responsable.getApellido1(), responsable.getApellido2());
        }

        proyectoInvestigacion.addNumeroInvestigadoresParticipantes(participacionProyecto
                .getProyectoInvestigacion().getNInvestigadores());
        proyectoInvestigacion.addNumeroPersonasAnyo(participacionProyecto
                .getProyectoInvestigacion().getNPersonasAnyo());
        proyectoInvestigacion.addFechaInicio(participacionProyecto.getProyectoInvestigacion()
                .getFechaInicio());
        proyectoInvestigacion.addDuracion(participacionProyecto.getProyectoInvestigacion()
                .getDuracion());
        proyectoInvestigacion.addResultadosRelevantes(participacionProyecto
                .getProyectoInvestigacion().getResultadosRelevantes());
        proyectoInvestigacion.addResultadosPalabrasClave(participacionProyecto
                .getProyectoInvestigacion().getResultadosKeywords());
        ((ProyectoInvestigacionCompetitivo) proyectoInvestigacion)
                .addFechaFin(participacionProyecto.getProyectoInvestigacion().getFechaFin());

        for (es.uji.apps.cvn.model.Entidad entidadParticipante : participacionProyecto
                .getProyectoInvestigacion().getEntidadesParticipantes())
        {
            proyectoInvestigacion.addEntidadParticipante(entidadParticipante.getNombre());
        }

        Entidad entidad = ((ProyectoInvestigacionCompetitivo) proyectoInvestigacion).getEntidad();
        entidad.addNombre(participacionProyecto.getProyectoInvestigacion().getEntidadEjecutora());
        // TODO : ??? Entidad debe ser una referencia a objeto, no una cadena
        // entidad.addTipo(participacionProyecto.getProyectoInvestigacion().getTipo()); String
        // entidad.addTipoOtros(participacionProyecto.getProyectoInvestigacion().getSubtipo());
        // entidad.addPais(participacionProyecto.getProyectoInvestigacion().getE)

        FinanciacionProyecto financiacionProyecto = ((ProyectoInvestigacionCompetitivo) proyectoInvestigacion)
                .getFinanciacionProyecto();
        financiacionProyecto.addNombrePrograma(participacionProyecto.getProyectoInvestigacion()
                .getNombreProgramaFinanciacion());
        financiacionProyecto.addCodigoProyecto(participacionProyecto.getProyectoInvestigacion()
                .getCodigoExterno());
        financiacionProyecto.addFinanciacionTotal(participacionProyecto.getProyectoInvestigacion()
                .getDotacionTotal());
        financiacionProyecto.addFinanciacionSubproyecto(participacionProyecto
                .getProyectoInvestigacion().getDotacionSubproyecto());
        financiacionProyecto.addFinanciacionPorcentaje(participacionProyecto
                .getProyectoInvestigacion().getPorcentajeSubvencion());
        financiacionProyecto.addFinanciacionCredito(participacionProyecto
                .getProyectoInvestigacion().getPorcentajeCredito());
        financiacionProyecto.addFinanciacionMixto(participacionProyecto.getProyectoInvestigacion()
                .getPorcentajeMixto());

        for (es.uji.apps.cvn.model.Entidad entidadFinanciadora : participacionProyecto
                .getProyectoInvestigacion().getEntidadesFinanciadoras())
        {
            Entidad entidadFinanciadoraPC = financiacionProyecto.addEntidadFinanciadora();
            entidadFinanciadoraPC.addNombre(entidadFinanciadora.getNombre());
            entidadFinanciadoraPC.addTipo(entidadFinanciadora.getTipo());
            entidadFinanciadoraPC.addTipoOtros(entidadFinanciadora.getTipoDescr());
            entidadFinanciadoraPC.addPais(entidadFinanciadora.getPais());
            entidadFinanciadoraPC.addRegion(entidadFinanciadora.getRegionStr());
            entidadFinanciadoraPC.addCiudad(entidadFinanciadora.getCiudad());
        }

        InvestigadorProyecto investigadorProyecto = cvnItemBean.getInvestigadorProyecto();
        investigadorProyecto.addCalidadParticipacion(participacionProyecto
                .getCalidadParticipacion());
        ((InvestigadorProyectoCompetitivo) investigadorProyecto)
                .addTipoParticipacion(participacionProyecto.getTipoParticipacion());
        ((InvestigadorProyectoCompetitivo) investigadorProyecto)
                .addAportacion(participacionProyecto.getAportaciones());
        ((InvestigadorProyectoCompetitivo) investigadorProyecto)
                .addDedicacion(participacionProyecto.getDedicacion());

        return cvnItemBean;
    }

    private CvnItemBean generateDatosProyectoInvestigacionNoCompetitivo(
            ParticipacionProyecto participacionProyecto)
    {
        ParticipacionProyectoInvestigacionNoCompetitivo cvnItemBean = new ParticipacionProyectoInvestigacionNoCompetitivo();

        ProyectoInvestigacion proyectoInvestigacion = cvnItemBean.getProyectoInvestigacion();
        proyectoInvestigacion.addTitulo(participacionProyecto.getProyectoInvestigacion()
                .getTitulo());
        proyectoInvestigacion.addIdentificacionPalabrasClave(participacionProyecto
                .getProyectoInvestigacion().getTituloKeywords());
        proyectoInvestigacion.addModalidad(participacionProyecto.getProyectoInvestigacion()
                .getModalidad());
        proyectoInvestigacion.addAmbito(participacionProyecto.getProyectoInvestigacion()
                .getAmbito());
        proyectoInvestigacion.addAmbitoOtros(participacionProyecto.getProyectoInvestigacion()
                .getAmbitoStr());

        for (Persona responsable : participacionProyecto.getProyectoInvestigacion()
                .getResponsables())
        {
            proyectoInvestigacion.addResponsable(responsable.getNombre(),
                    responsable.getApellido1(), responsable.getApellido2());
        }

        proyectoInvestigacion.addNumeroInvestigadoresParticipantes(participacionProyecto
                .getProyectoInvestigacion().getNInvestigadores());
        proyectoInvestigacion.addNumeroPersonasAnyo(participacionProyecto
                .getProyectoInvestigacion().getNPersonasAnyo());
        proyectoInvestigacion.addFechaInicio(participacionProyecto.getProyectoInvestigacion()
                .getFechaInicio());
        proyectoInvestigacion.addDuracion(participacionProyecto.getProyectoInvestigacion()
                .getDuracion());
        proyectoInvestigacion.addResultadosRelevantes(participacionProyecto
                .getProyectoInvestigacion().getResultadosRelevantes());
        proyectoInvestigacion.addResultadosPalabrasClave(participacionProyecto
                .getProyectoInvestigacion().getResultadosKeywords());

        for (es.uji.apps.cvn.model.Entidad entidadParticipante : participacionProyecto
                .getProyectoInvestigacion().getEntidadesParticipantes())
        {
            proyectoInvestigacion.addEntidadParticipante(entidadParticipante.getNombre());
        }

        Entidad entidad = ((ProyectoInvestigacionNoCompetitivo) proyectoInvestigacion).getEntidad();
        entidad.addNombre(participacionProyecto.getProyectoInvestigacion().getEntidadEjecutora());
        // TODO : ??? Entidad debe ser una referencia a objeto, no una cadena
        // entidad.addTipo(participacionProyecto.getProyectoInvestigacion().getTipo()); String
        // entidad.addTipoOtros(participacionProyecto.getProyectoInvestigacion().getSubtipo());
        // entidad.addPais(participacionProyecto.getProyectoInvestigacion().getE)

        FinanciacionProyecto financiacionProyecto = ((ProyectoInvestigacionNoCompetitivo) proyectoInvestigacion)
                .getFinanciacionProyecto();
        financiacionProyecto.addNombrePrograma(participacionProyecto.getProyectoInvestigacion()
                .getNombreProgramaFinanciacion());
        financiacionProyecto.addCodigoProyecto(participacionProyecto.getProyectoInvestigacion()
                .getCodigoExterno());
        financiacionProyecto.addFinanciacionTotal(participacionProyecto.getProyectoInvestigacion()
                .getDotacionTotal());
        financiacionProyecto.addFinanciacionSubproyecto(participacionProyecto
                .getProyectoInvestigacion().getDotacionSubproyecto());
        financiacionProyecto.addFinanciacionPorcentaje(participacionProyecto
                .getProyectoInvestigacion().getPorcentajeSubvencion());
        financiacionProyecto.addFinanciacionCredito(participacionProyecto
                .getProyectoInvestigacion().getPorcentajeCredito());
        financiacionProyecto.addFinanciacionMixto(participacionProyecto.getProyectoInvestigacion()
                .getPorcentajeMixto());

        for (es.uji.apps.cvn.model.Entidad entidadFinanciadora : participacionProyecto
                .getProyectoInvestigacion().getEntidadesFinanciadoras())
        {
            Entidad entidadFinanciadoraPNC = financiacionProyecto.addEntidadFinanciadora();
            entidadFinanciadoraPNC.addNombre(entidadFinanciadora.getNombre());
            entidadFinanciadoraPNC.addTipo(entidadFinanciadora.getTipo());
            entidadFinanciadoraPNC.addTipoOtros(entidadFinanciadora.getTipoDescr());
            entidadFinanciadoraPNC.addPais(entidadFinanciadora.getPais());
            entidadFinanciadoraPNC.addRegion(entidadFinanciadora.getRegionStr());
            entidadFinanciadoraPNC.addCiudad(entidadFinanciadora.getCiudad());
        }

        InvestigadorProyecto investigadorProyecto = cvnItemBean.getInvestigadorProyecto();
        investigadorProyecto.addCalidadParticipacion(participacionProyecto
                .getCalidadParticipacion());

        return cvnItemBean;
    }

    private CvnItemBean generateDatosPublicacion(
            ParticipacionPublicacionCientificoTecnica participacionPublicacion)
    {
        ParticipacionEnPublicacion participacionEnPublicacion = new ParticipacionEnPublicacion();
        participacionEnPublicacion.addPosicionAutor(participacionPublicacion.getPosicionAutor());
        participacionEnPublicacion.addCalidad(participacionPublicacion.getCalidad());
        participacionEnPublicacion.addResultadosDestacados(participacionPublicacion
                .getResultadosDestacados());
        participacionEnPublicacion.addPublicacionRelevante(participacionPublicacion
                .getIsPublicacionRelevante());
        participacionEnPublicacion.addResenyasEnRevistas(participacionPublicacion
                .getPublicacionCientificoTecnica().getNResenyasEnRevistas());

        for (String traduccion : participacionPublicacion.getPublicacionCientificoTecnica()
                .getTraducciones())
        {
            participacionEnPublicacion.addTraducciones(traduccion);
        }

        PublicacionCientificoTecnica publicacion = (PublicacionCientificoTecnica) participacionEnPublicacion
                .getPublicacion();
        publicacion.addTitulo(participacionPublicacion.getPublicacionCientificoTecnica()
                .getPublicacion().getTitulo());
        publicacion.addNombre(participacionPublicacion.getPublicacionCientificoTecnica()
                .getPublicacion().getNombre());
        publicacion.addVolumen(participacionPublicacion.getPublicacionCientificoTecnica()
                .getPublicacion().getVolumen());
        publicacion.addPaginasInicioFin(participacionPublicacion.getPublicacionCientificoTecnica()
                .getPublicacion().getPaginaInicio(), participacionPublicacion
                .getPublicacionCientificoTecnica().getPublicacion().getPaginaFin());
        publicacion.addEditorial(participacionPublicacion.getPublicacionCientificoTecnica()
                .getPublicacion().getEditorial());
        publicacion.addPais(participacionPublicacion.getPublicacionCientificoTecnica()
                .getPublicacion().getPais());
        publicacion.addRegion(participacionPublicacion.getPublicacionCientificoTecnica()
                .getPublicacion().getRegion());
        publicacion.addFecha(participacionPublicacion.getPublicacionCientificoTecnica()
                .getPublicacion().getFecha());
        publicacion.addURL(participacionPublicacion.getPublicacionCientificoTecnica()
                .getPublicacion().getUrl());
        publicacion.addTipo(participacionPublicacion.getPublicacionCientificoTecnica()
                .getPublicacion().getTipo());
        publicacion.addTipoOtros(participacionPublicacion.getPublicacionCientificoTecnica()
                .getPublicacion().getTipoOtros());

        for (AutorPublicacion autorPublicacion : participacionPublicacion
                .getPublicacionCientificoTecnica().getPublicacion().getAutores())
        {
            publicacion.addAutorPorOrdenFirma(autorPublicacion.getNombre(),
                    autorPublicacion.getApellido1(), autorPublicacion.getApellido2(), autorPublicacion.getOrden());
        }

        publicacion.addSoporte(participacionPublicacion.getPublicacionCientificoTecnica()
                .getPublicacion().getSoporte());
        publicacion.addColeccion(participacionPublicacion.getPublicacionCientificoTecnica()
                .getPublicacion().getColeccion());

        for (String isbn : participacionPublicacion.getPublicacionCientificoTecnica()
                .getPublicacion().getIsbns())
        {
            if (participacionPublicacion.getPublicacionCientificoTecnica().getPublicacion()
                    .getTipo().equals(TipoProduccion.ARTICULO.getTipo()))
            {
                publicacion.addISSN(isbn);
            }
            else if (participacionPublicacion.getPublicacionCientificoTecnica().getPublicacion()
                    .getTipo().equals(TipoProduccion.CAPITULOLIBRO.getTipo())
                    || participacionPublicacion.getPublicacionCientificoTecnica().getPublicacion()
                            .getTipo().equals(TipoProduccion.LIBRO.getTipo()))
            {
                publicacion.addISBN(isbn);
            }
        }

        publicacion.addDepositoLegal(participacionPublicacion.getPublicacionCientificoTecnica()
                .getPublicacion().getDepositoLegal());
        ((PublicacionCientificoTecnica) publicacion).addCiudad(participacionPublicacion
                .getPublicacionCientificoTecnica().getPublicacion().getCiudad());

        for (Impacto impactoPublicacion : participacionPublicacion
                .getPublicacionCientificoTecnica().getListaImpacto())
        {
            es.uji.apps.cvn.model.cvn.publicaciones.Impacto impacto = participacionEnPublicacion
                    .addNewImpacto();
            impacto.addIndice(impactoPublicacion.getIndice());
            impacto.addFuente(impactoPublicacion.getFuente());
            impacto.addFuenteOtros(impactoPublicacion.getFuenteOtros());
            impacto.addCategoria(impactoPublicacion.getCategoria());
            impacto.addPosicionCategoria(impactoPublicacion.getPosicion());
            impacto.addNRevistasCategoria(impactoPublicacion.getNRevistas());
            impacto.addDentroPorc25MayorIndice(impactoPublicacion.getIsDentroPorc25Top());
        }

        return participacionEnPublicacion;
    }

    private CvnItemBean generateDatosCongreso(ParticipacionCongreso participacionCongreso)
    {
        ParticipacionEnCongreso participacionEnCongreso = new ParticipacionEnCongreso();

        Congreso congreso = participacionEnCongreso.getCongreso();
        congreso.addTipoEvento(participacionCongreso.getCongreso().getTipoEvento());
        congreso.addTipoEventoOtros(participacionCongreso.getCongreso().getTipoEventoOtros());
        congreso.addTitulo(participacionCongreso.getCongreso().getTitulo());
        congreso.addTipoParticipacion(participacionCongreso.getTipoParticipacion());
        congreso.addIntervencion(participacionCongreso.getCongreso().getIntervencion());
        congreso.addIntervencionOtros(participacionCongreso.getCongreso().getIntervencionOtros());
        congreso.addAmbito(participacionCongreso.getCongreso().getAmbito());
        congreso.addAmbitoOtros(participacionCongreso.getCongreso().getAmbitoOtros());
        congreso.addFechaFinalizacion(participacionCongreso.getCongreso().getFechaFinalizacion());
        congreso.addPublicacionActa(participacionCongreso.getCongreso().getIsPublicadoActas());
        congreso.addComiteExterno(participacionCongreso.getCongreso().getHasComiteExterno());

        Entidad entidadOrganizadora = congreso.getEntidadOrganizadora();
        entidadOrganizadora.addNombre(participacionCongreso.getCongreso().getEntidadOrganizadora()
                .getNombre());
        entidadOrganizadora.addTipo(participacionCongreso.getCongreso().getEntidadOrganizadora()
                .getTipo());
        entidadOrganizadora.addTipoOtros(participacionCongreso.getCongreso()
                .getEntidadOrganizadora().getTipoDescr());
        entidadOrganizadora.addPais(participacionCongreso.getCongreso().getEntidadOrganizadora()
                .getPais());
        entidadOrganizadora.addRegion(participacionCongreso.getCongreso().getEntidadOrganizadora()
                .getRegionStr());
        entidadOrganizadora.addCiudad(participacionCongreso.getCongreso().getEntidadOrganizadora()
                .getCiudad());

        Localizacion localizacion = congreso.getLocalizacion();
        ((LocalizacionCongreso) localizacion).addNombreCongreso(participacionCongreso.getCongreso()
                .getNombre());
        localizacion.addPais(participacionCongreso.getCongreso().getPais());
        localizacion.addRegion(participacionCongreso.getCongreso().getRegion());
        localizacion.addCiudad(participacionCongreso.getCongreso().getCiudad());

        if (participacionCongreso.getCongreso().getFecha() != null)
        {
            localizacion.addFecha(participacionCongreso.getCongreso().getFecha());
        }
        else
        {
            ((LocalizacionCongreso) localizacion).addAno(participacionCongreso.getCongreso()
                    .getAno());
        }

        Publicacion publicacion = participacionEnCongreso.getPublicacion();
        publicacion.addTipo(participacionCongreso.getCongreso().getPublicacion().getTipo());
        publicacion.addNombre(participacionCongreso.getCongreso().getPublicacion().getNombre());
        publicacion.addVolumen(participacionCongreso.getCongreso().getPublicacion().getVolumen());
        publicacion.addPaginasInicioFin(participacionCongreso.getCongreso().getPublicacion()
                .getPaginaInicio(), participacionCongreso.getCongreso().getPublicacion()
                .getPaginaFin());
        publicacion.addEditorial(participacionCongreso.getCongreso().getPublicacion()
                .getEditorial());
        publicacion.addPais(participacionCongreso.getCongreso().getPublicacion().getPais());
        publicacion.addRegion(participacionCongreso.getCongreso().getPublicacion().getRegion());
        publicacion.addFecha(participacionCongreso.getCongreso().getPublicacion().getFecha());
        publicacion.addURL(participacionCongreso.getCongreso().getPublicacion().getUrl());

        for (String isbn : participacionCongreso.getCongreso().getPublicacion().getIsbns())
        {
            if (participacionCongreso.getCongreso().getPublicacion().getTipo()
                    .equals(TipoProduccion.ARTICULO.getTipo()))
            {
                publicacion.addISSN(isbn);
            }
            else if (participacionCongreso.getCongreso().getPublicacion().getTipo()
                    .equals(TipoProduccion.CAPITULOLIBRO.getTipo()))
            {
                publicacion.addISBN(isbn);
            }
        }

        publicacion.addDepositoLegal(participacionCongreso.getCongreso().getPublicacion()
                .getDepositoLegal());

        for (AutorPublicacion autorPublicacion : participacionCongreso.getCongreso()
                .getPublicacion().getAutores())
        {
            publicacion.addAutorPorOrdenFirma(autorPublicacion.getNombre(),
                    autorPublicacion.getApellido1(), autorPublicacion.getApellido2(), autorPublicacion.getOrden());
        }

        return participacionEnCongreso;
    }

    private CvnItemBean generateDatosPublicacionDocente(
            ParticipacionPublicacionDocente participacionPublicacion)
    {
        ParticipacionEnPublicacionDocente participacionEnPublicacion = new ParticipacionEnPublicacionDocente();
        participacionEnPublicacion.addPosicionAutor(participacionPublicacion.getPosicionAutor());
        participacionEnPublicacion.addCalidad(participacionPublicacion.getCalidad());
        participacionEnPublicacion.addDenominacion(participacionPublicacion.getPublicacionDocente()
                .getDenominacion());
        participacionEnPublicacion.addDestinatarios(participacionPublicacion
                .getPublicacionDocente().getDestinatarios());
        participacionEnPublicacion.addFechaCreacion(participacionPublicacion
                .getPublicacionDocente().getFechaCreacion());
        participacionEnPublicacion.addJustificacion(participacionPublicacion
                .getPublicacionDocente().getJustificacion());

        PublicacionDocente publicacion = (PublicacionDocente) participacionEnPublicacion
                .getPublicacion();
        publicacion.addTitulo(participacionPublicacion.getPublicacionDocente().getPublicacion()
                .getTitulo());
        publicacion.addNombre(participacionPublicacion.getPublicacionDocente().getPublicacion()
                .getNombre());
        publicacion.addVolumen(participacionPublicacion.getPublicacionDocente().getPublicacion()
                .getVolumen());
        publicacion.addPaginasInicioFin(participacionPublicacion.getPublicacionDocente()
                .getPublicacion().getPaginaInicio(), participacionPublicacion
                .getPublicacionDocente().getPublicacion().getPaginaFin());
        publicacion.addEditorial(participacionPublicacion.getPublicacionDocente().getPublicacion()
                .getEditorial());
        publicacion.addPais(participacionPublicacion.getPublicacionDocente().getPublicacion()
                .getPais());
        publicacion.addRegion(participacionPublicacion.getPublicacionDocente().getPublicacion()
                .getRegion());
        publicacion.addFecha(participacionPublicacion.getPublicacionDocente().getPublicacion()
                .getFecha());
        publicacion.addURL(participacionPublicacion.getPublicacionDocente().getPublicacion()
                .getUrl());

        for (AutorPublicacion autorPublicacion : participacionPublicacion.getPublicacionDocente()
                .getPublicacion().getAutores())
        {
            publicacion.addAutorPorOrdenFirma(autorPublicacion.getNombre(),
                    autorPublicacion.getApellido1(), autorPublicacion.getApellido2(), autorPublicacion.getOrden());
        }

        publicacion.addSoporte(participacionPublicacion.getPublicacionDocente().getPublicacion()
                .getSoporte());

        for (String isbn : participacionPublicacion.getPublicacionDocente().getPublicacion()
                .getIsbns())
        {
            if (participacionPublicacion.getPublicacionDocente().getPublicacion().getTipo()
                    .equals(TipoProduccion.ARTICULO.getTipo()))
            {
                publicacion.addISSN(isbn);
            }
            else if (participacionPublicacion.getPublicacionDocente().getPublicacion().getTipo()
                    .equals(TipoProduccion.CAPITULOLIBRO.getTipo())
                    || participacionPublicacion.getPublicacionDocente().getPublicacion().getTipo()
                            .equals(TipoProduccion.LIBRO.getTipo()))
            {
                publicacion.addISBN(isbn);
            }
        }

        publicacion.addDepositoLegal(participacionPublicacion.getPublicacionDocente()
                .getPublicacion().getDepositoLegal());

        return participacionEnPublicacion;
    }

    private CvnItemBean generateDatosCongresoDocente(ParticipacionCongreso participacionCongreso)
    {
        ParticipacionEnCongresoDocente participacionEnCongreso = new ParticipacionEnCongresoDocente();

        CongresoDocente congreso = participacionEnCongreso.getCongreso();
        congreso.addTipoEvento(participacionCongreso.getCongreso().getTipoEvento());
        congreso.addTipoEventoOtros(participacionCongreso.getCongreso().getTipoEventoOtros());
        congreso.addTipoParticipacion(participacionCongreso.getTipoParticipacion());
        congreso.addFechaFinalizacion(participacionCongreso.getCongreso().getFechaFinalizacion());

        congreso.addObjetivos(participacionCongreso.getCongreso().getObjetivos());
        congreso.addDestinatarios(participacionCongreso.getCongreso().getDestinatarios());
        congreso.addIdioma(participacionCongreso.getCongreso().getIdioma());
        congreso.addFechaPresentacion(participacionCongreso.getCongreso().getFechaPresentacion());
        congreso.addHoras(participacionCongreso.getCongreso().getHoras());

        Entidad entidadOrganizadora = congreso.getEntidadOrganizadora();
        entidadOrganizadora.addNombre(participacionCongreso.getCongreso().getEntidadOrganizadora()
                .getNombre());
        entidadOrganizadora.addTipo(participacionCongreso.getCongreso().getEntidadOrganizadora()
                .getTipo());
        entidadOrganizadora.addTipoOtros(participacionCongreso.getCongreso()
                .getEntidadOrganizadora().getTipoDescr());
        entidadOrganizadora.addPais(participacionCongreso.getCongreso().getEntidadOrganizadora()
                .getPais());
        entidadOrganizadora.addRegion(participacionCongreso.getCongreso().getEntidadOrganizadora()
                .getRegionStr());
        entidadOrganizadora.addCiudad(participacionCongreso.getCongreso().getEntidadOrganizadora()
                .getCiudad());

        LocalizacionCongresoDocente localizacion = (LocalizacionCongresoDocente) congreso
                .getLocalizacion();
        localizacion.addNombreCongreso(participacionCongreso.getCongreso().getNombre());
        localizacion.addPais(participacionCongreso.getCongreso().getPais());
        localizacion.addRegion(participacionCongreso.getCongreso().getRegion());
        localizacion.addCiudad(participacionCongreso.getCongreso().getCiudad());

        if (participacionCongreso.getCongreso().getFecha() != null)
        {
            localizacion.addFecha(participacionCongreso.getCongreso().getFecha());
        }
        else
        {
            localizacion.addAno(participacionCongreso.getCongreso().getAno());
        }

        Publicacion publicacion = participacionEnCongreso.getPublicacion();
        publicacion.addTipo(participacionCongreso.getCongreso().getPublicacion().getTipo());
        publicacion.addTitulo(participacionCongreso.getCongreso().getPublicacion().getTitulo());
        publicacion.addNombre(participacionCongreso.getCongreso().getPublicacion().getNombre());
        publicacion.addVolumen(participacionCongreso.getCongreso().getPublicacion().getVolumen());
        publicacion.addPaginasInicioFin(participacionCongreso.getCongreso().getPublicacion()
                .getPaginaInicio(), participacionCongreso.getCongreso().getPublicacion()
                .getPaginaFin());
        publicacion.addEditorial(participacionCongreso.getCongreso().getPublicacion()
                .getEditorial());
        publicacion.addPais(participacionCongreso.getCongreso().getPublicacion().getPais());
        publicacion.addRegion(participacionCongreso.getCongreso().getPublicacion().getRegion());
        publicacion.addFecha(participacionCongreso.getCongreso().getPublicacion().getFecha());
        publicacion.addURL(participacionCongreso.getCongreso().getPublicacion().getUrl());

        for (String isbn : participacionCongreso.getCongreso().getPublicacion().getIsbns())
        {
            if (participacionCongreso.getCongreso().getPublicacion().getTipo()
                    .equals(TipoProduccion.ARTICULO.getTipo()))
            {
                publicacion.addISSN(isbn);
            }
            else if (participacionCongreso.getCongreso().getPublicacion().getTipo()
                    .equals(TipoProduccion.CAPITULOLIBRO.getTipo()))
            {
                publicacion.addISBN(isbn);
            }
        }

        publicacion.addDepositoLegal(participacionCongreso.getCongreso().getPublicacion()
                .getDepositoLegal());

        return participacionEnCongreso;
    }

    private CvnItemBean generateDatosTesis(Tesis tesis)
    {

        ParticipacionEnTesis participacionEnTesis = new ParticipacionEnTesis();
        CvnTesis cvnTesis = participacionEnTesis.getCvnTesis();

        cvnTesis.addTipo(tesis.getTipoId());
        cvnTesis.addTitulo(tesis.getTitulo());
        cvnTesis.addCodirector(tesis.getCodirectorNombre(), tesis.getCodirectorApellido1(),
                tesis.getCodirectorApellido2());
        cvnTesis.addPaisLectura(tesis.getPais());
        cvnTesis.addCiudadDireccion(tesis.getCiudad());
        cvnTesis.addEntidad(tesis.getEntidad());
        cvnTesis.addTipoEntidad(tesis.getTipoEntidad());
        cvnTesis.addAlumno(tesis.getAlumnoNombre(), tesis.getAlumnoApellido1(),
                tesis.getAlumnoApellido2());
        cvnTesis.addFechaLectura(tesis.getFechaLectura());
        cvnTesis.addCalificacion(tesis.getCalificacion());
        cvnTesis.addFechaDoctorEuropeo(tesis.getFechaDoctorEuropeo());
        cvnTesis.addDoctorEuropeo(tesis.isDoctorEuropeo());
       // cvnTesis.addMencionCalidad(tesis.isMencionCalidad());
        cvnTesis.addComunidadAutonoma(tesis.getRegion());

        return participacionEnTesis;

    }

    private CvnItemBean generateDatosSituacionProfesionalActiva(SituacionProfesional sp)
    {
        SituacionProfesionalActual spa = new SituacionProfesionalActual();
        CvnDetalleSituacionProfesionalActual actual = spa.getCvnDetalleSituacionProfesionalActual();

        actual.addGestionDocente(sp.getGestionDocente());
        actual.addEntidad(sp.getNombreEntidad());
        actual.addTipoEntidad(sp.getTipoEntidad());
        actual.addCentro(sp.getCentroVal());
        actual.addServicio(sp.getServicioVal());
        actual.addCiudad(sp.getCiudad());
        actual.addPais(sp.getPais().toString());
        actual.addRegion(sp.getRegion());
        actual.addTelefono(sp.getTelefono());
        actual.addFax(sp.getFax());
        actual.addCorreo(sp.getMail());
        actual.addCategoria(sp.getCategoria());
        actual.addFechaInicio(sp.getFechaInicio());
        actual.addSituacionActual(sp.getSituacion().toString());
        if (sp.getSituacion().toString() == "030") // 030 es otros
            actual.addSituacionActualOtros(sp.getSituacionOtros());
        actual.addDedicacion(sp.getDedicacion());
        actual.addUNESCO1(sp.getUnesco1());
        actual.addUNESCO2(sp.getUnesco2());
        actual.addUNESCO3(sp.getUnesco3());
        actual.addDedicacinProfesional(sp.getTextoDedicacion());
        actual.addPalabrasClave(sp.getPalabrasClave());

        return spa;
    }

    private CvnItemBean generateDatosSituacionProfesionalAntigua(SituacionProfesional sp)
    {
        SituacionProfesionalAntigua spa = new SituacionProfesionalAntigua();
        CvnDetalleSituacionProfesionalAntigua antigua = spa.getCvnDetalleSituacionProfesionalAntigua();

        antigua.addGestionDocente(sp.getGestionDocente());
        antigua.addEntidad(sp.getNombreEntidad());
        antigua.addTipoEntidad(sp.getTipoEntidad());
        antigua.addCentro(sp.getCentroVal());
        antigua.addServicio(sp.getServicioVal());
        antigua.addCiudad(sp.getCiudad());
        antigua.addPais(sp.getPais().toString());
        antigua.addRegion(sp.getRegion());
        antigua.addTelefono(sp.getTelefono());
        antigua.addFax(sp.getFax());
        antigua.addCorreo(sp.getMail());
        antigua.addCategoria(sp.getCategoria());
        antigua.addFechaInicio(sp.getFechaInicio());
        antigua.addDuracion(sp.getDuracion());
      //  antigua.addSituacionActual(sp.getSituacion().toString());
      //  if (sp.getSituacion().toString() == "030") // 030 es otros
      //      antigua.addSituacionActualOtros(sp.getSituacionOtros());
        antigua.addDedicacion(sp.getDedicacion());
        antigua.addUNESCO1(sp.getUnesco1());
        antigua.addUNESCO2(sp.getUnesco2());
        antigua.addUNESCO3(sp.getUnesco3());
        antigua.addDedicacinProfesional(sp.getTextoDedicacion());
        antigua.addPalabrasClave(sp.getPalabrasClave());

        return spa;
    }

}
