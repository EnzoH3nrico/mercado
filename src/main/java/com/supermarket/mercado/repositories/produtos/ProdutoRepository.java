package com.supermarket.mercado.repositories.produtos;

import com.supermarket.mercado.model.produtos.Produtos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produtos, Long> {
    Page<Produtos> findAllByAtivoTrue(Pageable paginacao);
}
