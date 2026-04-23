package com.supermarket.mercado.service.produtos;

import com.supermarket.mercado.dto.produtos.DadosDetalhamentoProduto;
import com.supermarket.mercado.model.consulta.IConsultas;
import org.springframework.stereotype.Component;

@Component
public class VerificarProdutoExistente implements IConsultas {


    @Override
    public void consultaMercado(DadosDetalhamentoProduto dados) {
        var quantiaProduto = dados.estoque();

        if(quantiaProduto < 1){
            throw new RuntimeException("Não há produtos o suficiente na loja");
        }
    }
}
