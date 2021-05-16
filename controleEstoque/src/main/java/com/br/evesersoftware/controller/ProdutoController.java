package com.br.evesersoftware.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.evesersoftware.model.Produto;
import com.br.evesersoftware.model.Usuario;
import com.br.evesersoftware.repository.ProdutoRepository;

@RestController
@RequestMapping(value = ("/produtos"))
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping (value = "/{id}", produces = "application/json")
	public ResponseEntity<Produto> init(@PathVariable (value = "id") Long id){
		Optional<Produto> produto = produtoRepository.findById(id);
		return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
		
	}
	
	@GetMapping (value = "/", produces = "application/json")
	public ResponseEntity<List<Produto>> produtos(){
		List<Produto> list = (List<Produto>) produtoRepository.findAll();

		return new ResponseEntity<List<Produto>>(list, HttpStatus.OK);
	}
	
	@PostMapping (value = "/", produces = "application/json")
	public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto){
		Produto produtoSalvo = produtoRepository.save(produto);
		return new ResponseEntity<Produto>(produtoSalvo, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Produto> atualizar(@RequestBody Produto produto){
		Produto produtoSalvo = produtoRepository.save(produto);
		return new ResponseEntity<Produto>(produtoSalvo, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String delete (@PathVariable(value = "id") Long id){
		
		produtoRepository.deleteById(id);
		
		return "Ok";
	}
	
}
