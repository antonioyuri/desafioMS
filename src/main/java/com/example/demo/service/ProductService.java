package com.example.demo.service;

import com.example.demo.dto.ProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.form.BuscaProductSpecification;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.modelmapper.ModelMapper;
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

    @Autowired
    ModelMapper modelMapper;

    public ResponseEntity<ProductResponseDTO> salvar (ProductRequestDTO productRequestDTO){
        ProductResponseDTO newProduct = modelMapper.map(productRepository.save(productRequestDTO.build()),ProductResponseDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    public ResponseEntity<ProductResponseDTO> buscarPorId(String id){
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            ProductResponseDTO newProduct = modelMapper.map(optional.get(),ProductResponseDTO.class);
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

    public List<ProductResponseDTO> buscarTodosFiltros(String q, String minPrice, String maxPrice){
        Specification<Product> productSpecification = buscaProductSpecification.toSpec(q, minPrice, maxPrice);
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
        return productRepository.findById(id)
                .map(record -> {
                    record.setName(product.getName());
                    record.setDescription(product.getDescription());
                    record.setPrice(product.getPrice());
                    record.setId(id);
                    Product productSave = productRepository.save(record);
                    ProductResponseDTO newProduct = modelMapper.map(productSave,ProductResponseDTO.class);
                    return ResponseEntity.ok().body(newProduct);
                }).orElse(ResponseEntity.notFound().build());
    }
}
