package com.abila.Store.domain.DTO;

import com.abila.Store.domain.Clientes;
import com.abila.Store.domain.ItemVendas;
import com.abila.Store.domain.Vendas;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VendaResponse(
        Integer id,
        String descricao,
        BigDecimal precoTotal,
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
