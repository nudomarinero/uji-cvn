-- Vistas de publicaciones científicas

CREATE OR REPLACE FORCE EDITIONABLE VIEW "UJI_CVN"."CVN_VIEW_PROD_PUBLI_PCI" ("ID", "TITULO", "TIPO", "CARACTER", "SOPORTE", "NOMBRE_PUB", "VOLUMEN_PUB", "PAGINA_INICIO", "PAGINA_FIN", "FECHA_PUB", "ISBN_PUB", "EDITORIAL_PUB", "PAIS_PUB", "REGION_PUB", "CIUDAD_PUB", "DEP_LEGAL_PUB", "COLECCION", "NUM_RESE", "TRADUCCIONES", "DENOMINACION", "DESTINATARIOS", "FECHA_CREACION", "JUSTIFICACION",
  "TIPO_DOCUMENTO", "DOI", "URL_DOCUMENTO", "HANDLE") AS

-- Artículos
select p.id id,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 86) titulo,
   p.tipo_id tipo,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 90) caracter,
   p.tipo_id soporte,
  (select nvl(r.nombre_extendido, r.nombre) from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_revistas r on r.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 92) nombre_pub,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 167) volumen,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 165) pagina_inicio,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 166) pagina_fin,
  nvl((select to_date(valor) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 175),
    (select to_date(valor) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 176)) fecha_pub, -- Normal o online
  (select r.issn from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_revistas r on r.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 92) isbn_pub,
  '' editorial_pub,
  (select pai.codigo_mec from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_revistas r on r.id = to_number(d.valor) join per_paises pai on pai.id = r.pais_id where d.produccion_id = p.id and atributo_tipo_id = 92) pais_pub,
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
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 169) tipo_documento,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 91) doi,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 171) url_documento,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 172 ) handle

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 1 and e.estado_id = 'J'

UNION ALL

-- Libros
select p.id id,
  (select l.titulo from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 93) titulo,
  p.tipo_id tipo,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 95) caracter,
  p.tipo_id soporte,
  null nombre_pub,
  null volumen_pub,
  null pagina_inicio,
  null pagina_fin,
  (select l.fecha_publicacion from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 93) fecha_pub,
  (select l.isbn from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 93) isbn,
  (select e.nombre from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) join uji_investigacion.pci_editoriales e on e.id = l.editorial_id where d.produccion_id = p.id and atributo_tipo_id = 93) editorial,
  (select pai.codigo_mec from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) join per_paises pai on pai.id = l.pais_publicacion_id where d.produccion_id = p.id and atributo_tipo_id = 93) pais_pub,
  '' region_pub,
  (select l.lugar_publicacion from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 93)||':' lugar_pub,
  (select l.deposito_legal from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 93) dep_legal_pub,
  (select l.coleccion from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 93) coleccion,
  null num_rese,
  '' traducciones,
  '' denominacion,
  '' destinatarios,
  null fecha_creacion,
  '' justificacion,
  null tipo_documento,
  (select l.doi from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 93) doi,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 183) url_documento,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 184) handle

from uji_investigacion.pci_producciones p
  join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
  join uji_investigacion.pci_producciones_detalle d on d.produccion_id = p.id
where tipo_id = 2 and e.estado_id = 'J'
  and d.atributo_tipo_id = 177 and d.valor in (271, 272) -- Autor o Editor;

UNION ALL

-- Capítulos de libro
select p.id id,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 96) titulo,
  p.tipo_id tipo,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 99) caracter,
  p.tipo_id soporte,
  (select l.titulo from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 97) nombre_pub,
  null volumen_pub,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 178) pagina_inicio,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 179) pagina_fin,
  (select l.fecha_publicacion from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 97) fecha_pub,
  (select l.isbn from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 97) isbn,
  (select e.nombre from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) join uji_investigacion.pci_editoriales e on e.id = l.editorial_id where d.produccion_id = p.id and atributo_tipo_id = 97) editorial,
  (select pai.codigo_mec from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) join per_paises pai on pai.id = l.pais_publicacion_id where d.produccion_id = p.id and atributo_tipo_id = 97) pais_pub,
  '' region_pub,
  (select l.lugar_publicacion from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 97)||':' lugar_pub,
  (select l.deposito_legal from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 97) dep_legal_pub,
  (select l.coleccion from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 97) coleccion,
  null num_rese,
  '' traducciones,
  '' denominacion,
  '' destinatarios,
  null fecha_creacion,
  '' justificacion,
  null tipo_documento,
  nvl((select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 100), (select l.doi from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 97)) doi,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 182) url_documento,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 181) handle

