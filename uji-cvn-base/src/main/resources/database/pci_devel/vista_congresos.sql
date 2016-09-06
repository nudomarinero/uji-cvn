
  CREATE OR REPLACE FORCE VIEW "UJI_CVN"."CVN_VIEW_PROD_CONGRESOS_PCI" ("ID", "TITULO", "TIPO_EVENTO", "CARACTER", "TIPO_ACCESO", "AMBITO", "NOMBRE_CONGRESO", "ENTIDAD_ORGANIZADORA", "PAIS_CONGRESO", "REGION_CONGRESO", "CIUDAD_CONGRESO", "FECHA_CONGRESO", "ANO_CONGRESO", "IS_ACTA", "IS_PUB_EVALUADA", "TIPO_PUBLICACION", "TITULO_PUBLICACION", "NOMBRE_PUBLICACION", "VOLUMEN_PUB", "PAGINA_INICIO", "PAGINA_FIN", "EDITORIAL_PUB", "PAIS_PUB", "REGION_PUB", "FECHA_PUB", "WEB_PUB", "ISBN_PUB", "DEP_LEGAL_PUB", "FECHA_FIN", "OBJETIVOS", "DESTINATARIOS", "IDIOMA", "FECHA_PRESENTACION", "HORAS") AS
  select p.id,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 102) titulo,
  '008' as tipo_evento,
  to_number((select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 107)) caracter,
  '' tipo_acceso, --Tipo participación???
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 192) ambito,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 101) nombre_congreso,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 189) entidad_organizadora,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 188) pais_congreso,
  null region_congreso,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 103) ciudad_congreso,
  (select to_date(valor) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 105) fecha_congreso,
  (select to_number(to_char(to_date(valor), 'yyyy')) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 105) ano_congreso,
  substr((select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 110), 0, 1) is_acta,
  null is_pub_evaluada,
  (select l.isbn from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 104) tipo_publicacion,
  (select l.titulo from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 104) titulo_publicacion,
  '' nombre_publicacion,
  null volumen_pub,
  to_number((select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 194)) pagina_inicio,
  to_number((select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 195)) pagina_fin,
  (select e.nombre from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) join pci_editoriales e on e.id = l.editorial_id where d.produccion_id = p.id and atributo_tipo_id = 104) editorial_pub,
  (select pai.codigo_mec from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) join per_paises pai on pai.id = l.pais_publicacion_id where d.produccion_id = p.id and atributo_tipo_id = 104) pais_pub,
  (select l.lugar_publicacion from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 104) region_pub,
  nvl((select to_date(valor) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 191), (select l.fecha_publicacion from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 104)) fecha_pub,
  nvl((select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 108), (select l.doi from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 104)) web_pub,
  (select l.isbn from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 104) isbn_pub,
  (select l.deposito_legal from pci_producciones_detalle d join pci_libros l on l.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 104) dep_legal_pub,
  to_date(null) fecha_fin,
  '' objetivos,
  '' destinatarios,
  '' idioma,
  to_date(null) fecha_presentacion,
  null horas

from pci_producciones p
  join pci_producciones_detalle d on d.produccion_id = p.id
  join pci_producciones_estados e on e.produccion_id = p.id
where p.tipo_id = 4 and d.atributo_tipo_id = 104 and e.estado_id = 'J'
  and (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 110) = 'S'

UNION ALL

-- Publicados en actas, Revistas
select p.id,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 102) titulo,
  '008' as tipo_evento,
  to_number((select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 107)) caracter,
  '' tipo_acceso, --Tipo participación???
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 192) ambito,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 101) nombre_congreso,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 189) entidad_organizadora,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 188) pais_congreso,
  null region_congreso,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 103) ciudad_congreso,
  (select to_date(valor) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 105) fecha_congreso,
  (select to_number(to_char(to_date(valor), 'yyyy')) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 105) ano_congreso,
  substr((select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 110), 0, 1) is_acta,
  null is_pub_evaluada,
  (select r.issn from pci_producciones_detalle d join pci_revistas r on r.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 190) tipo_publicacion,
  (select r.nombre_extendido from pci_producciones_detalle d join pci_revistas r on r.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 190) titulo_publicacion,
  '' nombre_publicacion,
  null volumen_pub,
  to_number((select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 194)) pagina_inicio,
  to_number((select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 195)) pagina_fin,
  '' editorial_pub,
  (select pai.codigo_mec from pci_producciones_detalle d join pci_revistas r on r.id = to_number(d.valor) join per_paises pai on pai.id = r.pais_id where d.produccion_id = p.id and atributo_tipo_id = 190) pais_pub,
  (select r.lugar from pci_producciones_detalle d join pci_revistas r on r.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 190) region_pub,
  (select to_date(valor) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 191) fecha_pub,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 108) web_pub,
  (select r.issn from pci_producciones_detalle d join pci_revistas r on r.id = to_number(d.valor) where d.produccion_id = p.id and atributo_tipo_id = 190) isbn_pub,
  '' dep_legal_pub,
  to_date(null) fecha_fin,
  '' objetivos,
  '' destinatarios,
  '' idioma,
  to_date(null) fecha_presentacion,
  null horas

from pci_producciones p
  join pci_producciones_detalle d on d.produccion_id = p.id
  join pci_producciones_estados e on e.produccion_id = p.id
where p.tipo_id = 4 and d.atributo_tipo_id = 190 and e.estado_id = 'J'
  and d.valor not like '%-%'
  and (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 110) = 'S'

UNION ALL

-- No publicado en actas
select p.id,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 102) titulo,
  '008' as tipo_evento,
  to_number((select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 107)) caracter,
  '' tipo_acceso, --Tipo participación???
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 192) ambito,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 101) nombre_congreso,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 189) entidad_organizadora,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 188) pais_congreso,
  null region_congreso,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 103) ciudad_congreso,
  (select to_date(valor) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 105) fecha_congreso,
  (select to_number(to_char(to_date(valor), 'yyyy')) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 105) ano_congreso,
  substr((select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 110), 0, 1) is_acta,
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

from pci_producciones p
  join pci_producciones_detalle d on d.produccion_id = p.id
  join pci_producciones_estados e on e.produccion_id = p.id
where p.tipo_id = 4 and d.atributo_tipo_id = 110 and d.valor = 'N' and e.estado_id = 'J'

UNION ALL

-- Comités de congresos
select p.id,
  null titulo,
  '008' as tipo_evento,
  null caracter,
  '' tipo_acceso, --Tipo participación???
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 140) ambito,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 137) nombre_congreso,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 141) entidad_organizadora,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 217) pais_congreso,
  null region_congreso,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 138) ciudad_congreso,
  (select to_date(valor) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 139) fecha_congreso,
  (select to_number(to_char(to_date(valor), 'yyyy')) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 105) ano_congreso,
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

from pci_producciones p
  join pci_producciones_estados e on e.produccion_id = p.id
where p.tipo_id = 11 and e.estado_id = 'J';

