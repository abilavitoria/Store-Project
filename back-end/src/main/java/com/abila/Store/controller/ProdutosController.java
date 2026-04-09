package com.abila.Store.controller;

import com.abila.Store.domain.DTO.ProdutoRequest;
import com.abila.Store.domain.DTO.ProdutoResponse;
import com.abila.Store.service.ProdutoService;
import com.abila.Store.domain.Produtos;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutosController {
    private final ProdutoService produtoService;

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> findProdutosById(@PathVariable Integer id){
        return ResponseEntity.ok(produtoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> save(@RequestBody @Valid ProdutoRequest produtos){
        return ResponseEntity.status(201).body(produtoService.saveProdutos(produtos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        produtoService.deleteProdutos(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> update(@PathVariable Integer id, @RequestBody @Valid ProdutoRequest produtos){
        return ResponseEntity.ok(produtoService.updateProdutos(id, produtos));
    }
}
