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

from pci_producciones p
  join pci_producciones_detalle d on p.id = d.produccion_id and
    (d.atributo_tipo_id = 92 or (atributo_tipo_id = 190 and regexp_like(valor, '^\d+$')))
  join pci_revistas r on r.id = to_number(d.valor)
  join pci_revistas_anualidades a on a.revista_id = r.id
  join pci_revistas_cuartiles c on c.anualidad_id = a.id

  where (a.anio_id = (select to_number(to_char(to_date(valor), 'YYYY')) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 175)
    or a.anio_id = (select to_number(to_char(to_date(valor), 'YYYY')) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 176)
    or a.anio_id = (select to_number(to_char(to_date(valor), 'YYYY')) from pci_producciones_detalle where produccion_id = p.id and atributo_tipo_id = 191));