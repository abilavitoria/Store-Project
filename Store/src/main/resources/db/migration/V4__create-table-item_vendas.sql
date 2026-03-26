CREATE TABLE vendas.item_vendas(
    id serial PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco FLOAT NOT NULL,
    quantidade INTEGER NOT NULL,
    venda_id INTEGER,

    CONSTRAINT fk_item_vendas_venda FOREIGN KEY (venda_id) REFERENCES vendas(id)
);