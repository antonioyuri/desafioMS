package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.ProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.form.BuscaProductForm;
import com.example.demo.model.Product;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.example.demo.service.ProductService;
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

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping
	public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
		return productService.salvar(productRequestDTO);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable String id, @RequestBody @Valid ProductRequestDTO product) {
		return productService.atualizarProduto(id,product);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDTO> listProductById(@PathVariable String id) {
		return productService.buscarPorId(id);
	}

	@GetMapping
	public List<ProductResponseDTO> listProduct() {
		return productService.buscarTodos();
	}

	@GetMapping("/search")
	public List<ProductResponseDTO> searchProduct(BuscaProductForm buscaProductForm) {
		return  productService.buscarTodosFiltros(buscaProductForm.toSpec());
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> deleteProduct(@PathVariable String id) {
		return productService.remover(id);
	}

}
