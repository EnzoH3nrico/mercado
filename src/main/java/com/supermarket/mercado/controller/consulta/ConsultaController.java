package com.supermarket.mercado.controller.consulta;

import com.supermarket.mercado.dto.fornecedor.DadosFornecedorAutenticacao;
import com.supermarket.mercado.service.consulta.ConsultaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consulta")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ConsultaService estoque;

    @PostMapping
    @Transactional
    public ResponseEntity buscar(@RequestBody @Valid DadosFornecedorAutenticacao dados){
        var DTO = estoque.consultar(dados);
        return ResponseEntity.ok(DTO);
    }

}
