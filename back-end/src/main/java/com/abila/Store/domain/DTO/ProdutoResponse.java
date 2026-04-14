package com.abila.Store.domain.DTO;

import com.abila.Store.domain.ItemVendas;
import com.abila.Store.domain.Produtos;

import java.math.BigDecimal;

public record ProdutoResponse(
        Integer id,
        String nome,
        String descricao,
        BigDecimal preco,
        Integer quantidade
) {
    public ProdutoResponse(Produtos produtos){
        this(
                produtos.getId(),
                produtos.getNome(),
                produtos.getDescricao(),
                produtos.getPreco(),
                produtos.getQuantidade()
        );
    }
}
