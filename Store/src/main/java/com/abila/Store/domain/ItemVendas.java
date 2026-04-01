package com.abila.Store.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "item_vendas")
public class ItemVendas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100, nullable = false)
    private String nome;
    @Column(nullable = false)
    private BigDecimal precoUnitario;
    @Column(nullable = false)
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Vendas vendas;

    @OneToOne
    @JoinColumn(name = "produto_id")
    private Produtos produto;

    //FUNCOES
    public void setPrecoUnitario(BigDecimal preco){
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("O preco do item deve ser maior que zero");
        }
        this.precoUnitario = preco;
    }

    public void setQuantidade(Integer quantidade){
        if (quantidade == null || quantidade <= 0){
            throw new RuntimeException("Quantidade deve ser pelo menos 1");
        }
        this.quantidade = quantidade;
    }

    public BigDecimal getSubtotal(){
        if (this.precoUnitario == null || this.precoUnitario == null)return BigDecimal.ZERO;
        return this.precoUnitario.multiply(new BigDecimal(this.quantidade));
    }
}
