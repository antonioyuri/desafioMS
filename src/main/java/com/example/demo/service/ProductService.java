package com.example.demo.service;

import com.example.demo.dto.ProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.form.BuscaProductSpecification;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BuscaProductSpecification buscaProductSpecification;

    public ResponseEntity<ProductResponseDTO> salvar (ProductRequestDTO productRequestDTO){
        ProductResponseDTO newProduct = new ProductResponseDTO(productRepository.save(productRequestDTO.build()));
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    public ResponseEntity<ProductResponseDTO> buscarPorId(String id){
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            ProductResponseDTO newProduct = new ProductResponseDTO(optional.get());
            return ResponseEntity.ok().body(newProduct);
        }
        return ResponseEntity.notFound().build();
    }

    public List<ProductResponseDTO> buscarTodos(){
        List<Product> listaProduc = productRepository.findAll();
        List<ProductResponseDTO> listaRetorno = new ArrayList<>();
        for(Product product:listaProduc){
            listaRetorno.add(new ProductResponseDTO(product));
        }
        return listaRetorno;
    }

    public List<ProductResponseDTO> buscarTodosFiltros(String q,String min_price,String max_price){
        Specification<Product> productSpecification = buscaProductSpecification.toSpec(q, min_price, max_price);
        List<Product> listaProduc = productRepository.findAll(productSpecification);
        List<ProductResponseDTO> listaRetorno = new ArrayList<>();
        for(Product product:listaProduc){
            listaRetorno.add(new ProductResponseDTO(product));
        }
        return listaRetorno;
    }

    public ResponseEntity<Object> remover (String id){
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<ProductResponseDTO> atualizarProduto(String id, ProductRequestDTO product){
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product productActualized = product.convertProduct(id, optional.get());
            ProductResponseDTO newProduct = new ProductResponseDTO(productActualized);
            return ResponseEntity.ok().body(newProduct);
        }
        return ResponseEntity.notFound().build();
    }


}
