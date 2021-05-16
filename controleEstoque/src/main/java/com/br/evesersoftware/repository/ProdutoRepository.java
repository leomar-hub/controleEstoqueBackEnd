package com.br.evesersoftware.repository;

import org.springframework.data.repository.CrudRepository;

import com.br.evesersoftware.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long>{

}
