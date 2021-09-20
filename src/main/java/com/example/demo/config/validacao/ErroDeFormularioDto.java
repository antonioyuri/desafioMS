package com.example.demo.config.validacao;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ErroDeFormularioDto {

	private Integer code;
	private String message;

	public ErroDeFormularioDto(Integer code, String message) {
		this.code = code;
		this.message = message;
	}


}

