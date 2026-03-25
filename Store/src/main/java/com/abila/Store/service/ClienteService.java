package com.abila.Store.service;
import com.abila.Store.domain.Clientes;
import com.abila.Store.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepo;

    //consultar
    public Optional<Clientes> findById(Integer id){
        return clienteRepo.findById(id);
    }

    //cadastrar
    public Clientes saveClientes(Clientes clientes){
        return clienteRepo.save(clientes);
    }

    //excluir
    public void deleteClientes(Integer id){
        if(!clienteRepo.existsById(id)){
            throw new RuntimeException("Cliente não encontrado");
        }
        else{clienteRepo.deleteById(id);}
    }

    //editar
    public Clientes updateClientes(Clientes clientes, Integer id){
       return clienteRepo.findById(id)
               .map(existingClientes -> {
                    clientes.setId(id);
                    return clienteRepo.save(clientes);
               })
               .orElseThrow(()-> new RuntimeException("Cliente com id" + id + "não encontrado"));
    }

}
