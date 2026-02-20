package com.supermarket.mercado.repositories;

import com.supermarket.mercado.model.Produtos;
import org.springframework.beans.PropertyValues;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ProdutoRepository extends JpaRepository<Produtos, Long> {
    Page<Produtos> findAllByAtivoTrue(Pageable paginacao);
}
