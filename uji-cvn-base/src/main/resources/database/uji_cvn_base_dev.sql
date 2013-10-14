
create or replace
FUNCTION concatenate_list (p_cursor IN  SYS_REFCURSOR)
  RETURN  VARCHAR2
IS
  l_return  VARCHAR2(32767); 
  l_temp    VARCHAR2(32767);
BEGIN
  LOOP
    FETCH p_cursor
    INTO  l_temp;
    EXIT WHEN p_cursor%NOTFOUND;
    l_return := RTRIM(l_return,'.:,;-');
    l_return := l_return || '. ' || l_temp;
  END LOOP;
  l_return := RTRIM(l_return,'.:,;-');
  RETURN LTRIM(l_return, '.:,;-');
END;



CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_EXT_ENTIDADES" ("ID", "NOMBRE", "ACRONIMO", "TIPO", "SUBTIPO", "TIPO_DESCR", "PAIS", "REGION", "REGION_STR", "CIUDAD")
                                     AS
  SELECT p.id                        AS id,
    NVL(p.nombre_comercial,p.nombre) AS nombre,
    o.acronimo                       AS acronimo,
    o.t_org_tipo                     AS tipo,
    o.t_org_subtipo                  AS subtipo,
    torg.nombre                      AS tipo_descr,
    UPPER(c.codigo_mec)              AS pais,
    NULL                             AS region,
    NULL                             AS region_str,
    d.pueblo                         AS ciudad
  FROM per_personas p
  JOIN grs_oct.oct_organismos o
  ON o.per_id = p.id
  JOIN per_paises c
  ON p.pai_id=c.id --para sacar el iso del pais
  JOIN per_vw_domicilios d
  ON p.id=d.per_id --para sacar la dir de la empresa
  JOIN grs_oct.oct_tipos_organismo torg
  ON o.t_org_tipo     =torg.tipo
  AND o.t_org_subtipo =torg.subtipo
  WHERE p.tipo_persona='J'
WITH READ ONLY;
  
  
  
CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_EXT_PERSONAS" ("ID", "NOMBRE", "APELLIDO1", "APELLIDO2", "IDENTIFICACION", "TIPO_IDENTIFICACION", "NACIONALIDAD", "FECHA_NACIMIENTO", "PAIS_NACIMIENTO", "REGION_NACIMIENTO", "CIUDAD_NACIMIENTO", "GENERO", "TELEFONO_FIJO", "TELEFONO_MOVIL", "FAX", "EMAIL", "WEBPAGE", "INDICE_H", "INDICE_H_FECHA", "FOTO", "MIME_TYPE")
                                                                    AS
  SELECT p.id                                                       AS id,
    p.nombre                                                        AS nombre,
    p.apellido1                                                     AS apellido1,
    p.apellido2                                                     AS apellido2,
    UPPER(p.identificacion)                                         AS identificacion,
    DECODE(p.tip_id,1,1, 2,1, 3,1, 4,4, 5,5, 6,NULL, 7,4, 8,5, 9,5) AS tipo_identificacion, --consultar esta asociación
    c.codigo_mec                                                    AS nacionalidad,        -- .iso=iso3166 de 2 letras .iso3=iso de 3 letras  .codigos_mec=iso numérico
    n.fecha                                                         AS fecha_nacimiento,
    nc.codigo_mec                                                   AS pais_nacimiento,
    ''                                                              AS region_nacimiento, -- poner la región seria un problema  y no aporta nada
    np.nombre                                                       AS ciudad_nacimiento,
    n.sexo                                                          AS genero, -- 1 V   2 H
    d.telefono                                                      AS telefono_fijo,
    d.movil                                                         AS telefono_movil,
    ''                                                              AS fax,
    d.email                                                         AS email,
    ''                                                              AS webpage,
    ''                                                              AS indice_h,
    ''                                                              AS indice_h_fecha,
    f.binario                                                       AS foto,
    f.mime_type
  FROM per_personas p
  LEFT JOIN per_fotos f
  ON p.id = f.per_id
  LEFT JOIN per_paises c
  ON p.pai_id=c.id --para sacar el iso de la nacionalidad
  LEFT JOIN per_nacimientos n
  ON p.id=n.per_id
  LEFT JOIN per_paises nc
  ON n.pai_id=nc.id --para sacar el iso del país de nacimiento
  LEFT JOIN per_pueblos np
  ON n.pue_id     =np.id
  AND n.pue_pro_id=np.pro_id -- la PK es id+pro_id
  LEFT JOIN per_vw_domicilios d
  ON p.id =d.per_id -- en esta vista pone la nacionalidad y el pais de nacimiento. pedir si pudieran ser los códigos ISO y me ahorro joins.
  --  WHERE p.tipo_persona='F'  -- LA COMENTO PORQUE HAY INVESTIGADORES QUE TIENEN TIPO J (JURÍDICO)
WITH READ ONLY;
  
  

CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_EXT_DOMICILIOS" ("ID", "PER_ID", "CIUDAD_CONTACTO", "DIRECCION_CONTACTO", "DIRECCION_CONTACTO_AUX", "CODIGO_POSTAL", "REGION_CONTACTO", "PAIS_CONTACTO", "PROVINCIA_CONTACTO", "ORDEN_PREFERENCIA")
              AS
  SELECT p.id AS id,
    p.id      AS per_id,
    d.pueblo  AS ciudad_contacto,
    d.via
    ||' '
    ||d.domicilio
    ||' '
    ||d.numero  AS direccion_contacto,
    NULL        AS direccion_contacto_aux,
    d.codpostal AS codigo_postal,
    NULL        AS region_contacto,
    '999'       AS pais_contacto, --no puede ser null 999:desconocido, 724:ES
    pr.id       AS provincia_contacto,
    '10'        AS orden_preferencia
  FROM per_personas p
  JOIN per_vw_domicilios d
  ON p.id =d.per_id
  JOIN per_provincias pr
  ON d.provincia      = pr.nombre
  WHERE p.tipo_persona='F'
WITH READ ONLY;
  
  
  
CREATE TABLE "UJI_CVN"."CVN_PDFS_GENERADOS"
  (
    "ID"     NUMBER NOT NULL ENABLE,
    "PER_ID" NUMBER NOT NULL ENABLE,
    "FECHA_ULTIMA_ACTUALIZACION" DATE,
    "CVN" BLOB,
    "ESTADO"      VARCHAR2(100 BYTE),
    "MENSAJE"     VARCHAR2(500 BYTE),
    "SOLICITANTE" NUMBER NOT NULL ENABLE,
    CONSTRAINT "cvn_pdfs_generados_pk" PRIMARY KEY ("ID") USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT) TABLESPACE "USERS" ENABLE
  )
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING STORAGE
  (
    INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT
  )
  TABLESPACE "USERS" LOB
  (
    "CVN"
  )
  STORE AS BASICFILE
  (
    TABLESPACE "USERS" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION NOCACHE LOGGING STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  ) ;
  
  
  
