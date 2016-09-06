CREATE OR REPLACE FORCE EDITIONABLE VIEW "UJI_CVN"."CVN_VIEW_PER_PART_PROY_EXT_PCI" ("PERSONA", "PROYECTO", "CALIDAD_PARTICIPACION", "RESPONSABLE", "TIPO_PARTICIPACION", "APORTACIONES", "DEDICACION", "CORRESPONDING_AUTHOR") AS

select a.persona_id persona,
  p.id proyecto,
  (CASE a.orden WHEN 0 THEN 'S' ELSE 'N' END) calidad_participacion,
  (CASE a.orden WHEN 0 THEN 'S' ELSE 'N' END) responsable,
  '' tipo_participacion,
  '' aportaciones,
  NULL dedicacion,
  decode(a.role_destacado,1,1,0) corresponding_author

from pci_producciones p
    join pci_producciones_autores a on a.produccion_id = p.id
    join pci_producciones_estados e on e.produccion_id = p.id
where tipo_id = 6 and e.estado_id = 'J' and a.persona_id is not null
  and regexp_like((select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 124), '^(1C[^T])|(2P.)|(2AP)$');