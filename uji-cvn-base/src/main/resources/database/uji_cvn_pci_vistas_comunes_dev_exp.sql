CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PROD_PUBLI_PCI" ("ID", "TITULO", "TIPO", "CARACTER", "SOPORTE", "NOMBRE_PUB", "VOLUMEN_PUB", "PAGINA_INICIO", "PAGINA_FIN", "FECHA_PUB", "ISBN_PUB", "EDITORIAL_PUB", "PAIS_PUB", "REGION_PUB", "CIUDAD_PUB", "DEP_LEGAL_PUB", "COLECCION", "NUM_RESE", "TRADUCCIONES", "DENOMINACION", "DESTINATARIOS", "FECHA_CREACION", "JUSTIFICACION", "TIPO_DOCUMENTO", "DOI", "URL_DOCUMENTO", "HANDLE") AS
-- Publicaciones periódicas
select id,
  titulo,
  tipo_id tipo,
  caracter_id caracter,
  tipo_id soporte,
  revista nombre_pub,
  volumen,
  pagina_inicio,
  pagina_fin,
  nvl(fecha_pub, fecha_pub_online) fecha_pub,
  issn isbn_pub,
  '' editorial_pub,
  pais_cod pais_pub,
  '' region_pub,
  '' ciudad_pub,
  '' dep_legal_pub,
  '' coleccion,
  null num_rese,
  '' traducciones,
  '' denominacion,
  '' destinatarios,
  null fecha_creacion,
  '' justificacion,
  tipo_documento,
  doi,
  url_documento,
  handle
from pci_producciones_01
  where estado = 'J'

union

-- Libros
select id,
  titulo,
  tipo_id tipo,
  caracter_id caracter,
  tipo_id soporte,
  null nombre_pub,
  null volumen_pub,
  null pagina_inicio,
  null pagina_fin,
  fecha_pub,
  isbn,
  editorial,
  pais_cod pais_pub,
  '' region_pub,
  lugar lugar_pub,
  deposito_legal dep_legal_pub,
  coleccion,
  null num_rese,
  '' traducciones,
  '' denominacion,
  '' destinatarios,
  null fecha_creacion,
  '' justificacion,
  null tipo_documento,
  doi,
  url_documento,
  handle
from pci_producciones_02
  where participacion_id in (271, 272) -- Autor o Editor
    and estado = 'J'

union

-- Capítulos de libro
select id,
  titulo_capitulo titulo,
  tipo_id tipo,
  caracter_id caracter,
  tipo_id soporte,
  titulo_libro nombre_pub,
  null volumen_pub,
  pagina_inicio,
  pagina_fin,
  fecha_pub,
  isbn isbn_pub,
  editorial,
  pais_cod pais_pub,
  '' region,
  lugar lugar_pub,
  deposito_legal dep_legal_pub,
  coleccion,
  null num_rese,
  '' traducciones,
  '' denominacion,
  '' destinatarios,
  null fecha_creacion,
  '' justificacion,
  null tipo_documento,
  doi,
  url_documento,
  handle
from pci_producciones_03
  where estado = 'J'

union

-- Edición de monográficos
select id,
  titulo_monografico,
  tipo_id tipo,
  caracter_id caracter,
  tipo_id soporte,
  revista nombre_pub,
  volumen,
  null pagina_inicio,
  null pagina_fin,
  to_date(decode(anyo, null, null, to_char(to_date('01/01/'||anyo, 'DD/MM/YYYY')))) fecha_pub,
  issn isbn_pub,
  '' editorial_pub,
  '' pais,
  '' region_pub,
  '' ciudad_pub,
  '' dep_legal_pub,
  '' coleccion,
  null num_rese,
  '' traducciones,
  '' denominacion,
  '' destinatarios,
  null fecha_creacion,
    '' justificacion,
  '' tipo_documento,
  doi,
  null url_documento,
  null handle_repositorio
from pci_producciones_17
  where estado = 'J'

union

-- Apuntes docentes
select id,
  titulo,
  tipo_id tipo,
  286 caracter, -- Docente
  tipo_id soporte,
  null nombre_pub,
  null volumen,
  null pagina_inicio,
  null pagina_fin,
  fecha fecha_pub,
  null isbn_pub,
  editorial editorial_pub,
  '' pais,
  '' region_pub,
  '' ciudad_pub,
  referencia dep_legal_pub,
  '' coleccion,
  null num_rese,
  '' traducciones,
  '' denominacion,
  '' destinatarios,
  null fecha_creacion,
    '' justificacion,
  '' tipo_documento,
  null doi,
  null url_documento,
  null handle_repositorio