CREATE TABLE "UJI_CVN"."CVN_PLANTILLAS"
  (
    "ID"     NUMBER NOT NULL ENABLE,
    "PER_ID" NUMBER NOT NULL ENABLE,
    "FECHA_ULTIMA_ACTUALIZACION" DATE NOT NULL ENABLE,
    "NOMBRE" VARCHAR2(200 BYTE) NOT NULL ENABLE,
    "PLANTILLA" BLOB,
    "IDIOMA" VARCHAR2(20 BYTE) NOT NULL ENABLE,
    CONSTRAINT "cvn_plantillas_pk" PRIMARY KEY ("ID") USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT) TABLESPACE "USERS" ENABLE
  )
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING STORAGE
  (
    INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT
  )
  TABLESPACE "USERS" LOB
  (
    "PLANTILLA"
  )
  STORE AS BASICFILE
  (
    TABLESPACE "USERS" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION NOCACHE LOGGING STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  ) ;
  
  
  
CREATE TABLE "UJI_CVN"."CVN_LOGS"
  (
    "ID"          NUMBER NOT NULL ENABLE,
    "PER_CVN_ID"  NUMBER NOT NULL ENABLE,
    "SOLICITANTE" NUMBER NOT NULL ENABLE,
    "FECHA" DATE NOT NULL ENABLE,
    "MENSAJE" VARCHAR2(500 BYTE) NOT NULL ENABLE,
    "ERROR" CLOB,
    CONSTRAINT "cvn_logs_pk" PRIMARY KEY ("ID") USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT) TABLESPACE "USERS" ENABLE
  )
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING STORAGE
  (
    INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT
  )
  TABLESPACE "USERS" LOB
  (
    "ERROR"
  )
  STORE AS BASICFILE
  (
    TABLESPACE "USERS" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION NOCACHE LOGGING STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  ) ;
  
  
  
CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_ENT_FINANCIA_PROY" ("ENTIDAD", "PROYECTO")
                  AS
  SELECT o.per_id AS entidad,
    (SELECT MIN(s2.n)
    FROM grs_oct.oct_solicitudes_contratos s2
    WHERE s2.codigo_externo = s.codigo_externo
    ) AS proyecto
  FROM grs_oct.oct_organismos o
  JOIN grs_oct.oct_promueven oc
  ON o.id=oc.org_id
  JOIN grs_oct.oct_convocatorias c
  ON oc.convoc_id        =c.id
  AND oc.convoc_ejercicio=c.ejercicio
  JOIN grs_oct.oct_solicitudes_contratos s
  ON (c.id        = s.anexo_convoc_id
  AND c.ejercicio =s.anexo_convoc_ejercicio)
  JOIN grs_oct.oct_anexos a
  ON a.id                 = s.anexo_id
  AND a.convoc_id         = s.anexo_convoc_id
  AND a.convoc_ejercicio  = s.anexo_convoc_ejercicio
  WHERE c.titulo         IS NOT NULL
  AND s.codigo_externo   IS NOT NULL
  AND (a.t_anexo_tipo     = 2
  AND (a.t_anexo_subtipo  ='P'
  OR (a.t_anexo_subtipo   ='A'
  AND a.t_anexo_microtipo ='P')))
  GROUP BY s.codigo_externo,
    o.per_id
  UNION ALL
  SELECT o.per_id AS entidad,
    (SELECT MIN(s2.n)
    FROM grs_oct.oct_solicitudes_contratos s2
    WHERE s2.codigo_externo IS NULL
    AND s2.titulo            = s.titulo
    ) AS proyecto
  FROM grs_oct.oct_organismos o
  JOIN grs_oct.oct_promueven oc
  ON o.id=oc.org_id
  JOIN grs_oct.oct_convocatorias c
  ON oc.convoc_id        =c.id
  AND oc.convoc_ejercicio=c.ejercicio
  JOIN grs_oct.oct_solicitudes_contratos s
  ON (c.id        = s.anexo_convoc_id
  AND c.ejercicio =s.anexo_convoc_ejercicio)
  JOIN grs_oct.oct_anexos a
  ON a.id                = s.anexo_id
  AND a.convoc_id        = s.anexo_convoc_id
  AND a.convoc_ejercicio = s.anexo_convoc_ejercicio
  WHERE c.titulo        IS NOT NULL
    --  AND s.codigo_externo   IS NULL
  AND ((a.t_anexo_tipo    = 1
  AND a.t_anexo_subtipo   ='C'
  AND a.t_anexo_microtipo!= 'T'))
  GROUP BY s.titulo,
    o.per_id
WITH READ ONLY;



CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_ENT_PARTICIPA_PROY" ("ENTIDAD", "PROYECTO")
                  AS
  SELECT o.per_id AS entidad,
    (SELECT MIN(s2.n)
    FROM grs_oct.oct_solicitudes_contratos s2
    WHERE s2.codigo_externo = s.codigo_externo
    ) AS proyecto
  FROM grs_oct.oct_organismos o
  JOIN grs_oct.oct_participan os
  ON o.id = os.org_id
  JOIN grs_oct.oct_solicitudes_contratos s
  ON os.contr_n = s.n
  LEFT JOIN grs_oct.oct_datos_contrataciones c
  ON s.n = c.contr_n
  JOIN grs_oct.oct_anexos a
  ON a.id                 = s.anexo_id
  AND a.convoc_id         = s.anexo_convoc_id
  AND a.convoc_ejercicio  = s.anexo_convoc_ejercicio
  WHERE s.codigo_externo IS NOT NULL
  AND c.fecha_inicio     IS NOT NULL
  AND (a.t_anexo_tipo     = 2
  AND (a.t_anexo_subtipo  ='P'
  OR (a.t_anexo_subtipo   ='A'
  AND a.t_anexo_microtipo ='P')))
  GROUP BY s.codigo_externo,
    o.per_id
  UNION ALL
  SELECT o.per_id AS entidad,
    (SELECT MIN(s2.n)
    FROM grs_oct.oct_solicitudes_contratos s2
    WHERE s2.codigo_externo IS NULL
    AND s2.titulo            = s.titulo
    ) AS proyecto
  FROM grs_oct.oct_organismos o
  JOIN grs_oct.oct_participan os
  ON o.id = os.org_id
  JOIN grs_oct.oct_solicitudes_contratos s
  ON os.contr_n = s.n
  LEFT JOIN grs_oct.oct_datos_contrataciones c
  ON s.n = c.contr_n
  JOIN grs_oct.oct_anexos a
  ON a.id                = s.anexo_id
  AND a.convoc_id        = s.anexo_convoc_id
  AND a.convoc_ejercicio = s.anexo_convoc_ejercicio
  WHERE s.titulo        IS NOT NULL
  AND c.fecha_inicio    IS NOT NULL
    --  AND s.codigo_externo   IS NULL
  AND ((a.t_anexo_tipo    = 1
  AND a.t_anexo_subtipo   ='C'
  AND a.t_anexo_microtipo!= 'T'))
  GROUP BY s.titulo,
    o.per_id
WITH READ ONLY;



CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_GRUPOS_INVESTIGACION" ("ID", "NOMBRE", "OBJETIVO", "CODIGO_NORMALIZADO", "PAIS", "REGION", "CIUDAD", "RESPONSABLE", "NOMBRE_ENTIDAD", "NCOMPONENTES", "FECHA_ALTA", "FECHA_ACTUALIZACION", "ACTIVO", "NTESIS", "NPOSTDOC")
AS
  SELECT g.id,
    g.nombre,
    concatenate_list(CURSOR
    (SELECT (linea)
    FROM grs_oct.oct_lineas_trabajos lt
    WHERE lt.grupo_id=g.id
    AND lt.tabulacion='1'
    ))                      AS objetivo,
    g.id                    AS codigo_normalizado,
    'España'                AS pais,
    'Comunidad Valenciana'  AS region,
    'Castellón de la Plana' AS ciudad,
    (SELECT i.per_id
    FROM grs_oct.oct_investigadores i
    JOIN grs_oct.oct_investigadores_agrupados ig
    ON ig.inv_id     = i.id
    WHERE ig.grupo_id=g.id
    AND ig.principal ='S'
    AND (ig.f_baja  IS NULL
    OR ig.f_baja     >sysdate)
    AND rownum       < 2
    )                     AS responsable,
    'Universitat Jaume I' AS nombre_entidad, -- #cambiar por referencia a la vista de entidades
    (SELECT COUNT('*')
    FROM grs_oct.oct_investigadores_agrupados sig
    WHERE sig.grupo_id=g.id
    AND (sig.f_baja  IS NULL
    OR sig.f_baja     >sysdate)
    ) AS ncomponentes,
    g.fecha_alta,
    g.fecha_actualizacion,
    g.activo,
    NULL AS ntesis,
    NULL AS npostdoc
  FROM grs_oct.oct_grupos g
  WHERE regexp_like(g.id, '^[0-9]+$') --filtra los nombres de institutos, que tienen id en letras, de los nombres de grupos, que son números
WITH READ ONLY;



CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_IMPACTO_PROD_PUBL" ("PRODUCCION", "FUENTE_IMPACTO", "INDICE_IMPACTO", "CUARTIL", "IS_25P_TOP", "CATEGORIA1", "CATEGORIA2", "POSICION", "TOTAL")
                AS
  SELECT p.id   AS produccion,
    'WOS (JCR)' AS fuente_impacto,
    (
    CASE
      WHEN r.indice_impacto > 0
      AND r.indice_impacto  < 100
      THEN r.indice_impacto
      ELSE NULL
    END) AS indice_impacto,
    --    r.cuartil,  -- lo corrijo para que salga como toca
    DECODE(r.cuartil,4,1,3,2,2,3,1,4,NULL) AS cuartil,
    (
    CASE r.cuartil
      WHEN 4 -- el 4 es el cuartil 1
      THEN 1
      ELSE 0
    END)       AS is_25p_top, -- en la vista de la uji, 4 es el Q1
    r.prt_tipo AS categoria1,
    r.prt_id   AS categoria2,
    NULL       AS posicion,
    NULL       AS total
  FROM GRS_OCT.oct_produc_cientificas p
  JOIN UJI_CVN.oct_vw_revistas_cuartiles r
  ON p.pre_issn=r.issn
  AND p.ano    = r.ano
WITH read only;



CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PER_EXT_PART_PUBL" ("PERSONA", "PRODUCCION", "CALIDAD_PARTICIPACION", "CALIDAD_PARTICIPACION_STR", "CARACTER", "ORDEN", "POS_SOBRE_TOTAL", "RESULTADOS_DESTACADOS", "IS_RELEVANTE_PUB")
                   AS
  SELECT ax.nombre AS persona,
    ax.pci_id      AS produccion,
    '070'          AS calidad_participacion,
    ''             AS calidad_participacion_str,
    p.pri_pca_id   AS caracter, -- 20: docente !20: científico
    ax.orden,
    NULL AS pos_sobre_total,
    ''   AS resultados_destacados,
    NULL AS is_relevante_pub
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_otras_personas_pci ax
  ON ax.pci_id        = p.id
  WHERE p.justificado ='S'
  AND p.borrado       ='N'
  AND p.iin_id        =32 --artículos
  UNION ALL
  SELECT ax.nombre AS persona,
    ax.pci_id      AS produccion,
    '090'          AS calidad_participacion,
    ''             AS calidad_participacion_str,
    p.pri_pca_id   AS caracter, -- 20: docente !20: científico
    ax.orden,
    NULL AS pos_sobre_total,
    ''   AS resultados_destacados,
    NULL AS is_relevante_pub
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_otras_personas_pci ax
  ON ax.pci_id          = p.id
  WHERE p.justificado   ='S'
  AND p.borrado         ='N'
  AND p.iin_id          =34   -- capítulos de libro
  AND p.ppi_ppr_id     IN (1) --es autor
  AND p.pri_pca_id NOT IN (7) --no es obra de arte
  UNION ALL
  SELECT ax.nombre AS persona,
    ax.pci_id      AS produccion,
    '120'          AS calidad_participacion,
    ''             AS calidad_participacion_str,
    p.pri_pca_id   AS caracter, -- 20: docente !20: científico
    ax.orden,
    NULL AS pos_sobre_total,
    ''   AS resultados_destacados,
    NULL AS is_relevante_pub
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_otras_personas_pci ax
  ON ax.pci_id          = p.id
  WHERE p.justificado   ='S'
  AND p.borrado         ='N'
  AND p.iin_id          =33   -- libro
  AND p.ppi_ppr_id     IN (1) --es autor
  AND p.pri_pca_id NOT IN (7) --no es obra de arte
  UNION ALL
  SELECT ax.nombre AS persona,
    ax.pci_id      AS produccion,
    '140'          AS calidad_participacion,
    ''             AS calidad_participacion_str,
    p.pri_pca_id   AS caracter, -- 20: docente !20: científico
    ax.orden,
    NULL AS pos_sobre_total,
    ''   AS resultados_destacados,
    NULL AS is_relevante_pub
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_otras_personas_pci ax
  ON ax.pci_id        = p.id
  WHERE p.justificado ='S'
  AND p.borrado       ='N'
  AND p.iin_id       IN (33,34) -- libro o capítulo
  AND p.ppi_ppr_id   IN (1)     --es autor
  AND p.pri_pca_id   IN (7)     --SÍ es obra de arte
  UNION ALL
  SELECT ax.nombre AS persona,
    ax.pci_id      AS produccion,
    '390'          AS calidad_participacion,
    ''             AS calidad_participacion_str,
    p.pri_pca_id   AS caracter, -- 20: docente !20: científico
    ax.orden,
    NULL AS pos_sobre_total,
    ''   AS resultados_destacados,
    NULL AS is_relevante_pub
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_otras_personas_pci ax
  ON ax.pci_id        = p.id
  WHERE p.justificado ='S'
  AND p.borrado       ='N'
  AND p.iin_id        =33   -- libro
  AND p.ppi_ppr_id   IN (2) --es editor
WITH READ ONLY;



CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PER_PART_PROD_CONG" ("PERSONA", "PRODUCCION", "TIPO_PARTICIPACION", "TIPO_PARTICIPACION_STR", "CARACTER", "ORDEN")
                  AS
  SELECT i.per_id AS persona,
    a.pci_id      AS produccion,
    '730'         AS tipo_participacion,
    ''            AS tipo_participacion_str,
    p.pri_pca_id  AS caracter, -- 20: docente !20: científico
    a.orden       AS orden
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_personas_pci a
  ON a.pci_id = p.id
  JOIN GRS_OCT.oct_investigadores i
  ON a.inv_id                                                    = i.id
  WHERE p.justificado                                            ='S'
  AND p.borrado                                                  ='N'
  AND p.iin_id                                                  IN (35) --ponente
  AND p.logico2                                                  ='S'   --invitado
  AND regexp_instr(p.titulo,'\(\s*p[oóò]ster\s*\)\s*$',1,1,0,'i')=0     --Si no lleva al final la cadena ' (Poster)' (si la lleva, es un poster)
  UNION ALL
  SELECT i.per_id AS persona,
    a.pci_id      AS produccion,
    '960'         AS tipo_participacion,
    ''            AS tipo_participacion_str,
    p.pri_pca_id  AS caracter, -- 20: docente !20: científico
    a.orden       AS orden
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_personas_pci a
  ON a.pci_id = p.id
  JOIN GRS_OCT.oct_investigadores i
  ON a.inv_id                                                    = i.id
  WHERE p.justificado                                            ='S'
  AND p.borrado                                                  ='N'
  AND p.iin_id                                                  IN (35) --ponente
  AND p.logico2                                                  ='N'   --libre
  AND regexp_instr(p.titulo,'\(\s*p[oóò]ster\s*\)\s*$',1,1,0,'i')=0     --Si no lleva al final la cadena ' (Poster)' (si la lleva, es un poster)
  UNION ALL
  SELECT i.per_id AS persona,
    a.pci_id      AS produccion,
    '970'         AS tipo_participacion,
    ''            AS tipo_participacion_str,
    p.pri_pca_id  AS caracter, -- 20: docente !20: científico
    a.orden       AS orden
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_personas_pci a
  ON a.pci_id = p.id
  JOIN GRS_OCT.oct_investigadores i
  ON a.inv_id                                                     = i.id
  WHERE p.justificado                                             ='S'
  AND p.borrado                                                   ='N'
  AND p.iin_id                                                   IN (35) --ponente
  AND regexp_instr(p.titulo,'\(\s*p[oóò]ster\s*\)\s*$',1,1,0,'i')!=0     --Si lleva al final la cadena ' (Poster)'
  UNION ALL
  SELECT i.per_id AS persona,
    a.pci_id      AS produccion,
    '980'         AS tipo_participacion,
    ''            AS tipo_participacion_str,
    p.pri_pca_id  AS caracter, -- 20: docente !20: científico
    NULL          AS orden
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_personas_pci a
  ON a.pci_id = p.id
  JOIN GRS_OCT.oct_investigadores i
  ON a.inv_id         = i.id
  WHERE p.justificado ='S'
  AND p.borrado       ='N'
  AND p.iin_id       IN (41) --organizador
  AND p.ppi_ppr_id   IN (9)  --comité organizador
  UNION ALL
  SELECT i.per_id AS persona,
    a.pci_id      AS produccion,
    '990'         AS tipo_participacion,
    ''            AS tipo_participacion_str,
    p.pri_pca_id  AS caracter, -- 20: docente !20: científico  --> si es docente, este no se va a dar.
    NULL          AS orden
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_personas_pci a
  ON a.pci_id = p.id
  JOIN GRS_OCT.oct_investigadores i
  ON a.inv_id         = i.id
  WHERE p.justificado ='S'
  AND p.borrado       ='N'
  AND p.iin_id       IN (41) --organizador
  AND p.ppi_ppr_id   IN (10) --comité científico
WITH READ ONLY;



CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PER_PART_PROD_CONG_EX" ("PERSONA", "PRODUCCION", "TIPO_PARTICIPACION", "TIPO_PARTICIPACION_STR", "ORDEN")
                   AS
  SELECT ax.nombre AS persona,
    ax.pci_id      AS produccion,
    '730'          AS tipo_participacion,
    ''             AS tipo_participacion_str,
    ax.orden       AS orden
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_otras_personas_pci ax
  ON ax.pci_id        = p.id
  WHERE p.justificado ='S'
  AND p.borrado       ='N'
  AND p.iin_id       IN (35) --ponente
  AND p.logico2       ='S'   --invitado
  UNION ALL
  SELECT ax.nombre AS persona,
    ax.pci_id      AS produccion,
    '960'          AS tipo_participacion,
    ''             AS tipo_participacion_str,
    ax.orden       AS orden
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_otras_personas_pci ax
  ON ax.pci_id        = p.id
  WHERE p.justificado ='S'
  AND p.borrado       ='N'
  AND p.iin_id       IN (35) --ponente
  AND p.logico2       ='N'   --libre
  UNION ALL
  SELECT ax.nombre AS persona,
    ax.pci_id      AS produccion,
    '980'          AS tipo_participacion,
    ''             AS tipo_participacion_str,
    ax.orden       AS orden
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_otras_personas_pci ax
  ON ax.pci_id        = p.id
  WHERE p.justificado ='S'
  AND p.borrado       ='N'
  AND p.iin_id       IN (41) --organizador
  AND p.ppi_ppr_id   IN (9)  --comité organizador
  UNION ALL
  SELECT ax.nombre AS persona,
    ax.pci_id      AS produccion,
    '990'          AS tipo_participacion,
    ''             AS tipo_participacion_str,
    ax.orden       AS orden
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_otras_personas_pci ax
  ON ax.pci_id        = p.id
  WHERE p.justificado ='S'
  AND p.borrado       ='N'
  AND p.iin_id       IN (41) --organizador
  AND p.ppi_ppr_id   IN (10) --comité científico
WITH READ ONLY;



CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PER_PART_PROD_PUBL" ("PERSONA", "PRODUCCION", "CALIDAD_PARTICIPACION", "CALIDAD_PARTICIPACION_STR", "CARACTER", "ORDEN", "POS_SOBRE_TOTAL", "RESULTADOS_DESTACADOS", "IS_RELEVANTE_PUB")
                  AS
  SELECT i.per_id AS persona,
    a.pci_id      AS produccion,
    '070'         AS calidad_participacion,
    ''            AS calidad_participacion_str,
    p.pri_pca_id  AS caracter, -- 20: docente !20: científico
    a.orden,
    NULL AS pos_sobre_total,
    ''   AS resultados_destacados,
    NULL AS is_relevante_pub
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_personas_pci a
  ON a.pci_id = p.id
  JOIN GRS_OCT.oct_investigadores i
  ON a.inv_id         = i.id
  WHERE p.justificado ='S'
  AND p.borrado       ='N'
  AND p.iin_id        =32 --artículos
  UNION ALL
  SELECT i.per_id AS persona,
    a.pci_id      AS produccion,
    '090'         AS calidad_participacion,
    ''            AS calidad_participacion_str,
    p.pri_pca_id  AS caracter, -- 20: docente !20: científico
    a.orden,
    NULL AS pos_sobre_total,
    ''   AS resultados_destacados,
    NULL AS is_relevante_pub
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_personas_pci a
  ON a.pci_id = p.id
  JOIN GRS_OCT.oct_investigadores i
  ON a.inv_id           = i.id
  WHERE p.justificado   ='S'
  AND p.borrado         ='N'
  AND p.iin_id          =34   -- capítulos de libro
  AND p.ppi_ppr_id     IN (1) --es autor
  AND p.pri_pca_id NOT IN (7) --no es obra de arte
  UNION ALL
  SELECT i.per_id AS persona,
    a.pci_id      AS produccion,
    '120'         AS calidad_participacion,
    ''            AS calidad_participacion_str,
    p.pri_pca_id  AS caracter, -- 20: docente !20: científico
    a.orden,
    NULL AS pos_sobre_total,
    ''   AS resultados_destacados,
    NULL AS is_relevante_pub
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_personas_pci a
  ON a.pci_id = p.id
  JOIN GRS_OCT.oct_investigadores i
  ON a.inv_id           = i.id
  WHERE p.justificado   ='S'
  AND p.borrado         ='N'
  AND p.iin_id          =33   -- libro
  AND p.ppi_ppr_id     IN (1) --es autor
  AND p.pri_pca_id NOT IN (7) --no es obra de arte
  UNION ALL
  SELECT i.per_id AS persona,
    a.pci_id      AS produccion,
    '140'         AS calidad_participacion,
    ''            AS calidad_participacion_str,
    p.pri_pca_id  AS caracter, -- 20: docente !20: científico
    a.orden,
    NULL AS pos_sobre_total,
    ''   AS resultados_destacados,
    NULL AS is_relevante_pub
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_personas_pci a
  ON a.pci_id = p.id
  JOIN GRS_OCT.oct_investigadores i
  ON a.inv_id         = i.id
  WHERE p.justificado ='S'
  AND p.borrado       ='N'
  AND p.iin_id       IN (33,34) -- libro o capítulo
  AND p.ppi_ppr_id   IN (1)     --es autor
  AND p.pri_pca_id   IN (7)     --SÍ es obra de arte
  UNION ALL
  SELECT i.per_id AS persona,
    a.pci_id      AS produccion,
    '390'         AS calidad_participacion,
    ''            AS calidad_participacion_str,
    p.pri_pca_id  AS caracter, -- 20: docente !20: científico
    a.orden       AS orden,
    NULL          AS pos_sobre_total,
    ''            AS resultados_destacados,
    NULL          AS is_relevante_pub
  FROM grs_oct.oct_produc_cientificas p
  JOIN GRS_OCT.oct_personas_pci a
  ON a.pci_id = p.id
  JOIN GRS_OCT.oct_investigadores i
  ON a.inv_id         = i.id
  WHERE p.justificado ='S'
  AND p.borrado       ='N'
  AND p.iin_id        =33   -- libro
  AND p.ppi_ppr_id   IN (2) --es editor
