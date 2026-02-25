package com.supermarket.mercado.domain.repositories;

import com.supermarket.mercado.domain.model.Produtos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produtos, Long> {
    Page<Produtos> findAllByAtivoTrue(Pageable paginacao);
}
