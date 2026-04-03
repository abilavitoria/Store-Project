package com.abila.Store.controller;

import com.abila.Store.domain.DTO.ClienteRequest;
import com.abila.Store.domain.DTO.ClienteResponse;
import com.abila.Store.service.ClienteService;
import com.abila.Store.domain.Clientes;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClientesController {
    private final ClienteService clienteService;
    //consultar
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> findClienteById(@PathVariable Integer id){
        return ResponseEntity.ok(clienteService.findById(id));
    }
    //salvar
    @PostMapping
    public ResponseEntity<ClienteResponse> save(@RequestBody @Valid ClienteRequest clientes){
        return ResponseEntity.status(201).body(clienteService.saveClientes(clientes));
    }
    //excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clienteService.deleteClientes(id);
        return ResponseEntity.noContent().build();
    }
    //editar
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> update(@PathVariable Integer id, @RequestBody @Valid ClienteRequest clientes) {
        return ResponseEntity.ok(clienteService.updateClientes(clientes, id));
    }
}
