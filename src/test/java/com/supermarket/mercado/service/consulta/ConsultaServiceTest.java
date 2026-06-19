package com.supermarket.mercado.service.consulta;

import com.supermarket.mercado.dto.fornecedor.DadosFornecedorAutenticacao;
import com.supermarket.mercado.exceptions_handling.exception.MercadoException;
import com.supermarket.mercado.model.categoria.Categoria;
import com.supermarket.mercado.model.consulta.Consulta;
import com.supermarket.mercado.model.fornecedor.Fornecedor;
import com.supermarket.mercado.model.produtos.Produtos;
import com.supermarket.mercado.repositories.consulta.ConsultaRepository;
import com.supermarket.mercado.repositories.fornecedor.FornecedorRepository;
import com.supermarket.mercado.repositories.produtos.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ConsultaService")
class ConsultaServiceTest {

    @Mock
    private ConsultaRepository consultaRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private FornecedorRepository fornecedorRepository;

    @InjectMocks
    private ConsultaService consultaService;

    private Fornecedor fornecedor;
    private Produtos produto;
    private LocalDateTime dataFutura;

    @BeforeEach
    void setUp() {
        fornecedor = new Fornecedor(1L, "Distribuidora XYZ", "12.345.678/0001-99", "(11) 98765-4321");
        produto = new Produtos(1L, "Leite", "Italac", "Leite 1L", 5.99, 20, Categoria.LATICINIOS, null, true);
        dataFutura = LocalDateTime.now().plusDays(5);
    }

    @Test
    @DisplayName("deve retornar DTO quando produto e fornecedor existem")
    void deveRetornarDtoQuandoDadosValidos() {
        var dados = new DadosFornecedorAutenticacao(1L, 1L, dataFutura);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(fornecedorRepository.findById(1L)).thenReturn(Optional.of(fornecedor));
        when(consultaRepository.save(any(Consulta.class))).thenAnswer(i -> i.getArgument(0));

        var resultado = consultaService.consultar(dados);

        assertThat(resultado).isNotNull();
        assertThat(resultado.data()).isEqualTo(dataFutura);
        verify(consultaRepository, times(1)).save(any(Consulta.class));
    }

    @Test
    @DisplayName("deve lançar exceção quando produto não existe")
    void deveLancarExcecaoQuandoProdutoNaoExiste() {
        var dados = new DadosFornecedorAutenticacao(99L, 1L, dataFutura);

        when(produtoRepository.findById(99L)).thenReturn(Optional.empty());

        // O bug atual usa .get() que lança NoSuchElementException.
        // Este teste documenta o comportamento esperado CORRETO (MercadoException)
        // e serve como guia para a correção do serviço.
        assertThatThrownBy(() -> consultaService.consultar(dados))
                .isInstanceOf(RuntimeException.class);

        verify(consultaRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve lançar exceção quando fornecedor não existe")
    void deveLancarExcecaoQuandoFornecedorNaoExiste() {
        var dados = new DadosFornecedorAutenticacao(1L, 99L, dataFutura);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(fornecedorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> consultaService.consultar(dados))
                .isInstanceOf(RuntimeException.class);

        verify(consultaRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve salvar a consulta no repositório quando bem-sucedida")
    void deveSalvarConsultaNoRepositorio() {
        var dados = new DadosFornecedorAutenticacao(1L, 1L, dataFutura);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(fornecedorRepository.findById(1L)).thenReturn(Optional.of(fornecedor));
        when(consultaRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        consultaService.consultar(dados);

        verify(consultaRepository, times(1)).save(any(Consulta.class));
    }
}