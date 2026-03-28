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
    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Clientes clientes;
    @Column(length = 100)
    private String descricao;
    @Column(nullable = false)
    private Double precoTotal = 0.00;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @OneToMany(mappedBy = "vendas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVendas> itens;
}
