package com.abila.Store.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;


public record ItemVendaRequest(
        @NotBlank String nome,
        @NotNull @Positive BigDecimal precoUnitario,
        @NotNull @Positive Integer quantidade
) { }
