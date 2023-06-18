INSERT INTO `mydb`.`Usuario` (`codigopucp`, `nombre`, `apellido`, `edad`, `correopucp`, `especialidad`, `contrasenia`)
VALUES
    ("20193470", "Massiel", "Arroyo", 20, "a20193470@pucp.edu.pe", "Ingeniería de Telecomunicaciones", sha2('Prueba1!',256));
    
insert into `mydb`.`usuario` (codigopucp, nombre,apellido,edad,correopucp,especialidad,contrasenia) 
values ('20198521','Arturo','Mendoza','20','a20198521@pucp.edu.pe','Mecatronica',sha2('Primera1234!', 256)),
	('20194251','Micaela','Benavides','21','a20194251@pucp.edu.pe','Ingeniería de te Telecomunicaciones',sha2('Segunda1234!', 256)),
	('20196215','Manuel','Yarleque','21','a20196215@pucp.edu.pe','Telecomunicaciones',sha2('Tercera1234!', 256));
    
delete from `mydb`.`usuario` where codigopucp = '20198521';
delete from `mydb`.`usuario` where codigopucp = '20194251';
delete from `mydb`.`usuario` where codigopucp = '20196215';

insert into `mydb`.`viajes` (idviajes, fecha_reserva,fecha_viaje,ciudad_origen,ciudad_destino,empresa_seguro,cantidad_boletos,usuario_codigopucp) 
values ('1','18-07-2023','18-10-2023','Lima','Buenos Aires','La Positiva','15','20198521'),
	('2','18-08-2023','18-11-2023','Madrid','Ciudad de Mexico','10','20198521','20194251'),
	('3','18-09-2023','18-12-2023','Alicante','Ottawa','Rimac','2','20196215');
    
use `mydb`;

update usuario set status='silver' where codigopucp='20196215';
update usuario set status='platinum' where codigopucp='20194251';
update usuario set status='gold' where codigopucp='20176320';

