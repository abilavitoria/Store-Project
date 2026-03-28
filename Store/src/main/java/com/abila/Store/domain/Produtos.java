package com.abila.Store.domain;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100, nullable = false)
    private String nome;
    @Column(length = 100)
    private String descricao;
    @Column(nullable = false)
    private Double preco;
    @Column(nullable = false)
    private Integer quantidade;

    @OneToOne(mappedBy = "itemVendas")
    private ItemVendas itemVendas;
}