from  uji_investigacion.pci_producciones p
  join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 3 and e.estado_id = 'J'

UNION ALL

-- Edición de monográficos
select p.id id,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 249) titulo,
  p.tipo_id tipo,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 251) caracter,
  p.tipo_id soporte,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 244) nombre_pub,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 246) volumen,
  null pagina_inicio,
  null pagina_fin,
  to_date(decode((select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 248),
        null, null,
        (select to_char(to_date('01/01/'||valor, 'DD/MM/YYYY')) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 248))) fecha_pub,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 245) isbn_pub,
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
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 255) doi,
  null url_documento,
  null handle_repositorio

from  uji_investigacion.pci_producciones p
  join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 17 and e.estado_id = 'J'

UNION ALL

-- Apuntes docentes
select p.id,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 163) titulo,
  p.tipo_id tipo,
  '286' caracter, --Docente
  p.tipo_id soporte,
  null nombre_pub,
  null volumen,
  null pagina_inicio,
  null pagina_fin,
  (select to_date(valor) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 164) fecha_pub,
  null isbn_pub,
  (select e.nombre from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_editoriales e on e.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 261) editorial_pub,
  '' pais,
  '' region_pub,
  '' ciudad_pub,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 253) dep_legal_pub,
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

from  uji_investigacion.pci_producciones p
  join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 18 and e.estado_id = 'J';


CREATE OR REPLACE FORCE EDITIONABLE VIEW "UJI_CVN"."CVN_VIEW_PER_PART_PUBL_PCI" ("PERSONA", "PRODUCCION", "CALIDAD_PARTICIPACION", "CALIDAD_PARTICIPACION_STR", "CARACTER", "ORDEN", "POS_SOBRE_TOTAL", "RESULTADOS_DESTACADOS", "IS_RELEVANTE_PUB", "CORRESPONDING_AUTHOR") AS

-- Artículos
select a.persona_id persona,
  p.id produccion,
  '070' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 90) caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 1 and e.estado_id = 'J' and a.persona_id is not null

UNION ALL

-- Libros
select a.persona_id persona,
   p.id produccion,
  '120' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 95) caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 2 and e.estado_id = 'J' and a.persona_id is not null
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 177) = '271' -- Es autor
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 95) not in ('279', '280') -- No es obra de arte

UNION ALL

--Capítulos de libro
select a.persona_id persona,
   p.id produccion,
  '090' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 99) caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 3 and e.estado_id = 'J' and a.persona_id is not null
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 99) not in ('279', '280') -- No es obra de arte

UNION ALL

-- Obras de arte
select a.persona_id persona,
  p.id produccion,
  '140' calidad_participacion,
  '' calidad_participacion_str,
  nvl((select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 95), (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 99)) caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id in (2, 3) and e.estado_id = 'J' and a.persona_id is not null
  and (((select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 177) = '271' -- Es autor
    and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 95) in ('279', '280'))
    or (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 99) in ('279', '280'))

UNION ALL

-- Editor libros
select a.persona_id persona,
   p.id produccion,
  '390' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 95) caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 2 and e.estado_id = 'J' and a.persona_id is not null
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 177) = '272' -- Es editor

UNION ALL

-- Editor de monográficos
select a.persona_id persona,
   p.id produccion,
  '390' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 251) caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 17 and e.estado_id = 'J' and a.persona_id is not null

UNION ALL

-- Apuntes docentes
select a.persona_id persona,
   p.id produccion,
  '120' calidad_participacion, -- Habrá que cambiarlo?
  '' calidad_participacion_str,
  '286' caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 18 and e.estado_id = 'J' and a.persona_id is not null;


