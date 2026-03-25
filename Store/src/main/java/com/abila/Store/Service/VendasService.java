package com.abila.Store.service;

import com.abila.Store.domain.Vendas;
import com.abila.Store.repository.VendasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendasService {
    private final VendasRepository vendasRepo;

    //consultar
    public Optional<Vendas> findById(Integer id){
        return vendasRepo.findById(id);
    }
    //salvar
    public Vendas saveVendas(Vendas vendas){
        return vendasRepo.save(vendas);
    }
    //excluir
    public void deleteVendas(Integer id){
        if (!vendasRepo.existsById(id)){
            throw new RuntimeException("Venda não encontrada");
        }
        else {
            vendasRepo.deleteById(id);
        }
    }
    //editar
    public Vendas updateVendas(Vendas vendas, Integer id){
        return vendasRepo.findById(id)
                .map(existisVendas ->{
                    vendas.setId(id);
                    return vendasRepo.save(vendas);
                })
                .orElseThrow(() -> new RuntimeException("Venda com id" + id + "não encontrada"));
    }

    //adicionar itens
    //remover itens
    //editar itens
}
