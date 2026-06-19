package com.supermarket.mercado.service.consulta;

import com.supermarket.mercado.dto.consulta.DadosDetalhamentoConsulta;
import com.supermarket.mercado.dto.fornecedor.DadosFornecedorAutenticacao;
import com.supermarket.mercado.exceptions_handling.exception.MercadoException;
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
        var produto = produtoRepository.findById(dados.idProduto())
                .orElseThrow(() -> new MercadoException("Produto não encontrado"));

        var fornecedor = fornecedorRepository.findById(dados.idProduto())
                .orElseThrow(() -> new MercadoException("Fornecedor não encontrado"));

        var consulta = new com.supermarket.mercado.model.consulta.Consulta(null, fornecedor, produto, dados.data());
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }
}
