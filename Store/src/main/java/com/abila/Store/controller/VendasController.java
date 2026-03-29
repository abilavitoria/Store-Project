package com.abila.Store.controller;

import com.abila.Store.domain.ItemVendas;
import com.abila.Store.service.VendasService;
import com.abila.Store.domain.Vendas;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor

@RequestMapping("/vendas")
public class VendasController {
    private final VendasService vendasService;

    //                  METODOS DE VENDA
    //consultar
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Vendas> findVendasById(@PathVariable Integer id){
        return vendasService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //salvar
    @PostMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Vendas> save(@RequestBody Vendas vendas){
        Vendas novaVenda = vendasService.saveVendas(vendas);
        return ResponseEntity.status(201).body(novaVenda);
    }
    //excluir
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        vendasService.deleteVendas(id);
        return ResponseEntity.noContent().build();
    }
    //editar
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Vendas> update(@RequestBody Vendas vendas, @PathVariable Integer id){
        return ResponseEntity.ok(vendasService.updateVendas(vendas, id));
    }

    //                  METODOS DE ITEM
    //adicionar itens
    @PostMapping("/{vendaId}/itens")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<ItemVendas> addItem(@PathVariable Integer vendaId, @RequestBody ItemVendas item){
        ItemVendas novoItem = vendasService.addItemVendas(vendaId, item);
        return ResponseEntity.status(201).body(novoItem);
    }
    //editar itens
    @PutMapping("/itens/{itemId}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<ItemVendas> updateItem(@PathVariable Integer itemId, @RequestBody ItemVendas item){
        return ResponseEntity.ok(vendasService.updateItemVendas(itemId, item));
    }
    //delete itens
    @DeleteMapping("/iten/{itemId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer itemId){
        vendasService.removeItemVendas(itemId);
        return ResponseEntity.noContent().build();
    }
}
