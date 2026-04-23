package com.supermarket.mercado.service.produtos;

import com.supermarket.mercado.dto.produtos.DadosDetalhamentoProduto;
import com.supermarket.mercado.model.consulta.IConsultas;
import org.springframework.stereotype.Component;

@Component
public class VerificarPrecoAtual implements IConsultas {
    @Override
    public void consultaMercado(DadosDetalhamentoProduto dados) {
        var quantiaPreco = dados.preco();

        if (quantiaPreco < 0.00){
            throw new RuntimeException("Não pode ter itens abaixo de 0");
        }
    }

}
