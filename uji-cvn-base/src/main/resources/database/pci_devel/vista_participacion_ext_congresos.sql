CREATE OR REPLACE FORCE EDITIONABLE VIEW "UJI_CVN"."CVN_VIEW_PER_PART_CONG_EX_PCI" ("PERSONA", "PRODUCCION", "TIPO_PARTICIPACION", "TIPO_PARTICIPACION_STR", "CARACTER", "ORDEN", "CORRESPONDING_AUTHOR") AS

-- Ponencia invitada
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '730' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 107) caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author

from pci_producciones p
    join pci_producciones_autores a on a.produccion_id = p.id
    join pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 4 and e.estado_id = 'J' and a.persona_id is null
  and (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 109) = '283'
  
UNION ALL
  
-- Ponencia libre  
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '960' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 107) caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author

from pci_producciones p
    join pci_producciones_autores a on a.produccion_id = p.id
    join pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 4 and e.estado_id = 'J' and a.persona_id is null
  and (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 109) != '283'
  
UNION ALL
  
-- Comité organizador  
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '980' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 2023366) caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author

from pci_producciones p
    join pci_producciones_autores a on a.produccion_id = p.id
    join pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 11 and e.estado_id = 'J' and a.persona_id is null
  and (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 218) = '289'
  
UNION ALL
  
-- Comité científico  
select trim(a.nombre||' '||a.apellidos) persona,
  p.id produccion,
  '990' calidad_participacion,
  '' calidad_participacion_str,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 2023366) caracter,
  a.orden orden,
  decode(a.role_destacado,1,1,0) corresponding_author

from pci_producciones p
    join pci_producciones_autores a on a.produccion_id = p.id
    join pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 11 and e.estado_id = 'J' and a.persona_id is null
  and (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 218) = '290'; 