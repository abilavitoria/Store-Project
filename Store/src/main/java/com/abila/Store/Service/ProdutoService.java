package com.abila.Store.Service;

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

    public List<Produtos> listAll(){
        return produtosRepo.findAll();
    }

    //consultar
    public Optional<Produtos> findById(Integer id){
        return produtosRepo.findById(id);
    }

    //cadastrar
    public Produtos save(Produtos produtos){
        return produtosRepo.save(produtos);
    }

    //excluir
    public void delete(Integer id){
        if (produtosRepo.existsById(id)) produtosRepo.deleteById(id);
    }

    //editar

}
