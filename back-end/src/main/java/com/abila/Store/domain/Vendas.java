package com.abila.Store.domain;

import com.abila.Store.domain.DTO.ItemVendaRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, columnDefinition = "timestamp(6)")
    private LocalDateTime data ;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Clientes cliente;

    @OneToMany(mappedBy = "vendas", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ItemVendas> itens = new ArrayList<>();

    public void adicionarNovoItem(ItemVendas itemVendas){
        validarPreco(itemVendas.getPrecoUnitario());
        if (this.itens == null) this.itens = new ArrayList<>();

        this.itens.add(itemVendas);
        itemVendas.setVendas(this);

        this.precoTotal = totalVenda();
    }

    public void removerItem(ItemVendas itemVendas){
        validarPreco(itemVendas.getPrecoUnitario());
        if(this.itens.remove(itemVendas)) this.precoTotal = totalVenda();
    }

    private void validarPreco(BigDecimal precoTotal){
        if (precoTotal == null || precoTotal.compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("O preço do item deve ser maior que zero!");
        }
    }

    public BigDecimal totalVenda(){
        if(itens == null || itens.isEmpty()) return BigDecimal.ZERO;

        return itens.stream()
                .map(item -> item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @PrePersist
    @PreUpdate
    public void recalcular(){
        this.precoTotal = totalVenda();
        if (this.data == null) this.data = LocalDateTime.now();
    }
}
