package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity (name="product")
public class Product {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@NotEmpty
	@NotNull
	private String name;
	
	@NotEmpty
	@NotNull
	private String description;
		
	@NotNull
	private Float price;

	public Product() {
	}

	public Product(String id, @NotEmpty @NotNull String name, @NotEmpty @NotNull String description,
			@NotNull Float price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public Product(Product produtoNovo) {
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Product convertProduct(String id, Product produto){		
		produto.setDescription(this.description);
		produto.setName(this.name);
		produto.setPrice(this.price);		
		return produto;
}

}
