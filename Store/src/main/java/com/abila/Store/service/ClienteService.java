package com.abila.Store.service;
import com.abila.Store.domain.Clientes;
import com.abila.Store.domain.DTO.ClienteRequest;
import com.abila.Store.domain.DTO.ClienteResponse;
import com.abila.Store.domain.DTO.VendaResponse;
import com.abila.Store.domain.Vendas;
import com.abila.Store.repository.ClienteRepository;
import com.abila.Store.util.Utils;
import jdk.jshell.execution.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepo;

    //consultar
    public ClienteResponse findById(Integer id){
        Clientes clientes = clienteRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Cliente naõ encontrado"));

        return new ClienteResponse(
                clientes.getId(),
                clientes.getNome(),
                clientes.getEmail(),
                clientes.getTelefone(),
                clientes.getCpf(),
                clientes.getCnpj(),
                clientes.getVendas()
        );
    }

    //cadastrar
    public ClienteResponse saveClientes(ClienteRequest request){
        Clientes novoCliente = new Clientes();

        novoCliente.setVendas(request.vendas());
        novoCliente.setNome(request.nome());
        novoCliente.setEmail(request.email());
        novoCliente.setTelefone(request.telefone());
        novoCliente.setCpf(request.cpf());
        novoCliente.setCnpj(request.cnpj());

        Clientes clientes = clienteRepo.save(novoCliente);

        Utils.validarDocumentos(clientes);
        return new ClienteResponse(novoCliente);
    }

    //excluir
    public void deleteClientes(Integer id){
        if(!clienteRepo.existsById(id)){
            throw new RuntimeException("Cliente não encontrado");
        }
       clienteRepo.deleteById(id);
    }

    //editar
    public ClienteResponse updateClientes(ClienteRequest request, Integer id){
       return clienteRepo.findById(id)
               .map(clientesExistentes -> {
                   clientesExistentes.setVendas(request.vendas());

                   Clientes atualizado = clienteRepo.save(clientesExistentes);
                    return new ClienteResponse(
                            atualizado.getId(),
                            atualizado.getNome(),
                            atualizado.getEmail(),
                            atualizado.getTelefone(),
                            atualizado.getCpf(),
                            atualizado.getCnpj(),
                            atualizado.getVendas()
                    );
               })
               .orElseThrow(()-> new RuntimeException("Cliente com id" + id + "não encontrado"));
    }

}
