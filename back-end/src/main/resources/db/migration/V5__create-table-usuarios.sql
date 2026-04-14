CREATE TABLE usuarios(
    id serial PRIMARY KEY,
    login VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    roles VARCHAR(100) NOT NULL
);