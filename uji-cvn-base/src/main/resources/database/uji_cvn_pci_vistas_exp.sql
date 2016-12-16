-- Dirección de tesis

CREATE OR REPLACE FORCE EDITIONABLE VIEW "UJI_CVN"."CVN_VIEW_DIRECCION_TESIS_PCI" ("ID", "TESIS_PROPIA", "TIPO_ID", "TIPO_TXT", "PERSONA", "TITULO", "CODIRECTOR_NOMBRE", "CODIRECTOR_APELLIDO1", "CODIRECTOR_APELLIDO2", "DIRECTOR_NOMBRE", "DIRECTOR_APELLIDO1", "DIRECTOR_APELLIDO2", "OTRODIRECTOR_NOMBRE", "OTRODIRECTOR_APELLIDO1", "OTRODIRECTOR_APELLIDO2", "PAIS", "REGION", "CIUDAD", "ENTIDAD", "TIPO_ENTIDAD", "ALUMNO_NOMBRE", "ALUMNO_APELLIDO1", "ALUMNO_APELLIDO2", "FECHA_LECTURA", "CALIFICACION", "DOCTOR_EUROPEO", "F_DOCTOR_EUROPEO", "PROGRAMA", "ENTIDAD_DEA", "F_OBTENCION_DEA", "MENCION_CALIDAD", "F_MENCION_CALIDAD", "PREMIO_EXTRAORDINARIO", "F_PREMIO_EXTRAORDINARIO", "DOCTOR_INTERNACIONAL") AS
  SELECT 'DOC*' || pro_id || '*' || alumno.id || '*' || x.id id,
          DECODE (x.id, e.per_id, 1, 0)                       tesis_propia,
          '067'                                               tipo_id,
          'Tesis'                                             tipo_txt,
          x.id                                                persona,
          titu_tesis_def                                      titulo,
          codirector.nombre
             codirector_nombre,
          codirector.apellido1
             codirector_apellido1,
          codirector.apellido2
             codirector_apellido2,
          director.nombre                                     director_nombre,
          director.apellido1
             director_apellido1,
          director.apellido2
             director_apellido2,
          otrodirector.nombre
             otrodirector_nombre,
          otrodirector.apellido1
             otrodirector_apellido1,
          otrodirector.apellido2
             otrodirector_apellido2 --, (select wm_concat(nombre2) from per_vw_personas p where p.id in (e.per_id_codirector, e.per_id_codirector2)) codirector
                                   --, (select wm_concat(nombre2) from per_vw_personas p where p.id in (e.per_id_director, e.per_id_codirector, e.per_id_codirector2) and p.id!=x.id) otrosdirectores
          ,
          '724'                                               pais -- ISO_3166
                                                                  ,
          'ES52'                                              region -- ISO_CVN_REGION
                                                                    ,
          'Castellón'                                        ciudad,
          'Universitat Jaume I'                               entidad -- Referencia a CVN_EXT_ENTIDADES
                                                                     ,
          '000'                                               tipo_entidad -- Universidad (CVN_ENTITY_TYPE)
                                                                          ,
          alumno.nombre                                       alumno_nombre,
          alumno.apellido1
             alumno_apellido1,
          alumno.apellido2
             alumno_apellido2,
          e.f_apro_tesis                                      fecha_lectura,
          c.nombre                                            calificacion,
          e.doctor_europeo                                    doctor_europeo,
          DECODE (e.doctor_europeo, 'S', e.f_apro_tesis, NULL)
             f_doctor_europeo,
          p.nombre                                            programa,
          'Universitat Jaume I'                               entidad_dea,
          f_acta_dea                                          f_obtencion_dea,
          DECODE ( (SELECT COUNT (*)
                      FROM doc_datos_pro dp
                     WHERE dp.pro_id = e.pro_id AND mencion_calidad = 'S'),
                  1, 'S',
                  NULL)
             mencion_calidad,
          (SELECT MIN (TO_DATE ('3112' || curso_aca, 'ddmmyyyy'))
             FROM doc_datos_pro dp
            WHERE     dp.pro_id = e.pro_id
                  AND curso_aca IS NOT NULL
                  AND mencion_calidad = 'S')
             f_mencion_calidad,
          DECODE (e.premio_extra, 'S', 'S', NULL)
             premio_extraordinario,
          DECODE (
             LENGTH (e.f_propuesta_premio_extra),
             4, TO_DATE ('3112' || e.f_propuesta_premio_extra, 'ddmmyyyy'),
             NULL)
             f_premio_extraordinario,
          DECODE (e.doctor_internacional, 'S', 'S', NULL)
             doctor_internacional
     FROM doc_expedientes_3c e,
          per_personas       alumno,
          per_personas       director,
          per_personas       codirector,
          per_personas       otrodirector,
          per_personas       x,
          doc_calificaciones c,
          doc_programas      p
    WHERE     f_apro_tesis IS NOT NULL
          AND director.id = e.per_id_director
          AND codirector.id(+) = e.per_id_codirector
          AND (   (    x.id != e.per_id
                   AND otrodirector.id IN
                          (e.per_id_director, e.per_id_codirector)
                  /* AND otrodirector.id != x.id*/)
               OR (x.id = e.per_id AND otrodirector.id = 40000))
          AND p.id = e.pro_id
          -- and e.per_id=62339
          AND x.id IN (SELECT e.per_id_director FROM DUAL
                       UNION
                       SELECT e.per_id_codirector FROM DUAL
                       UNION
                       SELECT e.per_id_codirector2 FROM DUAL
                       UNION
                       SELECT e.per_id FROM DUAL)
          AND e.per_id = alumno.id
          AND e.cal_id = c.id
          AND director.id != NVL (otrodirector.id, -1)
   union
   select 'DOC*' || pro_id || '*' || alumno.id || '*' || director.id id, 0 tesis_propia, '067' tipo_id, 'Tesis' tipo_txt, director.id persona, titu_tesis_def titulo, null codirector_nombre, null codirector_apellido1,
          null codirector_apellido2, director.nombre director_nombre, director.apellido1 director_apellido1, director.apellido2 director_apellido2, null otrodirector_nombre, null otrodirector_apellido1, null otrodirector_apellido2 --, (select wm_concat(nombre2) from per_vw_personas p where p.id in (e.per_id_codirector, e.per_id_codirector2)) codirector
                                                                                                                                                                                                                                      --, (select wm_concat(nombre2) from per_vw_personas p where p.id in (e.per_id_director, e.per_id_codirector, e.per_id_codirector2) and p.id!=x.id) otrosdirectores
          ,
          '724' pais -- ISO_3166
                    , 'ES52' region -- ISO_CVN_REGION
                                   , 'Castellón' ciudad, 'Universitat Jaume I' entidad -- Referencia a CVN_EXT_ENTIDADES
                                                                                      , '000' tipo_entidad -- Universidad (CVN_ENTITY_TYPE)
                                                                                                          , alumno.nombre alumno_nombre, alumno.apellido1 alumno_apellido1, alumno.apellido2 alumno_apellido2, e.f_apro_tesis fecha_lectura,
          c.nombre calificacion, e.doctor_europeo doctor_europeo, decode(e.doctor_europeo, 'S', e.f_apro_tesis, null) f_doctor_europeo, p.nombre programa, 'Universitat Jaume I' entidad_dea, f_acta_dea f_obtencion_dea,
          decode((select count(*)
                    from doc_datos_pro dp
                   where dp.pro_id = e.pro_id
                     and mencion_calidad = 'S'),
                 1, 'S',
                 null)
             mencion_calidad, (select min(to_date('3112' || curso_aca, 'ddmmyyyy'))
                                 from doc_datos_pro dp
                                where dp.pro_id = e.pro_id
                                  and curso_aca is not null
                                  and mencion_calidad = 'S')
                                 f_mencion_calidad, decode(e.premio_extra, 'S', 'S', null) premio_extraordinario,
          decode(length(e.f_propuesta_premio_extra), 4, to_date('3112' || e.f_propuesta_premio_extra, 'ddmmyyyy'), null) f_premio_extraordinario, decode(e.doctor_internacional, 'S', 'S', null) doctor_internacional
     from doc_expedientes_3c e, per_personas alumno, per_personas director, doc_calificaciones c, doc_programas p
    where f_apro_tesis is not null
      and director.id = e.per_id_director
      and e.per_id_codirector is null
      and p.id = e.pro_id
      --    and e.per_id=55773
      and e.per_id = alumno.id
      and e.cal_id = c.id
   union

