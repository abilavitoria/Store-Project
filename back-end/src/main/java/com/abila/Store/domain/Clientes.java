package com.abila.Store.domain;

import com.abila.Store.util.Utils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.helpers.Util;

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
    @Column(length = 14)
    private String documento;

    @OneToMany(mappedBy = "cliente")
    private List<Vendas> vendas;

    public void setDocumento(String documento){
        if (documento == null || documento.isBlank()){
            throw new IllegalArgumentException("Campo documento não pode estar vazio");
        }

        String docLimpo = documento.replaceAll("\\D", "");

        if (docLimpo.length() == 11){
            if (Utils.validacaoCpf(docLimpo)){
                this.documento = docLimpo;
            }else {
                throw new IllegalArgumentException("CPF inválido");
            }
        } else if (docLimpo.length() == 14) {
            if (Utils.validacaoCnpj(docLimpo)){
                this.documento = docLimpo;
            }else {
                throw new IllegalArgumentException("CNPJ inválido");
            }
        }else {
            throw new IllegalArgumentException("Documento deve ter 11 ou 14 dígitos");
        }
    }
}
