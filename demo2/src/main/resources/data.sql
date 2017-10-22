INSERT INTO roles(strrol) values ('ROLE_ADMIN');
INSERT INTO roles(strrol) values ('ROLE_USER');
INSERT INTO usuarios(strlogin, strpassword, strnombre, strapellido1, strapellido2, email) values ('padepa', '$2a$10$jLGgJxWXa9QChKVhso3TNOizqJnHxRTY5BMlWDWIqkE6AxUo1ODb.', 'Paco', 'De Lucia', 'Pasante', 'paco@gmail.com');
INSERT INTO usuarios(strlogin, strpassword, strnombre, strapellido1, strapellido2, email) values ('midesa', '$2a$10$lY.jnFHewp90LNM9JrVb6Oe.rmcvagPE4VMMRXWLwaSv1YcZneAPC', 'Miguel', 'De Cervantes', 'Saavedra', 'miguel@gmail.com');
INSERT INTO usuarios(strlogin, strpassword, strnombre, strapellido1, strapellido2, email) values ('manebo', '$2a$10$GsdfkO/7STgK/81aM5rI.OZyxsTHvF5XR2tOg73o7rKwFN9Kwnhxm', 'Manolo', 'Santana', 'Chicote', 'manolo@gmail.com');
INSERT INTO roles_usuarios(idusu, idrolusu) values (1, 1);
INSERT INTO roles_usuarios(idusu, idrolusu) values (1, 2);
INSERT INTO roles_usuarios(idusu, idrolusu) values (2, 2);
INSERT INTO roles_usuarios(idusu, idrolusu) values (3, 2);