from pci_producciones_18
  where estado = 'J';


-- Congresos
CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PROD_CONGRESOS_PCI" ("ID", "TITULO", "TIPO_EVENTO", "CARACTER", "TIPO_ACCESO", "AMBITO", "NOMBRE_CONGRESO", "ENTIDAD_ORGANIZADORA", "PAIS_CONGRESO", "REGION_CONGRESO", "CIUDAD_CONGRESO", "FECHA_CONGRESO", "ANO_CONGRESO", "IS_ACTA", "IS_PUB_EVALUADA", "TIPO_PUBLICACION", "TITULO_PUBLICACION", "NOMBRE_PUBLICACION", "VOLUMEN_PUB", "PAGINA_INICIO", "PAGINA_FIN", "EDITORIAL_PUB", "PAIS_PUB", "REGION_PUB", "FECHA_PUB", "DOI", "ISBN_PUB", "DEP_LEGAL_PUB", "FECHA_FIN", "OBJETIVOS", "DESTINATARIOS", "IDIOMA", "FECHA_PRESENTACION", "HORAS", "URL_DOCUMENTO") AS
-- Publicado en actas, Libros
select id,
  titulo,
  '008' tipo_evento,
  caracter_id caracter,
  '' tipo_acceso,
  ambito_id ambito,
  congreso,
  entidad_organizadora,
  pais_cod pais_congreso,
  null region_congreso,
  lugar ciudad_congreso,
  fecha_inicio fecha_congreso,
  to_number(to_char(to_date(fecha_inicio), 'yyyy')) ano_congreso,
  publicado_actas is_acta,
  null is_pub_evaluada,
  isbn tipo_publicacion,
  titulo_libro titulo_publicacion,
  '' nombre_publicacion,
  null volumen_pub,
  pagina_inicio,
  pagina_fin,
  editorial_libro editorial_pub,
  pais_cod_libro pais_pub,
  null region_pub,
  fecha_pub,
  doi,
  isbn isbn_pub,
  dep_legal_libro dep_legal_pub,
  to_date(null) fecha_fin,
  '' objetivos,
  '' destinatarios,
  '' idioma,
  to_date(null) fecha_presentacion,
  null horas,
  handle,
  url_documento
from pci_producciones_04
  where isbn is not null and publicado_actas = 'S'
    and estado = 'J'

union all

-- Publicado en actas, Revistas
select id,
  titulo,
  '008' tipo_evento,
  caracter_id caracter,
  '' tipo_acceso,
  ambito_id ambito,
  congreso,
  entidad_organizadora,
  pais_cod pais_congreso,
  null region_congreso,
  lugar ciudad_congreso,
  fecha_inicio fecha_congreso,
  to_number(to_char(to_date(fecha_inicio), 'yyyy')) ano_congreso,
  publicado_actas is_acta,
  null is_pub_evaluada,
  issn tipo_publicacion,
  titulo_revista titulo_publicacion,
  '' nombre_publicacion,
  null volumen_pub,
  pagina_inicio,
  pagina_fin,
  '' editorial_pub,
  pais_cod_revista pais_pais_pub,
  null region_pub,
  fecha_pub,
  doi,
  issn isbn_pub,
  '' dep_legal_pub,
  to_date(null) fecha_fin,
  '' objetivos,
  '' destinatarios,
  '' idioma,
  to_date(null) fecha_presentacion,
  null horas,
  handle,
  url_documento
from pci_producciones_04
  where issn is not null and publicado_actas = 'S'
    and estado = 'J'

union all

-- No publicados en actas
select id,
  titulo,
  '008' tipo_evento,
  caracter_id caracter,
  '' tipo_acceso,
  ambito_id ambito,
  congreso,
  entidad_organizadora,
  pais_cod pais_congreso,
  null region_congreso,
  lugar ciudad_congreso,
  fecha_inicio fecha_congreso,
  to_number(to_char(to_date(fecha_inicio), 'yyyy')) ano_congreso,
  null is_acta,
  null is_pub_evaluada,
  null tipo_publicacion,
  null titulo_publicacion,
  '' nombre_publicacion,
  null volumen_pub,
  null pagina_inicio,
  null pagina_fin,
  null editorial_pub,
  null pais_pub,
  '' region_pub,
  null fecha_pub,
  null doi,
  null isbn_pub,
  '' dep_legal_pub,
  to_date(null) fecha_fin,
  '' objetivos,
  '' destinatarios,
  '' idioma,
  to_date(null) fecha_presentacion,
  null horas,
  null handle,
  null url_documento
