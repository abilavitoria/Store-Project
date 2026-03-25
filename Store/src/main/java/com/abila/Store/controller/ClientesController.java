package com.abila.Store.controller;

import com.abila.Store.Service.ClienteService;
import com.abila.Store.domain.Clientes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor

@RestController
@RequestMapping("/clientes")
public class ClientesController {
    private final ClienteService clienteService;
    //consultar
    @GetMapping("/{id}")
    public ResponseEntity<Clientes> findClienteById(@PathVariable Integer id){
        return clienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //salvar
    @PostMapping
    public ResponseEntity<Clientes> save(@RequestBody Clientes clientes){
        Clientes novoCliente = clienteService.saveClientes(clientes);
        return ResponseEntity.status(201).body(novoCliente);
    }
    //excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clienteService.deleteClientes(id);
        return ResponseEntity.noContent().build();
    }
    //editar
    @PutMapping("/{id}")
    public ResponseEntity<Clientes> update(@PathVariable Integer id, @RequestBody Clientes clientes) {
        return ResponseEntity.ok(clienteService.updateClientes(clientes, id));
    }
}
