package com.abila.Store.domain.DTO;

import com.abila.Store.domain.ItemVendas;
import com.abila.Store.domain.Produtos;
import com.abila.Store.domain.Vendas;

import java.math.BigDecimal;

public record ItemVendaResponse(
         Integer id,
         String nome,
         BigDecimal precoUnitario,
         Integer quantidade,
         BigDecimal subtotal
) {
    public ItemVendaResponse(ItemVendas itemVendas){
        this(   itemVendas.getId(),
                itemVendas.getNome(),
                itemVendas.getPrecoUnitario(),
                itemVendas.getQuantidade(),
                itemVendas.getSubtotal()
        );
    }
}
