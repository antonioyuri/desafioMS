package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
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
	@DecimalMin("0.1")
	private Float price;

}
