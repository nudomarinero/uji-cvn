drop table pci_revistas_cuartiles;
drop table pci_revistas_anualidades;
drop table pci_revistas;
drop table pci_editoriales;
drop table pci_libros;

create table pci_revistas as
  select * from uji_investigacion.pci_revistas@alma;

create table pci_revistas_anualidades as
  select * from uji_investigacion.pci_revistas_anualidades@alma;

create table pci_revistas_cuartiles as
  select * from uji_investigacion.pci_revistas_cuartiles@alma;

create table pci_editoriales as
  select * from uji_investigacion.pci_editoriales@alma;

create table pci_libros as
  select * from uji_investigacion.pci_libros@alma;

drop table pci_producciones_autores;
drop table pci_producciones_detalle;
drop table pci_producciones_estados;
drop table pci_producciones;

create table pci_producciones as
  select * from uji_investigacion.pci_producciones@alma;

create table pci_producciones_autores as
  select * from uji_investigacion.pci_producciones_autores@alma;

create table pci_producciones_detalle as
  select * from uji_investigacion.pci_producciones_detalle@alma;

create table pci_producciones_estados as
  select * from uji_investigacion.pci_producciones_estados@alma;

drop table pci_producciones_01;
drop table pci_producciones_02;
drop table pci_producciones_03;
drop table pci_producciones_04;
drop table pci_producciones_05;
drop table pci_producciones_06;
drop table pci_producciones_07;
drop table pci_producciones_09;
drop table pci_producciones_10;
drop table pci_producciones_11;
drop table pci_producciones_12;
drop table pci_producciones_13;
drop table pci_producciones_14;
drop table pci_producciones_15;
drop table pci_producciones_16;
drop table pci_producciones_17;
drop table pci_producciones_18;

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


drop table cvn_view_direccion_tesis_pci;

create table cvn_view_direccion_tesis_pci as
  select * from cvn_view_direccion_tesis_pci@alma;


