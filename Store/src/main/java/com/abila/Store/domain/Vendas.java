package com.abila.Store.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table
public class Vendas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer clienteId;
    @Column(nullable = false)
    private Integer produto_Id;
    @Column(length = 100)
    private String descricao;
    @Column(nullable = false)
    private Double precoTotal;
    @Column(nullable = false)
    private Date data;

    @OneToMany(mappedBy = "vendas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVendas> itens;
}
