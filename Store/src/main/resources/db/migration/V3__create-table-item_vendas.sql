CREATE TABLE item_vendas(
    id serial PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    precoUnitario DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    quantidade INTEGER NOT NULL,
    venda_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,

    CONSTRAINT fk_venda FOREIGN KEY (venda_id) REFERENCES vendas(id) ON DELETE CASCADE,
    CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES produtos(id)
);