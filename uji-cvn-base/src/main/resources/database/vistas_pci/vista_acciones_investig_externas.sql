CREATE OR REPLACE FORCE EDITIONABLE VIEW "UJI_CVN"."CVN_VIEW_PROY_EXT_PCI" ("ID", "CODIGO_EXTERNO", "TITULO", "FECHA_INICIO", "FECHA_FIN", "DURACION", "NINVESTIGADORES", "NINVESTIGADORESEXT", "TITULO_KEYWORDS", "MODALIDAD", "TIPO", "SUBTIPO", "MICROTIPO", "AMBITO", "ENTIDAD_EJECUTORA", "NPERSONASANYO", "NOMBRE_PROGRAMA_FINANCIACION", "CODIGO_PROYECTO_FINANCIADORA", "DOTACION_TOTAL", "DOTACION_SUBPROYECTO", "PORCENTAJE_SUBVENCION", "PORCENTAJE_CREDITO", "PORCENTAJE_MIXTO", "RESULTADOS_RELEVANTES", "RESULTADOS_KEYWORDS", "NOMBRE_ENT_FINANCIADORA") AS
  select p.id,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 119) codigo_externo,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 118) titulo,
  (select to_date(valor) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 120) fecha_inicio,
  (select to_date(valor) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 123) fecha_fin,
  (select to_date(valor) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 123) - (select to_date(valor) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 120) duracion,
  0 ninvestigadores,
  0 ninvestigadoresext,
  NULL titulo_keywords,
  NULL modalidad,
  (select tipo from pci_ext_tipos_anexos where id = (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 124)) tipo,
  (select subtipo from pci_ext_tipos_anexos where id = (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 124)) subtipo,
  (select microtipo from pci_ext_tipos_anexos where id = (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 124)) microtipo,
  (select a.nombre from pci_ext_tipos_anexos a join pci_producciones_detalle d on d.valor = a.id where d.produccion_id = p.id and atributo_tipo_id = 124) ambito,
  nvl((select o.nombre from pci_ext_organismos o join pci_producciones_detalle d on d.valor = o.id where d.produccion_id = p.id and atributo_tipo_id = 243), (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 206)) entidad_ejecutora,
  NULL npersonasanyo,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 121) nombre_programa_financiacion,
  (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 119) codigo_proyecto_financiadora,
  (select to_number(valor) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 209) dotacion_total,
  NULL dotacion_subproyecto,
  NULL porcentaje_subvencion,
  NULL porcentaje_credito,
  NULL porcentaje_mixto,
  NULL resultados_relevantes,
  NULL   resultados_keywords,
  nvl((select o.nombre from pci_ext_organismos o join pci_producciones_detalle d on d.valor = o.id where d.produccion_id = p.id and atributo_tipo_id = 242), (select valor from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 122)) nombre_ent_financiadora

from pci_producciones p
  join pci_producciones_estados e on e.produccion_id = p.id
  join pci_producciones_detalle d on d.produccion_id = p.id
where tipo_id = 6 and e.estado_id = 'J'
  and d.atributo_tipo_id = 124 and regexp_like(d.valor, '^(1C[^T])|(2P.)|(2AP)$');