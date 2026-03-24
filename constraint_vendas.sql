/*tabela vendas*/
CREATE TABLE vendas.vendas(
	id serial PRIMARY KEY,/*serial autoincr*/
	cliente_id INT NOT NULL,
	produto_id INT NOT NULL,
	descricao_venda TEXT,
	preco_total DECIMAL(10,2) NOT NULL,
	data_venda TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	data_venda TIMESTAMP DEFAULT CURRENT_TIMESTAMP/*dd/mm/aaaa/h:m:s*/
);

ALTER TABLE vendas.vendas 
ADD CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES comercial.clientes(id),
ADD CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES comercial.produtos(id)