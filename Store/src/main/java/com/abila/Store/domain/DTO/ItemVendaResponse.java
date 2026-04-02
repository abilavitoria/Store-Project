package com.abila.Store.domain.DTO;

import com.abila.Store.domain.ItemVendas;

import java.math.BigDecimal;

public record ItemVendaResponse(
         Integer id,
         String nome,
         BigDecimal precoUnitario,
         Integer quantidade,
         BigDecimal subTotal
) {
    public ItemVendaResponse(ItemVendas itemVendas){
        this(
                itemVendas.getId(),
                itemVendas.getNome(),
                itemVendas.getPrecoUnitario(),
                itemVendas.getQuantidade(),
                itemVendas.getSubtotal()
        );
    }
}
