package com.supermarket.mercado.controller;

import com.supermarket.mercado.model.Produtos;
import com.supermarket.mercado.model.record.DadosProdutos;
import com.supermarket.mercado.produtos.DadosAtualizacaoProdutos;
import com.supermarket.mercado.produtos.DadosListagemProdutos;
import com.supermarket.mercado.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mercado")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @Transactional
    public void cadatrarProduto(@RequestBody @Valid DadosProdutos dados){
        repository.save(new Produtos(dados));
    }

    @GetMapping
    public Page<DadosListagemProdutos> listar(
            @PageableDefault(size = 10, sort = {"item"}) Pageable pageable) {

        return repository.findAllByAtivoTrue(pageable)
                .map(DadosListagemProdutos::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoProdutos dados){
            var produto = repository.getReferenceById(dados.id());
            produto.atualizarInfos(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var produto = repository.getReferenceById(id);
        produto.excluir();
    }
}
