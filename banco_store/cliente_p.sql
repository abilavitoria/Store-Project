/*tabela clientes*/
CREATE TABLE comercial.clientes(
	id serial PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	email VARCHAR(100) UNIQUE,
	telefone VARCHAR(13),
	cpf VARCHAR(11) UNIQUE NOT NULL,
	cnpj VARCHAR(14) UNIQUE NOT NULL
);

/*tabela produtos*/
CREATE TABLE comercial.produtos(
	id serial PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	descricao TEXT,
	preco DECIMAL(10,2) NOT NULL,
	quantidade INT DEFAULT 0
);