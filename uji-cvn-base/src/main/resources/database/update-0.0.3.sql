--------------------------------------------------------
--  DDL for Table CVN_TIPO_ENTITY
--------------------------------------------------------

  CREATE TABLE "UJI_CVN"."CVN_TIPO_ENTITY"
   (	"ID" NUMBER,
	"ENTIDAD" VARCHAR2(500 BYTE),
	"CODIGO" VARCHAR2(10 BYTE),
	"DELEGADO" VARCHAR2(10 BYTE),
	"DESCRIPCION_CA" VARCHAR2(500 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT);
REM INSERTING into UJI_CVN.CVN_TIPO_ENTITY
SET DEFINE OFF;
Insert into UJI_CVN.CVN_TIPO_ENTITY (ID,ENTIDAD,CODIGO,DELEGADO,DESCRIPCION_CA) values ('1','Universidad','000',null,'Universitat');
Insert into UJI_CVN.CVN_TIPO_ENTITY (ID,ENTIDAD,CODIGO,DELEGADO,DESCRIPCION_CA) values ('2','Instituto Universitario de Investigación','010',null,'Institut Universitari d''Investigació');
Insert into UJI_CVN.CVN_TIPO_ENTITY (ID,ENTIDAD,CODIGO,DELEGADO,DESCRIPCION_CA) values ('3','Centros y Estructuras Universitarios y Asimilados','020',null,'Centres i Estructures Universitaris i Asimilats');
Insert into UJI_CVN.CVN_TIPO_ENTITY (ID,ENTIDAD,CODIGO,DELEGADO,DESCRIPCION_CA) values ('4','Departamento Universitario','030',null,'Departament Universitari');
Insert into UJI_CVN.CVN_TIPO_ENTITY (ID,ENTIDAD,CODIGO,DELEGADO,DESCRIPCION_CA) values ('5','Fundación','040',null,'Fundació');
Insert into UJI_CVN.CVN_TIPO_ENTITY (ID,ENTIDAD,CODIGO,DELEGADO,DESCRIPCION_CA) values ('6','Agencia Estatal','050',null,'Agencia Estatal');
Insert into UJI_CVN.CVN_TIPO_ENTITY (ID,ENTIDAD,CODIGO,DELEGADO,DESCRIPCION_CA) values ('7','Organismo Público de Investigación','060',null,'Organisme Públic d''Investigació');
Insert into UJI_CVN.CVN_TIPO_ENTITY (ID,ENTIDAD,CODIGO,DELEGADO,DESCRIPCION_CA) values ('8','Organismo, Otros','070','OTHERS','Organisme, Altres');
Insert into UJI_CVN.CVN_TIPO_ENTITY (ID,ENTIDAD,CODIGO,DELEGADO,DESCRIPCION_CA) values ('9','Entidad Empresarial','080',null,'Entitat Empresarial');
Insert into UJI_CVN.CVN_TIPO_ENTITY (ID,ENTIDAD,CODIGO,DELEGADO,DESCRIPCION_CA) values ('10','Entidad Gestora del Sistema Nacional de Salud','090',null,'Entitat Gestora del Sistema Nacional de Salut');
Insert into UJI_CVN.CVN_TIPO_ENTITY (ID,ENTIDAD,CODIGO,DELEGADO,DESCRIPCION_CA) values ('11','Instituciones Sanitarias','100',null,'Institucions Sanitaries');
Insert into UJI_CVN.CVN_TIPO_ENTITY (ID,ENTIDAD,CODIGO,DELEGADO,DESCRIPCION_CA) values ('12','Centro Tecnológico','110',null,'Centre Tecnològic');
Insert into UJI_CVN.CVN_TIPO_ENTITY (ID,ENTIDAD,CODIGO,DELEGADO,DESCRIPCION_CA) values ('13','Centro de I+D','120',null,'Centre d''I+D');
Insert into UJI_CVN.CVN_TIPO_ENTITY (ID,ENTIDAD,CODIGO,DELEGADO,DESCRIPCION_CA) values ('14','Asociaciones y Agrupaciones','130',null,'Associacions i Agrupacions');
Insert into UJI_CVN.CVN_TIPO_ENTITY (ID,ENTIDAD,CODIGO,DELEGADO,DESCRIPCION_CA) values ('15','CIBER','140',null,'CIBER');
Insert into UJI_CVN.CVN_TIPO_ENTITY (ID,ENTIDAD,CODIGO,DELEGADO,DESCRIPCION_CA) values ('16','Centros de Innovación y Tecnología','150',null,'Centres d''Innovació i Tecnologia');
Insert into UJI_CVN.CVN_TIPO_ENTITY (ID,ENTIDAD,CODIGO,DELEGADO,DESCRIPCION_CA) values ('17','Organismo, Otros','OTHERS',null,'Organisme, Altres');
--------------------------------------------------------
--  DDL for Index CVN_TIPO_ENTITY_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "UJI_CVN"."CVN_TIPO_ENTITY_PK" ON "UJI_CVN"."CVN_TIPO_ENTITY" ("ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT);
--------------------------------------------------------
--  DDL for Index CVN_TIPO_ENTITY_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "UJI_CVN"."CVN_TIPO_ENTITY_UK1" ON "UJI_CVN"."CVN_TIPO_ENTITY" ("CODIGO")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT);
--------------------------------------------------------
--  Constraints for Table CVN_TIPO_ENTITY
--------------------------------------------------------

  ALTER TABLE "UJI_CVN"."CVN_TIPO_ENTITY" ADD CONSTRAINT "CVN_TIPO_ENTITY_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT);

  ALTER TABLE "UJI_CVN"."CVN_TIPO_ENTITY" ADD CONSTRAINT "CVN_TIPO_ENTITY_UK1" UNIQUE ("CODIGO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT);

  ALTER TABLE "UJI_CVN"."CVN_TIPO_ENTITY" MODIFY ("ID" NOT NULL ENABLE);

  ALTER TABLE "UJI_CVN"."CVN_TIPO_ENTITY" MODIFY ("ENTIDAD" NOT NULL ENABLE);

  ALTER TABLE "UJI_CVN"."CVN_TIPO_ENTITY" MODIFY ("CODIGO" NOT NULL ENABLE);

  ALTER TABLE "UJI_CVN"."CVN_TIPO_ENTITY" MODIFY ("DESCRIPCION_CA" NOT NULL ENABLE);


-- Vista de docencia impartida con datos en varios idiomas


  CREATE OR REPLACE FORCE EDITIONABLE VIEW "UJI_CVN"."CVN_VW_PER_DOCENCIA_IMPART_M" ("PER_ID", "TIPO", "TITULACION", "TITULACION_TEXTO", "TITULACION_TEXTO_ES", "TITULACION_TEXTO_UK", "PAIS", "REGION", "CIUDAD", "ENTIDAD", "TIPO_ENTIDAD", "DEPARTAMENTO", "DEPARTAMENTO_ES", "NOMBRE_ASIGNATURA", "NOMBRE_ASIGNATURA_ES", "NOMBRE_ASIGNATURA_UK", "ID_ASIGNATURA", "TIPO_HORAS_CREDITOS", "CREDITOS", "IDIOMA", "N_VECES", "ULTIMA_VEZ") AS
  SELECT sp.cdo_per_id                   per_id,
            '016'                           tipo           -- Docencia oficial
                                                ,
            t.codmec                        titulacion,
            t.nombre                        titulacion_texto,
            t.nomval                        titulacion_texto_cas,
            t.nomang                        titulacion_texto_ang,
            '724'                           pais                     -- España
                                                ,
            'ES52'                          region     -- Comunidad Valenciana
                                                  ,
            'Castellón'                    ciudad,
            'Universitat Jaume I'           entidad -- Referencia a CVN_EXT_ENTIDADES
                                                   ,
            '000'                           tipo_entidad -- Universidad (CVN_ENTITY_TYPE)
                                                        ,
            e.dept_nombre                   departamento,
            e.dept_nombre_cas               departamento_cas,
            a.nombre                        nombre_asignatura,
            a.nomval                        nombre_asignatura_cas,
            a.nomang                        nombre_asignatura_ang,
            a.id                            id_asignatura,
            '000'                           tipo_horas_creditos   --CVN_TIME_A
                                                               ,
            SUM (sp.creditos)               creditos,
            i.iso_639                       idioma,
            COUNT (DISTINCT sp.cdo_curso_aca) n_veces,
            NVL (
               MAX (
                  (SELECT MAX (fecha)
                     FROM pod_horarios_fechas hf
                    WHERE     hf.hor_sgr_grp_asi_id = sp.sgr_grp_asi_id
                          AND hf.hor_sgr_grp_id = sp.sgr_grp_id
                          AND hf.hor_sgr_grp_curso_aca = sp.sgr_grp_curso_aca
                          AND hf.hor_sgr_tipo = sp.sgr_tipo)),
               TO_DATE ('3107' || (MAX (sp.cdo_curso_aca) + 1), 'ddmmyyyy'))
               ultima_vez
       FROM pod_asignaturas            a,
            pod_subgrupo_pdi           sp,
            pod_titulaciones           t,
            pod_asignaturas_titulaciones atit,
            pod_v_estructuras          e,
            pod_idiomas                i
      WHERE     a.id = sp.sgr_grp_asi_id
            AND t.id = atit.tit_id
            AND atit.asi_id = a.id
            AND e.area_id = sp.cdo_uest_id
            AND sp.idioma = i.id
            AND t.id BETWEEN 1 AND 9999              -- Solo titulos oficiales
   --and length(t.codmec) = 7
   GROUP BY sp.cdo_per_id,
            t.codmec,
            e.dept_nombre,
            e.dept_nombre_cas,
            a.id,
            a.nombre,
            a.nomval,
            a.nomang,
            i.iso_639,
            t.nombre,
            t.nomval,
            t.nomang
   UNION
   SELECT sp.cdo_per_id                   per_id,
            '015'                           tipo           -- Docencia oficial
                                                ,
            t.codmec                        titulacion,
            t.nombre                        titulacion_texto,
            t.nomval                        titulacion_texto_cas,
            t.nomang                        titulacion_texto_ang,
            '724'                           pais                     -- España
                                                ,
            'ES52'                          region     -- Comunidad Valenciana
                                                  ,
            'Castellón'                    ciudad,
            'Universitat Jaume I'           entidad -- Referencia a CVN_EXT_ENTIDADES
                                                   ,
            '000'                           tipo_entidad -- Universidad (CVN_ENTITY_TYPE)
                                                        ,
            e.dept_nombre                   departamento,
            e.dept_nombre_cas               departamento_cas,
            a.nombre                        nombre_asignatura,
            a.nomval                        nombre_asignatura_cas,
            a.nomang                        nombre_asignatura_ang,
            a.id                            id_asignatura,
            '000'                           tipo_horas_creditos   --CVN_TIME_A
                                                               ,
            SUM (sp.creditos)               creditos,
            i.iso_639                       idioma,
            COUNT (DISTINCT sp.cdo_curso_aca) n_veces,
            NVL (
               MAX (
                  (SELECT MAX (fecha)
                     FROM pod_horarios_fechas hf
                    WHERE     hf.hor_sgr_grp_asi_id = sp.sgr_grp_asi_id
                          AND hf.hor_sgr_grp_id = sp.sgr_grp_id
                          AND hf.hor_sgr_grp_curso_aca = sp.sgr_grp_curso_aca
                          AND hf.hor_sgr_tipo = sp.sgr_tipo)),
               TO_DATE ('3107' || (MAX (sp.cdo_curso_aca) + 1), 'ddmmyyyy'))
               ultima_vez
       FROM pod_asignaturas            a,
            pod_subgrupo_pdi           sp,
            pod_titulaciones           t,
            pod_asignaturas_titulaciones atit,
            pod_v_estructuras          e,
            pod_idiomas                i
      WHERE     a.id = sp.sgr_grp_asi_id
            AND t.id = atit.tit_id
            AND atit.asi_id = a.id
            AND e.area_id = sp.cdo_uest_id
            AND sp.idioma = i.id
            AND t.id >= 10000              -- Solo titulos NO oficiales
   --and length(t.codmec) = 7
   GROUP BY sp.cdo_per_id,
            t.codmec,
            e.dept_nombre,
            e.dept_nombre_cas,
            a.id,
            a.nombre,
            a.nomval,
            a.nomang,
            i.iso_639,
            t.nombre,
            t.nomval,
            t.nomang
   UNION
     SELECT gp.per_id,
            DECODE (m.oficial,  'S', '016',  'N', '015') tipo -- oficial, no oficial
                                                             ,
            m.cod_mec,
            m.nombre,
            m.nombre_es,
            m.nombre_uk,
            '724'                                      pais          -- España
                                                           ,
            'ES52'                                     region -- Comunidad Valenciana
                                                             ,
            'Castellón'                               ciudad,
            'Universitat Jaume I'                      entidad -- Referencia a CVN_EXT_ENTIDADES
                                                              ,
            '000'                                      tipo_entidad -- Universidad (CVN_ENTITY_TYPE)
                                                                   ,
            (SELECT MAX (e.dept_nombre)
               FROM pod_v_estructuras e, pod_carga_docente cd
              WHERE     e.area_id = cd.uest_id
                    AND cd.per_id = gp.per_id
                    AND cd.curso_aca = ca.curso_aca)
               departamento,
               (SELECT MAX (e.dept_nombre_cas)
               FROM pod_v_estructuras e, pod_carga_docente cd
              WHERE     e.area_id = cd.uest_id
                    AND cd.per_id = gp.per_id
                    AND cd.curso_aca = ca.curso_aca)
               departamento_cas,
            a.nombre                                   nombre_asignatura,
            a.nombre_es                        nombre_asignatura_cas,
            a.nombre_uk                        nombre_asignatura_ang,
            a.id                                       id_asignatura,
            '000'                                      tipo_horas_creditos --CVN_TIME_A
                                                                          ,
            SUM (gp.crd_computables)                   creditos,
            i.iso_639                                  idioma,
            COUNT (DISTINCT gp.pgr_pof_curso_aca)      n_veces,
            TO_DATE ('3107' || (MAX (gp.pgr_pof_curso_aca) + 1), 'ddmmyyyy')
               ultima_vez
       FROM pop_masters   m,
            pop_grupos_pdi gp,
            pop_asignaturas a,
            pod_idiomas   i,
            (  SELECT MAX (gp2.pgr_pof_curso_aca) curso_aca,
                      pgr_pof_pmas_id,
                      per_id
                 FROM pop_grupos_pdi gp2
             GROUP BY pgr_pof_pmas_id, per_id) ca
      WHERE     m.id = gp.pgr_pof_pmas_id
            AND a.id = gp.pgr_pof_pasi_id
            AND gp.pgr_pof_pmas_id = ca.pgr_pof_pmas_id
            AND gp.per_id = ca.per_id
            AND m.oficial = 'S'
            AND gp.idioma = i.pop_id(+)
   --    and a.id='MIA041'
   --   and gp.per_id=65262
   -- and gp.idioma is null
   GROUP BY gp.per_id,
            m.cod_mec,
            m.nombre,
            m.nombre_es,
            m.nombre_uk,
            a.nombre,
            a.nombre_es,
            a.nombre_uk,
            i.iso_639,
            m.oficial,
            a.id,
            ca.curso_aca;
