package com.supermarket.mercado.domain.model.record;

import com.supermarket.mercado.domain.model.Categoria;
import com.supermarket.mercado.domain.model.Fornecedor;
import com.supermarket.mercado.domain.model.Produtos;

public record DadosDetalhamentoProduto(Long id, String item, String marca, String descricao, Double preco, int estoque,
                                       Categoria categoria, Fornecedor fornecedor) {

    public DadosDetalhamentoProduto(Produtos produtos){
        this(produtos.getId(), produtos.getItem(), produtos.getMarca(), produtos.getDescricao(), produtos.getPreco(),
                produtos.getEstoque(), produtos.getCategoria(), produtos.getFornecedor());
    }
}
