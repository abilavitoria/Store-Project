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
    private final ClientesController clientesController;
    //consultar
    @GetMapping
    public ResponseEntity<Clientes> findClienteById(@PathVariable Integer id){
        Clientes 
        return ResponseEntity.status(201).body()
    }
    //salvar
    public Clientes save(@RequestBody Clientes clientes){
        return clienteRepo.save(clientes);
    }
    //excluir
    public void delete(@PathVariable Integer id){
        clienteRepo.deleteById(id);
    }
    //editar
    public Clientes update(@PathVariable Integer id, @RequestBody Clientes clientes){
        clientes.setId(id);
        return clienteRepo.save(clientes);
    }
}
