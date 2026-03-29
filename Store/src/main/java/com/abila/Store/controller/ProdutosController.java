package com.abila.Store.controller;

import com.abila.Store.service.ProdutoService;
import com.abila.Store.domain.Produtos;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutosController {
    private final ProdutoService produtoService;

    //consultar
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Produtos> findProdutosById(@PathVariable Integer id){
        return produtoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //cadastrar
    @PostMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Produtos> save(@RequestBody Produtos produtos){
        Produtos novoProduto = produtoService.saveProdutos(produtos);
        return ResponseEntity.status(201).body(novoProduto);
    }
    //excluir
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        produtoService.deleteProdutos(id);
        return ResponseEntity.noContent().build();
    }

    //editar
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Produtos> update(@PathVariable Integer id, @RequestBody Produtos produtos){
        return ResponseEntity.ok(produtoService.updateProdutos(id, produtos));
    }
}
