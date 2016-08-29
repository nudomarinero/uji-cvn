CREATE OR REPLACE FORCE EDITIONABLE VIEW "UJI_CVN"."CVN_VIEW_DIRECCION_TESIS_PCI" ("ID", "TESIS_PROPIA", "TIPO_ID", "TIPO_TXT", "PERSONA", "TITULO", "CODIRECTOR_NOMBRE", "CODIRECTOR_APELLIDO1", "CODIRECTOR_APELLIDO2", "DIRECTOR_NOMBRE", "DIRECTOR_APELLIDO1", "DIRECTOR_APELLIDO2", "OTRODIRECTOR_NOMBRE", "OTRODIRECTOR_APELLIDO1", "OTRODIRECTOR_APELLIDO2", "PAIS", "REGION", "CIUDAD", "ENTIDAD", "TIPO_ENTIDAD", "ALUMNO_NOMBRE", "ALUMNO_APELLIDO1", "ALUMNO_APELLIDO2", "FECHA_LECTURA", "CALIFICACION", "DOCTOR_EUROPEO", "F_DOCTOR_EUROPEO", "PROGRAMA", "ENTIDAD_DEA", "F_OBTENCION_DEA", "MENCION_CALIDAD", "F_MENCION_CALIDAD", "PREMIO_EXTRAORDINARIO", "F_PREMIO_EXTRAORDINARIO", "DOCTOR_INTERNACIONAL") AS
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
  (select listagg(trim(nombre||' '||apellidos), ',') within group (order by nombre)
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
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 128) f_apro_tesis,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 127) calificacion,
  nvl((select 'S' from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 228 and valor = 293), 'N') doctor_europeo,
  decode(nvl((select 'S' from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 228 and valor = 293), 'N'), 'S', (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 128), null) f_doctor_europeo,
  null programa,
  null entidad_dea, 
  null f_obtencion_dea, 
  null mencion_calidad, 
  null f_mencion_calidad, 
  null premio_extraordinario, 
  null f_premio_extraordinario, 
  nvl((select 'S' from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 228 and valor = 294), null) doctor_internacional

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