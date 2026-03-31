package com.abila.Store.service;

import com.abila.Store.domain.ItemVendas;
import com.abila.Store.domain.Vendas;
import com.abila.Store.repository.ItemVendasRepository;
import com.abila.Store.repository.VendasRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.MathContext;

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
    public ItemVendas addItemVendas(Integer vendaId, ItemVendas novoItem){
        return vendasRepo.findById(vendaId)
                .map(venda ->{
                    novoItem.setVendas(venda);
                    return itemVendasRepo.save(novoItem);
                })
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
    }
    //remover itens
    @Transactional
    public void removeItemVendas(Integer itemId){
        if (!itemVendasRepo.existsById(itemId)){
            throw new RuntimeException("Item não encontrado");
        }
        else{
            itemVendasRepo.deleteById(itemId);
        }
    }

    //editar itens
    @Transactional
    public ItemVendas updateItemVendas(Integer itemId, ItemVendas itemAtualizado){
        return itemVendasRepo.findById(itemId)
                .map(itemExistente -> {
                    itemExistente.setNome(itemAtualizado.getNome());
                    itemExistente.setQuantidade(itemAtualizado.getQuantidade());
                    itemExistente.setPrecoUnitario(itemAtualizado.getPrecoUnitario());

                    return itemVendasRepo.save(itemExistente);
                })
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }

    //FUNCOES
    public void adicionarItem(Vendas vendas, ItemVendas itemVendas){
        vendas.getItens().add(itemVendas);

        BigDecimal novoTotal = vendas.getPrecoTotal().add(itemVendas.getPrecoUnitario());
        vendas.setPrecoTotal(novoTotal);
    }

    public void removerItem(Vendas vendas, ItemVendas itemVendas){
        vendas.getItens().remove(itemVendas);

        BigDecimal novoTotal = vendas.getPrecoTotal().subtract(itemVendas.getPrecoUnitario());
        vendas.setPrecoTotal(novoTotal);
    }
}
