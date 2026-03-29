package com.abila.Store.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "clientes")
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100, nullable = false)
    private String nome;
    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @Column(length = 13)
    private String telefone;
    @Column(length = 11)
    private String cpf;
    @Column(length = 14)
    private String cnpj;

    @OneToMany(mappedBy = "cliente")
    private List<Vendas> vendas;
}
