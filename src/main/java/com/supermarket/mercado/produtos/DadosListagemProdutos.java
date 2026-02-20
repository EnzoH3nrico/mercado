package com.supermarket.mercado.produtos;

import com.supermarket.mercado.model.Categoria;
import com.supermarket.mercado.model.Fornecedor;
import com.supermarket.mercado.model.Produtos;
import com.supermarket.mercado.model.record.DadosProdutos;

public record DadosListagemProdutos(Long id, String item, String marca, String descricao, Double preco, Categoria categoria) {

    public DadosListagemProdutos(Produtos produtos){
        this(produtos.getId(), produtos.getItem(), produtos.getMarca(), produtos.getDescricao(), produtos.getPreco(), produtos.getCategoria());
    }

}
