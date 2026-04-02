package com.abila.Store.domain.DTO;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;


public record ItemVendaRequest(
        @NotBlank String nome,
        @NotNull @DecimalMin("0.01") BigDecimal precoUnitario,
        @NotNull @Min(1) Integer quantidade
) { }