from pci_producciones_04
  where publicado_actas = 'N'
    and estado = 'J'

union all

-- Comités de congresos
select id,
  null titulo,
  '008' as tipo_evento,
  null caracter,
  '' tipo_acceso,
  ambito_id ambito,
  nombre_congreso congreso,
  entidad_organizadora,
  pais_cod pais_congreso,
  null region_congreso,
  ciudad ciudad_congreso,
  fecha_inicio fecha_congreso,
  to_number(to_char(to_date(fecha_inicio), 'yyyy')) ano_congreso,
  null is_acta,
  null is_pub_evaluada,
  null tipo_publicacion,
  null titulo_publicacion,
  '' nombre_publicacion,
  null volumen_pub,
  null pagina_inicio,
  null pagina_fin,
  null editorial_pub,
  null pais_pub,
  '' region_pub,
  null fecha_pub,
  null doi,
  null isbn_pub,
  '' dep_legal_pub,
  to_date(null) fecha_fin,
  '' objetivos,
  '' destinatarios,
  '' idioma,
  to_date(null) fecha_presentacion,
  null horas,
  null handle,
  null url_documento
from pci_producciones_11
  where estado = 'J';

-- Proyectos de investigación externos
CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PROY_EXT_PCI" ("ID", "CODIGO_EXTERNO", "TITULO", "FECHA_INICIO", "FECHA_FIN", "DURACION", "NINVESTIGADORES", "NINVESTIGADORESEXT", "TITULO_KEYWORDS", "MODALIDAD", "TIPO", "SUBTIPO", "MICROTIPO", "AMBITO", "ENTIDAD_EJECUTORA", "NPERSONASANYO", "NOMBRE_PROGRAMA_FINANCIACION", "CODIGO_PROYECTO_FINANCIADORA", "DOTACION_TOTAL", "DOTACION_SUBPROYECTO", "PORCENTAJE_SUBVENCION", "PORCENTAJE_CREDITO", "PORCENTAJE_MIXTO", "RESULTADOS_RELEVANTES", "RESULTADOS_KEYWORDS", "NOMBRE_ENT_FINANCIADORA") AS
select id,
  codigo_externo,
  titulo,
  fecha_inicio,
  fecha_fin,
  fecha_fin - fecha_inicio duracion,
  0 ninvestigadores,
  0 ninvestigadoresext,
  NULL titulo_keywords,
  NULL modalidad,
  tipo,
  subtipo,
  microtipo,
  tipo_anexo ambito,
  institucion_ejecutora entidad_ejecutora,
  NULL npersonasanyo,
  programa nombre_programa_financiacion,
  codigo_externo codigo_proyecto_financiadora,
  importe dotacion_total,
  NULL dotacion_subproyecto,
  NULL porcentaje_subvencion,
  NULL porcentaje_credito,
  NULL porcentaje_mixto,
  NULL resultados_relevantes,
  NULL   resultados_keywords,
  institucion_financiadora nombre_ent_financiadora
from pci_producciones_06
  where regexp_like(tipo_anexo_id, '^(1C[^T])|(2P.)|(2AP)$')
    and estado = 'J';


-- Participación en producciones científicas
CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PER_PART_PUBL_PCI" ("PERSONA", "PRODUCCION", "CALIDAD_PARTICIPACION", "CALIDAD_PARTICIPACION_STR", "CARACTER", "ORDEN", "POS_SOBRE_TOTAL", "RESULTADOS_DESTACADOS", "IS_RELEVANTE_PUB", "CORRESPONDING_AUTHOR") AS
-- Artículos
select a.persona_id persona,
  p.id produccion,
  '070' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
   a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_01 p
  join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is not null
  and p.estado = 'J'

union all

-- Libros
select a.persona_id persona,
   p.id produccion,
  '120' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_02 p
  join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is not null
  and participacion_id = 271 -- Es autor
  and caracter_id not in (279, 280) -- No es obra de arte
    and p.estado = 'J'

union all

-- Capítulos de libro
select a.persona_id persona,
   p.id produccion,
  '090' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_03 p
  join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is not null
  and caracter_id not in (279, 280) -- No es obra de arte
    and p.estado = 'J'

