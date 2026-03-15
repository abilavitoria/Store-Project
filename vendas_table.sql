
/*tabela vendas*/
CREATE TABLE vendas.vendas(
	id serial PRIMARY KEY,/*serial autoincr*/
	cliente_id INT NOT NULL,
	produto_id INT NOT NULL,
	descricao_venda TEXT,
	preco_total DECIMAL(10,2) NOT NULL,
	data_venda TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

	/*criando os relacionamentos*/
	CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES comercial.clientes(id),
	CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES comercial.produtos(id)
);