CREATE OR REPLACE FORCE EDITIONABLE VIEW "UJI_CVN"."CVN_VIEW_PER_EXT_PART_PUBL_PCI" ("PERSONA", "PRODUCCION", "CALIDAD_PARTICIPACION", "CALIDAD_PARTICIPACION_STR", "CARACTER", "ORDEN", "POS_SOBRE_TOTAL", "RESULTADOS_DESTACADOS", "IS_RELEVANTE_PUB", "CORRESPPONDING_AUTHOR") AS

-- Artículos
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '070' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 90) caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 1 and e.estado_id = 'J' and a.persona_id is null

UNION ALL

-- Libros
select trim(a.nombre||' '||a.apellidos) persona,
   p.id produccion,
  '120' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 95) caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 2 and e.estado_id = 'J' and a.persona_id is null
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 177) = '271' -- Es autor
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 95) not in ('279', '280') -- No es obra de arte

UNION ALL

--Capítulos de libro
select trim(a.nombre||' '||a.apellidos) persona,
   p.id produccion,
  '090' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 99) caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 3 and e.estado_id = 'J' and a.persona_id is null
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 99) not in ('279', '280') -- No es obra de arte

UNION ALL

-- Obras de arte
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '140' calidad_participacion,
  '' calidad_participacion_str,
  nvl((select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 95), (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 99)) caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id in (2, 3) and e.estado_id = 'J' and a.persona_id is null
  and (((select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 177) = '271' -- Es autor
    and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 95) in ('279', '280'))
    or (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 99) in ('279', '280'))

UNION ALL

-- Editor libros
select trim(a.nombre||' '||a.apellidos) persona,
   p.id produccion,
  '390' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 95) caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 2 and e.estado_id = 'J' and a.persona_id is null
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 177) = '272' -- Es editor

UNION ALL

-- Editor de monográficos
select trim(a.nombre||' '||a.apellidos) persona,
   p.id produccion,
  '390' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 251) caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 17 and e.estado_id = 'J' and a.persona_id is null

UNION ALL

-- Apuntes docentes
select trim(a.nombre||' '||a.apellidos) persona,
   p.id produccion,
  '120' calidad_participacion, -- Habrá que cambiarlo?
  '' calidad_participacion_str,
  '286' caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 18 and e.estado_id = 'J' and a.persona_id is null;


-- Vistas de congresos

CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PROD_CONGRESOS_PCI" ("ID", "TITULO", "TIPO_EVENTO", "CARACTER", "TIPO_ACCESO", "AMBITO", "NOMBRE_CONGRESO", "ENTIDAD_ORGANIZADORA", "PAIS_CONGRESO", "REGION_CONGRESO", "CIUDAD_CONGRESO", "FECHA_CONGRESO", "ANO_CONGRESO", "IS_ACTA", "IS_PUB_EVALUADA", "TIPO_PUBLICACION", "TITULO_PUBLICACION", "NOMBRE_PUBLICACION", "VOLUMEN_PUB", "PAGINA_INICIO", "PAGINA_FIN", "EDITORIAL_PUB", "PAIS_PUB", "REGION_PUB", "FECHA_PUB", "WEB_PUB", "ISBN_PUB", "DEP_LEGAL_PUB", "FECHA_FIN", "OBJETIVOS", "DESTINATARIOS", "IDIOMA", "FECHA_PRESENTACION", "HORAS") AS
  select p.id,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 102) titulo,
  '008' as tipo_evento,
  to_number((select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 107)) caracter,
  '' tipo_acceso, --Tipo participación???
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 192) ambito,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 101) nombre_congreso,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 189) entidad_organizadora,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 188) pais_congreso,
  null region_congreso,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 103) ciudad_congreso,
  (select to_date(valor) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 105) fecha_congreso,
  (select to_number(to_char(to_date(valor), 'yyyy')) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 105) ano_congreso,
  substr((select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 110), 0, 1) is_acta,
  null is_pub_evaluada,
  (select l.isbn from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 104) tipo_publicacion,
  (select l.titulo from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 104) titulo_publicacion,
  '' nombre_publicacion,
  null volumen_pub,
  to_number((select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 194)) pagina_inicio,
  to_number((select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 195)) pagina_fin,
  (select e.nombre from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) join uji_investigacion.pci_editoriales e on e.id = l.editorial_id where d.produccion_id = p.id and atributo_tipo_id = 104) editorial_pub,
  (select pai.codigo_mec from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) join per_paises pai on pai.id = l.pais_publicacion_id where d.produccion_id = p.id and atributo_tipo_id = 104) pais_pub,
  (select l.lugar_publicacion from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 104) region_pub,
  nvl((select to_date(valor) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 191), (select l.fecha_publicacion from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 104)) fecha_pub,
  nvl((select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 108), (select l.doi from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 104)) web_pub,
  (select l.isbn from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 104) isbn_pub,
  (select l.deposito_legal from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 104) dep_legal_pub,
  to_date(null) fecha_fin,
  '' objetivos,
  '' destinatarios,
  '' idioma,
  to_date(null) fecha_presentacion,
  null horas

