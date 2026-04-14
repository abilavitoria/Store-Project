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
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/vendas")
public class VendasController {
    private final VendasService vendasService;

    @GetMapping("/{id}")
    public ResponseEntity<VendaResponse> findVendasById(@PathVariable Integer id){
        return ResponseEntity.ok(vendasService.findById(id));
    }

    @PostMapping
    public ResponseEntity<VendaResponse> save(@RequestBody @Valid VendaRequest vendas){
        return ResponseEntity.status(201).body(vendasService.saveVendas(vendas));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        vendasService.deleteVendas(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendaResponse> update(@RequestBody @Valid VendaRequest venda, @PathVariable Integer id){
        return ResponseEntity.ok(vendasService.updateVendas(venda, id));
    }

    @PostMapping("/{vendaId}/itens")
    public ResponseEntity<ItemVendaResponse> addItem(@PathVariable Integer vendaId, @RequestBody @Valid ItemVendaRequest item){
        return ResponseEntity.status(201).body(vendasService.addItemVendas(vendaId, item));
    }

    @PutMapping("/itens/{itemId}")
    public ResponseEntity<ItemVendaResponse> updateItem(@PathVariable Integer itemId, @RequestBody @Valid ItemVendaRequest item){
        return ResponseEntity.ok(vendasService.updateItemVendas(itemId, item));
    }

    @DeleteMapping("/itens/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer itemId){
        vendasService.removeItemVendas(itemId);
        return ResponseEntity.noContent().build();
    }
}
