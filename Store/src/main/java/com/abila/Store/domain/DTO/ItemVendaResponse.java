package com.abila.Store.domain.DTO;

import com.abila.Store.domain.ItemVendas;
import jakarta.persistence.Column;

public record ItemVendaResponse(
         Integer id,
         String nome,
         Float precoUnitario,
         Integer quantidade
) {
    public ItemVendaResponse(ItemVendas item){
        this(
                item.getId(),
                item.getNome(),
                item.getPrecoUnitario(),
                item.getQuantidade()
        );
    }
}
