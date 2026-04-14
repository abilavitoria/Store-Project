package com.abila.Store.domain.DTO;

import com.abila.Store.domain.Produtos;
import com.abila.Store.domain.Vendas;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;


public record ItemVendaRequest(
        @NotBlank String nome,
        @Positive BigDecimal precoUnitario,
        @Positive Integer quantidade
) { }
