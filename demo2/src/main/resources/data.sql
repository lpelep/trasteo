INSERT INTO roles(strrol) values ('ROL_ADMIN');
INSERT INTO roles(strrol) values ('ROL_USER');
INSERT INTO usuarios(strlogin, strpassword, strnombre, strapellido1, strapellido2, email) values ('padepa', 'pacopassword', 'Paco', 'De Lucia', 'Pasante', 'paco@gmail.com');
INSERT INTO usuarios(strlogin, strpassword, strnombre, strapellido1, strapellido2, email) values ('midesa', 'miguelpassword', 'Miguel', 'De Cervantes', 'Saavedra', 'miguel@gmail.com');
INSERT INTO usuarios(strlogin, strpassword, strnombre, strapellido1, strapellido2, email) values ('manebo', 'manolopassword', 'Manolo', 'Santana', 'Chicote', 'manolo@gmail.com');
INSERT INTO roles_usuarios(idusu, idrolusu) values (1, 1);
INSERT INTO roles_usuarios(idusu, idrolusu) values (1, 2);
INSERT INTO roles_usuarios(idusu, idrolusu) values (2, 2);
INSERT INTO roles_usuarios(idusu, idrolusu) values (3, 2);