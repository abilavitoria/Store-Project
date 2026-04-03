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
        return vendasRepo.findById(id)
                .map(VendaResponse::new)
                .orElseThrow(()-> new RuntimeException("Venda não encontrada"));
    }
    //salvar
    public VendaResponse saveVendas(VendaRequest request){
        Vendas novaVenda = new Vendas();

        novaVenda.setDescricao(request.descricao());
        novaVenda.setPrecoTotal(request.precoTotal());
        novaVenda.setData(request.data());
        novaVenda.setCliente(request.clientes());
        novaVenda.setItens((List<ItemVendas>) request.itens());

        Vendas salvo = vendasRepo.save(novaVenda);
        return new VendaResponse(salvo);
    }
    //excluir
    public void deleteVendas(Integer id){
        if(!vendasRepo.existsById(id)){
            throw new RuntimeException("Venda não encontrada");
        }
        vendasRepo.deleteById(id);
    }
    //editar
    public VendaResponse updateVendas(VendaRequest request, Integer id){
        return vendasRepo.findById(id)
                .map(vendasExistentes -> {
                    vendasExistentes.setDescricao(request.descricao());
                    vendasExistentes.setPrecoTotal(request.precoTotal());
                    vendasExistentes.setData(request.data());
                    vendasExistentes.setCliente(request.clientes());
                    vendasExistentes.setItens(request.itens());

                    Vendas atualizadas = vendasRepo.save(vendasExistentes);
                    return new VendaResponse(atualizadas);
                })
                .orElseThrow(()-> new RuntimeException("Venda com o id" + id + "não encontrada"));
    }

    //METODOS DE ITENS
    //adicionar itens a uma venda já existente
    @Transactional
    public ItemVendaResponse addItemVendas(Integer id, ItemVendaRequest request){
        ItemVendas novoItem = new ItemVendas();
        novoItem.setNome(request.nome());
        novoItem.setPrecoUnitario(request.precoUnitario());
        novoItem.setQuantidade(request.quantidade());
        novoItem.setVendas(request.vendas());
        novoItem.setProduto(request.produtos());

        ItemVendas salvo = itemVendasRepo.save(novoItem);
        return new ItemVendaResponse(salvo);
    }
    //remover itens
    @Transactional
    public void removeItemVendas(Integer id){
        if (!itemVendasRepo.existsById(id)){
            throw new RuntimeException("Item não encontrado");
        }
        itemVendasRepo.deleteById(id);
    }

    //editar itens
    @Transactional
    public ItemVendaResponse updateItemVendas(Integer id, ItemVendaRequest request){
        return itemVendasRepo.findById(id)
                .map(itemExistente ->{
                    itemExistente.setNome(request.nome());
                    itemExistente.setPrecoUnitario(request.precoUnitario());
                    itemExistente.setQuantidade(request.quantidade());
                    itemExistente.setVendas(request.vendas());
                    itemExistente.setProduto(request.produtos());

                    ItemVendas atualizado = itemVendasRepo.save(itemExistente);
                    return new ItemVendaResponse(atualizado);
                })
                .orElseThrow(()->new RuntimeException("Item com id" + id + "não encontrado"));
    }
}
