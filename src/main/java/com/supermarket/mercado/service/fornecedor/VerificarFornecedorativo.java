package com.supermarket.mercado.service.fornecedor;

import com.supermarket.mercado.dto.produtos.DadosDetalhamentoProduto;
import com.supermarket.mercado.model.consulta.IConsultas;
import com.supermarket.mercado.repositories.fornecedor.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerificarFornecedorativo implements IConsultas {

    @Autowired
    private FornecedorRepository fornecedor;

    @Override
    public void consultaMercado(DadosDetalhamentoProduto dados) {
        if(dados.id() == null) {
            return;
        }

        var fornecedorAtivo = fornecedor.existsById(dados.id());

        if (!fornecedorAtivo){
            throw new RuntimeException("Este fornecedor não está em nosso sistema");
        }
    }
}
