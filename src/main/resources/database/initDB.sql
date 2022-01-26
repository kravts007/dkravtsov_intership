CREATE TABLE IF NOT EXISTS clients
(
    id       INTEGER PRIMARY KEY,
    name     VARCHAR(200) NOT NULL,
    role     VARCHAR(50)  NOT NULL,
    phone    VARCHAR(50)  NOT NULL,
    email    VARCHAR(200) NOT NULL UNIQUE,
    login    VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(50)  NOT NULL
);
CREATE SEQUENCE clients_id_seq START WITH 3 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS roles
(
    id INTEGER PRIMARY KEY ,
    name VARCHAR(200) NOT NULL
);
CREATE SEQUENCE roles_id_seq START WITH 3 INCREMENT BY 1;

-- CREATE TABLE IF NOT EXISTS client_role
-- (
-- client_id INTEGER REFERENCES clients,
-- role_id INTEGER REFERENCES roles,
-- PRIMARY KEY (client_id , role_id)
-- );
-- DROP TABLE IF EXISTS clients;
-- DROP SEQUENCE IF EXISTS clients_id_seq
-- DROP TABLE IF EXISTS roles;
-- DROP SEQUENCE IF EXISTS roles_id_seq
