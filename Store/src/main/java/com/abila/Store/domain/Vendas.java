package com.abila.Store.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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
    private BigDecimal precoTotal;
    @Column(nullable = false)
    private LocalDateTime data = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Clientes cliente;

    @OneToMany(mappedBy = "vendas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVendas> itens = new ArrayList<>();
}
