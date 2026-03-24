package com.abila.Store.controller;

<<<<<<< Updated upstream
import com.abila.Store.domain.Clientes;
=======
import com.abila.Store.Service.ClienteService;
import com.abila.Store.domain.Clientes;
import com.abila.Store.repository.ClienteRepository;
>>>>>>> Stashed changes
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor

@RestController
@RequestMapping("/clientes")
public class ClientesController {
<<<<<<< Updated upstream
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
=======
    private final ClienteService clienteService;
    //consultar
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Clientes>> consultaClientes(@PathVariable Integer id){
        return ResponseEntity.ok(clienteService.findById(id));
    }

    //cadastrar
    @PostMapping
    public ResponseEntity<Clientes> salvarClientes(@RequestBody Clientes clientes){
        Clientes savedClientes = clienteService.saveClientes(clientes);
        return ResponseEntity.status(201).body(savedClientes);
    }

    //excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Integer id){
        clienteService.deleteClientes(id);
        return ResponseEntity.status(204).build();
    }

    //editar
    @PutMapping("/{id}")
    public ResponseEntity<Clientes> updateClientes(@RequestBody Clientes clientes, @PathVariable Integer id){
        clientes.setId(id);
        return ResponseEntity.ok(clienteService.saveClientes(clientes));
>>>>>>> Stashed changes
    }
}
