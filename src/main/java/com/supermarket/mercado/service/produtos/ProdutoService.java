package com.supermarket.mercado.service.produtos;

import com.supermarket.mercado.dto.produtos.DadosDetalhamentoProduto;
import com.supermarket.mercado.dto.produtos.DadosProdutos;
import com.supermarket.mercado.exceptions_handling.exception.MercadoException;
import com.supermarket.mercado.model.produtos.Produtos;
import com.supermarket.mercado.repositories.produtos.ProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repo;

    public ResponseEntity registroProduto(DadosProdutos dados, UriComponentsBuilder uriBuilder){
        var produto = new Produtos(dados);

        repo.save(produto);

        var uri = uriBuilder.path("/mercado/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoProduto(produto));
    }

    public ResponseEntity<String> venda(DadosDetalhamentoProduto dados){

        var produto = repo.findById(dados.id())
                .orElseThrow(() ->
                        new MercadoException("Este id não existe no sistema"));

        if(produto.getEstoque() <= 0){
            throw new MercadoException("Este produto está sem estoque");
        }

        produto.setEstoque(produto.getEstoque() - 1);

        repo.save(produto);

        return ResponseEntity.ok("Venda concluída!");
    }
}