from uji_investigacion.pci_producciones p
  join uji_investigacion.pci_producciones_detalle d on d.produccion_id = p.id
  join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where p.tipo_id = 4 and d.atributo_tipo_id = 104 and e.estado_id = 'J'
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 110) = 'S'

UNION ALL

-- Publicados en actas, Revistas
select p.id,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 102) titulo,
  '008' as tipo_evento,
  to_number((select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 107)) caracter,
  '' tipo_acceso, --Tipo participación???
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 192) ambito,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 101) nombre_congreso,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 189) entidad_organizadora,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 188) pais_congreso,
  null region_congreso,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 103) ciudad_congreso,
  (select to_date(valor) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 105) fecha_congreso,
  (select to_number(to_char(to_date(valor), 'yyyy')) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 105) ano_congreso,
  substr((select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 110), 0, 1) is_acta,
  null is_pub_evaluada,
  (select r.issn from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_revistas r on r.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 190) tipo_publicacion,
  (select r.nombre_extendido from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_revistas r on r.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 190) titulo_publicacion,
  '' nombre_publicacion,
  null volumen_pub,
  to_number((select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 194)) pagina_inicio,
  to_number((select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 195)) pagina_fin,
  '' editorial_pub,
  (select pai.codigo_mec from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_revistas r on r.id = to_number(d.valor) join per_paises pai on pai.id = r.pais_id where d.produccion_id = p.id and atributo_tipo_id = 190) pais_pub,
  (select r.lugar from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_revistas r on r.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 190) region_pub,
  (select to_date(valor) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 191) fecha_pub,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 108) web_pub,
  (select r.issn from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_revistas r on r.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 190) isbn_pub,
  '' dep_legal_pub,
  to_date(null) fecha_fin,
  '' objetivos,
  '' destinatarios,
  '' idioma,
  to_date(null) fecha_presentacion,
  null horas

from uji_investigacion.pci_producciones p
  join uji_investigacion.pci_producciones_detalle d on d.produccion_id = p.id
  join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where p.tipo_id = 4 and d.atributo_tipo_id = 190 and e.estado_id = 'J'
  and d.valor not like '%-%'
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 110) = 'S'

UNION ALL

-- No publicado en actas
select p.id,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 102) titulo,
  '008' as tipo_evento,
  to_number((select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 107)) caracter,
  '' tipo_acceso, --Tipo participación???
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 192) ambito,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 101) nombre_congreso,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 189) entidad_organizadora,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 188) pais_congreso,
  null region_congreso,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 103) ciudad_congreso,
  (select to_date(valor) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 105) fecha_congreso,
  (select to_number(to_char(to_date(valor), 'yyyy')) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 105) ano_congreso,
  substr((select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 110), 0, 1) is_acta,
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
  null web_pub,
  null isbn_pub,
  '' dep_legal_pub,
  to_date(null) fecha_fin,
  '' objetivos,
  '' destinatarios,
  '' idioma,
  to_date(null) fecha_presentacion,
  null horas

