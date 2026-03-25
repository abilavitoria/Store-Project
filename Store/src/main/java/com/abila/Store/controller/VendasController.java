package com.abila.Store.controller;

import com.abila.Store.service.VendasService;
import com.abila.Store.domain.Vendas;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor

@RestController
@RequestMapping("/vendas")
public class VendasController {
    private final VendasService vendasService;
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
    @DeleteMapping("/{}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        vendasService.deleteVendas(id);
        return ResponseEntity.noContent().build();
    }
    //editar
    public ResponseEntity<Vendas> update(@RequestBody Vendas vendas, @PathVariable Integer id){
        return ResponseEntity.ok(vendasService.updateVendas(vendas, id));
    }

    //adicionar itens
    //remover itens
    //editar itens
}
