CREATE TABLE vendas(
    id serial PRIMARY KEY,
    descricao VARCHAR(100),
    preco_total DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cliente_id INTEGER NOT NULL,

    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);