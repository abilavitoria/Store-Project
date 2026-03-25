package com.abila.Store.service;

import com.abila.Store.domain.Clientes;
import com.abila.Store.domain.Produtos;
import com.abila.Store.repository.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutosRepository produtosRepo;

    //consultar
    public Optional<Produtos> findById(Integer id){
        return produtosRepo.findById(id);
    }

    //cadastrar
    public Produtos saveProdutos(Produtos produtos){
        return produtosRepo.save(produtos);
    }
    //excluir
    public void deleteProdutos(Integer id){
        if (!produtosRepo.existsById(id)){
            throw new RuntimeException("Produto não encontrado");
        }
        else {
            produtosRepo.deleteById(id);
        }
    }
    //editar
    public Produtos updateProdutos(Integer id, Produtos produtos){
        return produtosRepo.findById(id)
                .map(existingProdutos ->{
                    produtos.setId(id);
                    return produtosRepo.save(produtos);
                })
                .orElseThrow(() -> new RuntimeException("Produto com id" + id + "não encontrado"));
    }
}
