package com.example.demo.dto;

import com.example.demo.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private String name;
    private String description;
    private Float price;
    private String id;

    public ProductResponseDTO(Product product){
        this.description = product.getDescription();
        this.name = product.getName();
        this.price = product.getPrice();
        this.id = product.getId();
    }

}
