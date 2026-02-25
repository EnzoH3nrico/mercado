package com.supermarket.mercado.domain.produtos;

import com.supermarket.mercado.domain.model.Categoria;
import com.supermarket.mercado.domain.model.Produtos;

public record DadosListagemProdutos(Long id, String item, String marca, String descricao, Double preco, Categoria categoria) {

    public DadosListagemProdutos(Produtos produtos){
        this(produtos.getId(), produtos.getItem(), produtos.getMarca(), produtos.getDescricao(), produtos.getPreco(), produtos.getCategoria());
    }

}
