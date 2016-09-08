create table CVN_VIEW_DIRECCION_TESIS_PCI as
  select * from CVN_VIEW_DIRECCION_TESIS
  where id not like 'PC%'

union

select 'PC*'||p.id||'*'||a.orden id,
  0 tesis_propia,
  '067' tipo_id,
  'Tesis' tipo_txt,
  a.persona_id persona,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 125) titulo,
  null codirector_nombre,
  null codirector_apellido1,
  null codirector_apellido2,
  null director_nombre,
  null director_apellido1,
  null director_apellido2,
  (select wm_concat(trim(nombre||' '||apellidos))
                                                               from pci_producciones_autores a
                                                              where a.produccion_id = p.id and a.persona_id is null) otrodirector_nombre,
  null otrodirector_apellido1,
  null otrodirector_apellido2,
  (select pai.codigo_mec from pci_producciones_detalle d join pci_revistas r on r.id = to_number(d.valor) join per_paises pai on pai.id = r.pais_id where d.produccion_id = p.id and atributo_tipo_id = 229) pais,
  null region,
  null ciudad,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 126) entidad,
  null tipo_entidad,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 223) alumno_nombre,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 225) alumno_apellido1,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 226) alumno_apellido2,
  (select to_date(valor) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 128) f_apro_tesis,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 127) calificacion,
  nvl((select 'S' from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 228 and to_number(trim(valor)) = 293), 'N') doctor_europeo,
  decode(nvl((select 'S' from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 228 and to_number(trim(valor)) = 293), 'N'), 'S', (select to_date(valor) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 128), null) f_doctor_europeo,
  null programa,
  null entidad_dea,
  to_date(null) f_obtencion_dea,
  null mencion_calidad,
  to_date(null) f_mencion_calidad,
  null premio_extraordinario,
  to_date(null) f_premio_extraordinario,
  nvl((select 'S' from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 228 and to_number(trim(valor)) = 294), null) doctor_internacional

from pci_producciones p,
  pci_producciones_estados e,
  pci_producciones_autores a,
  (select a.nombre, a.apellidos, null, a.produccion_id, a.orden from pci_producciones_autores a where a.persona_id is not null and a.orden = (select max(orden) from pci_producciones_autores a2 where a2.produccion_id = a.produccion_id and a2.persona_id is not null)) codirector

where tipo_id = 7
  and e.produccion_id = p.id
  and a.produccion_id = p.id
  and e.estado_id = 'J'
  and a.persona_id is not null
  and a.orden = (select min(orden)
                        from pci_producciones_autores a2
                       where a2.produccion_id = a.produccion_id and a2.persona_id is not null)
  and p.id = codirector.produccion_id(+);