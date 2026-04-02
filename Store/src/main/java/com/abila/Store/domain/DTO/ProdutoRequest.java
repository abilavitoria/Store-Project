package com.abila.Store.domain.DTO;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ProdutoRequest (
        @NotBlank String nome,
        String descricao,
        @NotBlank BigDecimal preco,
        @NotBlank Integer quantidade
){
}
