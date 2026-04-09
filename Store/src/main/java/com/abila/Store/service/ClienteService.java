package com.abila.Store.service;
import com.abila.Store.domain.Clientes;
import com.abila.Store.domain.DTO.ClienteRequest;
import com.abila.Store.domain.DTO.ClienteResponse;
import com.abila.Store.repository.ClienteRepository;
import com.abila.Store.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepo;


    public ClienteResponse findById(Integer id){
        return clienteRepo.findById(id)
                .map(ClienteResponse::new)
                .orElseThrow(()-> new RuntimeException("Cliente não encontrado"));
    }


    public ClienteResponse saveClientes(ClienteRequest request){
        Clientes novoCliente = new Clientes();

        novoCliente.setVendas(request.vendas());
        novoCliente.setNome(request.nome());
        novoCliente.setEmail(request.email());
        novoCliente.setTelefone(request.telefone());
        novoCliente.setCpf(request.cpf());
        novoCliente.setCnpj(request.cnpj());

        Utils.validarDocumentos(novoCliente);

        Clientes salvo = clienteRepo.save(novoCliente);
        return new ClienteResponse(salvo);
    }


    public void deleteClientes(Integer id){
        if(!clienteRepo.existsById(id)){
            throw new RuntimeException("Cliente não encontrado");
        }
       clienteRepo.deleteById(id);
    }


    public ClienteResponse updateClientes(ClienteRequest request, Integer id){
       return clienteRepo.findById(id)
               .map(clientesExistentes -> {
                   clientesExistentes.setVendas(request.vendas());
                   clientesExistentes.setNome(request.nome());
                   clientesExistentes.setEmail(request.email());
                   clientesExistentes.setTelefone(request.telefone());
                   clientesExistentes.setCpf(request.cpf());
                   clientesExistentes.setCnpj(request.cnpj());

                   Utils.validarDocumentos(clientesExistentes);

                   Clientes atualizado = clienteRepo.save(clientesExistentes);
                    return new ClienteResponse(atualizado);
               })
               .orElseThrow(()-> new RuntimeException("Cliente com id" + id + "não encontrado"));
    }

}
