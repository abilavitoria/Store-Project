package com.abila.Store.controller;

import com.abila.Store.domain.DTO.ItemVendaRequestDTO;
import com.abila.Store.domain.DTO.ItemVendaResponseDTO;
import com.abila.Store.domain.ItemVendas;
import com.abila.Store.service.VendasService;
import com.abila.Store.domain.Vendas;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vendas")
public class VendasController {
    private final VendasService vendasService;

    //                  METODOS DE VENDA
    //consultar
    @GetMapping("/{id}")
    public ResponseEntity<Vendas> findVendasById(@PathVariable Integer id){
        return vendasService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //salvar
    @PostMapping
    public ResponseEntity<Vendas> save(@RequestBody Vendas vendas){
        Vendas novaVenda = vendasService.saveVendas(vendas);
        return ResponseEntity.status(201).body(novaVenda);
    }
    //excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        vendasService.deleteVendas(id);
        return ResponseEntity.noContent().build();
    }
    //editar
    @PutMapping("/{id}")
    public ResponseEntity<Vendas> update(@RequestBody Vendas vendas, @PathVariable Integer id){
        return ResponseEntity.ok(vendasService.updateVendas(vendas, id));
    }

    //                  METODOS DE ITEM
    //adicionar itens
    @PostMapping("/{vendaId}/itens")
    public ResponseEntity<ItemVendaResponseDTO> addItem(
            @PathVariable Integer vendaId,
            @Valid @RequestBody ItemVendaRequestDTO item
    ){
        ItemVendaResponseDTO novoItem = vendasService.addItemVendas(vendaId, item);
        return ResponseEntity.status(201).body(novoItem);
    }
    //editar itens
    @PutMapping("/itens/{itemId}")
    public ResponseEntity<ItemVendas> updateItem(@Valid @PathVariable Integer itemId, @RequestBody ItemVendas item){
        return ResponseEntity.ok(vendasService.updateItemVendas(itemId, item));
    }
    //delete itens
    @DeleteMapping("/itens/{itemId}")
    public ResponseEntity<Void> deleteItem(@Valid @PathVariable Integer itemId){
        vendasService.removeItemVendas(itemId);
        return ResponseEntity.noContent().build();
    }
}