from uji_investigacion.pci_producciones p
  join uji_investigacion.pci_producciones_detalle d on d.produccion_id = p.id
  join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where p.tipo_id = 4 and d.atributo_tipo_id = 110 and d.valor = 'N' and e.estado_id = 'J'

UNION ALL

-- Comités de congresos
select p.id,
  null titulo,
  '008' as tipo_evento,
  null caracter,
  '' tipo_acceso, --Tipo participación???
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 140) ambito,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 137) nombre_congreso,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 141) entidad_organizadora,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 217) pais_congreso,
  null region_congreso,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 138) ciudad_congreso,
  (select to_date(valor) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 139) fecha_congreso,
  (select to_number(to_char(to_date(valor), 'yyyy')) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 105) ano_congreso,
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
  null web_pub,
  null isbn_pub,
  '' dep_legal_pub,
  to_date(null) fecha_fin,
  '' objetivos,
  '' destinatarios,
  '' idioma,
  to_date(null) fecha_presentacion,
  null horas

from uji_investigacion.pci_producciones p
  join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where p.tipo_id = 11 and e.estado_id = 'J';


CREATE OR REPLACE FORCE EDITIONABLE VIEW "UJI_CVN"."CVN_VIEW_PER_PART_CONG_PCI" ("PERSONA", "PRODUCCION", "TIPO_PARTICIPACION", "TIPO_PARTICIPACION_STR", "CARACTER", "ORDEN", "CORRESPONDING_AUTHOR") AS

-- Ponencia invitada
select a.persona_id persona,
  p.id produccion,
  '730' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 90) caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 4 and e.estado_id = 'J' and a.persona_id is not null
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 109) = '283'

UNION ALL

-- Ponencia libre
select a.persona_id persona,
  p.id produccion,
  '960' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 90) caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 4 and e.estado_id = 'J' and a.persona_id is not null
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 109) not in ('281', '283')

UNION ALL

 -- Póster
select a.persona_id persona,
  p.id produccion,
  '970' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 90) caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 4 and e.estado_id = 'J' and a.persona_id is not null
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 109) = '281'

UNION ALL

-- Comité organizador
select a.persona_id persona,
  p.id produccion,
  '980' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 90) caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 11 and e.estado_id = 'J' and a.persona_id is not null
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 218) = '289'

UNION ALL

-- Comité científico
select a.persona_id persona,
  p.id produccion,
  '990' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 90) caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 11 and e.estado_id = 'J' and a.persona_id is not null
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 218) = '290';


CREATE OR REPLACE FORCE EDITIONABLE VIEW "UJI_CVN"."CVN_VIEW_PER_PART_CONG_EX_PCI" ("PERSONA", "PRODUCCION", "TIPO_PARTICIPACION", "TIPO_PARTICIPACION_STR", "CARACTER", "ORDEN", "CORRESPONDING_AUTHOR") AS

-- Ponencia invitada
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '730' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 90) caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 4 and e.estado_id = 'J' and a.persona_id is null
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 109) = '283'

UNION ALL

-- Ponencia libre
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '960' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 90) caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 4 and e.estado_id = 'J' and a.persona_id is null
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 109) != '283'

UNION ALL

-- Comité organizador
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '980' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 90) caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 11 and e.estado_id = 'J' and a.persona_id is null
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 218) = '289'

UNION ALL

-- Comité científico
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '990' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 90) caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 11 and e.estado_id = 'J' and a.persona_id is null
  and (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 218) = '290';



-- Vistas de acciones de investigación externas

