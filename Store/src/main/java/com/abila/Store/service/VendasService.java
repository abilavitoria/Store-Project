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
import java.util.List;
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
    public VendaResponse findById(Integer id){
        Vendas vendas = vendasRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        return new VendaResponse(
                vendas.getId(),
                vendas.getCliente(),
                vendas.getItens()
        );
    }
    //salvar
    public ItemVendaResponse saveVendas(ItemVendaRequest request){
            Vendas novaVenda = new Vendas();
            novaVenda.setCliente(request.nome());
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
    public VendaResponse updateVendas(VendaRequest request, Integer id){
        return vendasRepo.findById(id)
                .map(vendaExistente ->{
                    vendaExistente.setDescricao(request.descricao());
                    vendaExistente.setPrecoTotal(request.precoTotal());
                    vendaExistente.setData(request.data());

                    Vendas vendas = vendasRepo.save(vendaExistente);
                    return new VendaResponse(saveVendas(vendas));
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
    public ItemVendaResponse updateItemVendas(Integer itemId, ItemVendaRequest itemAtualizado){
        ItemVendas itemExistente = itemVendasRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado!"));

        itemExistente.setNome(itemAtualizado.nome());
        itemExistente.setQuantidade(itemAtualizado.quantidade());
        itemExistente.setPrecoUnitario(itemAtualizado.precoUnitario());

        vendasRepo.save(itemExistente.getVendas());
        return new ItemVendaResponse(
                itemExistente.getId(),
                itemExistente.getNome(),
                itemExistente.getPrecoUnitario(),
                itemExistente.getQuantidade(),
                itemExistente.getSubtotal()
        );
    }
}
