CREATE TABLE clientes(
    id serial PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefone VARCHAR(13),
    cpf VARCHAR(11) NOT NULL,
    cnpj VARCHAR(14) NOT NULL
);

CREATE TABLE produtos(
    id serial PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(100),
    preco DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    quantidade INTEGER NOT NULL
);