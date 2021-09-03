package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.form.BuscaProductForm;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
@RequestMapping("/products")
public class ProductController {


    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) {
            Product newProduct = productRepository.save(product);
            return ResponseEntity.ok().body(newProduct);       
    }

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody @Valid Product product) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			Product produtoAtualizado = product.convertProduct(id, optional.get());
			return ResponseEntity.ok().body(produtoAtualizado);
		}		
		return ResponseEntity.notFound().build();			
	}

	@GetMapping("/{id}")
    public ResponseEntity<Product> listProductById(@PathVariable String id) {           
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {			
			return ResponseEntity.ok().body(optional.get());
		}		
		return ResponseEntity.notFound().build();			     
    }

	@GetMapping
    public List<Product> listProduct() {           
            return productRepository.findAll();       
    }

	@GetMapping("/search") 
    public List<Product> searchProduct(BuscaProductForm buscaProductForm) {
		return  productRepository.findAll(buscaProductForm.toSpec());
    }

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}		
		return ResponseEntity.notFound().build();			
	}

}
