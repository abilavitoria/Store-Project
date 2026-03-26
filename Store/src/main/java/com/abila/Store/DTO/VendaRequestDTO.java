package com.abila.Store.DTO;

import java.util.List;

public record VendaRequestDTO(Integer clienteId, List<ItemVendaDTO> itens) {
}
