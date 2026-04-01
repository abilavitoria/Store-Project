package com.abila.Store.domain.DTO;

import com.abila.Store.domain.ItemVendas;

import java.math.BigDecimal;

public record ItemVendaResponseDTO(
         Integer id,
         String nome,
         BigDecimal precoUnitario,
         Integer quantidade
) {
    public ItemVendaResponseDTO(ItemVendas item){
        this(
                item.getId(),
                item.getNome(),
                item.getPrecoUnitario(),
                item.getQuantidade()
        );
    }
}
