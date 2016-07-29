CREATE OR REPLACE FORCE EDITIONABLE VIEW "UJI_CVN"."CVN_VIEW_PROD_PUBLICACIONES_PCI" ("ID", "TITULO", "TIPO", "CARACTER", "SOPORTE", "NOMBRE_PUB", "VOLUMEN_PUB", "PAGINAS_PUB", "FECHA_PUB", "WEB_PUB", "ISBN_PUB", "EDITORIAL_PUB", "PAIS_PUB", "REGION_PUB", "CIUDAD_PUB", "DEP_LEGAL_PUB", "COLECCION", "NUM_RESE", "TRADUCCIONES", "DENOMINACION", "DESTINATARIOS", "FECHA_CREACION", "JUSTIFICACION") AS

-- Artículos
select p.id id,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 86) titulo,
   p.tipo_id tipo,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 90) caracter,
   p.tipo_id soporte,
  (select nvl(r.nombre_extendido, r.nombre) from pci_producciones_detalle d join pci_revistas r on r.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 92) nombre_pub,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 167) volumen,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 165) pagina_inicio,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 166) pagina_fin,
  nvl((select to_date(valor) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 175),
    (select to_date(valor) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 176)) fecha_pub, -- Normal o online
  (select r.issn from pci_producciones_detalle d join pci_revistas r on r.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 92) isbn_pub,
  '' editorial_pub,
  (select pai.codigo_mec from pci_producciones_detalle d join pci_revistas r on r.id = to_number(d.valor) join per_paises pai on pai.id = r.pais_id where d.produccion_id = p.id and atributo_tipo_id = 92) pais_pub,
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
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 169) tipo_documento,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 91) doi,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 171) url_documento,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 172 ) handle
  
from pci_producciones p
    join pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 1 and e.estado_id = 'J'

UNION ALL

-- Libros
select p.id id,
  (select l.titulo from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 93) titulo,
  p.tipo_id tipo,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 95) caracter,
  p.tipo_id soporte,
  null nombre_pub,
  null volumen_pub,
  null pagina_inicio,
  null pagina_fin,
  (select l.fecha_publicacion from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 93) fecha_pub,
  (select l.isbn from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 93) isbn,
  (select e.nombre from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) join pci_editoriales e on e.id = l.editorial_id where d.produccion_id = p.id and atributo_tipo_id = 93) editorial,
  (select pai.codigo_mec from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) join per_paises pai on pai.id = l.pais_publicacion_id where d.produccion_id = p.id and atributo_tipo_id = 93) pais_pub,
  '' region_pub,
  (select l.lugar_publicacion from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 93)||':' lugar_pub,
  (select l.deposito_legal from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 93) dep_legal_pub,
  (select l.coleccion from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 93) coleccion,
  null num_rese,
  '' traducciones,
  '' denominacion,
  '' destinatarios,
  null fecha_creacion,
  '' justificacion,
  null tipo_documento,
  (select l.doi from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 93) doi,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 183) url_documento,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 184) handle

from pci_producciones p
  join pci_producciones_estados e on e.produccion_id = p.id
  join pci_producciones_detalle d on d.produccion_id = p.id
where tipo_id = 2 and e.estado_id = 'J'
  and d.atributo_tipo_id = 177 and d.valor in (271, 272) -- Autor o Editor;
  
UNION ALL
  
-- Capítulos de libro
select p.id id,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 96) titulo,
  p.tipo_id tipo,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 99) caracter,
  p.tipo_id soporte,
  (select l.titulo from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 97) nombre_pub,
  null volumen_pub,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 178) pagina_inicio,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 179) pagina_fin,
  (select l.fecha_publicacion from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 97) fecha_pub,
  (select l.isbn from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 97) isbn,
  (select e.nombre from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) join pci_editoriales e on e.id = l.editorial_id where d.produccion_id = p.id and atributo_tipo_id = 97) editorial,
  (select pai.codigo_mec from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) join per_paises pai on pai.id = l.pais_publicacion_id where d.produccion_id = p.id and atributo_tipo_id = 97) pais_pub,
  '' region_pub,
  (select l.lugar_publicacion from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 97)||':' lugar_pub,
  (select l.deposito_legal from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 97) dep_legal_pub,
  (select l.coleccion from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 97) coleccion,
  null num_rese,
  '' traducciones,
  '' denominacion,
  '' destinatarios,
  null fecha_creacion,
  '' justificacion,
  null tipo_documento,
  nvl((select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 100), (select l.doi from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 97)) doi,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 182) url_documento,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 181) handle
  
from  pci_producciones p
  join pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 3 and e.estado_id = 'J'

UNION ALL

-- Edición de monográficos
select p.id id,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 249) titulo,
  p.tipo_id tipo,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 251) caracter,
  p.tipo_id soporte,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 244) nombre_pub,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 246) volumen,
  null pagina_inicio,
  null pagina_fin,
  decode((select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 248),
        null, null,
        (select to_date('01/01/'||valor, 'DD/MM/YYYY') from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 248)) fecha_pub,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 245) isbn_pub,
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
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 255) doi,
  null url_documento,
  null handle_repositorio

from  pci_producciones p
  join pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 17 and e.estado_id = 'J'

UNION ALL

-- Apuntes docentes
select p.id,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 163) titulo,
  p.tipo_id tipo,
  '286' caracter, --Docente
  p.tipo_id soporte,
  null nombre_pub,
  null volumen,
  null pagina_inicio,
  null pagina_fin,
  (select to_date(valor) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 164) fecha_pub,
  null isbn_pub,
  (select e.nombre from pci_producciones_detalle d join pci_editoriales e on e.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 261) editorial_pub,
  '' pais,
  '' region_pub,
  '' ciudad_pub,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 253) dep_legal_pub,
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

from  pci_producciones p
  join pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 18 and e.estado_id = 'J';