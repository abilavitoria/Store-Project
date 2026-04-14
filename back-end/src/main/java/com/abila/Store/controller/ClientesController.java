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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/clientes")
public class ClientesController {
    private final ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> findClienteById(@PathVariable Integer id){
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> save(@RequestBody @Valid ClienteRequest clientes){
        return ResponseEntity.status(201).body(clienteService.saveClientes(clientes));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clienteService.deleteClientes(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> update(@PathVariable Integer id, @RequestBody @Valid ClienteRequest clientes) {
        return ResponseEntity.ok(clienteService.updateClientes(clientes, id));
    }
}
