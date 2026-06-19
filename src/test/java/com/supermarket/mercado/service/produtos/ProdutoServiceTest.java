package com.supermarket.mercado.service.produtos;

import com.supermarket.mercado.dto.produtos.DadosDetalhamentoProduto;
import com.supermarket.mercado.dto.produtos.DadosProdutos;
import com.supermarket.mercado.exceptions_handling.exception.MercadoException;
import com.supermarket.mercado.model.categoria.Categoria;
import com.supermarket.mercado.model.produtos.Produtos;
import com.supermarket.mercado.repositories.produtos.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProdutoService")
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repo;

    @InjectMocks
    private ProdutoService produtoService;

    private Produtos produtoComEstoque;
    private Produtos produtoSemEstoque;

    @BeforeEach
    void setUp() {
        produtoComEstoque = new Produtos(
                1L, "Leite Integral", "Italac", "Leite integral 1L",
                5.99, 10, Categoria.LATICINIOS, null, true
        );

        produtoSemEstoque = new Produtos(
                2L, "Queijo Minas", "Faixa Azul", "Queijo minas padrão 500g",
                18.90, 0, Categoria.LATICINIOS, null, true
        );
    }

    // -------------------------------------------------------------------------
    // registroProduto
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("registroProduto()")
    class RegistroProduto {

        @Test
        @DisplayName("deve salvar o produto e retornar status 201")
        void deveSalvarProdutoERetornar201() {
            var dados = new DadosProdutos(
                    "Leite Integral", "Italac", "Leite 1L", 5.99, 10, Categoria.LATICINIOS
            );

            when(repo.save(any(Produtos.class))).thenAnswer(invocation -> {
                Produtos p = invocation.getArgument(0);
                // simula o banco atribuindo o id
                return new Produtos(1L, p.getItem(), p.getMarca(), p.getDescricao(),
                        p.getPreco(), p.getEstoque(), p.getCategoria(), null, true);
            });

            var uri = UriComponentsBuilder.fromUriString("http://localhost:8080");
            var resposta = produtoService.registroProduto(dados, uri);

            assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            verify(repo, times(1)).save(any(Produtos.class));
        }

        @Test
        @DisplayName("deve chamar save exatamente uma vez")
        void deveChamarSaveUmaVez() {
            var dados = new DadosProdutos(
                    "Arroz", "Tio João", "Arroz agulhinha 5kg", 28.90, 50, Categoria.ALIMENTOS
            );
            when(repo.save(any())).thenReturn(produtoComEstoque);

            var uri = UriComponentsBuilder.fromUriString("http://localhost:8080");
            produtoService.registroProduto(dados, uri);

            verify(repo, times(1)).save(any(Produtos.class));
        }
    }

    // -------------------------------------------------------------------------
    // venda
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("venda()")
    class Venda {

        @Test
        @DisplayName("deve decrementar estoque e retornar 200 quando produto tem estoque")
        void deveDecrementarEstoqueERetornar200() {
            var dados = new DadosDetalhamentoProduto(
                    1L, "Leite Integral", "Italac", "Leite 1L",
                    5.99, 10, Categoria.LATICINIOS
            );

            when(repo.findById(1L)).thenReturn(Optional.of(produtoComEstoque));
            when(repo.save(any())).thenReturn(produtoComEstoque);

            var resposta = produtoService.venda(dados);

            assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(produtoComEstoque.getEstoque()).isEqualTo(9);
            verify(repo).save(produtoComEstoque);
        }

        @Test
        @DisplayName("deve lançar MercadoException quando produto não tem estoque")
        void deveLancarExcecaoQuandoSemEstoque() {
            var dados = new DadosDetalhamentoProduto(
                    2L, "Queijo Minas", "Faixa Azul", "Queijo 500g",
                    18.90, 0, Categoria.LATICINIOS
            );

            when(repo.findById(2L)).thenReturn(Optional.of(produtoSemEstoque));

            assertThatThrownBy(() -> produtoService.venda(dados))
                    .isInstanceOf(MercadoException.class)
                    .hasMessageContaining("sem estoque");

            verify(repo, never()).save(any());
        }

        @Test
        @DisplayName("deve lançar MercadoException quando produto não existe")
        void deveLancarExcecaoQuandoProdutoNaoExiste() {
            var dados = new DadosDetalhamentoProduto(
                    99L, "Produto Inexistente", "Marca X", "Desc",
                    1.0, 5, Categoria.ALIMENTOS
            );

            when(repo.findById(99L)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> produtoService.venda(dados))
                    .isInstanceOf(MercadoException.class)
                    .hasMessageContaining("não existe");

            verify(repo, never()).save(any());
        }

        @Test
        @DisplayName("não deve chamar save quando a venda falha por falta de estoque")
        void naoDeveChamarSaveQuandoVendaFalha() {
            var dados = new DadosDetalhamentoProduto(
                    2L, "Queijo Minas", "Faixa Azul", "Queijo 500g",
                    18.90, 0, Categoria.LATICINIOS
            );

            when(repo.findById(2L)).thenReturn(Optional.of(produtoSemEstoque));

            try {
                produtoService.venda(dados);
            } catch (MercadoException ignored) {}

            verify(repo, never()).save(any());
        }
    }
}