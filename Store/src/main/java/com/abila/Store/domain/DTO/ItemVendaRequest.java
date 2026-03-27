package com.abila.Store.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record ItemVendaRequest(
        @NotBlank String nome,
        @NotNull @Positive Float precoUnitario,
        @NotNull @Positive Integer quantidade
) { }