select 'PC*'||p.id||'*'||a.orden id,
  0 tesis_propia,
  '067' tipo_id,
  'Tesis' tipo_txt,
  a.persona_id persona,
  p.titulo,
  null codirector_nombre,
  null codirector_apellido1,
  null codirector_apellido2,
  null director_nombre,
  null director_apellido1,
  null director_apellido2,
  (select listagg(trim(nombre||' '||apellidos), ',') within group (order by nombre)
                                                               from uji_investigacion.pci_producciones_autores a
                                                              where a.produccion_id = p.id and a.persona_id is null) otrodirector_nombre,
  null otrodirector_apellido1,
  null otrodirector_apellido2,
  p.pais_cod pais,
  null region,
  null ciudad,
  p.universidad entidad,
  null tipo_entidad,
  p.doctorando_nombre alumno_nombre,
  p.doctorando_apellido1 alumno_apellido1,
  p.doctorando_apellido2 alumno_apellido2,
  p.fecha_lectura f_apro_tesis,
  p.calificacion calificacion,
  decode(p.mencion_tesis_id, 293, 'S','N') doctor_europeo,
  decode(p.mencion_tesis_id, 293, p.fecha_lectura, null) f_doctor_europeo,
  null programa,
  null entidad_dea,
  to_date(null) f_obtencion_dea,
  null mencion_calidad,
  to_date(null) f_mencion_calidad,
  null premio_extraordinario,
  to_date(null) f_premio_extraordinario,
  decode(p.mencion_tesis_id, 294, 'S',null) doctor_internacional

