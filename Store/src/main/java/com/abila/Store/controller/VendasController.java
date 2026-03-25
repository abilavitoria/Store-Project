package com.abila.Store.controller;

import com.abila.Store.Service.VendasService;
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
    

    //adicionar itens
    //remover itens
    //editar itens
}
