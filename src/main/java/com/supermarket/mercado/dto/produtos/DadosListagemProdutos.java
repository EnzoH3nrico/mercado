package com.supermarket.mercado.dto.produtos;

import com.supermarket.mercado.model.categoria.Categoria;
import com.supermarket.mercado.model.produtos.Produtos;

public record DadosListagemProdutos(Long id, String item, String marca, String descricao, Double preco, Categoria categoria) {

    public DadosListagemProdutos(Produtos produtos){
        this(produtos.getId(), produtos.getItem(), produtos.getMarca(), produtos.getDescricao(), produtos.getPreco(), produtos.getCategoria());
    }

}