from uji_investigacion.pci_producciones_07 p,
  uji_investigacion.pci_producciones_autores a,
  (select a.nombre, a.apellidos, null, a.produccion_id, a.orden from uji_investigacion.pci_producciones_autores a where a.persona_id is not null and a.orden = (select max(orden) from uji_investigacion.pci_producciones_autores a2 where a2.produccion_id = a.produccion_id and a2.persona_id is not null)) codirector

where a.produccion_id = p.id
  and a.persona_id is not null
  and a.orden = (select min(orden)
                        from uji_investigacion.pci_producciones_autores a2
                       where a2.produccion_id = a.produccion_id and a2.persona_id is not null)
  and p.id = codirector.produccion_id(+)

union
   select '0' id, -1 tesis_propia, '0' tipo_id, '0' tipo_txt, 0 persona, '0' titulo, '0' codirector_nombre, '0' codirector_apellido1, '0' codirector_apellido2, '0' director_nombre, '0' director_apellido1, '0' director_apellido2,
          '0' otrodirector_nombre, '0' otrodirector_apellido1, '0' otrodirector_apellido2, '0' pais, '0' region, '0' ciudad, '0' entidad, '0' tipo_entidad, '0' alumno_nombre, '0' alumno_apellido1, '0' alumno_apellido2, null fecha_lectura,
          '0' calificacion, '0' doctor_europeo, null f_doctor_europeo, '0' programa, null entidad_dea, null f_obtencion_dea, '0' mencion_calidad, null f_mencion_calidad, '0' premio_extraordinario, null f_premio_extraordinario,
          '0' doctor_internacional
    from dual;