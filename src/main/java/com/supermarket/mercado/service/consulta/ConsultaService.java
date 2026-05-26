package com.supermarket.mercado.service.consulta;

import com.supermarket.mercado.dto.consulta.DadosDetalhamentoConsulta;
import com.supermarket.mercado.dto.fornecedor.DadosFornecedorAutenticacao;
import com.supermarket.mercado.repositories.consulta.ConsultaRepository;
import com.supermarket.mercado.repositories.fornecedor.FornecedorRepository;
import com.supermarket.mercado.repositories.produtos.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public DadosDetalhamentoConsulta consultar(DadosFornecedorAutenticacao dados){
        var produto = produtoRepository.findById(dados.idProduto()).get();

        var fornecedor = fornecedorRepository.findById(dados.idFornecedor()).get();

        if (fornecedor == null){
            throw new RuntimeException("Este fornecedor não está disponível");
        }

        var consulta = new com.supermarket.mercado.model.consulta.Consulta(null, fornecedor, produto, dados.data());
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }
}
