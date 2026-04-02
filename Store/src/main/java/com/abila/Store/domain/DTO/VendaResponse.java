package com.abila.Store.domain.DTO;

import com.abila.Store.domain.Clientes;
import com.abila.Store.domain.ItemVendas;
import com.abila.Store.domain.Vendas;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record VendaResponse (
        Integer id,
        String descricao,
        BigDecimal precoTotal,
        LocalDateTime data,
        Clientes clientes,
        ItemVendas itens
){
    public VendaResponse(Vendas vendas){
        this(
                vendas.getId(),
                vendas.getDescricao(),
                vendas.getPrecoTotal(),
                vendas.getData(),
                vendas.getCliente(),
                vendas.getItens()
        );
    }

}
