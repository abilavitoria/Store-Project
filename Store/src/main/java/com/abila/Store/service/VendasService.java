package com.abila.Store.service;

import com.abila.Store.domain.DTO.ItemVendaRequest;
import com.abila.Store.domain.DTO.ItemVendaResponse;
import com.abila.Store.domain.ItemVendas;
import com.abila.Store.domain.Vendas;
import com.abila.Store.repository.ItemVendasRepository;
import com.abila.Store.repository.VendasRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendasService {
    private final VendasRepository vendasRepo;
    private final ItemVendasRepository itemVendasRepo;

//                  METODOS DE VENDA
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

    //METODOS DE ITENS
    //adicionar itens a uma venda já existente
    @Transactional
    public ItemVendaResponse addItemVendas(Integer vendaId, ItemVendaRequest request){
        Vendas vendas = vendasRepo.findById(vendaId)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        ItemVendas novoItem = new ItemVendas();
        novoItem.setNome(request.nome());
        novoItem.setPrecoUnitario(request.precoUnitario());
        novoItem.setQuantidade(request.quantidade());

        vendas.adicionarNovoItem(novoItem);
        vendasRepo.save(vendas);

        return new ItemVendaResponse(
                novoItem.getId(),
                novoItem.getNome(),
                novoItem.getPrecoUnitario(),
                novoItem.getQuantidade(),
                novoItem.getSubtotal()
        );
    }
    //remover itens
    @Transactional
    public void removeItemVendas(Integer itemId){
        ItemVendas itemVendas = itemVendasRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        Vendas vendas = itemVendas.getVendas();
        vendas.removerItem(itemVendas);
        vendasRepo.save(vendas);
    }

    //editar itens
    @Transactional
    public ItemVendas updateItemVendas(Integer itemId, ItemVendas itemAtualizado){
        ItemVendas itemExistente = itemVendasRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado!"));

        Vendas vendas = itemExistente.getVendas();
        vendas.removerItem(itemExistente);

        itemExistente.setNome(itemAtualizado.getNome());
        itemExistente.setQuantidade(itemAtualizado.getQuantidade());
        itemExistente.setPrecoUnitario(itemAtualizado.getPrecoUnitario());

        vendas.adicionarNovoItem(itemExistente);
        vendasRepo.save(vendas);
        return itemExistente;
    }
}
