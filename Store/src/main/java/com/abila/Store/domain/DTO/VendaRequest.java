package com.abila.Store.domain.DTO;

import com.abila.Store.domain.Clientes;
import com.abila.Store.domain.ItemVendas;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record VendaRequest(
        String descricao,
        @NotBlank @Positive BigDecimal precoTotal,
        @NotBlank LocalDate data,
        Clientes clientes,
        List<ItemVendas> itens
) {
}