WITH READ ONLY;



CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PER_PART_PROY" ("PERSONA", "PROYECTO", "CALIDAD_PARTICIPACION", "RESPONSABLE", "TIPO_PARTICIPACION", "APORTACIONES", "DEDICACION")
                  AS
  SELECT i.per_id AS persona,
    (SELECT MIN(s2.n)
    FROM grs_oct.oct_solicitudes_contratos s2
    WHERE s2.codigo_externo = s.codigo_externo
    )                          AS proyecto,
    MAX(ivs.principal)         AS calidad_participacion,
    MAX(ivs.principal)         AS responsable,
    ''                         AS tipo_participacion,
    ''                         AS aportaciones,
    MAX(ivs.horas_contratadas) AS dedicacion
  FROM grs_oct.oct_investigadores i
  JOIN grs_oct.oct_componentes_solicitudes ivs
  ON i.id = ivs.inv_id
  JOIN grs_oct.oct_solicitudes_contratos s
  ON ivs.contr_n = s.n
  LEFT JOIN grs_oct.oct_datos_contrataciones c
  ON s.n = c.contr_n
  JOIN grs_oct.oct_anexos a
  ON a.id                 = s.anexo_id
  AND a.convoc_id         = s.anexo_convoc_id
  AND a.convoc_ejercicio  = s.anexo_convoc_ejercicio
  WHERE s.codigo_externo IS NOT NULL
  AND c.fecha_inicio     IS NOT NULL
  AND (a.t_anexo_tipo     = 2
  AND (a.t_anexo_subtipo  ='P'
  OR (a.t_anexo_subtipo   ='A'
  AND a.t_anexo_microtipo ='P')))
  GROUP BY i.per_id,
    s.codigo_externo
  UNION ALL
  SELECT i.per_id              AS persona,
    s.n                        AS proyecto,
    MAX(ivs.principal)         AS calidad_participacion,
    MAX(ivs.principal)         AS responsable,
    ''                         AS tipo_participacion,
    ''                         AS aportaciones,
    MAX(ivs.horas_contratadas) AS dedicacion
  FROM grs_oct.oct_investigadores i
  JOIN grs_oct.oct_componentes_solicitudes ivs
  ON i.id = ivs.inv_id
  JOIN grs_oct.oct_solicitudes_contratos s
  ON ivs.contr_n = s.n
  LEFT JOIN grs_oct.oct_datos_contrataciones c
  ON s.n = c.contr_n
  JOIN grs_oct.oct_anexos a
  ON a.id                = s.anexo_id
  AND a.convoc_id        = s.anexo_convoc_id
  AND a.convoc_ejercicio = s.anexo_convoc_ejercicio
  WHERE s.titulo        IS NOT NULL
    -- AND s.codigo_externo   IS NULL
  AND c.fecha_inicio     IS NOT NULL
  AND ((a.t_anexo_tipo    = 1
  AND a.t_anexo_subtipo   ='C'
  AND a.t_anexo_microtipo!= 'T'))
  GROUP BY i.per_id,
    s.n
WITH READ ONLY;



CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PER_PART_PROY_EXT" ("PERSONA", "PROYECTO", "CALIDAD_PARTICIPACION", "RESPONSABLE", "TIPO_PARTICIPACION", "APORTACIONES", "DEDICACION")
                  AS
  SELECT i.per_id AS persona,
    p.id          AS proyecto,
    (
    CASE ip.orden
      WHEN 0
      THEN 'S'
      ELSE 'N'
    END) AS calidad_participacion,
    (
    CASE ip.orden
      WHEN 0
      THEN 'S'
      ELSE 'N'
    END) AS responsable,
    ''   AS tipo_participacion,
    ''   AS aportaciones,
    NULL AS dedicacion
  FROM grs_oct.oct_investigadores i
  JOIN grs_oct.oct_personas_pci ip
  ON i.id = ip.inv_id
  JOIN grs_oct.oct_produc_cientificas p
  ON ip.pci_id            = p.id
  WHERE p.justificado     ='S'
  AND p.borrado           ='N'
  AND p.iin_id            =46
  AND ((p.t_anexo_tipo    = 1
  AND p.t_anexo_subtipo   ='C'
  AND p.t_anexo_microtipo!= 'T')
  OR (p.t_anexo_tipo      = 2
  AND (p.t_anexo_subtipo  ='P'
  OR (p.t_anexo_subtipo   ='A'
  AND p.t_anexo_microtipo ='P'))))
WITH READ ONLY;



CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PER_PARTICIPA_GRUPO" ("PERSONA", "GRUPO", "FECHA_INICIO", "FECHA_FIN", "DURACION", "TIPO_COLABORACION", "RESULTADOS_RELEVANTES", "RESULTADOS_DESC", "RESULTADOS_KEYWORDS")
                   AS
  SELECT i.per_id  AS persona,
    ig.grupo_id    AS grupo,
    MIN(ig.f_alta) AS fecha_inicio,
    MAX(ig.f_baja) AS fecha_fin,
    TRUNC(SUM((
    CASE
      WHEN ig.f_baja IS NULL
      THEN sysdate
      ELSE ig.f_baja
    END)-ig.f_alta)) AS duracion,
    ''               AS tipo_colaboracion,
    ''               AS resultados_relevantes,
    ''               AS resultados_desc,
    ''               AS resultados_keywords
  FROM grs_oct.oct_investigadores_agrupados ig
  JOIN grs_oct.oct_investigadores i
  ON ig.inv_id = i.id
  WHERE regexp_like(ig.grupo_id, '^[0-9]+$')
  GROUP BY i.per_id,
    ig.grupo_id
WITH READ ONLY;



CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PROD_CONGRESOS" ("ID", "TITULO", "TIPO_EVENTO", "CARACTER", "TIPO_ACCESO", "AMBITO", "NOMBRE_CONGRESO", "ENTIDAD_ORGANIZADORA", "PAIS_CONGRESO", "REGION_CONGRESO", "CIUDAD_CONGRESO", "FECHA_CONGRESO", "ANO_CONGRESO", "IS_ACTA", "IS_PUB_EVALUADA", "TIPO_PUBLICACION", "TITULO_PUBLICACION", "NOMBRE_PUBLICACION", "VOLUMEN_PUB", "PAGINAS_PUB", "EDITORIAL_PUB", "PAIS_PUB", "REGION_PUB", "FECHA_PUB", "WEB_PUB", "ISBN_PUB", "DEP_LEGAL_PUB", "FECHA_FIN", "OBJETIVOS", "DESTINATARIOS", "IDIOMA", "FECHA_PRESENTACION", "HORAS")
AS
  SELECT p.id,
    regexp_replace(p.titulo,'^(.*)\s*\(\s*p[oóò]ster\s*\)\s*$','\1',1,0,'i')                             AS titulo, --para que no saque la cadena (Poster) al final de los poster.
    '008'                                                                                                AS tipo_evento,
    p.pri_pca_id                                                                                         AS caracter, -- 20: docente !20: científico
    ''                                                                                                   AS tipo_acceso,
    ''                                                                                                   AS ambito,
    p.nombre                                                                                             AS nombre_congreso,
    NULL                                                                                                 AS entidad_organizadora,
    regexp_replace(regexp_substr(p.lugar,'\(\s*([0-9]{3})\s*\)\s*$'),'^.*\(\s*([0-9]{3})\s*\)\s*$','\1') AS pais_congreso,
    NULL                                                                                                 AS region_congreso,
    p.lugar                                                                                              AS ciudad_congreso,
    p.fecha                                                                                              AS fecha_congreso, -- si esta es vacia, usaremos el año, de abajo.
    p.ano                                                                                                AS ano_congreso,
    NULL                                                                                                 AS is_acta,
    NULL                                                                                                 AS is_pub_evaluada,
    p.isbn                                                                                               AS tipo_publicacion, --artículo o capítulo de libro, según el formato que tenga el dato: isbn o issn.
    NVL(p.titulo,r.nombre_ext)                                                                           AS titulo_publicacion,
    ''                                                                                                   AS nombre_publicacion,
    --    p.editorial  AS volumen_pub,  --el contenido depende del tipo_publicacion
    NULL                                                                                                AS volumen_pub, --no contiene la info esperada. de momento esto.
    ''                                                                                                  AS paginas_pub,
    p.editorial                                                                                         AS editorial_pub, --el contenido depende del tipo_publicacion
    regexp_replace(regexp_substr(p.otro,'\(\s*([0-9]{3})\s*\)\s*$'),'^.*\(\s*([0-9]{3})\s*\)\s*$','\1') AS pais_pub,      -- p.otro llevará la cadena ' (iso_numérico_del_país_de_edición)'
    ''                                                                                                  AS region_pub,
    p.ano                                                                                               AS fecha_pub,
    p.texto                                                                                             AS web_pub,
    p.isbn                                                                                              AS isbn_pub, -- si exitieran más, serializarlos en este campo
    ''                                                                                                  AS dep_legal_pub,
    NULL                                                                                                AS fecha_fin,
    -- campos específicos de los congresos de caracter docente.
    ''   AS objetivos,
    ''   AS destinatarios,
    ''   AS idioma,
    NULL AS fecha_presentacion,
    NULL AS horas
  FROM grs_oct.oct_produc_cientificas p
    --  join oct_pci_participaciones ppr
    --  on p.ppi_ppr_id=ppr.id
    --  join oct_pci_caracteres pca
    --  on p.pri_pca_id=pca.id
  LEFT JOIN GRS_OCT.oct_pci_revistas r
  ON REGEXP_REPLACE(p.isbn,'^[^0-9]*([0-9]{4})[- :]([0-9Xx]{4})[^0-9]*$','\1-\2')=r.issn
  AND r.estado!                                                                  ='R' -- revista rechazada
  WHERE p.justificado                                                            ='S'
  AND p.borrado                                                                  ='N'
  AND p.iin_id                                                                  IN (35,41) --congreso_presenta, congreso_organiza
WITH READ ONLY;



CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PROD_PUBLICACIONES" ("ID", "TITULO", "TIPO", "CARACTER", "SOPORTE", "NOMBRE_PUB", "VOLUMEN_PUB", "PAGINAS_PUB", "FECHA_PUB", "WEB_PUB", "ISBN_PUB", "EDITORIAL_PUB", "PAIS_PUB", "REGION_PUB", "CIUDAD_PUB", "DEP_LEGAL_PUB", "COLECCION", "NUM_RESE", "TRADUCCIONES", "DENOMINACION", "DESTINATARIOS", "FECHA_CREACION", "JUSTIFICACION")
AS
  SELECT p.id,
    p.titulo                   AS titulo,
    p.iin_id                   AS tipo,     --artículo, libro o capítulo de libro.
    p.pri_pca_id               AS caracter, -- 20: docente !20: científico
    p.iin_id                   AS soporte,  --artículo->revista
    NVL(r.nombre_ext,p.nombre) AS nombre_pub,
    p.numero                   AS volumen_pub,
    p.paginas                  AS paginas_pub,
    p.ano                      AS fecha_pub,
    p.texto                    AS web_pub,
    p.pre_issn                 AS isbn_pub, -- si exitieran más, serializarlos en este campo
    ''                         AS editorial_pub,
    ''                         AS pais_pub,
    ''                         AS region_pub,
    ''                         AS ciudad_pub,
    ''                         AS dep_legal_pub,
    ''                         AS coleccion,
    NULL                       AS num_rese,
    ''                         AS traducciones, --serializar los códigos de idioma si se añaden algún día
    ''                         AS denominacion,
    ''                         AS destinatarios,
    NULL                       AS fecha_creacion,
    ''                         AS justificacion
  FROM grs_oct.oct_produc_cientificas p
  LEFT JOIN grs_oct.oct_pci_revistas r --debe ser left porque hay publicaciones sin revista.
  ON p.pre_issn = r.issn
    --  join oct_pci_participaciones ppr
    --  on p.ppi_ppr_id=ppr.id
    --  join oct_pci_caracteres pca
    --  on p.pri_pca_id=pca.id
  WHERE p.justificado ='S'
  AND p.borrado       ='N'
  AND p.iin_id        =32 --artículos (32 - '01' - '020').
  UNION ALL
  SELECT p.id,
    p.titulo                                                                                             AS titulo,
    p.iin_id                                                                                             AS tipo,     --artículo, libro o capítulo de libro.
    p.pri_pca_id                                                                                         AS caracter, -- 20: docente !20: científico  *** usar también para añadir la cadena '(tesis doctoral)' al final del título si pca=7
    p.iin_id                                                                                             AS soporte,  --libro->libro
    NULL                                                                                                 AS nombre_pub,
    NULL                                                                                                 AS volumen_pub,
    NULL                                                                                                 AS paginas_pub,
    p.ano                                                                                                AS fecha_pub,
    p.texto                                                                                              AS web_pub,
    NVL(p.isbn,p.pre_issn)                                                                               AS isbn_pub, -- si exitieran más, serializarlos en este campo  -- el nvl es porque si es editor de monografía de revista, en vez de isbn estará el issn, aunque se considera editor de libro en los indicadores.
    p.editorial                                                                                          AS editorial_pub,
    regexp_replace(regexp_substr(p.lugar,'\(\s*([0-9]{3})\s*\)\s*$'),'^.*\(\s*([0-9]{3})\s*\)\s*$','\1') AS pais_pub,
    ''                                                                                                   AS region_pub,
    p.lugar
    ||':'               AS ciudad_pub,
    NVL(p.otro,p.texto) AS dep_legal_pub, --'otro' es el correcto. en texto está en algunos y debe corregirse.
    ''                  AS coleccion,
    NULL                AS num_rese,
    ''                  AS traducciones, --serializar los códigos de idioma si se añaden algún día
    ''                  AS denominacion,
    ''                  AS destinatarios,
    NULL                AS fecha_creacion,
    ''                  AS justificacion
  FROM grs_oct.oct_produc_cientificas p
  WHERE p.justificado ='S'
  AND p.borrado       ='N'
  AND p.iin_id        =33     --libros (33 - '02' - '032')
  AND p.ppi_ppr_id   IN (1,2) -- sólo aquellos libros en que es autor o editor
  UNION ALL
  SELECT p.id,
    p.nombre                                                                                             AS titulo,
    p.iin_id                                                                                             AS tipo,     --artículo, libro o capítulo de libro.
    p.pri_pca_id                                                                                         AS caracter, -- 20: docente !20: científico
    p.iin_id                                                                                             AS soporte,  --capitulo-> Se resuelve en el código. básicamente porque en las prod docentes sí hay soporte capítulo
    p.titulo                                                                                             AS nombre_pub,
    NULL                                                                                                 AS volumen_pub,
    NULL                                                                                                 AS paginas_pub,
    p.ano                                                                                                AS fecha_pub,
    p.texto                                                                                              AS web_pub,
    p.isbn                                                                                               AS isbn_pub, -- si exitieran más, serializarlos en este campo
    p.editorial                                                                                          AS editorial_pub,
    regexp_replace(regexp_substr(p.lugar,'\(\s*([0-9]{3})\s*\)\s*$'),'^.*\(\s*([0-9]{3})\s*\)\s*$','\1') AS pais_pub,
    ''                                                                                                   AS region_pub,
    p.lugar
    ||':'               AS ciudad_pub,
    NVL(p.otro,p.texto) AS dep_legal_pub, --'otro' es el correcto. en texto está en algunos y debe corregirse.
    ''                  AS coleccion,
    NULL                AS num_rese,
    ''                  AS traducciones, --serializar los códigos de idioma si se añaden algún día
    ''                  AS denominacion,
    ''                  AS destinatarios,
    NULL                AS fecha_creacion,
    ''                  AS justificacion
  FROM grs_oct.oct_produc_cientificas p
  WHERE p.justificado ='S'
  AND p.borrado       ='N'
  AND p.iin_id        =34   --capítulos de libro (34 - '03' - '004').
  AND p.ppi_ppr_id   IN (1) -- sólo aquellos libros en que es autor
WITH READ ONLY;



CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PROY_EXT" ("ID", "CODIGO_EXTERNO", "TITULO", "FECHA_INICIO", "FECHA_FIN", "DURACION", "NINVESTIGADORES", "NINVESTIGADORESEXT", "TITULO_KEYWORDS", "MODALIDAD", "TIPO", "SUBTIPO", "MICROTIPO", "AMBITO", "ENTIDAD_EJECUTORA", "NPERSONASANYO", "NOMBRE_PROGRAMA_FINANCIACION", "CODIGO_PROYECTO_FINANCIADORA", "DOTACION_TOTAL", "DOTACION_SUBPROYECTO", "PORCENTAJE_SUBVENCION", "PORCENTAJE_CREDITO", "PORCENTAJE_MIXTO", "RESULTADOS_RELEVANTES", "RESULTADOS_KEYWORDS", "NOMBRE_ENT_FINANCIADORA")
AS
  SELECT p.id,
    p.isbn AS codigo_externo,
    p.titulo,
    p.fecha             AS fecha_inicio,
    p.fecha2            AS fecha_fin,
    p.fecha2-p.fecha    AS duracion,
    0                   AS ninvestigadores,
    0                   AS ninvestigadoresext,
    NULL                AS titulo_keywords,
    NULL                AS modalidad,
    p.t_anexo_tipo      AS tipo,      --consultar
    p.t_anexo_subtipo   AS subtipo,   --consultar
    p.t_anexo_microtipo AS microtipo, --cons
    ta.nombre           AS ambito,
    p.nombre            AS entidad_ejecutora,
    NULL                AS npersonasanyo,
    NULL                AS nombre_programa_financiacion,
    p.isbn              AS codigo_proyecto_financiadora,
    NULL                AS dotacion_total,
    NULL                AS dotacion_subproyecto,
    NULL                AS porcentaje_subvencion,
    NULL                AS porcentaje_credito,
    NULL                AS porcentaje_mixto,
    NULL                AS resultados_relevantes,
    NULL                AS resultados_keywords,
    p.otro              AS nombre_ent_financiadora -- ya que sólo hay una y nos toca hacer una vista aparte, la incrusto aquí.
  FROM grs_oct.oct_produc_cientificas p
  LEFT JOIN grs_oct.oct_tipos_anexo ta
  ON p.t_anexo_tipo       = ta.tipo
  AND p.t_anexo_subtipo   =ta.subtipo
  AND p.t_anexo_microtipo =ta.microtipo
  WHERE p.justificado     ='S'
  AND p.borrado           ='N'
  AND p.iin_id            =46
  AND ((p.t_anexo_tipo    = 1
  AND p.t_anexo_subtipo   ='C'
  AND p.t_anexo_microtipo!= 'T')
  OR (p.t_anexo_tipo      = 2
  AND (p.t_anexo_subtipo  ='P'
  OR (p.t_anexo_subtipo   ='A'
  AND p.t_anexo_microtipo ='P'))))
WITH READ ONLY;



CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PROY_INV" ("ID", "CODIGO_EXTERNO", "TITULO", "FECHA_INICIO", "FECHA_FIN", "DURACION", "NINVESTIGADORES", "NINVESTIGADORESEXT", "TITULO_KEYWORDS", "MODALIDAD", "TIPO", "SUBTIPO", "MICROTIPO", "AMBITO", "ENTIDAD_EJECUTORA", "NPERSONASANYO", "NOMBRE_PROGRAMA_FINANCIACION", "CODIGO_PROYECTO_FINANCIADORA", "DOTACION_TOTAL", "DOTACION_SUBPROYECTO", "PORCENTAJE_SUBVENCION", "PORCENTAJE_CREDITO", "PORCENTAJE_MIXTO", "RESULTADOS_RELEVANTES", "RESULTADOS_KEYWORDS", "SUBORGANISMO")
                  AS
  SELECT MIN(s.n) AS id,
    s.codigo_externo,
    MIN(s.titulo)                        AS titulo,
    MIN(c.fecha_inicio)                  AS fecha_inicio,
    MAX(c.fecha_fin)                     AS fecha_fin,
    MAX(c.fecha_fin)-MIN(c.fecha_inicio) AS duracion,
    (SELECT COUNT(DISTINCT ivs.inv_id)
    FROM grs_oct.oct_componentes_solicitudes ivs
    JOIN grs_oct.oct_solicitudes_contratos s2
    ON ivs.contr_n          = s2.n
    WHERE s2.codigo_externo = s.codigo_externo
    ) AS ninvestigadores,
    (SELECT COUNT(DISTINCT ext.per_id)
    FROM grs_oct.oct_componentes_externos ext
    JOIN grs_oct.oct_solicitudes_contratos s3
    ON ext.contr_n          = s3.n
    WHERE s3.codigo_externo = s.codigo_externo
    )                        AS ninvestigadoresext,
    ''                       AS titulo_keywords,
    ''                       AS modalidad,
    MIN(a.t_anexo_tipo)      AS tipo,      --consultar
    MIN(a.t_anexo_subtipo)   AS subtipo,   --consultar
    MIN(a.t_anexo_microtipo) AS microtipo, --cons
    MIN(ta.nombre)           AS ambito,
    'Universitat Jaume I'    AS entidad_ejecutora,
    ''                       AS npersonasanyo,
    (SELECT co.titulo
      ||' * '
      ||a1.titulo
    FROM grs_oct.oct_convocatorias co
    LEFT JOIN grs_oct.oct_anexos a1
    ON a1.convoc_id         = co.id
    AND a1.convoc_ejercicio = co.ejercicio
    LEFT JOIN grs_oct.oct_solicitudes_contratos s1
    ON a1.id                = s1.anexo_id
    AND a1.convoc_id        = s1.anexo_convoc_id
    AND a1.convoc_ejercicio = s1.anexo_convoc_ejercicio
    WHERE s1.codigo_externo = s.codigo_externo
    AND rownum              <2
    )                        AS nombre_programa_financiacion,
    s.codigo_externo         AS codigo_proyecto_financiadora,
    SUM(c.importe_concedido) AS dotacion_total,
    ''                       AS dotacion_subproyecto,
    ''                       AS porcentaje_subvencion,
    ''                       AS porcentaje_credito,
    ''                       AS porcentaje_mixto,
    ''                       AS resultados_relevantes,
    ''                       AS resultados_keywords,
    MIN(cv.suboraganismos)   AS suborganismo
  FROM grs_oct.oct_convocatorias cv
  LEFT JOIN grs_oct.oct_anexos a
  ON a.convoc_id         = cv.id
  AND a.convoc_ejercicio = cv.ejercicio
  LEFT JOIN grs_oct.oct_solicitudes_contratos s
  ON a.id                = s.anexo_id
  AND a.convoc_id        = s.anexo_convoc_id
  AND a.convoc_ejercicio = s.anexo_convoc_ejercicio
  JOIN grs_oct.oct_datos_contrataciones c
  ON s.n                       = c.contr_n
  AND c.mostrar_en_certificado = 'S'
  LEFT JOIN grs_oct.oct_tipos_anexo ta
  ON a.t_anexo_tipo       = ta.tipo
  AND a.t_anexo_subtipo   =ta.subtipo
  AND a.t_anexo_microtipo =ta.microtipo
  WHERE s.codigo_externo IS NOT NULL
  AND c.fecha_inicio     IS NOT NULL
  AND (a.t_anexo_tipo     = 2
  AND (a.t_anexo_subtipo  ='P'
  OR (a.t_anexo_subtipo   ='A'
  AND a.t_anexo_microtipo ='P')))
  GROUP BY s.codigo_externo
  UNION ALL -- el all elimina la comparación de cada par de elementos para eliminar duplicados, que en este caso no pueden existir.
  SELECT s.n                             AS id ,
    MIN(s.codigo_externo)                AS codigo_externo,
    MIN(s.titulo)                        AS titulo,
    MIN(c.fecha_inicio)                  AS fecha_inicio,
    MAX(c.fecha_fin)                     AS fecha_fin,
    MAX(c.fecha_fin)-MIN(c.fecha_inicio) AS duracion,
    (SELECT COUNT(DISTINCT ivs.inv_id)
    FROM grs_oct.oct_componentes_solicitudes ivs
    JOIN grs_oct.oct_solicitudes_contratos s2
    ON ivs.contr_n = s2.n
    WHERE s2.n     = s.n
    ) AS ninvestigadores,
    (SELECT COUNT(DISTINCT ext.per_id)
    FROM grs_oct.oct_componentes_externos ext
    JOIN grs_oct.oct_solicitudes_contratos s3
    ON ext.contr_n = s3.n
    WHERE s3.n     = s.n
    )                        AS ninvestigadoresext,
    ''                       AS titulo_keywords,
    ''                       AS modalidad,
    MIN(a.t_anexo_tipo)      AS tipo,      --consultar
    MIN(a.t_anexo_subtipo)   AS subtipo,   --consultar
    MIN(a.t_anexo_microtipo) AS microtipo, --consultar
    MIN(ta.nombre)           AS ambito,
    'Universitat Jaume I'    AS entidad_ejecutora,
    ''                       AS npersonasnyo,
    (SELECT co.titulo
      ||' * '
      ||a1.titulo
    FROM grs_oct.oct_convocatorias co
    LEFT JOIN grs_oct.oct_anexos a1
    ON a1.convoc_id         = co.id
    AND a1.convoc_ejercicio = co.ejercicio
    LEFT JOIN grs_oct.oct_solicitudes_contratos s1
    ON a1.id                = s1.anexo_id
    AND a1.convoc_id        = s1.anexo_convoc_id
    AND a1.convoc_ejercicio = s1.anexo_convoc_ejercicio
    WHERE s1.n              = s.n
    AND rownum              <2
    )                        AS nombre_programa_financiacion,
    MIN(s.codigo_externo)    AS codigo_proyecto_financiadora,
    SUM(c.importe_concedido) AS dotacion_total,
    ''                       AS dotacion_subproyecto,
    ''                       AS porcentaje_subvencion,
    ''                       AS porcentaje_credito,
    ''                       AS porcentaje_mixto,
    ''                       AS resultados_relevantes,
    ''                       AS resultados_keywords,
    MIN(cv.suboraganismos)   AS suborganismo
  FROM grs_oct.oct_convocatorias cv
  LEFT JOIN grs_oct.oct_anexos a
  ON a.convoc_id         = cv.id
  AND a.convoc_ejercicio = cv.ejercicio
  LEFT JOIN grs_oct.oct_solicitudes_contratos s
  ON a.id                = s.anexo_id
  AND a.convoc_id        = s.anexo_convoc_id
  AND a.convoc_ejercicio = s.anexo_convoc_ejercicio
  JOIN grs_oct.oct_datos_contrataciones c
  ON s.n                       = c.contr_n
  AND c.mostrar_en_certificado = 'S'
  LEFT JOIN grs_oct.oct_tipos_anexo ta
  ON a.t_anexo_tipo       = ta.tipo
  AND a.t_anexo_subtipo   =ta.subtipo
  AND a.t_anexo_microtipo =ta.microtipo
  WHERE s.titulo         IS NOT NULL
    --AND  s.codigo_externo IS NULL  -- esta condición desaparece porque ahora puede haber contratos con código externo
  AND c.fecha_inicio     IS NOT NULL
  AND ((a.t_anexo_tipo    = 1
  AND a.t_anexo_subtipo   ='C'
  AND a.t_anexo_microtipo!= 'T'))
  GROUP BY s.n
WITH READ ONLY;