CREATE OR REPLACE FORCE EDITIONABLE VIEW "UJI_CVN"."CVN_VIEW_PROY_EXT_PCI" ("ID", "CODIGO_EXTERNO", "TITULO", "FECHA_INICIO", "FECHA_FIN", "DURACION", "NINVESTIGADORES", "NINVESTIGADORESEXT", "TITULO_KEYWORDS", "MODALIDAD", "TIPO", "SUBTIPO", "MICROTIPO", "AMBITO", "ENTIDAD_EJECUTORA", "NPERSONASANYO", "NOMBRE_PROGRAMA_FINANCIACION", "CODIGO_PROYECTO_FINANCIADORA", "DOTACION_TOTAL", "DOTACION_SUBPROYECTO", "PORCENTAJE_SUBVENCION", "PORCENTAJE_CREDITO", "PORCENTAJE_MIXTO", "RESULTADOS_RELEVANTES", "RESULTADOS_KEYWORDS", "NOMBRE_ENT_FINANCIADORA") AS
  select p.id,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 119) codigo_externo,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 118) titulo,
  (select to_date(valor) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 120) fecha_inicio,
  (select to_date(valor) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 123) fecha_fin,
  (select to_date(valor) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 123) - (select to_date(valor) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 120) duracion,
  0 ninvestigadores,
  0 ninvestigadoresext,
  NULL titulo_keywords,
  NULL modalidad,
  (select tipo from uji_investigacion.pci_ext_tipos_anexos where id = (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 124)) tipo,
  (select subtipo from uji_investigacion.pci_ext_tipos_anexos where id = (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 124)) subtipo,
  (select microtipo from uji_investigacion.pci_ext_tipos_anexos where id = (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 124)) microtipo,
  (select a.nombre from uji_investigacion.pci_ext_tipos_anexos a join uji_investigacion.pci_producciones_detalle d on d.valor = a.id where d.produccion_id = p.id and atributo_tipo_id = 124) ambito,
  nvl((select o.nombre from uji_investigacion.pci_ext_organismos o join uji_investigacion.pci_producciones_detalle d on d.valor = o.id where d.produccion_id = p.id and atributo_tipo_id = 243), (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 206)) entidad_ejecutora,
  NULL npersonasanyo,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 121) nombre_programa_financiacion,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 119) codigo_proyecto_financiadora,
  (select to_number(valor) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 209) dotacion_total,
  NULL dotacion_subproyecto,
  NULL porcentaje_subvencion,
  NULL porcentaje_credito,
  NULL porcentaje_mixto,
  NULL resultados_relevantes,
  NULL   resultados_keywords,
  nvl((select o.nombre from uji_investigacion.pci_ext_organismos o join uji_investigacion.pci_producciones_detalle d on d.valor = o.id where d.produccion_id = p.id and atributo_tipo_id = 242), (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 122)) nombre_ent_financiadora

from uji_investigacion.pci_producciones p
  join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
  join uji_investigacion.pci_producciones_detalle d on d.produccion_id = p.id
where tipo_id = 6 and e.estado_id = 'J'
  and d.atributo_tipo_id = 124 and regexp_like(d.valor, '^(1C[^T])|(2P.)|(2AP)$');


CREATE OR REPLACE FORCE EDITIONABLE VIEW "UJI_CVN"."CVN_VIEW_PER_PART_PROY_EXT_PCI" ("PERSONA", "PROYECTO", "CALIDAD_PARTICIPACION", "RESPONSABLE", "TIPO_PARTICIPACION", "APORTACIONES", "DEDICACION", "CORRESPONDING_AUTHOR") AS

select a.persona_id persona,
  p.id proyecto,
  (CASE a.orden WHEN 0 THEN 'S' ELSE 'N' END) calidad_participacion,
  (CASE a.orden WHEN 0 THEN 'S' ELSE 'N' END) responsable,
  '' tipo_participacion,
  '' aportaciones,
  NULL dedicacion,
  decode(a.role_destacado,1,1,0) corresponding_author

from uji_investigacion.pci_producciones p
    join uji_investigacion.pci_producciones_autores a on a.produccion_id = p.id
    join uji_investigacion.pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 6 and e.estado_id = 'J' and a.persona_id is not null
  and regexp_like((select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 124), '^(1C[^T])|(2P.)|(2AP)$');


-- Vista tesis

