package com.abila.Store.domain.DTO;

import com.abila.Store.domain.Clientes;
import com.abila.Store.domain.ItemVendas;
import com.abila.Store.domain.Vendas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

public record VendaResponse(
        Integer id,
        String descricao,
        BigDecimal precoTotal,
        @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
        LocalDateTime data,
        Clientes clientes,
        List<ItemVendas> itens
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
