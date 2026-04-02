package com.abila.Store.domain.DTO;

import com.abila.Store.domain.Vendas;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VendaResponse (
        Integer id,
        String descricao,
        BigDecimal precoTotal,
        LocalDateTime data
){
    public VendaResponse(Vendas vendas){
        this(
                vendas.getId(),
                vendas.getDescricao(),
                vendas.getPrecoTotal(),
                vendas.getData()
        );
    }
}
