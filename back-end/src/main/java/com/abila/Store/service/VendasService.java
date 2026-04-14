package com.abila.Store.service;

import com.abila.Store.domain.Clientes;
import com.abila.Store.domain.DTO.ItemVendaRequest;
import com.abila.Store.domain.DTO.ItemVendaResponse;
import com.abila.Store.domain.DTO.VendaRequest;
import com.abila.Store.domain.DTO.VendaResponse;
import com.abila.Store.domain.ItemVendas;
import com.abila.Store.domain.Vendas;
import com.abila.Store.repository.ClienteRepository;
import com.abila.Store.repository.ItemVendasRepository;
import com.abila.Store.repository.VendasRepository;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.abila.Store.util.Utils;

@Service
@RequiredArgsConstructor
public class VendasService {
    private final VendasRepository vendasRepo;
    private final ItemVendasRepository itemVendasRepo;
    private final ClienteRepository clienteRepo;

    public VendaResponse findById(Integer id){
        return vendasRepo.findById(id)
                .map(VendaResponse::new)
                .orElseThrow(()-> new RuntimeException("Venda não encontrada"));
    }

    @Transactional
    public VendaResponse saveVendas(VendaRequest request){
        Vendas novaVenda = new Vendas();
        novaVenda.setDescricao(request.descricao());
        novaVenda.setData(LocalDateTime.now());

        Clientes clientes = clienteRepo.findById(request.clientes().getId())
                        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        novaVenda.setCliente(clientes);

        if (request.itens() != null){
            request.itens().forEach(novaVenda::adicionarNovoItem);
        }

        Vendas salvo = vendasRepo.save(novaVenda);
        return new VendaResponse(salvo);
    }

    @Transactional
    public void deleteVendas(Integer id){
        if(!vendasRepo.existsById(id)){
            throw new RuntimeException("Venda não encontrada");
        }
        vendasRepo.deleteById(id);
    }

    @Transactional
    public VendaResponse updateVendas(VendaRequest request, Integer id){
        return vendasRepo.findById(id)
                .map(vendasExistentes -> {
                    vendasExistentes.setDescricao(request.descricao());

                    Clientes cliente = clienteRepo.findById(request.clientes().getId())
                                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
                    vendasExistentes.setCliente(cliente);

                    vendasExistentes.getItens().clear();
                    vendasExistentes.setPrecoTotal(BigDecimal.ZERO);

                    if (request.itens() != null){
                        request.itens().forEach(vendasExistentes::adicionarNovoItem);
                    }

                    return new VendaResponse(vendasRepo.save(vendasExistentes));
                })
                .orElseThrow(()-> new RuntimeException("Venda com o id" + id + "não encontrada"));
    }

    @Transactional
    public ItemVendaResponse addItemVendas(Integer id, ItemVendaRequest request){
        Vendas vendas = vendasRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        ItemVendas novoItem = new ItemVendas();
        novoItem.setNome(request.nome());
        novoItem.setPrecoUnitario(request.precoUnitario());
        novoItem.setQuantidade(request.quantidade());

        vendas.adicionarNovoItem(novoItem);
        ItemVendas salvo = itemVendasRepo.save(novoItem);
        vendasRepo.save(vendas);
        return new ItemVendaResponse(salvo);
    }

    @Transactional
    public void removeItemVendas(Integer id){
        if (!itemVendasRepo.existsById(id)){
            throw new RuntimeException("Item não encontrado");
        }
        itemVendasRepo.deleteById(id);
    }


    @Transactional
    public ItemVendaResponse updateItemVendas(Integer id, ItemVendaRequest request){
        return itemVendasRepo.findById(id)
                .map(itemExistente ->{
                    itemExistente.setNome(request.nome());
                    itemExistente.setPrecoUnitario(request.precoUnitario());
                    itemExistente.setQuantidade(request.quantidade());

                    ItemVendas atualizado = itemVendasRepo.save(itemExistente);
                    return new ItemVendaResponse(atualizado);
                })
                .orElseThrow(()->new RuntimeException("Item com id" + id + "não encontrado"));
    }
}
