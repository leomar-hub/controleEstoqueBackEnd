package com.br.evesersoftware.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.br.evesersoftware.model.Produto;
import com.br.evesersoftware.repository.ProdutoRepository;


@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	//METODO QUE CARREGA A PAGINA CADASTRO DE PRODUTO
	@RequestMapping(method=RequestMethod.GET, value="/cadastroproduto")
	public ModelAndView init() {
		
		ModelAndView modelandView = new ModelAndView("cadastro/cadastroproduto");
		modelandView.addObject("produtoobj", new Produto());
		
		Iterable<Produto> produtoLista = produtoRepository.findAll();
		modelandView.addObject("produtos", produtoLista);
		
		return modelandView;
	}
	
	//METODO CADASTRAR PRODUTO
	@RequestMapping(method = RequestMethod.POST, value = "**/salvarproduto")
	public ModelAndView salvar(Produto produto) {
		
		produtoRepository.save(produto);
		
		ModelAndView modelandView = new ModelAndView("cadastro/cadastroproduto");
		
		Iterable<Produto> produtoLista = produtoRepository.findAll();
		modelandView.addObject("produtos", produtoLista);
		modelandView.addObject("produtoobj", new Produto());
		
		return modelandView;
	}
	
	//METODO EDITAR PRODUTO
	@GetMapping("/editarproduto/{idproduto}")
	public ModelAndView editar(@PathVariable("idproduto") Long idproduto) {
		
		Optional<Produto> produto = produtoRepository.findById(idproduto);
		
		ModelAndView modelandview = new ModelAndView("cadastro/cadastroproduto");
		modelandview.addObject("produtoobj",produto.get());
		
		Iterable<Produto> produtoLista = produtoRepository.findAll();
		modelandview.addObject("produtos", produtoLista);
		
		return modelandview;
	}
	
	//METODO EXCLUIR
	@GetMapping("/excluirproduto/{idproduto}")
	public ModelAndView excluir(@PathVariable("idproduto") Long idproduto) {
		
		produtoRepository.deleteById(idproduto);
		
		ModelAndView modelandview = new ModelAndView("cadastro/cadastroproduto");	
		Iterable<Produto> produtoLista = produtoRepository.findAll();
		modelandview.addObject("produtos", produtoLista);
		modelandview.addObject("produtoobj", new Produto());
		
		return modelandview;
	}
	
	//METODO LISTAR PRODUTO
	@RequestMapping(method = RequestMethod.GET, value="/listaprodutos")
	public ModelAndView produtos() {
		
		ModelAndView modelandview = new ModelAndView("cadastro/cadastroproduto");
		Iterable<Produto> produtoLista = produtoRepository.findAll();
		modelandview.addObject("produtos", produtoLista);
		modelandview.addObject("produtoobj", new Produto());
		
		return modelandview;
	}
	
	@PostMapping("**/pesquisaproduto")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView modelandview = new ModelAndView("cadastro/cadastroproduto");
		modelandview.addObject("produtos", produtoRepository.findProdutoByName(nomepesquisa));
		modelandview.addObject("produtoobj", new Produto());
		return modelandview;
	}

}
