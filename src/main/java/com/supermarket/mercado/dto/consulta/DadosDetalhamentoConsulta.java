package com.supermarket.mercado.dto.consulta;

import com.supermarket.mercado.model.consulta.Consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, Long idFornecedor, Long idProdutos, LocalDateTime data) {
    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getFornecedor().getId(), consulta.getProdutos().getId(), consulta.getData());
    }
}