union all

-- Obras de arte (libros)
select a.persona_id persona,
  p.id produccion,
  '140' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_02 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is not null
  and participacion_id = 271 -- Es autor
  and caracter_id in (279, 280)
  and p.estado = 'J'

union all

-- Obras de arte (capítulos)
select a.persona_id persona,
  p.id produccion,
  '140' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_03 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is not null
  and caracter_id in (279, 280)
  and p.estado = 'J'

union all

-- Editor libros
select a.persona_id persona,
   p.id produccion,
  '390' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author

from pci_producciones_02 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is not null
  and participacion_id = 272 -- Es editor
  and p.estado = 'J'


union all

-- Editor de monográficos
select a.persona_id persona,
   p.id produccion,
  '390' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author

from pci_producciones_17 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is not null
  and p.estado = 'J'

union all

-- Apuntes docentes
select a.persona_id persona,
   p.id produccion,
  '120' calidad_participacion, -- Habrá que cambiarlo?
  '' calidad_participacion_str,
  286 caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author

from pci_producciones_18 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is not null
  and p.estado = 'J';


-- Participación externa en producciones científicas
CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PER_EXT_PART_PUBL_PCI" ("PERSONA", "PRODUCCION", "CALIDAD_PARTICIPACION", "CALIDAD_PARTICIPACION_STR", "CARACTER", "ORDEN", "POS_SOBRE_TOTAL", "RESULTADOS_DESTACADOS", "IS_RELEVANTE_PUB", "CORRESPPONDING_AUTHOR") AS
-- Artículos
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '070' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
   a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_01 p
  join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is null
  and p.estado = 'J'

union all

-- Libros
select trim(a.nombre||' '||a.apellidos) persona,
   p.id produccion,
  '120' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_02 p
  join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is null
  and participacion_id = 271 -- Es autor
  and caracter_id not in (279, 280) -- No es obra de arte
    and p.estado = 'J'

union all

-- Capítulos de libro
select trim(a.nombre||' '||a.apellidos) persona,
   p.id produccion,
  '090' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_03 p
  join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is null
  and caracter_id not in (279, 280) -- No es obra de arte
  and p.estado = 'J'

union all

-- Obras de arte (libros)
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '140' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_02 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is null
  and participacion_id = 271 -- Es autor
  and caracter_id in (279, 280)
  and p.estado = 'J'

union all

-- Obras de arte (capítulos)
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '140' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_03 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is null
  and caracter_id in (279, 280)
  and p.estado = 'J'

union all

-- Editor libros
select trim(a.nombre||' '||a.apellidos) persona,
   p.id produccion,
  '390' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_02 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is null
  and participacion_id = 272 -- Es editor
  and p.estado = 'J'

union all

-- Editor de monográficos
select trim(a.nombre||' '||a.apellidos) persona,
   p.id produccion,
  '390' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author

from pci_producciones_17 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is null
  and p.estado = 'J'

union all

-- Apuntes docentes
select trim(a.nombre||' '||a.apellidos) persona,
   p.id produccion,
  '120' calidad_participacion, -- Habrá que cambiarlo?
  '' calidad_participacion_str,
  286 caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_18 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is null
  and p.estado = 'J';


CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PER_PART_CONG_PCI" ("PERSONA", "PRODUCCION", "TIPO_PARTICIPACION", "TIPO_PARTICIPACION_STR", "CARACTER", "ORDEN", "CORRESPONDING_AUTHOR") AS
-- Ponencia invitada
select a.persona_id persona,
  p.id produccion,
  '730' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author

from pci_producciones_04 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is not null
  and p.participacion_id = 283
  and p.estado = 'J'

union all

-- Ponencia libre
select a.persona_id persona,
  p.id produccion,
  '960' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_04 p
    join pci_producciones_autores a on a.produccion_id = p.id
    join pci_producciones_estados e on e.produccion_id = p.id
where a.persona_id is not null
  and p.participacion_id not in (281, 283)
  and p.estado = 'J'

union all

-- Póster
select a.persona_id persona,
  p.id produccion,
  '970' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_04 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is not null
  and p.participacion_id = 281
  and p.estado = 'J'

union all

-- Comité organizador
select a.persona_id persona,
  p.id produccion,
  '980' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_11 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is not null
  and p.tipo_comite_id = 289
  and p.estado = 'J'

union all

