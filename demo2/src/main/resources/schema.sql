drop table usuarios if exists;
drop table roles if exists;
drop table roles_usuarios if exists;

create table usuarios(
	idusuario serial, 
	strlogin varchar(255), 
	strpassword varchar(255), 
	strnombre varchar(255), 
	strapellido1 varchar(255), 
	strapellido2 varchar(255), 
	email varchar(255)
);

create table roles(
	idrol serial, 
	strrol varchar(255)
);

create table roles_usuarios(
	idusu integer, 
	idrolusu integer
);
