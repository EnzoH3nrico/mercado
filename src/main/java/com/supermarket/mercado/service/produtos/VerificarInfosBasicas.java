package com.supermarket.mercado.service.produtos;

import com.supermarket.mercado.dto.produtos.DadosDetalhamentoProduto;
import com.supermarket.mercado.model.consulta.IConsultas;
import com.supermarket.mercado.model.produtos.Produtos;
import com.supermarket.mercado.repositories.produtos.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class VerificarInfosBasicas implements IConsultas {

    @Autowired
    private ProdutoRepository produto;

    @Override
    public void consultaMercado(DadosDetalhamentoProduto dados) {
        List<Produtos> listaProdutos = new ArrayList<>();

        listaProdutos.stream().map(p -> {
            if(p.getItem().isBlank() && p.getDescricao().isBlank() && p.getCategoria() == null && p.getMarca().isBlank()
            && p.getPreco() == 0 && p.getEstoque() == 0){
                return new RuntimeException("Este produto não pode ser enviado! Há informações faltando");
            }
            return p;
        }).collect(Collectors.toUnmodifiableList());
    }
}
