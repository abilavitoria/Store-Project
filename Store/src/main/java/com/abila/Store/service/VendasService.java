package com.abila.Store.service;

import com.abila.Store.domain.DTO.ItemVendaRequest;
import com.abila.Store.domain.DTO.ItemVendaResponse;
import com.abila.Store.domain.DTO.VendaRequest;
import com.abila.Store.domain.DTO.VendaResponse;
import com.abila.Store.domain.ItemVendas;
import com.abila.Store.domain.Vendas;
import com.abila.Store.repository.ItemVendasRepository;
import com.abila.Store.repository.VendasRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.MathContext;
import java.math.BigDecimal;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendasService {
    private final VendasRepository vendasRepo;
    private final ItemVendasRepository itemVendasRepo;

//                  METODOS DE VENDA
    //consultar
    public VendaResponse findById(Integer id){
        return vendasRepo.findById(id)
                .map(VendaResponse::new)
                .orElseThrow(()->new RuntimeException("Venda não encontrada"));
    }
    //salvar
    @Transactional
    public VendaResponse saveVendas(VendaRequest request){
        Vendas vendas = new Vendas();

        vendas.setDescricao(request.descricao());
        vendas.setPrecoTotal(request.precoTotal());
        vendas.setData(request.data());

        Vendas salva = vendasRepo.save(vendas);
        return new VendaResponse(salva);
    }
    //excluir
    public void deleteVendas(Integer id){
        if (!vendasRepo.existsById(id)) throw new RuntimeException("Venda não encontrada");
        vendasRepo.deleteById(id);
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
                .orElseThrow(()-> new RuntimeException("Venda não encontrada"));

        ItemVendas novoItem = new ItemVendas();
        novoItem.setNome(request.nome());
        novoItem.setPrecoUnitario(request.precoUnitario());
        novoItem.setQuantidade(request.quantidade());

        vendas.adicionarItem(novoItem);
        vendasRepo.save(vendas);

        return new ItemVendaResponse(novoItem);
    }
    //remover itens
    @Transactional
    public void removeItemVendas(Integer itemId){
        ItemVendas itemVendas = itemVendasRepo.findById(itemId)
                .orElseThrow(()-> new RuntimeException("Item não encontrados"));

        Vendas vendas = itemVendas.getVendas();

        vendas.removerItem(itemVendas);
        vendasRepo.save(vendas);
    }

    //editar itens
    @Transactional
    public ItemVendas updateItemVendas(Integer itemId, ItemVendas itemAtualizado){
        ItemVendas itemExistente = itemVendasRepo.findById(itemId)
                .orElseThrow(()-> new RuntimeException("Item não encontrado"));

        Vendas vendas = itemAtualizado.getVendas();
        vendas.removerItem(itemExistente);

        itemExistente.setNome(itemAtualizado.getNome());
        itemExistente.setQuantidade(itemAtualizado.getQuantidade());
        itemExistente.setPrecoUnitario(itemAtualizado.getPrecoUnitario());

        vendas.adicionarItem(itemExistente);
        vendasRepo.save(vendas);
        return itemExistente;
    }
}
