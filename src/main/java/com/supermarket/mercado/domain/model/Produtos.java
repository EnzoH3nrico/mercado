package com.supermarket.mercado.domain.model;


import com.supermarket.mercado.domain.model.record.DadosProdutos;
import com.supermarket.mercado.domain.produtos.DadosAtualizacaoProdutos;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

 @Entity(name = "Produtos")
 @Table(name = "produto")
 @Getter
 @NoArgsConstructor
 @AllArgsConstructor
 @EqualsAndHashCode(of = "id")
public class Produtos {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item;
    private String marca;
    private String descricao;
    private double preco;
    private int estoque;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Embedded
    private Fornecedor fornecedor;

    private Boolean ativo;

    public Produtos(DadosProdutos dadosProdutos){
        this.ativo = true;
        this.item = dadosProdutos.item();
        this.marca = dadosProdutos.marca();
        this.descricao = dadosProdutos.descricao();
        this.preco = dadosProdutos.preco();
        this.estoque = dadosProdutos.estoque();
        this.categoria = dadosProdutos.categoria();
        this.fornecedor = new Fornecedor(dadosProdutos.fornecedor());

    }

     public void atualizarInfos(@Valid DadosAtualizacaoProdutos dados) {
        if(dados.descricao() != null){
            this.descricao = dados.descricao();
         }
         if(dados.marca()!= null){
            this.marca = dados.marca();
         }
         if(dados.preco() != null){
             this.preco = dados.preco();
         }
     }

     public void excluir(){
        this.ativo = false;
     }
 }
