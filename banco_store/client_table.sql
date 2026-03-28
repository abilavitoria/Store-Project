/*tabela clientes*/
CREATE TABLE comercial.clientes(
	id serial PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	email VARCHAR(100) UNIQUE,
	telefone VARCHAR(13)
);

/