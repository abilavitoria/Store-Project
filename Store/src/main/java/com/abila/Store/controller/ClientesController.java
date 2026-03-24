package com.abila.Store.controller;

import com.abila.Store.domain.Clientes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    private final com.abila.Store.Service.ClienteService clienteService;
    //consultar
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Clientes>> findClienteById(@PathVariable Integer id){
        return ResponseEntity.ok(clienteService.findById(id));
    }
    //salvar
    @PostMapping
    public ResponseEntity<Clientes> save(@RequestBody Clientes clientes){
        return ResponseEntity.status(201).build();
    }
    //excluir
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        clienteService.deleteClientes(id);
    }
    //editar
    @PutMapping
    public ResponseEntity<Clientes> update(@PathVariable Integer id, @RequestBody Clientes clientes) {
        clientes.setId(id);
        return ResponseEntity.ok(clienteService.updateClientes(clientes, id));
    }
}
