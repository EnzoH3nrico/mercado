package com.supermarket.mercado.controller.fornecedor;

import com.supermarket.mercado.dto.fornecedor.DadosDetalhamentoFornecedor;
import com.supermarket.mercado.dto.fornecedor.DadosFornecedor;
import com.supermarket.mercado.model.fornecedor.Fornecedor;
import com.supermarket.mercado.repositories.fornecedor.FornecedorRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/fornecedor")
@SecurityRequirement(name = "bearer-key")
public class FornecedorController {

    @Autowired
    private FornecedorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarFornecedor(@RequestBody @Valid DadosFornecedor dados, UriComponentsBuilder uriBuildeer) {
        var fornecedor = new Fornecedor(dados);

        repository.save(fornecedor);

        var uri = uriBuildeer.path("fornecedor/{id}").buildAndExpand(fornecedor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoFornecedor(fornecedor));
    }

    @GetMapping
    public ResponseEntity mostrarFornecedor(@RequestParam DadosDetalhamentoFornecedor dados){
        var acharFornecedor = repository.findById(dados.id());

        return ResponseEntity.ok(acharFornecedor);
    }
}