package com.example.demo.config.validacao;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ErroDeFormularioDto {

	private Integer status_code;
	private String message;

}

