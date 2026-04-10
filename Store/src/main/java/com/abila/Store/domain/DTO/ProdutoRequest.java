package com.abila.Store.domain.DTO;

import com.abila.Store.domain.ItemVendas;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequest (
        @NotBlank String nome,
        String descricao,
        @NotNull @DecimalMin("0.01") BigDecimal preco,
        @NotNull Integer quantidade,
        ItemVendas itens
){
}
