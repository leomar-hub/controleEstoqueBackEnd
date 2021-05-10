package com.br.evesersoftware.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.br.evesersoftware.model.Produto;

@Repository
@Transactional
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
	
	@Query("select p from Produto p where p.descricao like %?1%")
	List<Produto> findProdutoByName(String descricao);
	
}
