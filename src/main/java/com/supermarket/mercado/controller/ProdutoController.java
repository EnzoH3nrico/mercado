package com.supermarket.mercado.controller;

import com.supermarket.mercado.domain.model.Produtos;
import com.supermarket.mercado.domain.model.record.DadosDetalhamentoProduto;
import com.supermarket.mercado.domain.model.record.DadosProdutos;
import com.supermarket.mercado.domain.produtos.DadosAtualizacaoProdutos;
import com.supermarket.mercado.domain.produtos.DadosListagemProdutos;
import com.supermarket.mercado.domain.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/mercado")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadatrarProduto(@RequestBody @Valid DadosProdutos dados, UriComponentsBuilder uriBuilder){
        var produto = new Produtos(dados);

        repository.save(produto);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(produto.getId()).toUri();



        return ResponseEntity.created(uri).body(new DadosDetalhamentoProduto(produto));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemProdutos>> listar(
            @PageableDefault(size = 10, sort = {"item"}) Pageable pageable) {

        var page =  repository.findAllByAtivoTrue(pageable)
                .map(DadosListagemProdutos::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoProdutos dados){
            var produto = repository.getReferenceById(dados.id());
            produto.atualizarInfos(dados);

            return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var produto = repository.getReferenceById(id);
        produto.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var produto = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }
}
