package com.abila.Store.service;

import com.abila.Store.domain.Clientes;
import com.abila.Store.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@RequiredArgsConstructor

@Service
public class ClienteService {
    private final ClienteRepository clienteRepo;

    //consultar
    public Optional<Clientes> findById(@PathVariable Integer id){
        return clienteRepo.findById(id);
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
