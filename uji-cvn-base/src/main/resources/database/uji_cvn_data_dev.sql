-- CVN_EXT_PERSONAS

delete from cvn_ext_domicilios;
delete from cvn_ext_personas;

insert into cvn_ext_personas (id, nombre, apellido1, apellido2, identificacion, tipo_identificacion, pais_nacimiento, telefono_fijo, email)
	select distinct persona, concat('Persona', persona), 'Prueba1', 'Prueba2', '12345678X', 'DNI', 'España', '123456789', 'persona@uji.es'
		from cvn_view_per_part_proy
	union
	select distinct persona, concat('Persona', persona), 'Prueba1', 'Prueba2', '12345678X', 'DNI', 'España', '123456789', 'persona@uji.es'
    	from cvn_view_per_participa_grupo
   	union
   	select distinct persona, concat('Persona', persona), 'Prueba1', 'Prueba2', '12345678X', 'DNI', 'España', '123456789', 'persona@uji.es'
    	from cvn_view_per_part_prod_publ;

commit;



-- CVN_EXT_DOMICILIOS

insert into cvn_ext_domicilios (id, per_id, ciudad_contacto, direccion_contacto, direccion_contacto_aux, codigo_postal, region_contacto, pais_contacto, provincia_contacto, orden_preferencia)
	select distinct persona, persona, 'Castellón', 'C/ Alguna 10', null, '12000', 'Comunidad Valenciana', 'España', 'Castellón', '10'
		from cvn_view_per_part_proy
	union
	select distinct persona, persona, 'Castellón', 'C/ Alguna 10', null, '12000', 'Comunidad Valenciana', 'España', 'Castellón', '10'
		from cvn_view_per_participa_grupo
	union
	select distinct persona, persona, 'Castellón', 'C/ Alguna 10', null, '12000', 'Comunidad Valenciana', 'España', 'Castellón', '10'
		from cvn_view_per_part_prod_publ;
    
commit;

    	

-- CVN_EXT_ENTIDADES
    	
delete from cvn_ext_entidades;

insert into cvn_ext_entidades (id, nombre, acronimo, tipo)
	select distinct entidad, concat('Entidad ', entidad), 'ENT', '000'
		from cvn_view_ent_financia_proy
	union
	select distinct entidad, concat('Entidad ', entidad), 'ENT', '000'
		from cvn_view_ent_participa_proy;
		
commit;