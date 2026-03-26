package com.abila.Store.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record ItemVendaDTO(
        Integer id,

        @NotBlank(message = "O nome é um item obrigatorio")
        String nome,

        @NotNull(message = "O preco nao pode ser vazio")
        @Positive(message = "o preco deve ser maior que zero")
        Float precoUnitario,

        @NotNull(message = "O campo quantidade e obrigatorio")
        @Positive(message = "Quantidade deve ser ao menos 1")
        Integer quantidade) {
}
