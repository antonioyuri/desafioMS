package com.example.demo.dto;

import com.example.demo.model.Product;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    private String name;
    private String description;
    private Float price;

    public Product build(){
        Product product = new Product()
                .setDescription(this.description)
                .setName(this.name)
                .setPrice(this.price);
        return product;
    }

}