CREATE OR REPLACE FORCE EDITIONABLE VIEW "UJI_CVN"."CVN_VIEW_DIRECCION_TESIS_PCI" ("ID", "TESIS_PROPIA", "TIPO_ID", "TIPO_TXT", "PERSONA", "TITULO", "CODIRECTOR_NOMBRE", "CODIRECTOR_APELLIDO1", "CODIRECTOR_APELLIDO2", "DIRECTOR_NOMBRE", "DIRECTOR_APELLIDO1", "DIRECTOR_APELLIDO2", "OTRODIRECTOR_NOMBRE", "OTRODIRECTOR_APELLIDO1", "OTRODIRECTOR_APELLIDO2", "PAIS", "REGION", "CIUDAD", "ENTIDAD", "TIPO_ENTIDAD", "ALUMNO_NOMBRE", "ALUMNO_APELLIDO1", "ALUMNO_APELLIDO2", "FECHA_LECTURA", "CALIFICACION", "DOCTOR_EUROPEO", "F_DOCTOR_EUROPEO", "PROGRAMA", "ENTIDAD_DEA", "F_OBTENCION_DEA", "MENCION_CALIDAD", "F_MENCION_CALIDAD", "PREMIO_EXTRAORDINARIO", "F_PREMIO_EXTRAORDINARIO", "DOCTOR_INTERNACIONAL") AS
select 'PC*'||p.id||'*'||a.orden id,
  0 tesis_propia,
  '067' tipo_id,
  'Tesis' tipo_txt,
  a.persona_id persona,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 125) titulo,
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
  (select pai.codigo_mec from uji_investigacion.pci_producciones_detalle d join uji_investigacion.pci_revistas r on r.id = to_number(d.valor) join per_paises pai on pai.id = r.pais_id where d.produccion_id = p.id and atributo_tipo_id = 229) pais,
  null region,
  null ciudad,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 126) entidad,
  null tipo_entidad,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 223) alumno_nombre,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 225) alumno_apellido1,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 226) alumno_apellido2,
  (select to_date(valor) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 128) f_apro_tesis,
  (select valor from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 127) calificacion,
  nvl((select 'S' from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 228 and to_number(trim(valor)) = 293), 'N') doctor_europeo,
  decode(nvl((select 'S' from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 228 and to_number(trim(valor)) = 293), 'N'), 'S', (select to_date(valor) from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 128), null) f_doctor_europeo,
  null programa,
  null entidad_dea,
  to_date(null) f_obtencion_dea,
  null mencion_calidad,
  to_date(null) f_mencion_calidad,
  null premio_extraordinario,
  to_date(null) f_premio_extraordinario,
  nvl((select 'S' from uji_investigacion.pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 228 and to_number(trim(valor)) = 294), null) doctor_internacional

from uji_investigacion.pci_producciones p,
  uji_investigacion.pci_producciones_estados e,
  uji_investigacion.pci_producciones_autores a,
  (select a.nombre, a.apellidos, null, a.produccion_id, a.orden from uji_investigacion.pci_producciones_autores a where a.persona_id is not null and a.orden = (select max(orden) from uji_investigacion.pci_producciones_autores a2 where a2.produccion_id = a.produccion_id and a2.persona_id is not null)) codirector

where tipo_id = 7
  and e.produccion_id = p.id
  and a.produccion_id = p.id
  and e.estado_id = 'J'
  and a.persona_id is not null
  and a.orden = (select min(orden)
                        from uji_investigacion.pci_producciones_autores a2
                       where a2.produccion_id = a.produccion_id and a2.persona_id is not null)
  and p.id = codirector.produccion_id(+);



-- Vista impacto producciones

CREATE OR REPLACE FORCE EDITIONABLE VIEW "UJI_CVN"."CVN_VIEW_IMPACTO_PROD_PUBL_PCI" ("PRODUCCION", "FUENTE_IMPACTO", "INDICE_IMPACTO", "CUARTIL", "IS_25P_TOP", "CATEGORIA1", "CATEGORIA2", "POSICION", "TOTAL") AS
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

from uji_investigacion.pci_producciones p
  join uji_investigacion.pci_producciones_detalle d on p.id = d.produccion_id and
    (d.atributo_tipo_id = 92 or (atributo_tipo_id = 190 and regexp_like(valor, '^\d+$')))
  join uji_investigacion.pci_revistas r on r.id = to_number(d.valor)
  join uji_investigacion.pci_revistas_anualidades a on a.revista_id = r.id
  join uji_investigacion.pci_revistas_cuartiles c on c.anualidad_id = a.id;