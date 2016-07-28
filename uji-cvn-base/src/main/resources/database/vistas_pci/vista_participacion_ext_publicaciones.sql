CREATE OR REPLACE FORCE EDITIONABLE VIEW "UJI_CVN"."CVN_VIEW_PER_EXT_PART_PUBL_PCI" ("PERSONA", "PRODUCCION", "CALIDAD_PARTICIPACION", "CALIDAD_PARTICIPACION_STR", "CARACTER", "ORDEN", "POS_SOBRE_TOTAL", "RESULTADOS_DESTACADOS", "IS_RELEVANTE_PUB") AS

-- Artículos
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '070' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 90) caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub

from pci_producciones p
    join pci_producciones_autores a on a.produccion_id = p.id
    join pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 1 and e.estado_id = 'J' and a.persona_id is null

UNION ALL

-- Libros
select trim(a.nombre||' '||a.apellidos) persona,
   p.id produccion,
  '120' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 95) caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub
   
from pci_producciones p
    join pci_producciones_autores a on a.produccion_id = p.id
    join pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 2 and e.estado_id = 'J' and a.persona_id is null
  and (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 177) = '271' -- Es autor
  and (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 95) not in ('279', '280') -- No es obra de arte
  
UNION ALL
  
--Capítulos de libro
select trim(a.nombre||' '||a.apellidos) persona,
   p.id produccion,
  '090' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 99) caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub

from pci_producciones p
    join pci_producciones_autores a on a.produccion_id = p.id
    join pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 3 and e.estado_id = 'J' and a.persona_id is null
  and (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 99) not in ('279', '280') -- No es obra de arte
  
UNION ALL
  
-- Obras de arte
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '140' calidad_participacion,
  '' calidad_participacion_str,
  nvl((select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 95), (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 99)) caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub

from pci_producciones p
    join pci_producciones_autores a on a.produccion_id = p.id
    join pci_producciones_estados e on e.produccion_id = p.id
where tipo_id in (2, 3) and e.estado_id = 'J' and a.persona_id is null
  and (((select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 177) = '271' -- Es autor
    and (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 95) in ('279', '280')) 
    or (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 99) in ('279', '280'))
    
UNION ALL

-- Editor libros
select trim(a.nombre||' '||a.apellidos) persona,
   p.id produccion,
  '390' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 95) caracter,
  a.orden orden,
  null pos_sobre_total,
  '' resultados_destacados,
  null is_relevante_pub
   
from pci_producciones p
    join pci_producciones_autores a on a.produccion_id = p.id
    join pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 2 and e.estado_id = 'J' and a.persona_id is null
  and (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 177) = '272'; -- Es editor
  
  
-- Edición de monográficos??? Y material docente???