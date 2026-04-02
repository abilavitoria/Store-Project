package com.abila.Store.controller;

import com.abila.Store.domain.DTO.ItemVendaRequest;
import com.abila.Store.domain.DTO.ItemVendaResponse;
import com.abila.Store.domain.DTO.VendaRequest;
import com.abila.Store.domain.DTO.VendaResponse;
import com.abila.Store.domain.ItemVendas;
import com.abila.Store.service.VendasService;
import com.abila.Store.domain.Vendas;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vendas")
public class VendasController {
    private final VendasService vendasService;

    //                  METODOS DE VENDA
    //consultar
    @GetMapping("/{id}")
    public ResponseEntity<VendaResponse> findVendasById(@PathVariable Integer id){
        return ResponseEntity.ok(vendasService.findById(id));
    }
    //salvar
    @PostMapping
    public ResponseEntity<VendaResponse> save(@RequestBody @Valid VendaRequest request){
        return ResponseEntity.status(201).body(vendasService.saveVendas(request));
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
    public ResponseEntity<ItemVendaResponse> addItem(@PathVariable Integer vendaId, @RequestBody @Valid ItemVendaRequest request){
        return ResponseEntity.status(201).body(vendasService.addItemVendas(vendaId, request));
    }
    //editar itens
    @PutMapping("/itens/{itemId}")
    public ResponseEntity<ItemVendas> updateItem(@Valid @PathVariable Integer itemId, @RequestBody ItemVendas item){
        return ResponseEntity.ok(vendasService.updateItemVendas(itemId, item));
    }
    //delete itens
    @DeleteMapping("/itens/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer id){
        vendasService.deleteVendas(id);
        return ResponseEntity.noContent().build();
    }
}
