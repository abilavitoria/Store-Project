package com.abila.Store.service;

import com.abila.Store.domain.DTO.ItemVendaResponse;
import com.abila.Store.domain.DTO.ProdutoRequest;
import com.abila.Store.domain.DTO.ProdutoResponse;
import com.abila.Store.domain.Produtos;
import com.abila.Store.repository.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutosRepository produtosRepo;

    //consultar
    public ProdutoResponse findById(Integer id){
        return produtosRepo.findById(id)
                .map(ProdutoResponse::new)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado"));
    }
    //cadastrar
    public ProdutoResponse saveProdutos(ProdutoRequest request){
        Produtos novoProduto = new Produtos();

        novoProduto.setNome(request.nome());
        novoProduto.setDescricao(request.descricao());
        novoProduto.setPreco(request.preco());
        novoProduto.setQuantidade(request.quantidade());
        novoProduto.setItensVendidos(request.itens());

        Produtos salvo = produtosRepo.save(novoProduto);
        return new ProdutoResponse(salvo);
    }
    //excluir
    public void deleteProdutos(Integer id){
        if (!produtosRepo.existsById(id)){
            throw new RuntimeException("Produto nçao encontrado");
        }
        produtosRepo.deleteById(id);
    }
    //editar
    public ProdutoResponse updateProdutos(Integer id, ProdutoRequest request){
        return produtosRepo.findById(id)
                .map(produtosExistentes -> {
                    produtosExistentes.setNome(request.nome());
                    produtosExistentes.setDescricao(request.descricao());
                    produtosExistentes.setPreco(request.preco());
                    produtosExistentes.setQuantidade(request.quantidade());
                    produtosExistentes.setItensVendidos(request.itens());

                    Produtos atualizado = produtosRepo.save(produtosExistentes);
                    return new ProdutoResponse(atualizado);
                })
                .orElseThrow(() -> new RuntimeException("Produto com id" + id + "não encontrado"));
    }
}
