package com.abila.Store.domain.DTO;

import com.abila.Store.domain.Clientes;
import com.abila.Store.domain.ItemVendas;
import com.abila.Store.domain.Vendas;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

public record VendaResponse(
        Integer id,
        String descricao,
        BigDecimal precoTotal,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime data,
        String nomeCliente,
        List<ItemVendaResponse> itens
){
    public VendaResponse(Vendas vendas){
        this(
                vendas.getId(),
                vendas.getDescricao(),
                vendas.getPrecoTotal(),
                vendas.getData(),
                vendas.getCliente().getNome(),
                vendas.getItens().stream()
                        .map(ItemVendaResponse::new)
                        .toList()
        );
    }

}
