package com.supermarket.mercado.model.produtos;


import com.supermarket.mercado.dto.produtos.DadosProdutos;
import com.supermarket.mercado.dto.produtos.DadosAtualizacaoProdutos;
import com.supermarket.mercado.model.categoria.Categoria;
import com.supermarket.mercado.model.fornecedor.Fornecedor;
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

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
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
