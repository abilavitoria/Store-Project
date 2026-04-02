package com.abila.Store.domain.DTO;

import com.abila.Store.domain.Clientes;
import com.abila.Store.domain.ItemVendas;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VendaRequest(
        String descricao,
        @NotBlank BigDecimal precoTotal,
        @NotBlank LocalDateTime data,
        Clientes clientes,
        ItemVendas itens
) {
}
