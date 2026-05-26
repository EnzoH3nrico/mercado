package com.supermarket.mercado.dto.produtos;

import com.supermarket.mercado.model.categoria.Categoria;
import com.supermarket.mercado.model.produtos.Produtos;
import org.springframework.http.ResponseEntity;

public record DadosDetalhamentoProduto(Long id, String item, String marca, String descricao, Double preco, int estoque,
                                       Categoria categoria) {

    public DadosDetalhamentoProduto(Produtos produtos){
        this(produtos.getId(), produtos.getItem(), produtos.getMarca(), produtos.getDescricao(), produtos.getPreco(),
                produtos.getEstoque(), produtos.getCategoria());
    }

}
