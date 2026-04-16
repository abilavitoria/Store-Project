package com.abila.Store.service;

import com.abila.Store.domain.DTO.ItemVendaResponse;
import com.abila.Store.domain.DTO.ProdutoRequest;
import com.abila.Store.domain.DTO.ProdutoResponse;
import com.abila.Store.domain.Produtos;
import com.abila.Store.repository.ProdutosRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutosRepository produtosRepo;

    @Transactional
    public ProdutoResponse findById(Integer id){
        return produtosRepo.findById(id)
                .map(ProdutoResponse::new)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado"));
    }

    @Transactional
    public ProdutoResponse saveProdutos(ProdutoRequest request){
        Produtos novoProduto = new Produtos();

        novoProduto.setNome(request.nome());
        novoProduto.setPreco(request.preco());
        novoProduto.setQuantidade(request.quantidade());

        Produtos salvo = produtosRepo.save(novoProduto);
        return new ProdutoResponse(salvo);
    }

    @Transactional
    public void deleteProdutos(Integer id){
        if (!produtosRepo.existsById(id)){
            throw new RuntimeException("Produto nçao encontrado");
        }
        produtosRepo.deleteById(id);
    }

    @Transactional
    public ProdutoResponse updateProdutos(Integer id, ProdutoRequest request){
        return produtosRepo.findById(id)
                .map(produtosExistentes -> {
                    produtosExistentes.setNome(request.nome());
                    produtosExistentes.setPreco(request.preco());
                    produtosExistentes.setQuantidade(request.quantidade());

                    return new ProdutoResponse(produtosRepo.save(produtosExistentes));
                })
                .orElseThrow(() -> new RuntimeException("Produto com id" + id + "não encontrado"));
    }
}
