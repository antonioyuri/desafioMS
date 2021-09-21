package com.example.demo.controller;

import java.util.List;

import com.example.demo.dto.ProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
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
	public List<ProductResponseDTO> searchProduct(@RequestParam @Nullable String q, @RequestParam (name="min_price") @Nullable String minPrice, @RequestParam (name="max_price") @Nullable String maxPrice) {
		return  productService.buscarTodosFiltros(q,minPrice,maxPrice);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> deleteProduct(@PathVariable String id) {
		return productService.remover(id);
	}

}