-- Comité científico
select a.persona_id persona,
  p.id produccion,
  '990' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_11 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is not null
  and tipo_comite_id = 290
  and p.estado = 'J';

CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PER_PART_CONG_EX_PCI" ("PERSONA", "PRODUCCION", "TIPO_PARTICIPACION", "TIPO_PARTICIPACION_STR", "CARACTER", "ORDEN", "CORRESPONDING_AUTHOR") AS
-- Ponencia invitada
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '730' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_04 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is null
  and p.participacion_id = 283
  and p.estado = 'J'

union all

-- Ponencia libre
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '960' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_04 p
    join pci_producciones_autores a on a.produccion_id = p.id
    join pci_producciones_estados e on e.produccion_id = p.id
where a.persona_id is null
  and p.participacion_id not in (281, 283)
  and p.estado = 'J'

union all

-- Póster
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '970' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_04 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is null
  and p.participacion_id = 281
  and p.estado = 'J'

union all

-- Comité organizador
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '980' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_11 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is null
  and p.tipo_comite_id = 289
  and p.estado = 'J'

union all

-- Comité científico
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '990' calidad_participacion,
  '' calidad_participacion_str,
  p.caracter_id caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author
from pci_producciones_11 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is null
  and tipo_comite_id = 290
  and p.estado = 'J';


CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PER_PART_PROY_EXT_PCI" ("PERSONA", "PROYECTO", "CALIDAD_PARTICIPACION", "RESPONSABLE", "TIPO_PARTICIPACION", "APORTACIONES", "DEDICACION", "CORRESPONDING_AUTHOR") AS
select a.persona_id persona,
  p.id proyecto,
  (CASE a.orden WHEN 0 THEN 'S' ELSE 'N' END) calidad_participacion,
  (CASE a.orden WHEN 0 THEN 'S' ELSE 'N' END) responsable,
  '' tipo_participacion,
  '' aportaciones,
  NULL dedicacion,
  decode(a.role_destacado,1,1,0) corresponding_author

from pci_producciones_06 p
    join pci_producciones_autores a on a.produccion_id = p.id
where a.persona_id is not null
  and regexp_like(p.tipo_anexo_id, '^(1C[^T])|(2P.)|(2AP)$')
  and p.estado = 'J';


CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_IMPACTO_PROD_PUBL_PCI" ("PRODUCCION", "FUENTE_IMPACTO", "INDICE_IMPACTO", "CUARTIL", "IS_25P_TOP", "CATEGORIA1", "CATEGORIA2", "POSICION", "TOTAL") AS
-- Artículos
select p.id produccion,
  a.origen fuente_impacto,
  (case
         when a.indice_impacto > 0
          and a.indice_impacto < 100 then
            a.indice_impacto
         else
            null
      end) indice_impacto,
  c.cuartil cuartil,
  (case c.cuartil when 'Q1' -- el 4 es el cuartil 1
                    then 1 else 0 end) is_25p_top, -- en la vista de la uji, 4 es el Q1
  c.area_tipo as categoria1,
  c.area_id as categoria2,
  null as posicion,
  null as total
from pci_producciones_01 p
  join pci_revistas r on r.issn = p.issn
  join pci_revistas_anualidades a on a.revista_id = r.id
  join pci_revistas_cuartiles c on c.anualidad_id = a.id
where (a.anio_id = to_number(to_char(to_date(p.fecha_pub), 'YYYY'))
  or a.anio_id = to_number(to_char(to_date(p.fecha_pub_online), 'YYYY')))
    and p.estado = 'J'

union all

-- Congresos
select p.id produccion,
  a.origen fuente_impacto,
  (case
         when a.indice_impacto > 0
          and a.indice_impacto < 100 then
            a.indice_impacto
         else
            null
      end) indice_impacto,
  c.cuartil cuartil,
  (case c.cuartil when 'Q1' -- el 4 es el cuartil 1
                    then 1 else 0 end) is_25p_top, -- en la vista de la uji, 4 es el Q1
  c.area_tipo as categoria1,
  c.area_id as categoria2,
  null as posicion,
  null as total
from pci_producciones_04 p
  join pci_revistas r on r.issn = p.issn
  join pci_revistas_anualidades a on a.revista_id = r.id
  join pci_revistas_cuartiles c on c.anualidad_id = a.id
where a.anio_id = to_number(to_char(to_date(p.fecha_pub), 'YYYY'))
  and p.estado = 'J';






