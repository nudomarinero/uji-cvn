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
from pci_producciones_18;


-- Primer select de congresos
select id,
  titulo,
  '008' as tipo_evento,
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
  doi web_pub,
  isbn isbn_pub,
  dep_legal_libro dep_legal_pub,
  to_date(null) fecha_fin,
  '' objetivos,
  '' destinatarios,
  '' idioma,
  to_date(null) fecha_presentacion,
  null horas
from pci_producciones_04
  where isbn is not null and publicado_actas = 'S';