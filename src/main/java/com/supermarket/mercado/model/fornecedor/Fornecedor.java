package com.supermarket.mercado.model.fornecedor;

import com.supermarket.mercado.dto.fornecedor.DadosFornecedor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(name = "Fornecedor")
@Table(name = "fornecedor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;
    private String cnpj;
    private String telefone;

    public Fornecedor(DadosFornecedor fornecedor) {
        this.nome = fornecedor.nome();
        this.cnpj = fornecedor.cnpj();
        this.telefone = fornecedor.telefone();
    }
}
