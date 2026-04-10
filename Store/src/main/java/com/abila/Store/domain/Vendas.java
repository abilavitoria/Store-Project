package com.abila.Store.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "vendas")
public class Vendas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100)
    private String descricao;
    @Column(nullable = false)
    private BigDecimal precoTotal = BigDecimal.ZERO;
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/mm/yyyy")
    private LocalDate data = LocalDate.now();

    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Clientes cliente;

    @OneToMany(mappedBy = "vendas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVendas> itens = new ArrayList<>();

    //METODOS VENDA
    public void adicionarNovoItem(ItemVendas itemVendas){
        itemVendas.setVendas(this);
        this.itens.add(itemVendas);

        this.precoTotal = this.precoTotal.add(itemVendas.getSubtotal());
    }

    public void removerItem(ItemVendas itemVendas){
        if(this.itens.remove(itemVendas)){
            this.precoTotal = this.precoTotal.subtract(itemVendas.getSubtotal());
        }

        if (this.precoTotal.compareTo(BigDecimal.ZERO) < 0){
            this.precoTotal = BigDecimal.ZERO;
        }
    }

    private void validarPreco(BigDecimal precoTotal){
        if (precoTotal == null || precoTotal.compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("O preço do item deve ser maior que zero!");
        }
    }
}
