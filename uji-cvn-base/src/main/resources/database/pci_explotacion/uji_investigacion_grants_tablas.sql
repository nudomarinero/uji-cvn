grant select on pci_producciones to uji_cvn;

grant select on pci_producciones_detalle to uji_cvn;

grant select on pci_producciones_estados to uji_cvn;

grant select on pci_libros to uji_cvn;

grant select on pci_editoriales to uji_cvn;

grant select on pci_revistas to uji_cvn;

grant select on pci_producciones_autores to uji_cvn;

grant select on pci_ext_tipos_anexos to uji_cvn;

grant select on pci_ext_organismos to uji_cvn;

grant select on pci_revistas_anualidades to uji_cvn;

grant select on pci_revistas_cuartiles to uji_cvn;

grant select on pci_producciones_01 to uji_cvn;
grant select on pci_producciones_02 to uji_cvn;
grant select on pci_producciones_03 to uji_cvn;
grant select on pci_producciones_04 to uji_cvn;
grant select on pci_producciones_05 to uji_cvn;
grant select on pci_producciones_06 to uji_cvn;
grant select on pci_producciones_07 to uji_cvn;
grant select on pci_producciones_09 to uji_cvn;
grant select on pci_producciones_10 to uji_cvn;
grant select on pci_producciones_11 to uji_cvn;
grant select on pci_producciones_12 to uji_cvn;
grant select on pci_producciones_13 to uji_cvn;
grant select on pci_producciones_14 to uji_cvn;
grant select on pci_producciones_15 to uji_cvn;
grant select on pci_producciones_16 to uji_cvn;
grant select on pci_producciones_17 to uji_cvn;
grant select on pci_producciones_18 to uji_cvn;

create table pci_producciones_01 as
  select * from uji_investigacion.pci_producciones_01@alma;

create table pci_producciones_02 as
  select * from uji_investigacion.pci_producciones_02@alma;

create table pci_producciones_03 as
  select * from uji_investigacion.pci_producciones_03@alma;

create table pci_producciones_04 as
  select * from uji_investigacion.pci_producciones_04@alma;

create table pci_producciones_05 as
  select * from uji_investigacion.pci_producciones_05@alma;

create table pci_producciones_06 as
  select * from uji_investigacion.pci_producciones_06@alma;

create table pci_producciones_07 as
  select * from uji_investigacion.pci_producciones_07@alma;

create table pci_producciones_09 as
  select * from uji_investigacion.pci_producciones_09@alma;

create table pci_producciones_10 as
  select * from uji_investigacion.pci_producciones_10@alma;

create table pci_producciones_11 as
  select * from uji_investigacion.pci_producciones_11@alma;

create table pci_producciones_12 as
  select * from uji_investigacion.pci_producciones_12@alma;

create table pci_producciones_13 as
  select * from uji_investigacion.pci_producciones_13@alma;

create table pci_producciones_14 as
  select * from uji_investigacion.pci_producciones_14@alma;

create table pci_producciones_15 as
  select * from uji_investigacion.pci_producciones_15@alma;

create table pci_producciones_16 as
  select * from uji_investigacion.pci_producciones_16@alma;

create table pci_producciones_17 as
  select * from uji_investigacion.pci_producciones_17@alma;

create table pci_producciones_18 as
  select * from uji_investigacion.pci_producciones_18@alma;